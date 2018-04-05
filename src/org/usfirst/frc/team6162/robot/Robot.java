/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6162.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;

import javax.xml.crypto.Data;

import org.usfirst.frc.team6162.robot.commands.ExampleCommand;
import org.usfirst.frc.team6162.robot.commands.armDown;
import org.usfirst.frc.team6162.robot.commands.armUp;
import org.usfirst.frc.team6162.robot.commands.auto1;
import org.usfirst.frc.team6162.robot.commands.auto10;
import org.usfirst.frc.team6162.robot.commands.auto11;
import org.usfirst.frc.team6162.robot.commands.auto12;
import org.usfirst.frc.team6162.robot.commands.auto2;
import org.usfirst.frc.team6162.robot.commands.auto3;
import org.usfirst.frc.team6162.robot.commands.auto4;
import org.usfirst.frc.team6162.robot.commands.auto5;
import org.usfirst.frc.team6162.robot.commands.auto6;
import org.usfirst.frc.team6162.robot.commands.auto7;
import org.usfirst.frc.team6162.robot.commands.auto8;
import org.usfirst.frc.team6162.robot.commands.auto9;
import org.usfirst.frc.team6162.robot.commands.closeArm;
import org.usfirst.frc.team6162.robot.commands.deliverCube;
import org.usfirst.frc.team6162.robot.commands.getCube;
import org.usfirst.frc.team6162.robot.commands.gyroD;
import org.usfirst.frc.team6162.robot.commands.encoder;
import org.usfirst.frc.team6162.robot.commands.moveDownElevator;
import org.usfirst.frc.team6162.robot.commands.moveUpElevator;
import org.usfirst.frc.team6162.robot.commands.openArm;
import org.usfirst.frc.team6162.robot.commands.prepareGetCube;
import org.usfirst.frc.team6162.robot.commands.stopArm;
import org.usfirst.frc.team6162.robot.commands.stopElevator;
import org.usfirst.frc.team6162.robot.subsystems.RDrive;
import org.usfirst.frc.team6162.robot.subsystems.servo;
import org.usfirst.frc.team6162.robot.subsystems.Arms;
import org.usfirst.frc.team6162.robot.subsystems.Elevator;
import org.usfirst.frc.team6162.robot.subsystems.ExampleSubsystem;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static ExampleSubsystem m_subsystem = new ExampleSubsystem();
	public static OI m_oi;
	public static Arms arms;
	public static Elevator elevator;
	public static RDrive rdrive;
	public static servo Servo;
	XboxController controller;

	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser;
	
	public static NetworkTableEntry EC1e;
	public static NetworkTableEntry EC2e;
	public static NetworkTableEntry EC3e;
	public static NetworkTableEntry EC4e;
	NetworkTable table;
	NetworkTableInstance inst;
	
	//Set the timer for step accelerate
	//Integer stepAccelerateTimer = new Integer(0);
	int stepAccelerateTimer = 0;
	//RDrive drive =  new RDrive();
	double start = 0; //0 for left, 1 for middle, 2 for right
	double choice = 1; //1 for switch, 2 for scale, 3 for crossing the line
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		m_oi = new OI();
		arms = new Arms();
		rdrive = new RDrive();
		elevator = new Elevator();
		Servo = new servo();
		m_chooser = new SendableChooser<Command>();
		m_chooser.addDefault("Default Auto", new ExampleCommand());
		m_chooser.addObject("Cross the line", new auto1());
		m_chooser.addObject("R-Rswitch", new auto2());
		m_chooser.addObject("L-Lswitch", new auto3());
		m_chooser.addObject("M-Lswitch", new auto4());
		m_chooser.addObject("M-Rswitch", new auto5());
		m_chooser.addObject("L-Rswitch", new auto6());
		m_chooser.addObject("R-Lswitch", new auto7());
		m_chooser.addObject("L-Lscale", new auto8());
		m_chooser.addObject("R-Rscale", new auto9());
		m_chooser.addObject("L-Rscale", new auto10());
		m_chooser.addObject("R-Lscale", new auto11());
		m_chooser.addObject("forward->turning", new auto12());
		
		inst = NetworkTableInstance.getDefault();
		table = inst.getTable("SmartDashboard");
		EC1e = table.getEntry("EC1");
		EC2e = table.getEntry("EC2");
		EC3e = table.getEntry("EC3");
		EC4e = table.getEntry("EC4");
		SmartDashboard.putData("Auto mode", m_chooser);
	 	
		Robot.arms.compressor.setClosedLoopControl(true);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		/* auto modes
	String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
        if(gameData.length() > 0)
        {
 if (choice == 1) {
  if(gameData.charAt(0) == 'L')
  {
	  if (start == 0) {
		  new auto3();
	  }
	  else if (start == 1) {
		  new auto4();
	  }
	  else {
		  new auto7();
	  }
  } else if(gameData.charAt(0) == 'R') {
	  if (start == 0) {
		  new auto6();
	  }
	  else if (start == 1) {
		  new auto5();
  }
	  else {
		  new auto2();
	  }
        }
  else {
	  new auto1();
  }
 }
        
 if (choice == 2) {
	  if(gameData.charAt(1) == 'L')
	  {
		  if (start == 0) {
			  new auto8();
		  }
		  else if (start == 1) {
			  new auto1();
		  }
		  else {
			  new auto11();
		  }
	  } else if(gameData.charAt(1) == 'R') {
		  if (start == 0) {
			  new auto10();
		  }
		  else if (start == 1) {
			  new auto1();
	  }
		  else {
			  new auto9();
		  }
	        }
	  else {
		  new auto1();
	  }
	 }
        }
 if (choice == 3) {
	 new auto1();
 }
 */

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		m_autonomousCommand = (Command) m_chooser.getSelected();
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
		
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		Robot.rdrive.driveArcade(Robot.m_oi.leftJoy.getY()*0.75, Robot.m_oi.leftJoy.getX()*0.75);
		Robot.EC1e.setDouble(Robot.rdrive.EC1.getDistance());
		Robot.EC2e.setDouble(Robot.rdrive.EC2.getDistance());
		/*
		if (Robot.m_oi.leftJoy.getX() < 0.1 && Robot.m_oi.leftJoy.getY() < 0.1) {
			stepAccelerateTimer = 0;
		}
		else {
			if (stepAccelerateTimer < 50) {
				stepAccelerateTimer = stepAccelerateTimer + 1;
				Robot.rdrive.driveArcade(Robot.m_oi.leftJoy.getY()*stepAccelerateTimer*0.02, Robot.m_oi.leftJoy.getX());
			}
			else {
				Robot.rdrive.driveArcade(Robot.m_oi.leftJoy.getY(), Robot.m_oi.leftJoy.getX());
			}
		}
		*/
		
		
		/* for test robot
		m_oi.button1.whenPressed(new joystickDrive());
		m_oi.button2.whenReleased(new stopElevator());
		m_oi.button2.whenPressed(new moveDownElevator());
		m_oi.button3.whenPressed(new armUp());
		m_oi.button3.whenReleased(new stopArm());
		m_oi.button4.whenPressed(new prepare());
		m_oi.button5.whenPressed(new gyro());
		*/
		//code for competition robot
		m_oi.button1.whenPressed(new prepareGetCube());
		m_oi.button2.whenPressed(new getCube());
		m_oi.button3.whenPressed(new deliverCube());
		m_oi.button4.whenPressed(new moveUpElevator());
		m_oi.button5.whenPressed(new openArm());
		m_oi.button6.whenPressed(new closeArm());
		m_oi.button7.whenPressed(new armUp());
		m_oi.button8.whenPressed(new armDown());
		m_oi.button10.whenPressed(new moveDownElevator());
		
		//Try whileheld
		//m_oi.button4.whileHeld(new moveUpElevator());
		//m_oi.button7.whileHeld(new armUp());
		//m_oi.button8.whileHeld(new armDown());
		//m_oi.button10.whileHeld(new moveDownElevator());
		
		m_oi.button4.whenReleased(new stopElevator());
		m_oi.button7.whenReleased(new stopArm());
		m_oi.button8.whenReleased(new stopArm());
		m_oi.button10.whenReleased(new stopElevator());
		
	    if (m_oi.leftJoy.getPOV() == 0) {
	    	//camera facing front
	    	Robot.Servo.cameraFront();
	    }
	    
	    if (m_oi.leftJoy.getPOV() == 180) {
	    	//camera facing back
	    	Robot.Servo.cameraBack();
	    }
	    if (m_oi.leftJoy.getPOV() == 90) {
	    	//adjust left elevator up
	  	Robot.elevator.adjustLeftUp();
	    }
	    if (m_oi.leftJoy.getPOV() == 270) {
	    	//adjust left elevator down
		  	Robot.elevator.adjustLeftDown();
		    }
	    if (m_oi.leftJoy.getRawAxis(2) >= 0.8) {
	    	//climbing - set the arm
	    	Robot.arms.EncoderAUp(800);
	    }
	   
	    
	    
		/*
		if (Robot.m_oi.leftJoy.getX() == 0 && Robot.m_oi.leftJoy.getY() == 0) {
			stepAccelerateTimer = 0;
		}
		else {
			if (stepAccelerateTimer < 5) {
				stepAccelerateTimer = stepAccelerateTimer + 1;
				drive.driveArcade(Robot.m_oi.leftJoy.getMagnitude()*stepAccelerateTimer*0.02, Robot.m_oi.leftJoy.getX());
			}
		}
		*/
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
