package org.usfirst.frc.team6162.robot.subsystems;

import org.usfirst.frc.team6162.robot.Robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class Elevator extends Subsystem {
	//for test robot
	//public final Servo servo1 = new Servo(5);
	
	//Initialize motors for the elevator (competition robot)
	private final VictorSP E1 = new VictorSP(0);
	private final VictorSP E2 = new VictorSP(1);
	
	public final Encoder EC3 = new Encoder(4,5,false,Encoder.EncodingType.k1X); //encoder for the elevator
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void moveUp() {
    	E1.set(-0.6);
    	E2.set(0.6);
    }
   
	public void moveDown() {
		//for test robot
		//Robot.rdrive.motor1.set(-0.3);
    E1.set(0.6);
    E2.set(-0.6);
		
    	}
	public void stopE(){
		//for test robot
		//Robot.rdrive.motor1.set(0);
    	E1.set(0);
    	E2.set(0);
	
    	}
	public void encoderDown() {
		double m = 3000.0;
	if (Robot.elevator.EC3.getDistance() >= -m) {
			E1.set(0.6);
			E2.set(-0.6);
		}
	}
	public void encoderUp() {
		//double m = 3000.0;
	if (Robot.elevator.EC3.getDistance() <= 0) {
			E1.set(-0.6);
			E2.set(0.6);
		}
	}
	public void adjustLeftUp() {
		E1.set(0.3);
	}
	public void adjustLeftDown() {
		E1.set(-0.3);
	}
    	
}

