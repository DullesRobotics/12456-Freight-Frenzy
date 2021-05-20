package org.firstinspires.ftc.teamcode.Tolerance.OpModes.AutonomousOpModes.StartingPositions;

        import com.acmerobotics.dashboard.config.Config;
        import com.acmerobotics.roadrunner.geometry.Pose2d;
        import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
        import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

        import org.firstinspires.ftc.teamcode.Tolerance.AutonRunner;

        import static org.firstinspires.ftc.teamcode.Tolerance.AutonConstants.STARTING_BLUE_RIGHT;
        import static org.firstinspires.ftc.teamcode.Tolerance.AutonConstants.START_STACK_ANGLE;
        import static org.firstinspires.ftc.teamcode.Tolerance.AutonRunner.Team.BLUE;

@Autonomous
@Config
public class P_BlueRight extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        AutonRunner.start(this, STARTING_BLUE_RIGHT, BLUE, AutonRunner.Side.RIGHT);

    }
}