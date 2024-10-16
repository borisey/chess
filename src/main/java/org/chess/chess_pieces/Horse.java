package org.chess.chess_pieces;

import org.chess.ChessBoard;

public class Horse extends ChessPiece {
    public Horse(String color) {
        super(color);
    }

    @Override
    public String getSymbol() {
        return "H";
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Базовая проверка корректности хода (запрет проходить через другие фигуры, запрет выхода за пределы, запрет перехода в ту же клетку)
        if (!isBaseMoveCorrect(chessBoard, line, column, toLine, toColumn)) return false;

        // Конь может перемещаться вертикально
        if (Math.abs(toLine - line) > 2 || Math.abs(toLine - line) < 1) return false;

        // Конь может перемещаться горизонтально
        if (Math.abs(toColumn - column) > 2 || (Math.abs(toColumn - column) < 1)) return false;

        // Конь должен сменить линию и колонку
        if (Math.abs(line - toLine) <= 0 && Math.abs(toColumn - column) <= 0) return false;

        // Конь не может двигаться по диагонали
        if (Math.abs(line - toLine) == Math.abs(toColumn - column)) return false;

        return true;
    }
}
