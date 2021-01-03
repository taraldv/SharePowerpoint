/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package powerpoint;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author
 */
public class KeySender {

    private final Robot robot;

    public KeySender(Robot robot) {
        this.robot = robot;
    }

    public void sendKey(String key) {
        //System.out.println("Pressing key: " + key);
        if (key.contains("up")) {
            //Same as left, presentation backwards
            pressKey(KeyEvent.VK_UP);
        } else if (key.contains("down")) {
            //Same as right, presentation forwards
            pressKey(KeyEvent.VK_DOWN);
        } else if (key.contains("left")) {
            //Same as down, presentation backwards
            pressKey(KeyEvent.VK_LEFT);
        } else if (key.contains("right")) {
            //Same as up, presentation forwards
            pressKey(KeyEvent.VK_RIGHT);
        } else if (key.contains("escape")) {
            //Escape is leave presentation
            pressKey(KeyEvent.VK_ESCAPE);
        } else if (key.contains("f5")) {
            //F5 is start from beginning
            pressKey(KeyEvent.VK_F5);
        }
    }

    private void pressKey(int keyCode) {
        int min = 30;
        int max = 60;
        robot.delay(tilfeldigInt(min, max));
        robot.keyPress(keyCode);
        robot.delay(tilfeldigInt(min, max));
        robot.keyRelease(keyCode);
        robot.delay(tilfeldigInt(min, max));
    }

    private int tilfeldigInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }

}
