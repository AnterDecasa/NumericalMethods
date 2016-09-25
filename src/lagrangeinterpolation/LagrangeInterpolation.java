/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lagrangeinterpolation;

/**
 *
 * @author anter_000
 */
public class LagrangeInterpolation {
    
    public static String getEquation(float a, float[] x, float[] y){
        String equation = "";
        
        for(int i = 0; i < x.length; i++){
            for(int j = 0; j < x.length; j++){
                if(j != i){
                    equation += "(x-" + x[j] + ")/(" + (x[i]-x[j]) + ")*";
                }
            }
            equation += "(" + y[i] + ")";
            if(i < x.length-1){
                equation += "+";
            }
        }
        
        return equation;
        
    }
    
    public static String getEquation(float[] x, float[] y){
        String equation = "";
        
        for(int i = 0; i < x.length; i++){
            for(int j = 0; j < x.length; j++){
                if(j != i){
                    equation += "(x-" + x[j] + ")/(" + (x[i]-x[j]) + ")*";
                }
            }
            equation += "(" + y[i] + ")";
            if(i < x.length-1){
                equation += "+";
            }
        }
        System.out.println(equation);
        
        return equation;
        
    }
    
    public static String createSolution(String equation, float x){
        
        String retVal = "";
        
        float y = 0;
        String a = Float.toString(x);
        String[] lArray = equation.split("\\+");
        String currNumber = "";
        int lCntr = 0;
        String temp = equation;
        temp = temp.replaceAll("x", a);
        retVal += "f(" + x + ")="+ temp + "\n";
        for(String l : lArray){
            l = l.replaceAll("x", a);
            retVal += "L" + lCntr + "*f(x" + lCntr+ ")=" + currNumber;
            
            retVal += l + "\n";
            int i = 1;
            
            float currL = 0;
            char currLOperator = '+';
            for(i = 0; i < l.length();){
                if(l.charAt(i) == '('){
                    i++;
                    while(l.charAt(i) != ')'){
                        currNumber += l.charAt(i);
                        i++;
                    }
                    //System.out.println(calculate(currNumber));
                    currL = calculate(currLOperator,currL,calculate(currNumber));
                    //System.out.println("currL: " + currL);
                    currNumber = "";
                    i++;
                }
                else{
                    if(i < l.length() && isOperator(l.charAt(i)+"")){
                        currLOperator = l.charAt(i);
                        i++;
                    }
                }
            }
            retVal += "L" + lCntr + "*f(x" + lCntr+ ")=" + currL + "\n";
            lCntr++;
            y += currL;
        }
        retVal += "f(" + x + ")=";
        for(int i = 1; i < lCntr; i++){
            retVal += "L" + i + "*f(x" + i + ")";
            if(i < lCntr-1){
                retVal += " + ";
            }
        }
        retVal += "\n";
        retVal += "f(" + x + ")=" + y;
        return retVal;
        
    }
    
    public static float getY(float x, String equation){
        
        float retVal = 0;
        String a = Float.toString(x);
        String[] lArray = equation.split("\\+");
        String currNumber = "";
        
        for(String l : lArray){
            l = l.replaceAll("x", a);
            //System.out.println(l);
            int i = 1;
        
            float currL = 0;
            char currLOperator = '+';
            for(i = 0; i < l.length();){
                if(l.charAt(i) == '('){
                    i++;
                    while(l.charAt(i) != ')'){
                        currNumber += l.charAt(i);
                        i++;
                    }
                    //System.out.println(calculate(currNumber));
                    currL = calculate(currLOperator,currL,calculate(currNumber));
                    //System.out.println("currL: " + currL);
                    currNumber = "";
                    i++;
                }
                else{
                    if(i < l.length() && isOperator(l.charAt(i)+"")){
                        currLOperator = l.charAt(i);
                        i++;
                    }
                }
            }
            
            retVal += currL;
        }
        return retVal;
        
    }
    
    public static float calculate(String string){
        
        float retVal = 0;
        float operand2 = 0;
        char operator = '+';
        String currNumber = "";
        
        int i = 0;
        while(i < string.length() && !isOperator(string.charAt(i)+"")){
            currNumber += string.charAt(i);
            i++;
        }
        if(i < string.length() && !currNumber.isEmpty()){
            retVal = Float.parseFloat(currNumber);
            currNumber = "";
        }
        if(i < string.length() && isOperator(string.charAt(i)+"")){
            operator = string.charAt(i);
            i++;
        }
        while(i < string.length()){
            currNumber += string.charAt(i);
            i++;
        }
        if(!currNumber.isEmpty()){
            operand2 = Float.parseFloat(currNumber);
        }
        
        return calculate(operator,retVal,operand2);
    
    }
    
    public static float calculate(char operator, float operand1, float operand2){
        
        float retVal = operand1;
        
        switch(operator){
            case '+':
                retVal =+ operand2;
                break;
            case '-':
                retVal -= operand2;
                break;
            case '/':
                retVal /= operand2;
                break;
            case '*':
                retVal *= operand2;
                break;
        }
        
        return retVal;
        
    }
    
    public static boolean isOperator(String string){
        return string.matches("\\+|\\-|\\*|\\/");
    }
    
}
