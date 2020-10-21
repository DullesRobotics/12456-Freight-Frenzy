package org.firstinspires.ftc.teamcode.Libraries;

public class PID {

    private volatile double kp = 0, ki = 0, kd = 0;
    private volatile double errorSum = 0, prevError = 0, prevTime = 0;

    public PID(double kp, double ki, double kd){
        updateConstants(kp, ki, kd);
    }

    public void updateConstants(double kp, double ki, double kd){
        this.kp = kp;
        this.ki = ki;
        this.kd = kd;
    }

    /**
     * Calculates the current power the motor should move to meet the specified target angle
     * to ensure the robot does not overshoot
     * @param currentAngle The current angle of the robot
     * @param targetAngle The target angle of the robot
     * @return The power the motors should have to move to the target angle currently
     */
    public double update(double currentAngle, double targetAngle){
        if(prevTime == 0) prevTime = System.currentTimeMillis();
        double error = getError(currentAngle, targetAngle);
        double dt = System.currentTimeMillis() - prevTime;
        errorSum += (error * dt);

        /* PID Calculations */
        double newPower = 0;
        /* How much we should adjust movement to ensure a smooth slowdown */
        newPower += error * kp;
        /* Keeps track of how close we are, keeps getting bigger to counteract kp slowing down quickly */
        newPower += errorSum * ki;
        /* Keeps rest of calculations in check to ensure it doesn't overshoot */
        newPower += ((prevError - error) / dt) * kd;
        /* //////////////// */

        prevError = error;
        prevTime = System.currentTimeMillis();
        return newPower;
    }

    /**
     * Gets the error, aka how much we need to move to get to the target angle
     * @param currentAngle The current angle of the robot
     * @param targetAngle The target angle of the robot
     * @return The difference between the two angles, in a -180º to 180º form
     */
    private static double getError(double currentAngle, double targetAngle){
        double diff = currentAngle - targetAngle;
        return diff > 180 ? diff - 360 : diff;
    }

}