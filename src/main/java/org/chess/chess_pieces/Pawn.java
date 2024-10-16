package org.chess.chess_pieces;

import org.chess.ChessBoard;

public class Pawn extends ChessPiece {
    public Pawn(String color) {
        super(color);
    }

    @Override
    public String getSymbol() {
        return "P";
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Базовая проверка корректности хода (запрет проходить через другие фигуры, запрет выхода за пределы, запрет перехода в ту же клетку)
        if (!isBaseMoveCorrect(chessBoard, line, column, toLine, toColumn)) return false;

        // Пешка может ходить только вперед
        if ((getColor().equals("White")) && toLine <= line) return false;
        if ((getColor().equals("Black")) && line <= toLine) return false;

        // На первом ходу пешка может переместиться на одну или две клетки
        if ((line == 1 && getColor().equals("White")) && (toLine - line > 2)) return false;
        if (line == 6 && getColor().equals("Black") && (line - toLine > 2)) return false;

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
                return column == toColumn;
            } else {
                return Math.abs(toColumn - column) == 1 || line - toLine == 1;
            }
        }

        return true;
    }
}
