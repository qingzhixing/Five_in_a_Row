package starter;

import swing.SwingGameController;

public class SwingStarter {
    public static void main(String[] args) {
        System.out.println("SwingStarter");
        SwingGameController gameController = new SwingGameController();
        gameController.Start();
    }
}