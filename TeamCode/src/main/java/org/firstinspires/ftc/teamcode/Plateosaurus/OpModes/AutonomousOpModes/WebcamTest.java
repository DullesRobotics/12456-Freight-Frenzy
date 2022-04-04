package org.firstinspires.ftc.teamcode.Plateosaurus.OpModes.AutonomousOpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Hardware.USBWebcam;
import org.firstinspires.ftc.teamcode.Libraries.AddOns.EasyOpenCV;
import org.firstinspires.ftc.teamcode.Plateosaurus.Configurator;
import org.firstinspires.ftc.teamcode.Plateosaurus.OpenCVPipelines.GreenScanningPipeline;
import org.firstinspires.ftc.teamcode.RobotManager.MecanumDriveTrain;
import org.openftc.easyopencv.OpenCvCameraRotation;

@Autonomous
public class WebcamTest extends LinearOpMode {

    private MecanumDriveTrain robot;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new MecanumDriveTrain(this);

        robot.addHardware(new USBWebcam(robot, "Webcam"));
        GreenScanningPipeline pipe = new GreenScanningPipeline();
        EasyOpenCV ez = new EasyOpenCV(pipe, robot.getUSBWebcam("Webcam"), OpenCvCameraRotation.UPRIGHT);
        robot.addOnManager().initAddOn(ez);

        waitForStart();

        while (opModeIsActive())
            robot.getLogger().updateLog();

        robot.stopAllThreads();
    }
}