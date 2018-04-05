package org.usfirst.frc.team6162.robot.subsystems;

import org.usfirst.frc.team6162.robot.Robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
/**
 *
 */
public class Arms extends Subsystem {
	//Initialize motors for the arm (competition robot)
	private final VictorSP S1 = new VictorSP(2);
	private final VictorSP S2 = new VictorSP(3);
	
	//Initialize goldenArrowHead
	public final Compressor compressor = new Compressor();
	public final Solenoid gAH = new Solenoid(0);
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public final Encoder EC4 = new Encoder(6,7,false,Encoder.EncodingType.k1X); //encoder for the arm
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
   
    public void flipUp() {
    	//for test robot
    	//Robot.rdrive.motor1.set(0.4);
    	//Robot.elevator.servo1.set(1.0);
    	S1.set(0.3);
    	S2.set(0.3);
    
    }
    
    public void flipDown() {
    	S1.set(-0.3);
    	S2.set(-0.3);
    }
    
 	public void stopA(){
		//for test robot 
		//Robot.rdrive.motor1.set(0);
	    S1.set(0);
	    S2.set(0);
		
	    }
 	public void EncoderAUp(double m){
 		//double m = 1000.0;
 		if (Robot.arms.EC4.getDistance() <= m) {
 			S1.set(0.3);
 	    		S2.set(0.3);
 		}
 	}
 	
 	public void EncoderADown(){
 		//double m = 1000.0;
 		if (Robot.arms.EC4.getDistance() >= 0) {
 			S1.set(-0.3);
 	    		S2.set(-0.3);
 		}
 	}
    public void Open(){
    gAH.set(false);
    }
    public void Close(){
    gAH.set(true);
    }
    
    }
    
    


