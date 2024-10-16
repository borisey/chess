package org.chess.chess_pieces;

import org.chess.ChessBoard;

public class Pawn extends ChessPiece {
    public Pawn(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Пешка не может выйти за пределы доски
        if (toLine > 7 || toLine < 0 || toColumn < 0 || toColumn > 7) return false;

        // Пешка может ходить только вперед
        if ((getColor().equals("White")) && toLine <= line) return false;
        if ((getColor().equals("Black")) && line <= toLine) return false;

        // На первом ходу пешка может переместиться на одну или две клетки
        if ((line == 1 && getColor().equals("White")) && (toLine - line > 2)) return false;
        if (line == 6 && getColor().equals("Black") && (line - toLine > 2)) return false;

        // Пешка не может переместиться в ту же клетку
        if ((line == toLine) && (column == toColumn)) return false;

        // Если это не первый ход, то пешка может переместиться только на одну клетку
        if ((line != 1 && getColor().equals("White")) && (toLine - line > 1)) return false;
        if (line != 6 && getColor().equals("Black") && (line - toLine > 1)) return false;

        // Пешка должна ходить только по прямой, если она не съедает другую фигуру
        if (getColor().equals("White")) {
            if (chessBoard.board[toLine][toColumn] == null) {
                if (column != toColumn) return false;
            } else {
                if (Math.abs(toColumn - column) != 1 && toLine - line != 1) return false;
            }
        }
        if (getColor().equals("Black")) {
            if (chessBoard.board[toLine][toColumn] == null) {
                if (column != toColumn) return false;
            } else {
                if (Math.abs(toColumn - column) != 1 && line - toLine != 1) return false;
            }
        }

        // Пешка не может проходить через другие фигуры
        if (isObstacleExist(chessBoard, line, column, toLine, toColumn)) return false;

        return true;
    }

    @Override
    public String getSymbol() {
        return "P";
    }
}
