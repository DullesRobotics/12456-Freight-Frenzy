package org.firstinspires.ftc.teamcode.UnnamedRobot;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.Hardware.ComponentArea;
import org.firstinspires.ftc.teamcode.Hardware.Controller;
import org.firstinspires.ftc.teamcode.Hardware.Motor.Motor;
import org.firstinspires.ftc.teamcode.RobotManager.Robot;

import java.util.logging.Level;

@Config
public class Functions {

    public static double SPINNY_DUCKY_SPEED = 0.25;

    public static void carousel(Robot r, Controller c, boolean isRed){
        r.getLogger().log(Level.INFO, "starting robot movement function for carousel spinning");
        r.addThread(new Thread(() -> {
            Motor sm = r.getMotors(ComponentArea.SPINNY_DUCKY).get(0);
            if(sm != null) {
                while (r.op().opModeIsActive()) {
                    if (c.buttonY()) {
                        sm.get().setPower((isRed ? 1 : -1) * SPINNY_DUCKY_SPEED);
                    }
                    else{
                        sm.get().setPower(0);
                    }
                }
            }
        }), true, () -> r.getLogger().clearData());
    }

}
