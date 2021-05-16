package org.firstinspires.ftc.teamcode.Tolerance.OpModes.TeleOpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RobotManager.StandardDriveTrain;
import org.firstinspires.ftc.teamcode.Tolerance.Configurator;
import org.firstinspires.ftc.teamcode.Tolerance.Functions;

//@TeleOp
public class MainMode extends LinearOpMode {

    private StandardDriveTrain robot;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new StandardDriveTrain(this);
        robot.addHardware(Configurator.getHardware(robot));

        waitForStart();

        /* Robot functions */
        robot.driveWithController(robot.ctrl1());
        Functions.startIntake(robot, robot.ctrl2());
//        Functions.startShooter(robot, robot.ctrl2());
        Functions.startClaw(robot, robot.ctrl2());

        while (opModeIsActive())
            robot.getLogger().updateLog();

        robot.stopAllThreads();
    }
}