package org.firstinspires.ftc.teamcode.Shogun;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Libraries.RoadRunner.Drive.SampleTankDrive;
import org.firstinspires.ftc.teamcode.RobotManager.MecanumDriveTrain;
import org.firstinspires.ftc.teamcode.RobotManager.StandardDriveTrain;

import java.text.FieldPosition;

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
}


