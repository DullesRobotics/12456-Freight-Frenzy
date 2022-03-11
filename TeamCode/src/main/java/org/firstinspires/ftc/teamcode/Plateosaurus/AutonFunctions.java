package org.firstinspires.ftc.teamcode.Plateosaurus;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Hardware.ComponentArea;
import org.firstinspires.ftc.teamcode.Hardware.Motor.Motor;
import org.firstinspires.ftc.teamcode.Libraries.RoadRunner.Drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.RobotManager.MecanumDriveTrain;

@Config
public class AutonFunctions {
    private static volatile MecanumDriveTrain mainFrame;
    private static volatile SampleMecanumDrive roadRunner;
    public static int timeToDriveFar = 1000, timeToDriveNear = 1000;
    public static int timeToCarousel = 1600, correctionTime = 250, timeToSpin = 4000, timeToStrafe = 500;

    public static void start(LinearOpMode op, TeamColor t, FieldPosition position){
        mainFrame = new MecanumDriveTrain(op);
        mainFrame.addHardware(Configurator.getHardware(mainFrame));
        op.waitForStart();
        if(op.isStopRequested()) return;
        long timeToDrive = position == FieldPosition.CAROUSEL ? timeToDriveFar : timeToDriveNear;
        timeToDrive += System.currentTimeMillis();
        while(System.currentTimeMillis() < timeToDrive && op.opModeIsActive())
            mainFrame.setIndividualDrivePower(0,0, -0.3, -0.3);
        mainFrame.setSidedDrivePower(0,0);
        op.requestOpModeStop();
    }

    public static void startNew(LinearOpMode op, TeamColor t, FieldPosition position){
        roadRunner = new SampleMecanumDrive(op);
        mainFrame = roadRunner.getDriveTrain();
        //mainFrame.addHardware(new USBWebcam(mainFrame, "Webcam"));
        //GreenScanningPipeline pipe = new GreenScanningPipeline();
        //EasyOpenCV ez = new EasyOpenCV(pipe, mainFrame.getUSBWebcam("Webcam"), OpenCvCameraRotation.UPRIGHT);
        //mainFrame.addOnManager().initAddOn(ez);

        ///////////

        //Trajectory moveToWobble = roadRunner.trajectoryBuilder(new Pose2d())
        //        .forward(-12)
        //        .build();

//        Trajectory moveToCarousel = roadRunner.trajectoryBuilder(new Pose2d())
//                .back(40)
//                .build();
//
//        Trajectory parkInStorage = roadRunner.trajectoryBuilder(new Pose2d())
//                .strafeLeft(20)
//                .build();
//
//        Trajectory parkInWarehouse = roadRunner.trajectoryBuilder(new Pose2d())
//                .back(60)
//                .build();

        ///////////

        //resetLiftEncoder();

        op.waitForStart();

        if(op.isStopRequested()) return;

        //int level = pipe.getBestZone();

        // move lift to correct level
        //changeLiftLevel(level);

        // move to middle wobble
        //roadRunner.followTrajectory(moveToWobble);

        // wait for lift to extend
        //mainFrame.autonWait(1000);

        // place block
        ///intakeItems(false, true);

        // wait a few seconds to drop
        //mainFrame.autonWait(500);

        // turn off intake
        //intakeItems(false, false);

        // lower intake
        //changeLiftLevel(0);

        // IF NEAR CAROUSEL
        if(position == FieldPosition.CAROUSEL) {
            // move to carousel

            // roadRunner.followTrajectory(moveToCarousel);
            // spin 5 seconds
            mainFrame.setIndividualDrivePower(0,0,-0.2, -0.2);
            mainFrame.autonWait(timeToCarousel);
            //mainFrame.setSidedDrivePower(0,0);
            mainFrame.setIndividualDrivePower(0,0, -0.1, -0.1);
            mainFrame.autonWait(correctionTime);
            mainFrame.setSidedDrivePower(0,0);
            spinCarousel(true, t == TeamColor.RED ? true : false);
            mainFrame.autonWait(timeToSpin);
            spinCarousel(false, false);
            //mainFrame.autoStrafeTimed(timeToStrafe, t==TeamColor.RED ? true : false);
            // park Dropoff
            // roadRunner.followTrajectory(parkInStorage);
        } else {
            // ELSE NEAR WAREHOUSE
            // park warehouse
            //roadRunner.turn(Math.toRadians(90));
            //roadRunner.followTrajectory(parkInWarehouse);
        }
    }

    /**
     * Sets if the carousel should spin or not
     * @param isOn True - Carousel Spins; False - Carousel Stops
     */
    public static void spinCarousel (boolean isOn, boolean clockwise) {
        Motor carousel = mainFrame.getMotors(ComponentArea.CAROUSEL).get(0);
        if(carousel != null && carousel.get() != null)
            carousel.get().setPower(isOn ? clockwise ? 1 : -1 : 0);
    }

    public enum TeamColor{
        RED, BLUE
    }

    public enum FieldPosition{
        CAROUSEL, WAREHOUSE
    }

    public static void flipBucket(boolean up) {
        if (up)
            mainFrame.getServo("OT").get().setPosition(7);
        else
            mainFrame.getServo("OT").get().setPosition(0);
        // im cool and you are not - Mithilya
        }
}


