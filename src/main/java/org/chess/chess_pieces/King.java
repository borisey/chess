package org.chess.chess_pieces;

import org.chess.ChessBoard;

public class King extends ChessPiece {
    public King(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Король не может выйти за пределы доски
        if (toLine > 7 || toLine < 0 || toColumn < 0 || toColumn > 7) return false;

        // Король не может переместиться в ту же клетку
        if ((line == toLine) && (column == toColumn)) return false;

        // Король может переместиться в любое поле вокруг себя
        if ((Math.abs(toLine - line) > 1) || (Math.abs(toColumn - column) > 1)) return false;

        return true;
    }

    @Override
    public String getSymbol() {
        return "Q";
    }
}
