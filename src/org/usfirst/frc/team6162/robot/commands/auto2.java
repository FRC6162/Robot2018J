package org.usfirst.frc.team6162.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class auto2 extends CommandGroup {
	//Right -> Right Switch
    public auto2() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	addSequential(new gyroD(-14.0));
    	addSequential(new encoder(875));
    	addSequential(new encoderArmUp(1000));
    	addSequential(new openArm());
    	addSequential(new encoderArmDown(1000));
    }
}