package org.firstinspires.ftc.teamcode.Plateosaurus.OpModes.AutonomousOpModes;

import static org.firstinspires.ftc.teamcode.Plateosaurus.AutonFunctions.FieldPosition.CAROUSEL;
import static org.firstinspires.ftc.teamcode.Plateosaurus.AutonFunctions.TeamColor.BLUE;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Plateosaurus.AutonFunctions;

@Autonomous
@Config
public class AutonBlueCarousel extends LinearOpMode {
    @Override
    public void runOpMode()throws InterruptedException{
        AutonFunctions.startNew(this, BLUE, CAROUSEL);
    }
}
