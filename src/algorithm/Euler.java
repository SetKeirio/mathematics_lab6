package algorithm;

import support.input.Console;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.SortedMap;

public class Euler {

    ArrayList<BigDecimal> data;
    BigDecimal a, b, e, x1, x2, y1, y2, h;
    BigDecimal r;
    int n, methodNumber, iterationCount;

    public Euler(ArrayList<BigDecimal> y, int method){
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
        while ((y1.subtract(y2).abs().compareTo(e) > 0) && (iterationCount <= 10)) {
            y2 = y1;
            answer = new ArrayList<BigDecimal>();
            y1 = data.get(0);
            x1 = data.get(1);
            answer.add(x1);
            answer.add(y1);
            while (x1.compareTo(x2) < 0) {
                b = Functions.function_differentiation(x1, y1, methodNumber).multiply(h).setScale(10, RoundingMode.HALF_EVEN);
                y1 = y1.add(b);
                x1 = x1.add(h).setScale(10, RoundingMode.HALF_EVEN);
                answer.add(b);
                answer.add(x1);
                answer.add(y1);
            }
            Console.printEquations(answer);
            h = h.multiply(new BigDecimal(0.5));
            iterationCount += 1;
        }
        return answer;
    }

}
