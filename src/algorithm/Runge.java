package algorithm;

import support.input.Console;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class Runge {

    ArrayList<BigDecimal> data;
    BigDecimal a, b, e, x1, x2, y1, y2, h, k1, k2, k3, k4;
    BigDecimal r;
    int n, methodNumber, iterationCount;

    public Runge(ArrayList<BigDecimal> y, int method){
        data = y;
        y1 = data.get(0);
        y2 = y1.add(new BigDecimal(-100));
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
        while ((y1.subtract(y2).abs().setScale(10, RoundingMode.HALF_EVEN).divide(new BigDecimal(15), RoundingMode.HALF_EVEN).compareTo(e) > 0) && (iterationCount <= 1)) {
            y2 = y1;
            answer = new ArrayList<BigDecimal>();
            y1 = data.get(0);
            x1 = data.get(1);
            answer.add(x1);
            answer.add(y1);
            while (x1.compareTo(x2) < 0) {
                k1 = h.multiply(Functions.function_differentiation(x1, y1, methodNumber)).setScale(10, RoundingMode.HALF_EVEN);
                k2 = h.multiply(Functions.function_differentiation(x1.add(h.multiply(new BigDecimal(0.5))), y1.add(k1.multiply(new BigDecimal(0.5))), methodNumber));
                k3 = h.multiply(Functions.function_differentiation(x1.add(h.multiply(new BigDecimal(0.5))), y1.add(k2.multiply(new BigDecimal(0.5))), methodNumber));
                k4 = h.multiply(Functions.function_differentiation(x1.add(h), y1.add(k3), methodNumber));
                answer.add(k1);
                answer.add(k2);
                answer.add(k3);
                answer.add(k4);
                y1 = y1.add(k1.add(k2.multiply(new BigDecimal(2))).add(k3.multiply(new BigDecimal(2))).add(k4).setScale(10, RoundingMode.HALF_EVEN).divide(new BigDecimal(6), RoundingMode.HALF_EVEN));
                x1 = x1.add(h).setScale(10, RoundingMode.HALF_EVEN);
                answer.add(x1);
                answer.add(y1);
            }
            Console.printEquations(answer);
            h = h.multiply(new BigDecimal(0.5));
            iterationCount += 1;
        }
        return answer;
    }

    public ArrayList<BigDecimal> generate(){
        ArrayList<BigDecimal> answer = new ArrayList<BigDecimal>();
        y1 = data.get(0);
        x1 = data.get(1);
        answer.add(x1);
        answer.add(y1);
        for (int i = 0; i < 3; i++){
            k1 = h.multiply(Functions.function_differentiation(x1, y1, methodNumber)).setScale(10, RoundingMode.HALF_EVEN);
            k2 = h.multiply(Functions.function_differentiation(x1.add(h.multiply(new BigDecimal(0.5))), y1.add(k1.multiply(new BigDecimal(0.5))), methodNumber));
            k3 = h.multiply(Functions.function_differentiation(x1.add(h.multiply(new BigDecimal(0.5))), y1.add(k2.multiply(new BigDecimal(0.5))), methodNumber));
            k4 = h.multiply(Functions.function_differentiation(x1.add(h), y1.add(k3), methodNumber));
            y1 = y1.add(k1.add(k2.multiply(new BigDecimal(2))).add(k3.multiply(new BigDecimal(2))).add(k4).setScale(10, RoundingMode.HALF_EVEN).divide(new BigDecimal(6), RoundingMode.HALF_EVEN));
            x1 = x1.add(h).setScale(10, RoundingMode.HALF_EVEN);
            answer.add(x1);
            answer.add(y1);
        }
        Console.printEquations(answer);
        return answer;
    }


}
