package org.firstinspires.ftc.teamcode.Plateosaurus;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.RobotManager.MecanumDriveTrain;
import org.firstinspires.ftc.teamcode.RobotManager.StandardDriveTrain;

@Config
public class AutonFunctions {
    //private static volatile SampleTankDrive roadRunner;
    private static volatile StandardDriveTrain mainFrame;
    public static long timeToDriveFar = 1000, timeToDriveNear = 900;

    public static void start(LinearOpMode op, TeamColor t, FieldPosition position){
        mainFrame = new MecanumDriveTrain(op);
        mainFrame.addHardware(Configurator.getHardware(mainFrame));
        op.waitForStart();
        if(op.isStopRequested()) return;
        long timeToDrive = position == FieldPosition.NEAR_CAROUSEL ? timeToDriveFar : timeToDriveNear;
        timeToDrive += System.currentTimeMillis();
        while(System.currentTimeMillis() < timeToDrive && op.opModeIsActive())
            mainFrame.setSidedDrivePower(-1, 1);
        mainFrame.setSidedDrivePower(0,0);
        op.requestOpModeStop();
    }

    public enum TeamColor{
        RED, BLUE
    }

    public enum FieldPosition{
        NEAR_CAROUSEL, NOT_NEAR
    }

    public static void flipBucket(boolean up) {
        if (up)
            mainFrame.getServo("OT").get().setPosition(7);
        else
            mainFrame.getServo("OT").get().setPosition(0);
        // im cool and you are not - Mithilya
        }
}


