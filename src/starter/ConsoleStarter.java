package starter;

import core.ConsoleGameController;

public class ConsoleStarter {
    public static void main(String[] args) {
        ConsoleGameController gameController = new ConsoleGameController(9);
        gameController.Start();
    }
}
