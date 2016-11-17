package Model;

import Init.Game;
import Graphics.SpriteRotation;
import units.Values;
import units.interfaces.Acc;
import units.interfaces.Angle;
import units.interfaces.Force;
import units.interfaces.Length;
import units.interfaces.Mass;
import units.interfaces.Power;
import units.interfaces.Speed;
import units.interfaces.TimeDiff;

public class Porsche {
    // CONSTANTS
    
    public final double minControllevel = 0.05;
    
    public enum carState {
        STOP, DRIVE, CURVE, SLIDE
    }

    // ATTRIBUTES
    private TimeDiff time;
    private Length posX;
    private Length posY;
    private Angle angle; //rad
    private Speed speed;
    private double proplevel;
    private double breaklevel;
    private boolean isABSon;
    private boolean isASRon;
    private double controllevel;
    private Angle lastAngle;
    private double lastControllevel;
    private SpriteRotation sprite;
    
    private carState currentState;

    // CREATION
    private Porsche() {
        this.time = Values.timeDiff(0.0d);
        this.posX = Values.length(0.0d);
        this.posY = Values.length(0.0d);
        this.angle = Values.angle(0.0d);
        this.speed = Values.speed(0.0d);
        this.proplevel = 0.0;
        this.breaklevel = 0.0;
        this.isABSon = true;
        this.isASRon = true;
        this.controllevel = 0.0;
        this.lastAngle = Values.angle(0.0d);
        this.lastControllevel = 0.0;
        this.sprite = null;
        
        this.currentState = carState.STOP;
    }
    
    public static Porsche valueOf() {
        Porsche car = new Porsche();

        String spriteName = "Porsche";
        car.sprite(SpriteRotation.valueOf(spriteName, spriteName, 1));
        car.sprite().hide();
        car.reset();
        return car;
    }
    
    // SELECTORS
    public TimeDiff time() {
        return time;
    }
    
    private void time(TimeDiff time) {
        this.time = time;
    }

    public Length posX() {
        return posX;
    }

    public void posX(Length posX) {
        this.posX = posX;
        if (sprite() != null)
            sprite().x = posX.mul(5.0d).m();
    }

    public Length posY() {
        return posY;
    }

    public void posY(Length posY) {
        this.posY = posY;
        if (sprite() != null)
            sprite().y = posY.mul(5.0d).m();
    }

    public Angle angle() {
        return angle; //rad
    }

    public void angle(Angle angle) {
        this.angle = angle; //rad
    }

    public Speed speed() {
        return speed;
    }

    private void speed(Speed speed) {
        this.speed = speed;
    }

    public double proplevel() {
        return proplevel;
    }

    public void proplevel(double proplevel) {
        this.proplevel = proplevel;
    }

    public double breaklevel() {
        return breaklevel;
    }

    public void breaklevel(double breaklevel) {
        this.breaklevel = breaklevel;
    }

    public boolean isABSon() {
        return isABSon;
    }

    public void isABSon(boolean isABSon) {
        this.isABSon = isABSon;
    }

    public boolean isASRon() {
        return isASRon;
    }

    public void isASRon(boolean isASRon) {
        this.isASRon = isASRon;
    }

    public double controllevel() {
        return controllevel;
    }

    public void controllevel(double controllevel) {
        this.controllevel = controllevel;
    }

    public Angle lastAngle() {
        return lastAngle;
    }

    public void lastAngle(Angle lastAngle) {
        this.lastAngle = lastAngle;
    }

    public double lastControllevel() {
        return lastControllevel;
    }

    public void lastControllevel(double lastControllevel) {
        this.lastControllevel = lastControllevel;
    }

    public SpriteRotation sprite() {
        return sprite;
    }

    public void sprite(SpriteRotation sprite) {
        this.sprite = sprite;
    }
    
    public carState currentState() {
        return currentState;
    }

    public void currentState(carState newState) {
        this.currentState = newState;
    }
    
    
    // OPERATIONS
    public void set(TimeDiff time, Length posX, Length posY, Speed speed) {
        time(time);
        posX(posX);
        posY(posY);
        speed(speed);
        if (sprite() != null)
            sprite().rotate(angle());
    }
    
    public void reset() {
        currentState(carState.STOP);
        set(Values.timeDiff(0.0d), Values.length(200.0d), Values.length(200.0d), Values.speed(0.0d));
    }
    
    private double checklevel(double value, double minlevel, double maxlevel) {
        if (value < minlevel)
            return minlevel;
        else if (value > maxlevel)
            return maxlevel;
        else
            return value;
    }    
    
