package org.firstinspires.ftc.teamcode.Shogun;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Libraries.RoadRunner.Drive.SampleTankDrive;
import org.firstinspires.ftc.teamcode.RobotManager.StandardDriveTrain;

public class AutonFunctions {
    private static volatile SampleTankDrive roadRunner;
    private static volatile StandardDriveTrain mainFrame;
    private final static long timeToDriveFar = 2000, timeToDriveNear = 1000;

    public enum TeamColor{
        RED, BLUE
    }

    public enum FieldPosition{
        NEAR_CAROUSEL, NOT_NEAR
    }

    public static void start(LinearOpMode op, TeamColor t, FieldPosition fieldPosition){
        if(op.isStopRequested()) return;
        roadRunner = new SampleTankDrive(op);
        mainFrame = roadRunner.getDriveTrain();
        long timeToDrive = fieldPosition == FieldPosition.NEAR_CAROUSEL ? timeToDriveNear : timeToDriveFar;
        timeToDrive += System.currentTimeMillis();
        while(System.currentTimeMillis() < timeToDrive)
            mainFrame.setSidedDrivePower(1, -1);
        mainFrame.setSidedDrivePower(0,0);
        op.requestOpModeStop();

    }

//    private static volatile SampleMecanumDrive roadRunner;
//    private static volatile TeamColor team;
//    private volatile Point start = startRedRight;

//    public static void start(LinearOpMode op, TeamColor t, Direction position){
//
//        AutonFunctions.team = team;
//        roadRunner = new SampleMecanumDrive(op);
//        mainFrame = roadRunner.getDriveTrain();
//        mainFrame.addThread(new Thread(() -> {
//            while(op.opModeIsActive() && !op.isStopRequested()){
//                mainFrame.getLogger().updateLog();
//            }
//        }), true);
//
//        double angleStart = (team == TeamColor.blue)? 180:-180;
//        team = t;
//        Point start = (team == TeamColor.blue) && (position==Direction.left) ? (team == TeamColor.red) && (position==Direction.left) ?startBlueLeft:startBlueRight:startRedLeft;
//        Pose2d startPose = new Pose2d(start.x,start.y,angleStart);
//        roadRunner.setPoseEstimate(startPose);
//
//        op.waitForStart();
//        if(op.isStopRequested()) return;
//
//        //Move Forward to Carousel
//        Trajectory carousel = roadRunner.trajectoryBuilder(startPose)
//                .forward(-36)
//                .build();
//        roadRunner.followTrajectory(carousel);
//        //Rotate Carousel
//        turnCarousel(mainFrame);
//    }
//
//    private static void turnCarousel(Robot r) {
//        for(Motor m : r.getMotors(ComponentArea.CAROUSEL)) {
//            m.get().setPower(.6);
//        }
//    }

//    public enum Direction{
//        left,right;
//    }
//    public static Point
//        startRedLeft = new Point(-36,60),
//        startRedRight = new Point(12, -60),
//        startBlueLeft = new Point(-36, 60),
//        startBlueRight = new Point(12, 60);
}

