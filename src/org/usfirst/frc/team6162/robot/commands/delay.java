package org.usfirst.frc.team6162.robot.commands;

import org.usfirst.frc.team6162.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class delay extends Command {
double startTime;
    public delay() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	super("delay");
    	requires(Robot.arms);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startTime = System.currentTimeMillis(); 
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (System.currentTimeMillis() - startTime <= 2){
    		Robot.arms.stopA();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (System.currentTimeMillis() - startTime <= 2){
        return false;
    }
    	else {
    		return true;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
