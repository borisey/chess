package org.chess.chess_pieces;

import org.chess.ChessBoard;

public class Rook extends ChessPiece {
    String color;

    public Rook(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        return false;
    }

    @Override
    public String getSymbol() {
        return "R";
    }
}
