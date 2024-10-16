package org.chess.chess_pieces;

import org.chess.ChessBoard;
import java.util.ArrayList;

public abstract class ChessPiece {
    public String color;
    public boolean check = true;

    public ChessPiece(String color) {
        this.color = color;
    }

    public String getColor() {
        return this.color;
    }

    public abstract boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn);

    public abstract String getSymbol();

    /**
     * Метод проверяет, существует ли припятствие
     */
    public boolean isObstacleExist(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        ArrayList<ChessPiece> trajectory = new ArrayList<>();

        if (line == toLine) {
            for (int i = column + 1; i < toColumn; i++) {
                trajectory.add(chessBoard.board[line][i]);
            }
        }

        if (column == toColumn) {
            for (int i = line + 1; i < toLine; i++) {
                trajectory.add(chessBoard.board[i][column]);
            }
        }

        if (Math.abs(column - toColumn) == Math.abs(line - toLine)) {
            for (int i = line + 1, k = column + 1; i < toLine; i++, k++) {
                trajectory.add(chessBoard.board[i][k]);
            }
        }

        for (ChessPiece i: trajectory) {
            if (i != null) return true;
        }

        return false;
    }

    /**
     * Метод проверяет вышла ли фигура за пределы доски
     */
    public boolean isPieceMoveOutBoard(int toLine, int toColumn) {
        return (toLine > 7 || toLine < 0 || toColumn < 0 || toColumn > 7);
    }

    /**
     * Метод проверяет что фигура не перемещается в ту же клетку
     */
    public boolean isPieceMoveToSamePath(int line, int column, int toLine, int toColumn) {
        return ((line == toLine) && (column == toColumn));
    }
}
