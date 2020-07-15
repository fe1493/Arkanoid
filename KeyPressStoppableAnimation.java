package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The type Key press stoppable animation.
 */
public class KeyPressStoppableAnimation implements Animation {
   private KeyboardSensor keyboardSensor;
   private String k;
   private Animation ani;
   private boolean stop;
   private boolean keyIsAlreadyPressed;
   private boolean ignore;


    /**
     * Instantiates a new Key press stoppable animation.
     *
     * @param sensor    the sensor
     * @param key       the key
     * @param animation the animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboardSensor = sensor;
        this.k = key;
        this.ani = animation;
        this.keyIsAlreadyPressed = true;
        this.stop = false;
}
    @Override
    public void doOneFrame(DrawSurface d) {
        this.ani.doOneFrame(d);
        //if the key is pressed and the boolean is not pressed
        if (this.keyboardSensor.isPressed(this.k) && !this.keyIsAlreadyPressed) {
                this.stop = true;
            }
        if (!(this.keyboardSensor.isPressed(this.k))) {
            this.keyIsAlreadyPressed = false;
            }
        }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}