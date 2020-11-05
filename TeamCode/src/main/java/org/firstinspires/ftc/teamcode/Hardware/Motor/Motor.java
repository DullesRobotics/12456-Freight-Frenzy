package org.firstinspires.ftc.teamcode.Hardware.Motor;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Hardware.HardwareComponent;
import org.firstinspires.ftc.teamcode.Hardware.HardwareComponentArea;
import org.firstinspires.ftc.teamcode.RobotManager.Robot;

import java.util.logging.Level;

/**
 * Acts as a shell around DCMotor, providing more information and automating more processes surrounding it.
 */
public class Motor extends HardwareComponent {

    private boolean isOpposite;
    private MotorConfiguration motorConfiguration;

    /**
     * @param r The op mode this motor is registered in
     * @param id The id of the motor in the hardware map
     * @param componentArea Where the motor is on the robot
     * @param motorConfiguration The motor configuration, including the counts per inch and more
     * @param isOpposite If the motor should be inverted
     */
    public Motor(Robot r, String id, HardwareComponentArea componentArea, MotorConfiguration motorConfiguration, boolean isOpposite)
    {
        super(r, id, componentArea);
        this.isOpposite = isOpposite;
        this.motorConfiguration = motorConfiguration;
        get().setDirection(isOpposite ? DcMotorSimple.Direction.REVERSE : DcMotorSimple.Direction.FORWARD);
        try {
            setComponent(r.op.hardwareMap.dcMotor.get(id));
            r.op.hardwareMap.dcMotor.get(id).setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        } catch (Exception e) {
            r.getLogger().logKeyed(Level.SEVERE, "Error Adding Motor (encoded = " + motorConfiguration.isEncoded() + ") " + id, e.toString());
            r.op.requestOpModeStop();
        }
    }

    /**
     * Without a provided motorConfiguration, the motor cannot be encoded
     * @param r The op mode this motor is registered in
     * @param id The id of the motor in the hardware map
     * @param componentArea Where the motor is on the robot
     * @param isOpposite If the motor should be inverted
     */
    public Motor(Robot r, String id, HardwareComponentArea componentArea, boolean isOpposite)
    {
        super(r, id, componentArea);
        this.isOpposite = isOpposite;
        get().setDirection(isOpposite ? DcMotorSimple.Direction.REVERSE : DcMotorSimple.Direction.FORWARD);
        try {
            setComponent(r.op.hardwareMap.dcMotor.get(id));
            r.op.hardwareMap.dcMotor.get(id).setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        } catch (Exception e) {
            r.getLogger().logKeyed(Level.SEVERE, "Error Adding Motor " + id, e.toString());
            r.op.requestOpModeStop();
        }
    }

    @Override
    public DcMotor get() {
        return (DcMotor) component;
    }

    /**
     * @param isOpposite Whether or not the robot is inverted
     */
    public void setOpposite(boolean isOpposite){
        this.isOpposite = isOpposite;
        get().setDirection(isOpposite ? DcMotorSimple.Direction.REVERSE : DcMotorSimple.Direction.FORWARD);
    }

    /**
     * Usually a right wheel
     * @return Whether or not the robot is inverted
     */
    public boolean isOpposite(){
        return isOpposite;
    }

    public MotorConfiguration getMotorConfiguration(){ return motorConfiguration; }

    /**
     * clones the same motor with a different ID
     * @param newID the new ID of the component
     * @return The new motor with the same traits
     */
    public Motor clone(String newID){
        return new Motor(r, newID, getComponentArea(), motorConfiguration, isOpposite);
    }

    /**
     * stops and resets the motor encoder, and then resets to the original motor mode
     */
    public void stopAndResetEncoder(){
        DcMotor.RunMode _tempM = get().getMode();
        get().setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        get().setMode(_tempM);
    }

}
