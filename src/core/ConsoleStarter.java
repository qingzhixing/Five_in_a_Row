package core;

public class ConsoleStarter {
    public static void main(String[] args) {
        ConsoleGameController gameController = new ConsoleGameController(6);
        gameController.Start();
    }
}
