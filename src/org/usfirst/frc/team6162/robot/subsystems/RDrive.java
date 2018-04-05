package org.usfirst.frc.team6162.robot.subsystems;

import org.usfirst.frc.team6162.robot.Robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.drive.*;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;

/**
 *
 */
public class RDrive extends Subsystem {

    // Initialize motors (competition robot)
	public final WPI_TalonSRX rightFront = new WPI_TalonSRX(4);
	public final WPI_TalonSRX rightBack = new WPI_TalonSRX(3);
	public final WPI_TalonSRX leftFront = new WPI_TalonSRX(2);
	public final WPI_TalonSRX leftBack = new WPI_TalonSRX(1);	
	
	//Initialize motors (test robot)
	//private final VictorSP rightFront = new VictorSP(0);
	//private final VictorSP rightBack = new VictorSP(1);
	//private final VictorSP leftFront = new VictorSP(2);
	//private final VictorSP leftBack = new VictorSP(3);
	//public final VictorSP motor1 = new VictorSP(4);
	
	// This initializes the Encoders - two encoders at the base
	//Encoder EC1,EC2;
	public final Encoder EC1 = new Encoder(0,1,false,Encoder.EncodingType.k1X); //encoder at the base
	public final Encoder EC2 = new Encoder(2,3,false,Encoder.EncodingType.k1X); //encoder at the base
	
	
	
	//gyro
	public final ADXRS450_Gyro gyro = new ADXRS450_Gyro();
	
	//SpeedControllerGroup for arcade drive
	SpeedControllerGroup motorGroupLeft = new SpeedControllerGroup(leftFront, leftBack);
	SpeedControllerGroup motorGroupRight = new SpeedControllerGroup(rightFront, rightBack);
	DifferentialDrive motorDiffDriveMain =  new DifferentialDrive(motorGroupLeft, motorGroupRight);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
  
    
    }

    /* code for the test robot
    public void EncoderDrive() {
    	if (EC1.getDistance() <= 1000 && EC1.getDistance() >= 0){
    		motor1.set(0.3);
    }
    //else if (EC1.getDistance() <= 2000 && EC1.getDistance() >= 1000) {
    //	motor1.set(0.4);
    //}
    else {
    	motor1.set(0.4);
    }
    }
    */
    /*
    public void gyroDrive() {
   
    double angle = gyro.getAngle();
   
    	if (angle <= 90) {
    		motor1.set(0.4);
    	}
    	//else {
    		//motor1.set(0);
   // 	}
    }
    */
    
    public void driveArcade(double xSpeed, double zRotation) {
    	motorDiffDriveMain.arcadeDrive(xSpeed, zRotation);
    }
    
    public void driveDirect(double left, double right) {
    	runLeft(left);
    	runRight(right);
    }
    
    public void stop() {
    	leftFront.stopMotor();
    	leftBack.stopMotor();
    	rightFront.stopMotor();
    	rightBack.stopMotor();
    }
    
    private void runLeft(double input) { 
    	leftFront.set(input);
    	leftBack.set(input);
    }
    
    private void runRight(double input) {
    	rightFront.set(input);
    	rightBack.set(input);
    	
    }

	public void EncoderDrive(double d1) {
		if (Robot.rdrive.EC1.getDistance() <= d1 && EC1.getDistance() >= 0){
    		Robot.rdrive.driveArcade(0.6, 0);
    }
		
	}
	public void gyroDrive(double angle) {
		if (angle > 0) {
			if (Robot.rdrive.gyro.getAngle() <= angle) {
				Robot.rdrive.driveArcade(0.5, 0.7);
			}
		}
		else if (angle < 0) {
			if (Robot.rdrive.gyro.getAngle() >= angle) {
				Robot.rdrive.driveArcade(0.5, -0.7);
			}
			else {
				Robot.rdrive.driveArcade(0, 0);
			}
		}
		
	}
}
