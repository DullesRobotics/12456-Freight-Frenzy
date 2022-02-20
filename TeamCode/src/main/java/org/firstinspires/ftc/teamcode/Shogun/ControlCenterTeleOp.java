package org.firstinspires.ftc.teamcode.Shogun;

import org.firstinspires.ftc.teamcode.Hardware.ComponentArea;
import org.firstinspires.ftc.teamcode.Hardware.Controller;
import org.firstinspires.ftc.teamcode.Hardware.Motor.Motor;
import org.firstinspires.ftc.teamcode.Hardware.Servo;
import org.firstinspires.ftc.teamcode.RobotManager.Robot;

import java.util.UUID;
import java.util.logging.Level;

public class ControlCenterTeleOp {

    static final double motorCarouselSpeed = 1;

    public static void intakeUpDown(Robot r, Controller ctrl){
        UUID uuid = r.addThread(new Thread(() -> {
            Motor liftMotor = r.getMotor("LIFT");
            while(r.op().opModeIsActive()){
                if(ctrl.leftTrigger() > 0){ //DOWN
                    liftMotor.get().setPower(ctrl.leftTrigger() / 2);
                } else if(ctrl.rightTrigger() > 0) { //UP
                    liftMotor.get().setPower(-ctrl.rightTrigger());
                } else {
                    liftMotor.get().setPower(0);
                }
            }
        }), true);
    }

    public static void intakeInOut(Robot r, Controller ctrl){
        UUID uuid = r.addThread(new Thread(() -> {
            Motor intake = r.getMotor("IN");
            boolean goingForward = false, on = false, currentlyPressed = false;
            while(r.op().opModeIsActive()){
                if(ctrl.buttonUp() && !currentlyPressed){
                    if(goingForward && on)
                        on = false;
                    else {
                        on = true;
                        goingForward = true;
                    }
                    currentlyPressed = true;
                } else if(ctrl.buttonDown() && !currentlyPressed){
                    if(!goingForward && on)
                        on = false;
                    else {
                        on = true;
                        goingForward = false;
                    }
                    currentlyPressed = true;
                }

                if(currentlyPressed && !ctrl.buttonDown() && !ctrl.buttonUp())
                    currentlyPressed = false;

                intake.get().setPower(on ? goingForward ? 0.75 : -0.75 : 0);

            }
        }), true);
    }

    public static void carouselSpin(Robot r, Controller ctrl, boolean isRed){
        r.addThread(new Thread(() -> {
           // boolean on = false, alreadyPressed = false;
            Motor carouselMotor = r.getMotor("CAR");
            while (r.op().opModeIsActive()) {
                        carouselMotor.get().setPower(ctrl.buttonX() ? -motorCarouselSpeed : 0);
                        carouselMotor.get().setPower(ctrl.buttonY() ? motorCarouselSpeed : 0);
                    }

        }), true);
    }

    public static void bucketDrop(Robot r, Controller ctrl){
        r.addThread(new Thread(() -> {
            Servo outtakeServo = r.getServo("OT");
            outtakeServo.get().setPosition(0);
            while (r.op().opModeIsActive()) {
                if(ctrl.rightBumper())
                    outtakeServo.get().setPosition(1);
                else
                    outtakeServo.get().setPosition(0);
            }
        }), true);
    }
}
