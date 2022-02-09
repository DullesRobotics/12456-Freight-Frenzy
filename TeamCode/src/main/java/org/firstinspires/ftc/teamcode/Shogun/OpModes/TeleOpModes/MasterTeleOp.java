package org.firstinspires.ftc.teamcode.Shogun.OpModes.TeleOpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RobotManager.MecanumDriveTrain;
import org.firstinspires.ftc.teamcode.RobotManager.StandardDriveTrain;
import org.firstinspires.ftc.teamcode.Shogun.Configurator;
import org.firstinspires.ftc.teamcode.Shogun.ControlCenterTeleOp;

@TeleOp
public class MasterTeleOp extends LinearOpMode {
    private StandardDriveTrain baseRobot;
    @Override
    public void runOpMode() throws InterruptedException {
        baseRobot = new StandardDriveTrain(this);
        baseRobot.addHardware(Configurator.getHardware(baseRobot));

        waitForStart();

        baseRobot.driveWithController(baseRobot.ctrl1());
        ControlCenterTeleOp.carouselSpin(baseRobot, baseRobot.ctrl2(), false);
        ControlCenterTeleOp.intakeUpDown(baseRobot, baseRobot.ctrl2());
        ControlCenterTeleOp.intakeInOut(baseRobot, baseRobot.ctrl2());

        while (opModeIsActive())
            baseRobot.getLogger().updateLog();

        baseRobot.stopAllThreads();
    }
}
