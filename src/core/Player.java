package core;

public abstract class Player {

    public String name;

    Player(String name) {
        this.name = name;
    }

    Player() {
        this("Unnamed core.Player");
    }
}
