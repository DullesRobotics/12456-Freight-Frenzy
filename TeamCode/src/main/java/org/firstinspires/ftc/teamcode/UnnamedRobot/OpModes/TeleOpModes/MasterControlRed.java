package org.firstinspires.ftc.teamcode.UnnamedRobot.OpModes.TeleOpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RobotManager.MecanumDriveTrain;
import org.firstinspires.ftc.teamcode.UnnamedRobot.Configurator;
import org.firstinspires.ftc.teamcode.UnnamedRobot.Functions;

@TeleOp
public class MasterControlRed extends LinearOpMode {

    private MecanumDriveTrain robot;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new MecanumDriveTrain(this);
        robot.addHardware(Configurator.getHardware(robot));

        waitForStart();

        robot.driveWithController(robot.ctrl1());
        Functions.carousel(robot, robot.ctrl2(),true);

        while (opModeIsActive())
            robot.getLogger().updateLog();

        robot.stopAllThreads();
    }
}
