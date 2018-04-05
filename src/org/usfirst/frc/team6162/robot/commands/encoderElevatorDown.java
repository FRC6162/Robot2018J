package org.usfirst.frc.team6162.robot.commands;

import org.usfirst.frc.team6162.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class encoderElevatorDown extends Command {
double d4;
    public encoderElevatorDown(double d3) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	super("encoderElevatorDown");
    	requires(Robot.elevator);
    	d4 = d3;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.elevator.EC3.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.elevator.moveDown();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//double m = 3000.0;
    	if (Robot.elevator.EC3.getDistance() >= -d4) {
    		return false;
    	}
    	else {
    		return true;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.elevator.stopE();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
