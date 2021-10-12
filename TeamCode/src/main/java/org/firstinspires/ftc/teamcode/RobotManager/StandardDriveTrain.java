package org.firstinspires.ftc.teamcode.RobotManager;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Hardware.Controller;
import org.firstinspires.ftc.teamcode.Hardware.Motor.DrivetrainMotor;
import org.firstinspires.ftc.teamcode.Libraries.IMU;
import org.firstinspires.ftc.teamcode.Libraries.PID;

import java.util.logging.Level;

//@TargetApi(Build.VERSION_CODES.N)
public class StandardDriveTrain extends DriveTrain {

    /**
     * Takes in super initiators
     * @param op The op mode this is used for
     */
    public StandardDriveTrain(LinearOpMode op, PID pid) {
        super(op, pid);
    }

    /**
     * Takes in super initiators
     * @param op The op mode this is used for
     */
    public StandardDriveTrain(LinearOpMode op) {
        super(op);
    }

    /**
     * Drives using the joystick with the defined speed <br/>
     * By pressing the right or left trigger, you can enter precision mode
     * @param ctrl The controller to move the robot with
     */
    public void driveWithController(Controller ctrl){
        getLogger().log(Level.INFO, "Beginning drive with controller, standard");
        addThread(new Thread(() -> {
            double currentSpeed;
            while(op().opModeIsActive()){
                /* linear equation to calculate speed based on right trigger's position */
                currentSpeed = ctrl.rightTrigger() > 0 || ctrl.leftTrigger() > 0 ? precisionSpeed : speed;
                getLogger().putData("Motor Speed", currentSpeed);
                setSidedDrivePower(-1 * currentSpeed * ctrl.leftY(), -1 * currentSpeed * ctrl.rightY());
            }
        }), true);
    }


}
