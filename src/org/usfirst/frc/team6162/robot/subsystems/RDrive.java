package org.usfirst.frc.team6162.robot.subsystems;

//import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.drive.*;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.Encoder;

/**
 *
 */
public class RDrive extends Subsystem {

    // Initialize motors (competition robot)
	//private final WPI_TalonSRX rightFront = new WPI_TalonSRX(1);
	//private final WPI_TalonSRX rightBack = new WPI_TalonSRX(2);
	//private final WPI_TalonSRX leftFront = new WPI_TalonSRX(3);
	//private final WPI_TalonSRX leftBack = new WPI_TalonSRX(4);	
	
	//Initialize motors (test robot)
	private final VictorSP rightFront = new VictorSP(0);
	private final VictorSP rightBack = new VictorSP(1);
	private final VictorSP leftFront = new VictorSP(2);
	private final VictorSP leftBack = new VictorSP(3);
	private final VictorSP motor1 = new VictorSP(4);
	// This initializes the Encoders - left and right, attached to gearbox
	//Encoder EC1,EC2,EC3,EC4;
	private final Encoder EC1 = new Encoder(0,1,false,Encoder.EncodingType.k1X);
			//EC2 = new Encoder(2,3,false,Encoder.EncodingType.k1X); 
	    //Encoder for the elevator and shoulder
			//EC3= new Encoder(4,5,false,Encoder.EncodingType.k1X);  //This sets the encoder for the elevator.
			//EC4 = new Encoder(6,7,false,Encoder.EncodingType.k1X);  //This sets the encoder for the arm.
			//EC2.reset();
	
	//SpeedControllerGroup for arcade drive
	SpeedControllerGroup motorGroupLeft = new SpeedControllerGroup(leftFront, leftBack);
	SpeedControllerGroup motorGroupRight = new SpeedControllerGroup(rightFront, rightBack);
	DifferentialDrive motorDiffDriveMain =  new DifferentialDrive(motorGroupLeft, motorGroupRight);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
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
    
    public void EncoderDrive() {
    	if (EC1.getDistance() <= 1000 && EC1.getDistance() >= 0){
    		motor1.set(0.6);
    	
    }
    else if (EC1.getDistance() <= 2000 && EC1.getDistance() >= 1000) {
    	motor1.set(0.3);
    }
    else {
    	motor1.set(0);
    }
    
    }
}