    public void step(TimeDiff deltaTime) {
        // Check for Pedal levels
        proplevel(checklevel(proplevel(), 0.0, 1.0));
        breaklevel(checklevel(breaklevel(), 0.0, 1.0));
        controllevel(checklevel(controllevel(), -1.0, 1.0));
        
        // Load current traction value
        double traction = Game.getInstance().traction().value();
        
        Acc accCar;
        Angle currentAngle; //rad

        // Check for current car state and corresponding actions
        switch (currentState()) {
            
            // car is stopped
            case STOP:
                if (proplevel() >= minControllevel) {
                    currentState(carState.DRIVE);
                    speed(Values.speed(1.0));
                    set(time().add(deltaTime), posX(), posY(), speed());
                    break;
                }
                //proplevel(0.0);
                //breaklevel(0.0);
                //controllevel(0.0);
                set(time().add(deltaTime), posX(), posY(), Values.minSpeed);
                break;
            
            // linear drive forward
            case DRIVE:
                if (speed().smallerThan(Values.minSpeedlevel)) {
                    currentState(carState.STOP);
                    set(time().add(deltaTime), posX(), posY(), Values.minSpeed);
                    break;
                }
                if (Math.abs(controllevel()) > minControllevel ) {
                    currentState(carState.CURVE);
                    set(time().add(deltaTime), posX(), posY(), speed());
                    break;
                }
                
                // Dynamics
                
                // Acceleration for linear motion
                accCar = calculateDynamics(traction);
                
                // Kinematics
                currentAngle = angle(); //rad
                calculateKinematics(deltaTime, accCar, currentAngle);
        
                break;
                
            case CURVE:
                if (speed().smallerThan(Values.minSpeedlevel)) {
                    currentState(carState.STOP);
                    set(time().add(deltaTime), posX(), posY(), Values.minSpeed);
                    break;
                }
                if (Math.abs(controllevel()) <= minControllevel ) {
                    currentState(carState.DRIVE);
                    set(time().add(deltaTime), posX(), posY(), speed());
                    break;
                }
                
                // Dynamics
                
                // Acceleration for linear motion
                accCar = calculateDynamics(traction);
                
                // Acceleration for curve motion
                Acc curveAcc;
        
                // Steering calculations
                Length curveRadius = Values.minCurveRadius.div(Math.abs(controllevel()));
                curveAcc = speed().curveAcc(curveRadius);
            
                // Resulting acceleration
                Acc maxAcc = Values.accEarth.mul(traction);
                Acc acc = curveAcc.add(accCar.abs());
            
                // Check if control is lost, when a car is turned too quickly
                if (maxAcc.smallerThan(acc)) {
                    currentState(carState.SLIDE);
                    lastAngle(angle());
                    lastControllevel(controllevel());
                    controllevel(0.0);
                    break;
                }
                
                // Kinematics
                currentAngle = angle(); //rad
                Length deltaPos = calculateKinematics(deltaTime, accCar, currentAngle);
                
                // Course angle change (rotation)

                Angle deltaCourseAngle = Values.angle((deltaPos.div(curveRadius)) * Math.signum(controllevel())); //rad
                angle(angle().add(deltaCourseAngle)); //rad
                
                break;
            
            // control over car is lost (slide)
            case SLIDE:
                if (speed().smallerThan(Values.minSpeedlevel)) {
                    currentState(carState.STOP);
                    set(time().add(deltaTime), posX(), posY(), Values.minSpeed);
                    break;
                }
                controllevel(0.0);
                breaklevel(1.0);
                proplevel(0.0);
                stallState(deltaTime);
                
                // Dynamics
                
                // Acceleration for linear motion
                accCar = calculateDynamics(traction);
                
                // Kinematics
                currentAngle = lastAngle(); //rad
                calculateKinematics(deltaTime, accCar, currentAngle);
                
                break;
        }
    }
    
    private Acc calculateDynamics(double traction) {
        
        // Acceleration and Break calculation
        Power powerProp = Values.powerPropMax.mul(proplevel());
        Force forcePropMax = Values.mass.mul(Values.accEarth);
        Force forcePropAsr;
        Power powerPropBreak = Values.powerPropMax.mul(breaklevel());
        Force forcePropAbs;
        
        // different Force calculations for ASR/ABS on/off
        if (isASRon())
            forcePropAsr = forcePropMax.min(powerProp.div(speed()));
        else
            forcePropAsr = powerProp.div(speed());
        if (isABSon())
            forcePropAbs = forcePropMax.min(powerPropBreak.div(speed()));
        else
            forcePropAbs = powerPropBreak.div(speed());
        
        // Forces for Gas- and Break-pedals
        Force forceProp = forcePropAsr;
        Force forcePropBreak = forcePropAbs.mul(-1.0d);
        
        // Resistance
        Force forceDrag = speed().forceDrag(Values.speedMax, Values.powerPropMax).mul(-1.0d);
        
        // Resulting force
        Force force = forceProp.add(forcePropBreak).add(forceDrag);
        
        // Check if control is lost, when ABS/ASR is off
        if (forceProp.abs().biggerThan(forcePropMax.abs()) && !isASRon()) {
            currentState(carState.SLIDE);
        }
        else if (forcePropBreak.abs().biggerThan(forcePropMax.abs()) && !isABSon()) {
            currentState(carState.SLIDE);
        }
        
        // Acceleration for linear motion
        Acc accCar = (force.div(Values.mass)).mul(traction);
        
        return accCar;
    }
    
    private Length calculateKinematics(TimeDiff deltaTime, Acc accCar, Angle courseAngle) {
        
        // Values for the next step
        Speed speedNew = (accCar.mul(deltaTime)).add(speed());
        TimeDiff timeNew = time().add(deltaTime);
        Length deltaPos = speed().mul(deltaTime);
        
        Length deltaX = deltaPos.mul(Math.cos(courseAngle.rad()));
        Length deltaY = deltaPos.mul(Math.sin(courseAngle.rad()));
        Length posXNew = posX().add(deltaX);
        Length posYNew = posY().add(deltaY);
       
        set(timeNew, posXNew, posYNew, speedNew);
        
        return deltaPos;
    }
      
    // Sliding rotation (control is lost)
    private void stallState(TimeDiff deltaTime) {
        Angle stallAngle = Values.angle(deltaTime.mul(speed().div(Values.speedMax)).mul(7.5d).mul(Math.signum(lastControllevel())).s()); //rad
        angle(angle().add(stallAngle)); //rad
    }
}