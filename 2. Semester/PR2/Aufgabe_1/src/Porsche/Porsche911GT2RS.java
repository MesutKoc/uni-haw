package Porsche;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Anton Kirakozov und Mesut Koc
 */
import Helper.*;

public class Porsche911GT2RS {
    //statischen Attribute
   /* private static final double mass=1445.0, //
                                powerPropMax = 456.0, 
                                speedMax=330.0, 
                                accEarth=9.81;
                                //forcePropMax = mass * accEarth; */
    private static final double mass = 1445.0,
                                powerPropMax = converter.kwToW(456.0), 
                                speedMax = converter.kmhToMs(330.0),
                                accEarth = 9.798;
    
    //"normale" Attribute
    private double  time, 
                    pos, 
                    speed, 
                    level;
    
    public static Porsche911GT2RS valueOf() {
        Porsche911GT2RS instance = new Porsche911GT2RS();
        instance.reset();
        return instance;
    }
    
    public Porsche911GT2RS(){}
    
//Getter Methoden 
    public double mass(){
        return mass;
    }
    public double powerPropMax(){
        return powerPropMax;
    }
    public double speedMax(){
        return speedMax;
    }
    public double accEarth(){
        return accEarth;
    }
    public double time(){
        return time;
    }
    public double pos(){
        return pos;
    }
    public double speed(){
        return speed;
    }
    public double level(){
        return level;
    }
    
    //Die Methode Set
    public void set(double _time, double _pos, double _speed, double _level){
     
        this.time= _time;
        this.pos= _pos;
        this.speed= _speed;
        this.level= _level;
    }
    //Methode reset, die alle Werte von Set auf Null setzt
    public void reset(){
        set(0.0, 0.0, 0.0, 0.0);
    }
    //Step bewegt unseren Porsche.
    public void step(double _deltaTime, double _level){
        
        double powerProp = level * powerPropMax();
        double forcePropAbs;
        double forcePropMax = mass() * accEarth(); 
                if (speed()!=0.0) forcePropAbs = Math.min(forcePropMax,powerProp /speed());
                else forcePropAbs = forcePropMax;
        double forceProp = forcePropAbs * Math.signum(level);
        double dragConst = Math.abs(powerPropMax() / Math.pow(speedMax(),3.0)); 
        double forceDrag = dragConst * Math.pow(speed, 2.0) * Math.signum(-speed);
        double force = forceProp + forceDrag;
        
        double acc = force/ mass();
        
        double speed_New = speed() + (acc * _deltaTime);
        double pos_New = pos() + (speed_New *_deltaTime);
        
        double time_New = time() + _deltaTime;
        set(time_New, pos_New, speed_New, _level);
    }
    
   /* public void step(double deltaTime, double level){
        // Kräfte-Formel 
        double powerProp = level * powerPropMax();
        double forcePropMax = mass() * accEarth(); 
        double forcePropAbs;
        
        if (speed() != 0.0) forcePropAbs = Math.min(forcePropMax, powerProp / speed());
        else                forcePropAbs = forcePropMax;
      
        double forceProp = forcePropAbs * Math.signum(level); 
        double dragConst = Math.abs(powerPropMax() / Math.pow(speedMax(), 3.0));
        double forceDrag = dragConst * Math.pow(speed(), 2.0)* Math.signum(-speed());
        double force = forceProp + forceDrag;
        
        // Aus der auf eine Masse ausgeübten Kraft ergibt sich die Beschleunigung:
        double acc = force / mass();
        
        // Kinematik Formel
        double speedNew = speed() + (acc * deltaTime);
        double posNew = pos() + (speedNew * deltaTime);
        double timeNew = time() + deltaTime;
        
        //setze neue Geschwindigkeit, Position, time und level:
       // set(speedNew, posNew, timeNew, level);
        //set(double time, double pos, double speed, double proplevel)
        set(timeNew, posNew, speedNew, level);
    }*/
    
    @Override
    public String toString(){
        System.out.printf("%-8s  %-7s   %-7s   %-8s   %-7s  %-6s%n", "Mass", "Time", "Pos", "Speed(MS)", "Speed", "Problevel");
        System.out.printf("%7.2f  %7.2f   %7.1f   %7.2f   %7.2f  %7.2f%n", mass(), time(), pos(), speed(), converter.msToKmh(speed()), level() );
        return this.getClass().getSimpleName();

    }
}



