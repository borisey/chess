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
        // Проверяю вышла ли фигура за пределы доски
        if (isPieceMoveOutBoard(toLine, toColumn)) return false;

        // Проверяю, что фигура не перемещается в ту же клетку
        if (isPieceMoveToSamePath(line, column, toLine, toColumn)) return false;

        // Конь может перемещаться вертикально
        if (Math.abs(toLine - line) > 2 || Math.abs(toLine - line) < 1) return false;

        // Конь может перемещаться горизонтально
        if (Math.abs(toColumn - column) > 2 || (Math.abs(toColumn - column) < 1)) return false;

        // Конь должен сменить линию и колонку
        if (Math.abs(line - toLine) <= 0 && Math.abs(toColumn - column) <= 0) return false;

        // Конь не может двигаться по диагонали
        if (Math.abs(line - toLine) == Math.abs(toColumn - column)) return false;

        // Конь не может проходить через другие фигуры
        if (isObstacleExist(chessBoard, line, column, toLine, toColumn)) return false;

        return true;
    }
}
