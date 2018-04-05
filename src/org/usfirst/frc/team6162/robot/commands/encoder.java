package org.usfirst.frc.team6162.robot.commands;

import org.usfirst.frc.team6162.robot.Robot;
import org.usfirst.frc.team6162.robot.subsystems.RDrive;
import org.usfirst.frc.team6162.robot.subsystems.Arms;
import edu.wpi.first.wpilibj.command.Command;


/**
 *
 */
public class encoder extends Command {
	double d2;
	//RDrive drive = new RDrive();	
    public encoder(double d1) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	//requires(Drive);
    	super("encoder");
    	requires(Robot.rdrive);
    	d2 = d1;
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//Robot.rdrive.move();
    	Robot.rdrive.EC1.reset();
    	Robot.rdrive.EC2.reset();
    	Robot.rdrive.EC1.setDistancePerPulse(0.01);
    	Robot.rdrive.EC2.setDistancePerPulse(0.01);
    //Robot.rdrive.gyro.calibrate();
    //	Robot.rdrive.gyro.setSensitivity(0.007);
    	Robot.rdrive.gyro.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    public void execute() {
    	//drive.driveArcade(1,1);
    	//double d1 = 1000.0;
    	Robot.rdrive.EncoderDrive(d2);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    //double d1 = 1000.0;
    	if (Robot.rdrive.EC1.getDistance() <= d2) {
        return false;
    }
    	else {
    		return true;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    //	Robot.rdrive.motor1.set(0);
    	//stop the robot
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
