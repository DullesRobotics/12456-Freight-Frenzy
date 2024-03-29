package org.firstinspires.ftc.teamcode.Plateosaurus.OpModes.TeleOpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RobotManager.StandardDriveTrain;
import org.firstinspires.ftc.teamcode.Plateosaurus.Configurator;
import org.firstinspires.ftc.teamcode.Plateosaurus.ControlCenterTeleOp;

@TeleOp
public class StandardDriveTest extends LinearOpMode {

    private StandardDriveTrain robot;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new StandardDriveTrain(this);
        robot.addHardware(Configurator.getDriveTrainMotors(robot));

        waitForStart();

        robot.driveWithController(robot.ctrl1());
        ControlCenterTeleOp.carouselSpin(robot, robot.ctrl1(), false);

        while (opModeIsActive())
            robot.getLogger().updateLog();

        robot.stopAllThreads();
    }
}