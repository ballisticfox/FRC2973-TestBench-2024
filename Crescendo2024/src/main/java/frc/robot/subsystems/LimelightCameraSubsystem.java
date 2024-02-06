package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LimelightCameraSubsystem extends SubsystemBase {
  private final NetworkTable m_limelightTable =
      NetworkTableInstance.getDefault().getTable("limelight");

  private final NetworkTableEntry m_tx = m_limelightTable.getEntry("tx");
  private final NetworkTableEntry m_ty = m_limelightTable.getEntry("ty");
  private final NetworkTableEntry m_ta = m_limelightTable.getEntry("ta");
  private final NetworkTableEntry m_tv = m_limelightTable.getEntry("tv");

  private final NetworkTableEntry m_TargetPoseRS =
      m_limelightTable.getEntry("targetpose_robotspace");

  public static final double HEIGHT_OF_TARGET_INCHES = 32;
  public static final double HEIGHT_OF_CAMERA_INCHES = 16;
  public static final double ANGLE_BETWEEN_LEVEL_PLANE_AND_CAMERA = 0;

  public double getXAngleOffset() {
    return m_tx.getDouble(0.0);
  }

  public double getYAngleOffset() {
    return m_ty.getDouble(0.0);
  }

  public double getXDistOffset() {
    return m_TargetPoseRS.getDoubleArray(new double[6])[0];
  }

  public double getYDistOffset() {
    return m_TargetPoseRS.getDoubleArray(new double[6])[1];
  }

  public double getZDistOffset() {
    return m_TargetPoseRS.getDoubleArray(new double[6])[2];
  }

  public double getXRotation() {
    return m_TargetPoseRS.getDoubleArray(new double[6])[3];
  }

  public double getYRotation() {
    return m_TargetPoseRS.getDoubleArray(new double[6])[4];
  }

  public double getZRotation() {
    return m_TargetPoseRS.getDoubleArray(new double[6])[5];
  }

  public double getVisualArea() {
    return m_ta.getDouble(0.0);
  }

  public boolean isTargetVisible() {
    return m_tv.getDouble(0) == 1;
  }

  public double getDistance2D() {
    double distance =
        Math.sqrt(getXDistOffset() * getXDistOffset() + getZDistOffset() * getZDistOffset());

    return distance;
  }

  public double getDistance3D() {
    double distance =
        Math.sqrt(
            getXDistOffset() * getXDistOffset()
                + getYDistOffset() * getYDistOffset()
                + getZDistOffset() * getZDistOffset());
    return distance;
  }

  @Override
  public void periodic() {
    SmartDashboard.putBoolean("Is Visible", isTargetVisible());
    SmartDashboard.putNumber("X Distance Offset: ", getXDistOffset());
    SmartDashboard.putNumber("Y Distance Offset: ", getYDistOffset());
    SmartDashboard.putNumber("Z Distance Offset: ", getZDistOffset());

    SmartDashboard.putNumber("X Angle Offset: ", getXRotation());
    SmartDashboard.putNumber("Y Angle Offset: ", getYRotation());
    SmartDashboard.putNumber("Z Angle Offset: ", getZRotation());

    SmartDashboard.putNumber("Distance 2D", getDistance2D());
    SmartDashboard.putNumber("Distance 3D", getDistance3D());
  }
}
