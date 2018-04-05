package org.usfirst.frc.team6162.robot.commands;

import org.usfirst.frc.team6162.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class encoderArmDown extends Command {
double m2;
    public encoderArmDown(double m1) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	super("encoderArmDown");
    	requires(Robot.arms);
    	m2 = m1;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
     	Robot.arms.EC4.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.arms.flipDown();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    //	double m = 1000.0;
    	if (Robot.arms.EC4.getDistance() >= -m2)
    	{
    		return false;
    	}
    	else {
    		return true;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.arms.stopA();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
