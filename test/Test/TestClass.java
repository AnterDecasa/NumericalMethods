/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

/**
 *
 * @author anter_000
 */
public class TestClass {
    
    public static void main(String[] args){
        
        double x = Math.PI / 2;

        // get a variable y which is equal to PI/3
        double y = Math.PI / 3;

        // convert x  and y to degrees
        x = Math.toDegrees(x);
        y = Math.toDegrees(y);
        
        // get the polar coordinates
        System.out.println("Math.atan2(" + x + "," + y + ")=" + Math.atan2(x, y));
    }
    
}
