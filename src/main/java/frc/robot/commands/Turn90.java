// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;
import java.lang.Math;

public class Turn90 extends CommandBase {
  private final DriveSubsystem DriveSubsystem;
  private double v_error;
  /** Creates a new Turn90. */
  public Turn90(DriveSubsystem s_DriveSubsystem) {
    DriveSubsystem = s_DriveSubsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(s_DriveSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    DriveSubsystem.differentialTankDrive(0, 0);
    DriveSubsystem.resetGyro();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //v_error = (180 - Math.abs(DriveSubsystem.getRobotAngle()))*.0025;
    v_error = (360 - Math.abs(DriveSubsystem.getRobotAngle()))*.0025;
    /*
    if (((180 - Math.abs(DriveSubsystem.getRobotAngle()))*.0025) < .08){
      v_error = .08;
      System.out.println("SLOW NEW ERROR: ");
    }
    else{
      v_error = (180 - Math.abs(DriveSubsystem.getRobotAngle()))*.0025;
    }
    */
    if (((360 - Math.abs(DriveSubsystem.getRobotAngle()))*.0020) < .09){
      v_error = .09;
      System.out.println("SLOW NEW ERROR: ");
    }
    else{
      v_error = (360 - Math.abs(DriveSubsystem.getRobotAngle()))*.0020;
    }
    DriveSubsystem.differentialTankDrive(v_error, -v_error);
    System.out.println("ERROR: " + v_error);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    DriveSubsystem.differentialTankDrive(0, 0);
    //System.out.println("ANGLE: " + DriveSubsystem.getRobotAngle());
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //return false;
    if ((DriveSubsystem.getRobotAngle() > 360 )|| DriveSubsystem.getRobotAngle() < -360 ){
      return true;
    }
    else{
      return false;
    }
  }
}
