package org.usfirst.frc.team6162.robot.subsystems;

import org.usfirst.frc.team6162.robot.Robot;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class servo extends Subsystem {
	public final Servo servo1 = new Servo(4);

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void cameraFront()
    {
    	Robot.Servo.servo1.set(1.0);
    }
    public void cameraBack()
    {
    	Robot.Servo.servo1.set(-1.0);
    }
}

