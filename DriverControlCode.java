package org.firstinspires.ftc.robotcontroller.external.samples;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp (name="DriverControlCode", group="Linear Opmode")

public class DriverControlCode extends LinearOpMode {
    String RFmotor= "put config file name here";
    String RBmotor= "put config file name here";
    String LFmotor= "put config file name here";
    String LBmotor= "put config file name here";
    String Launchmotor= "put config file name here";
    String Hoppermotor= "put config file name here";

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor RFMotor = null;
    private DcMotor LFMotor = null;
    private DcMotor RBMotor = null;
    private DcMotor LBMotor = null;
    private DcMotor Launchmotor = null;
    private DcMotor Hoppermotor = null;


    @Override
    public void runOpMode() {
        RFMotor = hardwareMap.get(DcMotor.class, "RFmotor");
        RBMotor = hardwareMap.get(DcMotor.class, "RBmotor");
        LFMotor = hardwareMap.get(DcMotor.class, "LFmotor");
        LBMotor = hardwareMap.get(DcMotor.class, "LBmotor");
        servoTest = hardwareMap.get(Servo.class, "servoTest");
        Launchmotor=hardwareMap.get(DcMotor.class, "Launchmotor");
        Hoppermotor=hardwareMap.get(DcMotor.class, "Hoppermotor");
        LFMotor.setDirection(DcMotor.Direction.FORWARD);
        LBMotor.setDirection(DcMotor.Direction.FORWARD);
        RFMotor.setDirection(DcMotor.Direction.REVERSE);
        RBMotor.setDirection(DcMotor.Direction.REVERSE);
        Launchmotor.setDirection(DcMotor.Direction.FORWARD);
        Hoppermotor.setDirection(DcMotor.Direction.FORWARD);

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();
        
        double RFPower;
        double RBPower;
        double LFPower;
        double LBPower;
        double Launchpower;
        double Hopperpower;
        
        double max;
        double rot = 0.5;

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            X = -gamepad1.left_stick_x;
            Y = -gamepad1.left_stick_y;
            R =  gamepad1.right_stick_x;
            Launchpower = gamepad1.right_trigger;
            Hopperpower = gamepad1.left_trigger;
            
            max = abs(X) + abs(Y) + rot * abs(R);
            
            max = (max < 1)? 1 : max;

            RFMotor.setPower((-X + Y - R)/max);
            RBMotor.setPower(( X + Y - R)/max);
            LFMotor.setPower(( X + Y + R)/max);
            LBMotor.setPower((-X + Y + R)/max);
            Launchmotor.setPower(Launchpower);
            Hoppermotor.setPower(Hopperpower);

            telemetry.addData("Status", "Running");
            telemetry.update();

        }
    }
}
