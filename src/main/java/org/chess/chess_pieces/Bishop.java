package org.chess.chess_pieces;

import org.chess.ChessBoard;

public class Bishop extends ChessPiece {
    public Bishop(String color) {
        super(color);
    }

    @Override
    public String getSymbol() {
        return "B";
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Базовая проверка корректности хода (выход за пределы, переход в ту же клетку)
        if (isBaseMoveIncorrect(line, column, toLine, toColumn)) return false;

        // Слон может двигаться только по диагонали
        if (Math.abs(toLine - line) != Math.abs(toColumn - column)) return false;

        // Слон не может проходить через другие фигуры
        if (isObstacleExist(chessBoard, line, column, toLine, toColumn)) return false;

        return true;
    }
}
