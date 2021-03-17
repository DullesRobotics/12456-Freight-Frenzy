package org.firstinspires.ftc.teamcode.Hardware.Motor;

/**
 * The type of motor used on the wheel
 * Basically a list of motor types with pre-defined counts
 */
public enum MotorType {
    CORE_HEX_MOTOR(288, 100, 1),
    HD_HEX_MOTOR(28, 100, 1),
    NEVEREST_ORBITAL(537.6, 340, 19.2);

    private final double countsPerRevolution;
    private final double maxRPM;
    private final double driveGearReduction;

    MotorType(double countsPerRevolution, double maxRPM, double driveGearReduction) {
        this.countsPerRevolution = countsPerRevolution;
        this.maxRPM = maxRPM;
        this.driveGearReduction = driveGearReduction;
    }

    public double countsPerRev() {
        return countsPerRevolution;
    }

    public double getMaxRPM() {
        return maxRPM;
    }

    public double getDriveGearReduction() {
        return driveGearReduction;
    }
}
