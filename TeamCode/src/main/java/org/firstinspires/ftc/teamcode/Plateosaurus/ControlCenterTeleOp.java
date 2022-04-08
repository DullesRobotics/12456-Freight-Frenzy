package org.firstinspires.ftc.teamcode.Plateosaurus;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.Hardware.ColorSensor;
import org.firstinspires.ftc.teamcode.Hardware.Controller;
import org.firstinspires.ftc.teamcode.Hardware.Motor.Motor;
import org.firstinspires.ftc.teamcode.Hardware.Servo;
import org.firstinspires.ftc.teamcode.RobotManager.Robot;

import java.util.UUID;

@Config
public class ControlCenterTeleOp {

    public static double motorCarouselSpeed = 1;
    public static double intakeSpeed = 0.8;
    public static double liftUpModifier = 0.4, liftDownModifier = -0.4, liftDefaultPower = 0.05;
    public static double bucketPosNormal = 0, bucketPosExtended = 0.6, bucketPosExtendedMore = 0.72;
    public static double armPosNormal = 0.5, armSpeed = 0.1;

    public static void cappingArm(Robot r, Controller ctrl){
        r.addThread(new Thread(() -> {
            Servo armServo = r.getServo("ARM");
            armServo.get().scaleRange(0,1);
            armServo.get().setPosition(armPosNormal);
            while (r.op().opModeIsActive()) {
                if(ctrl.buttonRight())
                    armServo.get().setPosition(armPosNormal + armSpeed);
                else if(ctrl.buttonLeft())
                    armServo.get().setPosition(armPosNormal - armSpeed);
                else
                    armServo.get().setPosition(armPosNormal);
            }
        }), true);
    }

    public static void intakeUpDown(Robot r, Controller ctrl){
        r.addThread(new Thread(() -> {
            Motor liftMotor = r.getMotor("LIFT");
            while(r.op().opModeIsActive()){
                if(ctrl.leftTrigger() > 0){ //DOWN
                    liftMotor.get().setPower(ctrl.leftTrigger() * liftDownModifier);
                } else if(ctrl.rightTrigger() > 0) { //UP
                    liftMotor.get().setPower(-ctrl.rightTrigger() * liftUpModifier);
                } else {
                    liftMotor.get().setPower(liftDefaultPower);
                }
            }
        }), true);
    }

    public static void intakeInOut(Robot r, Controller ctrl){
        r.addThread(new Thread(() -> {
            Motor intake = r.getMotor("IN");
  //          ColorSensor cs =  r.getColorSensor("CS");
            boolean goingForward = false, on = false, currentlyPressed = false;
            while(r.op().opModeIsActive()){

//                if(!currentlyPressed){
//                   int red = cs.get().red(), grn = cs.get().green(), blu = cs.get().blue();
//                   //white sphere
//                   if(red > 200 && grn > 200 && blu > 200){
//                       on = false;
//                   } else if (red > 200 && grn > 200){
//                       //yellow block
//                       on = false;
//                   }
//                }

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

                intake.get().setPower(on ? goingForward ? intakeSpeed : -intakeSpeed : 0);

            }
        }), true);
    }

    public static void carouselSpin(Robot r, Controller ctrl, boolean isRed){
        r.addThread(new Thread(() -> {
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
            outtakeServo.get().setPosition(bucketPosNormal);
            while (r.op().opModeIsActive()) {
                if(ctrl.rightBumper())
                    outtakeServo.get().setPosition(bucketPosExtended);
                else if(ctrl.leftBumper()){
                    outtakeServo.get().setPosition(bucketPosExtendedMore);
                } else {
                    outtakeServo.get().setPosition(bucketPosNormal);
                }
            }
        }), true);
    }
}
