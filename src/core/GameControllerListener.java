package core;

import org.jetbrains.annotations.NotNull;

public class GameControllerListener {
    private GameStartHook gameStartHook;
    private GameEndHook gameEndHook;
    private MoveHook moveHook;

    public GameControllerListener(
            @NotNull GameStartHook gameStartHook,
            @NotNull GameEndHook gameEndHook,
            @NotNull MoveHook moveHook
    ) {
        this.gameStartHook = gameStartHook;
        this.gameEndHook = gameEndHook;
        this.moveHook = moveHook;
    }

    public GameControllerListener() {
        this(
                () -> {},
                (winner) -> {},
                (movePlayer,lastMove) -> {}
        );
    }

    public void SetGameStartHook(@NotNull GameStartHook gameStartHook) {
        this.gameStartHook = gameStartHook;
    }

    public void SetGameEndHook(@NotNull GameEndHook gameEndHook) {
        this.gameEndHook = gameEndHook;
    }

    public void SetMoveHook(@NotNull MoveHook moveHook) {
        this.moveHook = moveHook;
    }

    public void OnGameStart() {
        gameStartHook.OnGameStart();
    }

    public void OnGameEnd(ChessPiece winner) {
        gameEndHook.OnGameEnd(winner);
    }

    public void OnMove(ChessPiece movePlayer, ChessBoard.Coordinate lastMove) {
        moveHook.OnMove(movePlayer, lastMove);
    }

    @FunctionalInterface
    public interface GameStartHook {
        void OnGameStart();
    }

    @FunctionalInterface
    public interface GameEndHook {
        void OnGameEnd(ChessPiece winner);
    }

    @FunctionalInterface
    public interface MoveHook {
        void OnMove(ChessPiece movePlayer,ChessBoard.Coordinate lastMove);
    }
}