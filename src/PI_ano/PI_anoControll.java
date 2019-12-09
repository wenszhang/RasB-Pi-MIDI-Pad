package PI_ano;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;

public class PI_anoControll {

    private int velocity = 50;

    public PI_anoControll() {
        JFrame frame = new JFrame("Key Listener");

        Container contentPane = frame.getContentPane();

        PI_ano PIano = new PI_ano();
        Timer timer = new Timer();
        timer.schedule(PIano, 0, 50);


        KeyListener listener = new KeyListener() {

            @Override
            public void keyPressed(KeyEvent event) {
                keyboardLocation(event.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent event) {
                //keyboardLocation(event.getKeyCode());
            }

            @Override
            public void keyTyped(KeyEvent event) {
                //keyboardLocation(event.getKeyCode());
            }

            private String keyboardLocation(int keybrd) {
                if (keybrd == KeyEvent.VK_ESCAPE) {
                    System.exit(0);
                }
                if (keybrd == KeyEvent.VK_COMMA) {
                    PIano.changeInstrument(-1);
                }
                if (keybrd == KeyEvent.VK_PERIOD) {
                    PIano.changeInstrument(1);
                }
                if (keybrd == KeyEvent.VK_ENTER) {
                    velocity++;
                }
                if (keybrd == KeyEvent.VK_QUOTE) {
                    velocity--;
                }
                if (velocity < 0) {
                    velocity = 0;
                } else if (velocity > 100) {
                    velocity = 100;
                }
                if (0 <= keybrd && keybrd < 128) {
                    PIano.playNote(keybrd, velocity);
                }
                return "";

            }
        };

        JTextField textField = new JTextField();

        textField.addKeyListener(listener);

        contentPane.add(textField, BorderLayout.NORTH);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String args[]) {
        new PI_anoControll();
    }
}
