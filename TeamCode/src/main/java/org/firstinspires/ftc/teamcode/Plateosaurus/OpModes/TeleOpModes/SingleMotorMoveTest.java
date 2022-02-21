package org.firstinspires.ftc.teamcode.Plateosaurus.OpModes.TeleOpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Hardware.ComponentArea;
import org.firstinspires.ftc.teamcode.Hardware.Motor.Motor;
import org.firstinspires.ftc.teamcode.RobotManager.StandardDriveTrain;

@TeleOp
public class SingleMotorMoveTest extends LinearOpMode {
    StandardDriveTrain r;

    @Override
    public void runOpMode() throws InterruptedException {
        r = new StandardDriveTrain(this);
        r.addHardware(new Motor(r, "TEST", ComponentArea.MISCELLANEOUS, false));

        waitForStart();

        while (opModeIsActive()) {
            r.getMotor("TEST").get().setPower(0.2);
            r.getLogger().updateLog();
        }

        r.getMotor("TEST").get().setPower(0);
        r.stopAllThreads();
    }
}
