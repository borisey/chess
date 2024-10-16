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
        // Слон не может выйти за пределы доски
        if (toLine > 7 || toLine < 0 || toColumn < 0 || toColumn > 7) return false;

        // Слон может двигаться только по диагонали
        if (Math.abs(toLine - line) != Math.abs(toColumn - column)) return false;

        // Слон не может переместиться в ту же клетку
        if ((line == toLine) && (column == toColumn)) return false;

        // Слон не может проходить через другие фигуры
        if (isObstacleExist(chessBoard, line, column, toLine, toColumn)) return false;

        return true;
    }
}
