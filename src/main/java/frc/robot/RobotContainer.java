// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import frc.robot.commands.DriveTeleop;
import frc.robot.commands.Turn90;
import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  private final static XboxController io_drivercontroller = new XboxController(0);
  // The robot's subsystems and commands are defined here...
  private final DriveSubsystem s_DriveSubsystem = new DriveSubsystem();

  private final Command z_Turn90 = new Turn90(s_DriveSubsystem);
  private final Command z_DriveTeleop = new DriveTeleop(s_DriveSubsystem);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    s_DriveSubsystem.setDefaultCommand(z_DriveTeleop);
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    final JoystickButton d_aButton = new JoystickButton(io_drivercontroller, Button.kA.value);
    d_aButton.whenPressed(z_Turn90);
  }
  public static double deadZoneCheck(double rawControllerInput){
    if (rawControllerInput > Constants.kControllerDeadZone || rawControllerInput < -Constants.kControllerDeadZone){
      return rawControllerInput;
    }
    else{
      return 0;
    }
  }
  public static double getDriverLeftSpeed(){
    return deadZoneCheck(io_drivercontroller.getLeftY());
  }
  public static double getDriverRightSpeed() {
    return deadZoneCheck(io_drivercontroller.getRightY());
  }
  public static double getDriverLeftSpeedX(){
    return deadZoneCheck(io_drivercontroller.getLeftX());
  }
  public static double getDriverRightSpeedX(){
    return deadZoneCheck(io_drivercontroller.getRightX());
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }
}
