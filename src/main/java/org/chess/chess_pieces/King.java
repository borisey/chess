package org.chess.chess_pieces;

import org.chess.ChessBoard;

public class King extends ChessPiece {
    public King(String color) {
        super(color);
    }

    @Override
    public String getSymbol() {
        return "K";
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Базовая проверка корректности хода (запрет проходить через другие фигуры, запрет выхода за пределы, запрет перехода в ту же клетку)
        if (!isBaseMoveCorrect(chessBoard, line, column, toLine, toColumn)) return false;

        if (isUnderAttack(chessBoard, toLine, toColumn)) return false;

        // Король может переместиться в любое поле вокруг себя
        return (Math.abs(toLine - line) <= 1) && (Math.abs(toColumn - column) <= 1);
    }

    public boolean isUnderAttack(ChessBoard board, int line, int column) {
        if (board.castling0) {
            return checkIsUnderAttack(board, line, column);
        }

        if (board.castling7) {
            return checkIsUnderAttack(board, line, column);
        }

        return checkIsUnderAttack(board, line, column);
    }

    private boolean checkIsUnderAttack(ChessBoard board, int line, int column) {
        if (getColor().equals("White")) {
            String attackPieceColor = "Black";

            // Проверяю, есть ли угроза от черного коня
            if (isHorseAttack(board, line, column, attackPieceColor)) return true;

            // Проверяю, есть ли угроза от черной пешки
            if (isPawnAttack(board, line, column, attackPieceColor)) return true;

            // Проверяю, есть ли угроза по диагонали
            if (isPawnAttack(board, line, column, attackPieceColor)) return true;

            // Проверяю, есть ли угроза по вертикали

            // Проверяю, есть ли угроза по горизонтали
        }

        if (getColor().equals("Black")) {
            String attackPieceColor = "White";

            // Проверяю, есть ли угроза от белого коня
            if (isHorseAttack(board, line, column, attackPieceColor)) return true;

            // Проверяю, есть ли угроза от белой пешки
            if (isPawnAttack(board, line, column, attackPieceColor)) return true;
        }

        return false;
    }

    public boolean isHorseAttack(ChessBoard board, int line, int column, String color) {
        if (board.board[line + 2][column - 1] != null
                && board.board[line + 2][column - 1].getSymbol().equals("H")
                && board.board[line + 2][column - 1].getColor().equals(color)
        ) return true;

        if (board.board[line + 2][column + 1] != null
                && board.board[line + 2][column + 1].getSymbol().equals("H")
                && board.board[line + 2][column + 1].getColor().equals(color)
        ) return true;

        if (board.board[line - 2][column - 1] != null
                && board.board[line - 2][column - 1].getSymbol().equals("H")
                && board.board[line - 2][column - 1].getColor().equals(color)
        ) return true;

        if (board.board[line - 2][column + 1] != null
                && board.board[line - 2][column + 1].getSymbol().equals("H")
                && board.board[line - 2][column + 1].getColor().equals(color)
        ) return true;

        if (board.board[line + 1][column - 2] != null
                && board.board[line + 1][column - 2].getSymbol().equals("H")
                && board.board[line + 1][column - 2].getColor().equals(color)
        ) return true;

        if (board.board[line + 1][column + 2] != null
                && board.board[line + 1][column + 2].getSymbol().equals("H")
                && board.board[line + 1][column + 2].getColor().equals(color)
        ) return true;

        if (board.board[line - 1][column - 2] != null
                && board.board[line - 1][column - 2].getSymbol().equals("H")
                && board.board[line - 1][column - 2].getColor().equals(color)
        ) return true;

        if (board.board[line - 1][column + 2] != null
                && board.board[line - 1][column + 2].getSymbol().equals("H")
                && board.board[line - 1][column + 2].getColor().equals(color)
        ) return true;

        return false;
    }

    public boolean isPawnAttack(ChessBoard board, int line, int column, String color) {
        // Есть ли угроза от белой пешки
        if (color.equals("White")) {
            if (board.board[line - 1][column - 1] != null
                    && board.board[line - 1][column - 1].getSymbol().equals("P")
                    && board.board[line - 1][column - 1].getColor().equals("White")
            ) return true;
            if (board.board[line - 1][column + 1] != null
                    && board.board[line - 1][column + 1].getSymbol().equals("P")
                    && board.board[line - 1][column + 1].getColor().equals("White")
            ) return true;
        }

        // Есть ли угроза от черной пешки
        if (color.equals("Black")) {
            if (board.board[line + 1][column - 1] != null
                    && board.board[line + 1][column - 1].getSymbol().equals("P")
                    && board.board[line + 1][column - 1].getColor().equals("Black")
            ) return true;
            if (board.board[line + 1][column + 1] != null
                    && board.board[line + 1][column + 1].getSymbol().equals("P")
                    && board.board[line + 1][column + 1].getColor().equals("Black")
            ) return true;
        }

        return false;
    }

    public boolean isDiagonalAttack(ChessBoard board, int line, int column, String color) {

        return false;

    }
}
