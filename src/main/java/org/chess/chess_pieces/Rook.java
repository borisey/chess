package org.chess.chess_pieces;

import org.chess.ChessBoard;

public class Rook extends ChessPiece {
    public Rook(String color) {
        super(color);
    }

    @Override
    public String getSymbol() {
        return "R";
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Ладья не может выйти за пределы доски
        if (toLine > 7 || toLine < 0 || toColumn < 0 || toColumn > 7) return false;

        // Ладья перемещаться только по прямой
        if (Math.abs(toColumn-column) > 0 && Math.abs(toLine-line) > 0) return false;

        // Ладья не может переместиться в ту же клетку
        if ((line == toLine) && (column == toColumn)) return false;

        // Ладья не может проходить через другие фигуры
        if (isObstacleExist(chessBoard, line, column, toLine, toColumn)) return false;

        return true;
    }
}
