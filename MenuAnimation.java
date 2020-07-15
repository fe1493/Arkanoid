package menu;

import animation.AnimationRunner;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import levelreader.SelectionInfo;


import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Menu animation.
 *
 * @param <T> the type parameter
 */
public class MenuAnimation<T> implements Menu {

    private String title;
    private List<SelectionInfo> selections;
    private KeyboardSensor sensor;
    private boolean stop;
    private T task;
    private AnimationRunner r;
    private List<String> keyList, messageList;
    private List<Task> taskList;

    /**
     * The constructor.
     *
     * @param sensor the keyboard sensor.
     * @param title  the name of the menu.
     * @param r      the animation runner.
     */
    public MenuAnimation(KeyboardSensor sensor, String title, AnimationRunner r) {
        this.sensor = sensor;
        this.title = title;
        this.taskList = new ArrayList<>();
        this.selections = new ArrayList<>();
        this.stop = false;
        this.task = null;
        this.r = r;
        this.keyList = new ArrayList<>();
        this.messageList = new ArrayList<>();

    }

    /**
     * Adds selection options to the menu.
     * <p/>
     *
     * @param key       the key to wait for
     * @param message   the message to display
     * @param returnValue the return value
     * @param subMenu   the sub menu.
     */

    @Override
    public void addSelection(String key, String message, Object returnValue, Menu subMenu) {
        this.selections.add(new SelectionInfo(key, message, returnValue, null));


    }

    /**
     * Return the status of the menu.
     * <p/>
     *
     * @return T
     */
    @Override
    public T getStatus() {
        this.stop = false;
        return this.task;
    }

    /**
     * Sets a stop.
     *
     * @param stp a boolean.
     */
    @Override
    public void setStop(boolean stp) {
        this.stop = stp;
    }

    /**
     * Adds a sub menu to the selections options.
     *
     * @param key       a string key.
     * @param message   a message.
     * @param returnValue the value returned from the sub menu.
     * @param subMenu   a submenu.
     */
    @Override
    public void addSubMenu(String key, String message, Object returnValue, Menu subMenu) {
            this.selections.add(new SelectionInfo(key, message, returnValue, subMenu));
        }


    /**
     * Rest method.
     */
    public void rest() {
        this.stop = false;
        this.task = null;
    }

    /**
     * Displays the animation.
     * <p/>
     *
     * @param d  is the draw surface.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(new Color(88, 213, 73));
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.YELLOW);
        d.drawText(50, 70, "Arkanoid", 60);
        d.setColor(Color.blue);
        int y = 130;
        for (SelectionInfo selection : this.selections) {
            d.drawText(100, y, selection.toString(), 30);
            y += 40;
        }

        for (SelectionInfo selection : this.selections) {
            if (this.sensor.isPressed(selection.getKey())) {
                if (selection.getSubMenu() == null) {
                    this.stop = true;
                    this.task = (T) selection.getReturnVal();
                } else {
                    // Opens the sub menu if selected.
                    this.r.run(selection.getSubMenu());
                    // Makes the sub menu work.
                    this.task = (T) selection.getSubMenu().getStatus();
                    this.stop = true;
                    // Brings everything back to normal.
                    selection.getSubMenu().rest();
                }
            }
        }
    }

    /**
     * When to stop.
     * <p/>
     *
     * @return if it reached zero.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}