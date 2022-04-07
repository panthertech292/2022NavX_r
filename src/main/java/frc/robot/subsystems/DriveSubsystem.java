// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

import com.ctre.phoenix.sensors.WPI_Pigeon2;



public class DriveSubsystem extends SubsystemBase {
  private WPI_Pigeon2 Pigeon2;
  private final CANSparkMax FrontLeftMotor;
  private final CANSparkMax FrontRightMotor;

  private final DifferentialDrive DifDrive;

  private double v_leftSpeed;
  private double v_rightSpeed;
  private double v_leftXSpeed;
  private double v_rightYSpeed;
  /** Creates a new DriveSubsystem. */
  public DriveSubsystem() {
    Pigeon2 = new WPI_Pigeon2(6);
    FrontLeftMotor = new CANSparkMax(DriveConstants.kFrontLeftMotor, MotorType.kBrushless);
    FrontRightMotor = new CANSparkMax(DriveConstants.kFrontRightMotor, MotorType.kBrushless);
    DifDrive = new DifferentialDrive(FrontLeftMotor,FrontRightMotor);
    FrontLeftMotor.restoreFactoryDefaults();
    FrontRightMotor.restoreFactoryDefaults();
    FrontLeftMotor.setSmartCurrentLimit(60);
    FrontRightMotor.setSmartCurrentLimit(60);
    FrontLeftMotor.setIdleMode(IdleMode.kBrake);
    FrontRightMotor.setIdleMode(IdleMode.kBrake);
  }
   //Teleop
  public void differentialTankDrive(double leftspeed, double rightspeed){
    v_leftSpeed = -leftspeed;
    v_rightSpeed = rightspeed;
    DifDrive.tankDrive(v_leftSpeed,v_rightSpeed, false);
  }
  //Telop Drive
  public void differentialArcadeDrive(double leftXspeedTurn, double rightYspeed){
    v_leftXSpeed = leftXspeedTurn;
    v_rightYSpeed = rightYspeed;
    DifDrive.arcadeDrive(v_leftXSpeed, v_rightYSpeed);
  }
  public double getRobotAngle(){
    return Pigeon2.getAngle();
  }
  public void resetGyro(){
    Pigeon2.reset();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    System.out.println("ANGLE: " + Pigeon2.getAngle());
    System.out.println("MOTOR POWER LEFT: " + FrontLeftMotor.get() + "MOTOR POWER RIGHT: " + FrontRightMotor.get());
  }
}
