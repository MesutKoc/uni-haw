/**
 *
 * @author Mesut, Anton
 */
package model;
import help.*;

public class Porsche911GT2RS {
    /*===================================
        ATTRIBUTE
     ===================================*/
    private static final double mass = 1445.0,
                                powerProbMax = converter.convert(456.0,"kwToW"), 
                                speedMax = converter.convert(330.0,"kmhToMs"),
                                accEarth = 9.798;
    
    private double time, pos, speed, proplevel;
    
    /*===================================
      Unser Konstruktor, dem ist nichts zu übergeben
     ===================================*/
    private Porsche911GT2RS(){}
    
    public static Porsche911GT2RS valueOf() {
        Porsche911GT2RS instance = new Porsche911GT2RS();
        instance.reset();
        return instance;
    }
    /*===================================
        GET: mass (double)
     ===================================*/
    public double mass() {
        return mass;
    }
    /*===================================
        GET: powerProbMax (double)
     ===================================*/
    public double powerProbMax(){
        return powerProbMax;
    }
    /*===================================
        GET: speed (double)
     ===================================*/
    public double speed(){
        return speed;
    }
    /*===================================
        GET: speedMax (double)
     ===================================*/ 
    public double speedMax(){
        return speedMax;
    }
    /*===================================
        GET: time (double)
     ===================================*/
    public double time(){
            return time;
    }
    /*===================================
        GET: pos (double)
     ===================================*/
    public double pos(){
            return pos;
    }
    /*===================================
        GET: problevel (double)
     ===================================*/
    public double proplevel(){
            return proplevel;
    }
    /*===================================
        GET: problevel (double)
     ===================================*/
    public double accEarth() {
            return accEarth;
    }
    /*===================================
      Alle SET-Methoden
     ===================================*/
    public void set(double time, double pos, double speed, double proplevel){
        this.time = time;
        this.pos = pos;
        this.speed = speed;
        this.proplevel = proplevel;
    }
    /*===================================
      Reset setzt time, pos, speed und problevel auf 0.
      Die Funktion in void, da nicht zurückgegeben wird
     ===================================*/
    public void reset() {
        set(0.0, 0.0, 0.0, 0.0);
    }
    
    @Override
    public String toString(){
        System.out.printf("%-8s|  %-7s|   %-7s|   %-8s|   %-7s|  %-6s|%n", "Mass", "Time", "Pos", "Speed(MS)", "Speed", "Problevel");
        System.out.println("------------------------------------------------------------------");
        System.out.printf("%7.2f |  %4.2f   |   %4.1f   |  %7.2f   |  %7.2f | %7.2f   | %n", mass(), time(), pos(), speed(), converter.convert(speed(),"msToKmh"), proplevel() );
        return "\nObject from class: " + this.getClass().getSimpleName();
    }
    /*===================================
      Die Methode step(..)
     ===================================*/
    public void step(double deltaTime, double level){
        // Kräfte-Formel 
        double powerProp = level * powerProbMax();
        double forcePropMax = mass() * accEarth(); 
        double forcePropAbs;
        
        if (speed() != 0.0) forcePropAbs = Math.min(forcePropMax, powerProp / speed());
        else                forcePropAbs = forcePropMax;
      
        double forceProp = forcePropAbs * Math.signum(level); 
        double dragConst = Math.abs(powerProbMax() / Math.pow(speedMax(), 3.0));
        double forceDrag = dragConst * Math.pow(speed(), 2.0) * Math.signum(-speed());
        double force = forceProp + forceDrag;
        
        // Aus der auf eine Masse ausgeübten Kraft ergibt sich die Beschleunigung:
        double acc = force / mass();
        
        // Kinematik Formel
        double speedNew = speed() + (acc * deltaTime);
        double posNew = pos() + (speedNew * deltaTime);
        double timeNew = time() + deltaTime;
        
        //set(double time, double pos, double speed, double proplevel)
        set(timeNew, posNew, speedNew, level);
    }       
}
