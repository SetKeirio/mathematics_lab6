package algorithm;

import support.input.Console;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class Adams {

    ArrayList<BigDecimal> data;
    ArrayList<BigDecimal> temp;
    ArrayList<BigDecimal> y3;
    Runge method;
    BigDecimal a, b, e, x1, x2, y1, y2, h, f1, f2, f3, f4, df, d2f, d3f, k1, k2, k3, k4;
    BigDecimal r;
    int n, methodNumber, iterationCount;

    public Adams(ArrayList<BigDecimal> y, int method){
        data = y;
        a = new BigDecimal(100);
        y1 = data.get(0);
        y2 = y1.add(new BigDecimal(-100));
        y3 = new ArrayList<BigDecimal>();
        x1 = y.get(1);
        x2 = y.get(2);
        h = y.get(3);
        n = data.size() - 2;
        e = data.get(4);
        methodNumber = method;
        iterationCount = 0;
    }

    public ArrayList<BigDecimal> solve(){
        ArrayList<BigDecimal> answer = new ArrayList<BigDecimal>();
        if (methodNumber == 1) {
            b = Functions.g1_second(data.get(1), data.get(0));
        }
        else if (methodNumber == 2){
            b = Functions.g2_second(data.get(1), data.get(0));
        }
        else if (methodNumber == 3){
            b = Functions.g3_second(data.get(1), data.get(0));
        }
        while ((a.compareTo(e) > 0) && (iterationCount <= 3)) {
            y2 = y1;
            data.set(3, h);
            method = new Runge(data, methodNumber);
            temp = method.generate();
            answer = new ArrayList<BigDecimal>();
            for (int i = 0; i < temp.size(); i++){
                answer.add(temp.get(i));
            }
            y3.clear();
            for (int i = 0; i < (temp.size() / 2); i++){
                y3.add(Functions.function_differentiation_first(temp.get(2 * i), b, methodNumber));
            }
            a = new BigDecimal(0);
            for (int i = 0; i < (temp.size() / 2); i++){
                if (Functions.function_differentiation_first(temp.get(i * 2), b, methodNumber).subtract(temp.get(i * 2 + 1)).abs().compareTo(a) > 0){
                    a = Functions.function_differentiation_first(temp.get(i * 2), b, methodNumber).subtract(temp.get(i * 2 + 1)).abs();
                }
            }
            x1 = temp.get(temp.size() - 2);
            y1 = temp.get(temp.size() - 1);
            while (x1.compareTo(x2) < 0) {
                f1 = Functions.function_differentiation(temp.get(0), temp.get(1), methodNumber);
                f2 = Functions.function_differentiation(temp.get(2), temp.get(3), methodNumber);
                f3 = Functions.function_differentiation(temp.get(4), temp.get(5), methodNumber);
                f4 = Functions.function_differentiation(temp.get(6), temp.get(7), methodNumber);
                df = f4.subtract(f3);
                d2f = f4.subtract(f3.setScale(7, RoundingMode.HALF_EVEN).multiply(new BigDecimal(2))).add(f2);
                d3f = f4.subtract(f3.setScale(7, RoundingMode.HALF_EVEN).multiply(new BigDecimal(3))).add(f2.setScale(7, RoundingMode.HALF_EVEN).multiply(new BigDecimal(3))).subtract(f1);
                k1 = h.setScale(7, RoundingMode.HALF_EVEN).multiply(f4);
                k2 = h.setScale(7, RoundingMode.HALF_EVEN).pow(2).multiply(new BigDecimal(0.5)).multiply(df);
                k3 = h.setScale(7, RoundingMode.HALF_EVEN).pow(3).multiply(new BigDecimal(5)).setScale(7, RoundingMode.HALF_EVEN).divide(new BigDecimal(12), RoundingMode.HALF_EVEN).multiply(d2f);
                k4 = h.setScale(7, RoundingMode.HALF_EVEN).pow(4).multiply(new BigDecimal(0.375)).multiply(d3f);
                y1 = y1.add(k1).add(k2).add(k3).add(k4);
                x1 = x1.add(h).setScale(10, RoundingMode.HALF_EVEN);
                if (Functions.function_differentiation_first(x1, b, methodNumber).subtract(y1).abs().compareTo(a) > 0){
                    a = Functions.function_differentiation_first(x1, b, methodNumber).subtract(y1).abs();
                }
                y3.add(Functions.function_differentiation_first(x1, b, methodNumber));
                answer.add(x1);
                answer.add(y1);
                answer.add(k1);
                answer.add(k2);
                answer.add(k3);
                answer.add(k4);
                for (int i = 0; i < 3; i++) {
                    temp.set(2 * i, temp.get(2 * i + 2));
                    temp.set(2 * i + 1, temp.get(2 * i + 3));
                }
                temp.set(6, x1);
                temp.set(7, y1);
            }
            h = h.multiply(new BigDecimal(0.5));
            iterationCount += 1;
            System.out.println(a);
        }
        //Console.printEquations(answer);
        return answer;
    }
}
