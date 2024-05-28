package support.input;

import java.math.BigDecimal;

public class Validate {

    public boolean validateNumber(String s){
        if (!(s.matches("^[+-]?[0-9]*[.,]?[0-9]+$"))){
            return false;
        }
        else{
            return true;
        }
    }
    public boolean validateBorders(String s){
        String[] temp = s.strip().split(" ");
        if (temp.length != 3){
            return false;
        }
        for (int i = 0; i < temp.length; i++){
            temp[i] = temp[i].replace(",", ".");
        }
        /*System.out.println(temp[0]);
        System.out.println(temp[1]);
        System.out.println(temp[2]);*/
        if (!((temp[2]).matches("^[+]?[0-9]*[.,]?[0-9]+$"))){
            return false;
        }
        else{
            double accuracy = Double.parseDouble(temp[2]);
            if ((accuracy < 0.000001) || (accuracy > 1.0)){
                return false;
            }
        }
        return (((temp[0]).matches("^[+-]?[0-9]*[.,]?[0-9]+$")) && ((temp[1]).matches("^[+-]?[0-9]*[.,]?[0-9]+$")));
    }

    public boolean validateNumbers(String s){
        String[] temp = s.strip().split(" ");
        if (temp.length != 5){
            return false;
        }
        for (int i = 0; i < temp.length; i++){
            temp[i] = temp[i].replace(",", ".");
        }
        if (!((temp[1]).matches("^[+-]?[0-9]*[.,]?[0-9]+$"))){
            return false;
        }
        if (!((temp[2]).matches("^[+-]?[0-9]*[.,]?[0-9]+$"))){
            return false;
        }
        else {
            BigDecimal first = new BigDecimal(temp[1]);
            BigDecimal second = new BigDecimal(temp[2]);
            if (first.compareTo(second) > 0){
                return false;
            }
        }
        if (!((temp[3]).matches("^[+]?[0-9]*[.,]?[0-9]+$"))){
            return false;
        }
        else{
            double h = Double.parseDouble(temp[4]);
            if (h <= 0){
                return false;
            }
        }
        if (!((temp[4]).matches("^[+]?[0-9]*[.,]?[0-9]+$"))){
            return false;
        }
        else{
            double accuracy = Double.parseDouble(temp[4]);
            if ((accuracy < 0.000001) || (accuracy > 10.0)){
                return false;
            }
        }
        return ((temp[0]).matches("^[+-]?[0-9]*[.,]?[0-9]+$"));
    }

}
