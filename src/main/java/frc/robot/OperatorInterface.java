package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.AdjustSpeedCommand;
import frc.robot.commands.ChangeGearCommand;
import frc.robot.commands.PanelClutchCommand;
import frc.robot.commands.ReverseCommand;
import frc.robot.commands.ToggleGyroscopeCommand;
import frc.robot.commands.VisionCommand;
import frc.robot.helpers.Map;

/**
 * Operator Interface class, used for defining the joysticks and buttons used to control the robot.
 */
public class OperatorInterface {
  /**
   * Joystick used to drive the robot.
   */
  public static final Joystick mainStick = new Joystick(Map.MAIN_JOYSTICK_INDEX);

  /**
   * Xbox controller used to operate the robot's mechanisms.
   */
  public static final XboxController xbox = new XboxController(Map.XBOX_INDEX);

  /**
   * Button for reversing the direction the robot is driving.
   */
  private static final Button reverseDirection =
      new JoystickButton(mainStick, Map.REVERSE_DIRECTION);

  /**
   * Button for change between high and low gears.
   */
  private static final Button gearShift = new JoystickButton(mainStick, Map.SHIFT_BUTTON);

  /**
   * Button for opening the ball intake mechanism.
   */
  private static final Button openBallIntake =
      new JoystickButton(xbox, Map.BALL_INTAKE_OPEN_BUTTON);

  /**
   * Button for closing the ball intake mechanism.
   */
  private static final Button closeBallIntake =
      new JoystickButton(xbox, Map.BALL_INTAKE_CLOSE_BUTTON);

  /**
   * Button for incrementing the speed.
   */
  private static final Button incrementSpeed =
      new JoystickButton(mainStick, Map.INCREMENT_SPEED_BUTTON);

  /**
   * Button for decrementing the speed.
   */
  private static final Button decrementSpeed =
      new JoystickButton(mainStick, Map.DECREMENT_SPEED_BUTTON);

  /**
   * Button for disabling the gyroscope.
   */
  private static final Button toggleGyroscope =
      new JoystickButton(mainStick, Map.TOGGLE_GYROSCOPE_BUTTON);

  private static final Button visionAssist =
      new JoystickButton(mainStick, Map.VISION_ASSIST_BUTTON);

  /**
   * Method used to initialize the commands controlled by the joystick buttons.
   */
  public static void initialize() {
    reverseDirection.whenPressed(new ReverseCommand());

    gearShift.whenPressed(new ChangeGearCommand());

    openBallIntake.whenPressed(new PanelClutchCommand(PanelClutchCommand.Direction.Open));

    closeBallIntake.whenPressed(new PanelClutchCommand(PanelClutchCommand.Direction.Close));

    incrementSpeed.whenPressed(new AdjustSpeedCommand(true));

    decrementSpeed.whenPressed(new AdjustSpeedCommand(false));

    toggleGyroscope.whenPressed(new ToggleGyroscopeCommand());

    visionAssist.whileHeld(new VisionCommand());
  }
}
