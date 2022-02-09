package org.firstinspires.ftc.teamcode.Shogun.OpModes.AutonomousOpModes;

import static org.firstinspires.ftc.teamcode.Shogun.AutonFunctions.FieldPosition.NEAR_CAROUSEL;
import static org.firstinspires.ftc.teamcode.Shogun.AutonFunctions.TeamColor.RED;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Shogun.AutonFunctions;

@Autonomous
@Config
public class AutonRedCarousel extends LinearOpMode {
    @Override
    public void runOpMode()throws InterruptedException{
        AutonFunctions.start(this, RED, NEAR_CAROUSEL);
    }
}
