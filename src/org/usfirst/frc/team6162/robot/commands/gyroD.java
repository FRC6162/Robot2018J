package org.usfirst.frc.team6162.robot.commands;

import org.usfirst.frc.team6162.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class gyroD extends Command {
double a2;
    public gyroD(double a1) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	super("gyroD");
    	requires(Robot.rdrive);
    	a2 = a1;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.rdrive.gyro.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		Robot.rdrive.gyroDrive(a2);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    //	double a1 = -14.0;
    	if (Robot.rdrive.gyro.getAngle() >= a2)
    	{
    		return false;
	}
    	else {
    		return true;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.rdrive.driveArcade(0.0, 0.0);
    	Robot.rdrive.gyro.reset();
    	Robot.rdrive.EC1.reset();
    	Robot.rdrive.EC2.reset();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
