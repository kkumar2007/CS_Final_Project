import javax.swing.*;
import java.awt.*;
    public class Fighter {
        private int health;
        private int strength;

        public Fighter(int health, int strength) {
            this.health = health;
            this.strength = strength;
        }

        public static void main(String[] args) {
            int fighterHealth = 50;
            int fighterStrength = 30;

            Fighter fighter = new Fighter(fighterHealth, fighterStrength);

            JFrame frame = new JFrame("Fighter Health and Strength");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }
    }
