package org.chess.chess_pieces;

import org.chess.ChessBoard;

public class Queen extends ChessPiece {
    public Queen(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Королева не может выйти за пределы доски
        if (toLine > 7 || toLine < 0 || toColumn < 0 || toColumn > 7) return false;

        // Королева не может двигаться буквой Г
        if (Math.abs(toLine - line) != (Math.abs(toColumn - column))) {
            if (Math.abs(toLine - line) == 0 || Math.abs(toColumn - column) == 0) {
                return true;
            } else {
                return false;
            }
        }

        // Королева не может переместиться в ту же клетку
        if ((line == toLine) && (column == toColumn)) return false;

        // Королева не может проходить через другие
        if (isObstacleExist(chessBoard, line, column, toLine, toColumn)) return false;

        return true;
    }

    @Override
    public String getSymbol() {
        return "Q";
    }
}
