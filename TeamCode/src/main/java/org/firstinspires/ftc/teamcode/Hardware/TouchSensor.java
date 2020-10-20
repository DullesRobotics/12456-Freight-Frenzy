package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class TouchSensor extends HardwareComponent {

    /**
     * @param op The op mode this servo is registered in
     * @param id The id of the servo in the hardware map
     * @param componentArea Where the servo is on the robot
     */
    public TouchSensor(LinearOpMode op, String id, HardwareComponentArea componentArea) {
        super(op, id, componentArea);
        try { setComponent(op.hardwareMap.touchSensor.get(id));
        } catch (Exception e)
        {
            op.telemetry.addData("Error Adding Touch Sensor " + id + ":", e);
            op.telemetry.update();
            op.requestOpModeStop();
        }
    }

    @Override
    public com.qualcomm.robotcore.hardware.TouchSensor get() {
        return (com.qualcomm.robotcore.hardware.TouchSensor) component;
    }

}