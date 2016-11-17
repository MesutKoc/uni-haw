

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Anton Kirakozov und Mesut Koc
 */
package Porsche;
import Helper.*;
import Porsche.*;


public class Main {
    
    private static final long timeMS = 10;
    private static Porsche911GT2RS porsche;
    
    public static void driveSpeedTest(double speed) {
        speed = converter.kmhToMs(speed);
        
        while (porsche.speed() < speed) porsche.step(timeMS/1000.0, 1.0);
        System.out.println(porsche);
        System.out.println();
    }
    public static void main(String[] args) {
        porsche = Porsche911GT2RS.valueOf();
        driveSpeedTest(100);
    }
    
}
