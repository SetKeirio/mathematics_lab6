package algorithm;

import support.input.Console;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class AlgorithmManager {
    public ArrayList<BigDecimal> solve(int function, int method, ArrayList<BigDecimal> data){
        ArrayList<BigDecimal> answer = new ArrayList<BigDecimal>();
        BigDecimal temp = new BigDecimal(0);
        switch (function) {
            case 1:
                answer = euler(data, method);
                break;
            case 2:
                answer = runge(data, method);
                break;
            case 3:
                answer = adams(data, method);
                break;

        }
        return answer;
    }

    public ArrayList<BigDecimal> euler(ArrayList<BigDecimal> data, int methodNumber){
        Euler method = new Euler(data, methodNumber);
        ArrayList<BigDecimal> answer = method.solve();
        return answer;
    }

    public ArrayList<BigDecimal> runge(ArrayList<BigDecimal> data, int methodNumber){
        Runge method = new Runge(data, methodNumber);
        ArrayList<BigDecimal> answer = method.solve();
        return answer;
    }

    public ArrayList<BigDecimal> adams(ArrayList<BigDecimal> data, int methodNumber){
        Adams method = new Adams(data, methodNumber);
        ArrayList<BigDecimal> answer = method.solve();
        return answer;
    }




}
