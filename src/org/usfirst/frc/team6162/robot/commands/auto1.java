package org.usfirst.frc.team6162.robot.commands;

import org.usfirst.frc.team6162.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class auto1 extends Command {

    public auto1() {
    	//cross the line
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	super("auto1");
    	requires(Robot.rdrive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.rdrive.EC1.reset();
    	Robot.rdrive.EC2.reset();
    	Robot.rdrive.EC1.setDistancePerPulse(0.01);
    	Robot.rdrive.EC2.setDistancePerPulse(0.01);
    //Robot.rdrive.gyro.calibrate();
    //Robot.rdrive.gyro.setSensitivity(0.007);
    	Robot.rdrive.gyro.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.rdrive.EncoderDrive(800.0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	 double d1 = 800.0;
     	if (Robot.rdrive.EC1.getDistance() <= d1) {
         return false;
     }
     	else {
     		return true;
     	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.rdrive.driveArcade(0.0, 0.0);
    	//reset the encoders
    	Robot.rdrive.EC1.reset();
    	Robot.rdrive.EC2.reset();
    	Robot.rdrive.gyro.reset();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
