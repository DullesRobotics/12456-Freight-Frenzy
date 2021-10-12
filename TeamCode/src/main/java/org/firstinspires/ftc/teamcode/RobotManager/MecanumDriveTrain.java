package org.firstinspires.ftc.teamcode.RobotManager;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Hardware.Controller;
import org.firstinspires.ftc.teamcode.Hardware.Motor.DrivetrainMotor;
import org.firstinspires.ftc.teamcode.Libraries.IMU;
import org.firstinspires.ftc.teamcode.Libraries.PID;

import java.util.logging.Level;

/**
 * A drive-train for robots that can strafe
 */
//@TargetApi(Build.VERSION_CODES.N)
public class MecanumDriveTrain extends StandardDriveTrain {

    /**
     * Takes in super initiators
     * @param op The op mode this is used for
     */
    public MecanumDriveTrain(LinearOpMode op, PID pid) {
        super(op, pid);
    }

    /**
     * Takes in super initiators
     * @param op The op mode this is used for
     */
    public MecanumDriveTrain(LinearOpMode op) {
        super(op);
    }

    /**
     * Driving with the controller, including strafing
     * Hold either bumper for precision mode
     * @param ctrl The controller to move the robot with
     */
    @Override
    public void driveWithController(Controller ctrl) {
        getLogger().log(Level.INFO, "Beginning drive with controller, mechanum");
        addThread(new Thread(() -> {
            double flmPower, frmPower, blmPower, brmPower, currentSpeed;
            while(op().opModeIsActive()){
                currentSpeed = ctrl.rightBumper() || ctrl.leftBumper() ? precisionSpeed : speed;
                getLogger().putData("Joystick Speed", currentSpeed);

                flmPower = currentSpeed * (-ctrl.leftY() + ctrl.rightTrigger() - ctrl.leftTrigger());
                frmPower = currentSpeed * (-ctrl.rightY() - ctrl.rightTrigger() + ctrl.leftTrigger());
                blmPower = currentSpeed * (-ctrl.leftY() - ctrl.rightTrigger() + ctrl.leftTrigger());
                brmPower = currentSpeed * (-ctrl.rightY() + ctrl.rightTrigger() - ctrl.leftTrigger());

              //  getLogger().putData("Set Power (FL, FR, BL, BR)", flmPower + ", " + frmPower + ", " + blmPower + ", " + brmPower);
                getLogger().putData("Power (FL, FR, BL, BR)", getMotor("FLM").get().getPower() + ", " + getMotor("FRM").get().getPower() + ", " + getMotor("BLM").get().getPower() + ", " + getMotor("BRM").get().getPower());
                getLogger().putData("Velocity (FL, FR, BL, BR)", getMotor("FLM").getEncoded().getVelocity() + ", " + getMotor("FRM").getEncoded().getVelocity() + ", " + getMotor("BLM").getEncoded().getVelocity() + ", " + getMotor("BRM").getEncoded().getVelocity());

                setIndividualDrivePower(flmPower, frmPower, blmPower, brmPower);
            }
        }), true, () -> getLogger().clearData());
    }

}
