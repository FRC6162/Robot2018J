package org.usfirst.frc.team6162.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class auto8 extends CommandGroup {
	//Left -> Left Scale
    public auto8() {
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
    	addSequential(new encoder(1135));
    	addSequential(new gyroD(28));
    	addSequential(new encoder(721));
    	addSequential(new encoderArmUp(1000));
    	addSequential(new openArm());
    	addSequential(new encoderArmDown(1000));
    }
}
