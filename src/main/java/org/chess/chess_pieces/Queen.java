package org.chess.chess_pieces;

import org.chess.ChessBoard;

public class Queen extends ChessPiece {
    public Queen(String color) {
        super(color);
    }

    @Override
    public String getSymbol() {
        return "Q";
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Базовая проверка корректности хода (запрет проходить через другие фигуры, запрет выхода за пределы, запрет перехода в ту же клетку)
        if (!isBaseMoveCorrect(chessBoard, line, column, toLine, toColumn)) return false;

        // Королева не может двигаться буквой Г
        if (Math.abs(toLine - line) != (Math.abs(toColumn - column))) {
            return Math.abs(toLine - line) == 0 || Math.abs(toColumn - column) == 0;
        }

        return true;
    }
}
