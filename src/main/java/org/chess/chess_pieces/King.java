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
        if (isHorseAttack(board, line, column)) return true;
        if (isDiagonalAttack(board, line, column)) return true;
        if (isVerticalAttack(board, line, column)) return true;
        if (isHorizontalAttack(board, line, column)) return true;

        return false;
    }

    /**
     * Проверяю атаку коня
     */
    public boolean isHorseAttack(ChessBoard board, int line, int column) {
        String colorAttackPiece = "";
        if (getColor().equals("Black")) {
            colorAttackPiece = "White";
        } else {
            colorAttackPiece = "Black";
        }

        if (!isPieceMoveOutBoard(line - 2, column - 1) && board.board[line - 2][column - 1] != null && board.board[line - 2][column - 1].getSymbol().equals("H") && board.board[line - 2][column - 1].getColor().equals(colorAttackPiece)) return true;
        if (!isPieceMoveOutBoard(line - 2, column + 1) && board.board[line - 2][column + 1] != null && board.board[line - 2][column + 1].getSymbol().equals("H") && board.board[line - 2][column + 1].getColor().equals(colorAttackPiece)) return true;
        if (!isPieceMoveOutBoard(line + 2, column - 1) && board.board[line + 2][column - 1] != null && board.board[line + 2][column - 1].getSymbol().equals("H") && board.board[line + 2][column - 1].getColor().equals(colorAttackPiece)) return true;
        if (!isPieceMoveOutBoard(line + 2, column + 1) && board.board[line + 2][column + 1] != null && board.board[line + 2][column + 1].getSymbol().equals("H") && board.board[line + 2][column + 1].getColor().equals(colorAttackPiece)) return true;
        if (!isPieceMoveOutBoard(line - 1, column - 1) && board.board[line - 1][column - 2] != null && board.board[line - 1][column - 2].getSymbol().equals("H") && board.board[line - 1][column - 2].getColor().equals(colorAttackPiece)) return true;
        if (!isPieceMoveOutBoard(line - 1, column + 2) && board.board[line - 1][column + 2] != null && board.board[line - 1][column + 2].getSymbol().equals("H") && board.board[line - 1][column + 2].getColor().equals(colorAttackPiece)) return true;
        if (!isPieceMoveOutBoard(line + 1, column - 2) && board.board[line + 1][column - 2] != null && board.board[line + 1][column - 2].getSymbol().equals("H") && board.board[line + 1][column - 2].getColor().equals(colorAttackPiece)) return true;
        if (!isPieceMoveOutBoard(line + 1, column + 2) && board.board[line + 1][column + 2] != null && board.board[line + 1][column + 2].getSymbol().equals("H") && board.board[line + 1][column + 2].getColor().equals(colorAttackPiece)) return true;

        return false;
    }

    /**
     * Проверяю атаку по диагонали
     */
    public boolean isDiagonalAttack(ChessBoard board, int line, int column) {
        String colorAttackPiece = "";
        if (getColor().equals("Black")) {
            colorAttackPiece = "White";
        } else {
            colorAttackPiece = "Black";
        }

        // Вниз влево
        while (!isPieceMoveOutBoard(line - 1, column - 1)) {
            --line;
            --column;

            if (board.board[line][column] != null && (!board.board[line][column].getSymbol().equals("Q") || !board.board[line][column].getSymbol().equals("B"))) {
                if ((board.board[line][column].getSymbol().equals("Q") || board.board[line][column].getSymbol().equals("B")) && board.board[line][column].getColor().equals(colorAttackPiece)) {
                    return true;
                }
            }
        }

        // Вниз и вправо
        while (!isPieceMoveOutBoard(line - 1, column + 1)) {
            --line;
            ++column;

            if (board.board[line][column] != null && (!board.board[line][column].getSymbol().equals("Q") || !board.board[line][column].getSymbol().equals("B"))) {
                if ((board.board[line][column].getSymbol().equals("Q") || board.board[line][column].getSymbol().equals("B")) && board.board[line][column].getColor().equals(colorAttackPiece)) {
                    return true;
                }
            }
        }

        // Вверх и влево
        while (!isPieceMoveOutBoard(line + 1, column - 1)) {
            ++line;
            --column;

            if (board.board[line][column] != null && (!board.board[line][column].getSymbol().equals("Q") || !board.board[line][column].getSymbol().equals("B"))) {
                if ((board.board[line][column].getSymbol().equals("Q") || board.board[line][column].getSymbol().equals("B")) && board.board[line][column].getColor().equals(colorAttackPiece)) {
                    return true;
                }
            }
        }

        // Вверх и вправо
        while (!isPieceMoveOutBoard(line + 1, column + 1)) {
            ++line;
            ++column;

            if (board.board[line][column] != null && (!board.board[line][column].getSymbol().equals("Q") || !board.board[line][column].getSymbol().equals("B"))) {
                if ((board.board[line][column].getSymbol().equals("Q") || board.board[line][column].getSymbol().equals("B")) && board.board[line][column].getColor().equals(colorAttackPiece)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Проверяю атаку по вертикали
     */
    public boolean isVerticalAttack(ChessBoard board, int line, int column) {
        String colorAttackPiece = "";
        if (getColor().equals("Black")) {
            colorAttackPiece = "White";
        } else {
            colorAttackPiece = "Black";
        }

        // Вверх
        while (!isPieceMoveOutBoard(line + 1, column)) {
            ++line;

            if (board.board[line][column] != null && (!board.board[line][column].getSymbol().equals("Q") || !board.board[line][column].getSymbol().equals("R"))) {
                if ((board.board[line][column].getSymbol().equals("Q") || board.board[line][column].getSymbol().equals("R")) && board.board[line][column].getColor().equals(colorAttackPiece)) {
                    return true;
                }
            }
        }

        // Вниз
        while (!isPieceMoveOutBoard(line - 1, column)) {
            --line;

            if (board.board[line][column] != null && (!board.board[line][column].getSymbol().equals("Q") || !board.board[line][column].getSymbol().equals("R"))) {
                if ((board.board[line][column].getSymbol().equals("Q") || board.board[line][column].getSymbol().equals("R")) && board.board[line][column].getColor().equals(colorAttackPiece)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Проверяю атаку по горизонтали
     */
    public boolean isHorizontalAttack(ChessBoard board, int line, int column) {
        String colorAttackPiece = "";
        if (getColor().equals("Black")) {
            colorAttackPiece = "White";
        } else {
            colorAttackPiece = "Black";
        }

        // Вправо
        while (!isPieceMoveOutBoard(line, column + 1)) {
            ++column;

            if (board.board[line][column] != null && (!board.board[line][column].getSymbol().equals("Q") || !board.board[line][column].getSymbol().equals("R"))) {
                if ((board.board[line][column].getSymbol().equals("Q") || board.board[line][column].getSymbol().equals("R")) && board.board[line][column].getColor().equals(colorAttackPiece)) {
                    return true;
                }
            }
        }

        // Влево
        while (!isPieceMoveOutBoard(line, column - 1)) {
            --column;

            if (board.board[line][column] != null && (!board.board[line][column].getSymbol().equals("Q") || !board.board[line][column].getSymbol().equals("R"))) {
                if ((board.board[line][column].getSymbol().equals("Q") || board.board[line][column].getSymbol().equals("R")) && board.board[line][column].getColor().equals(colorAttackPiece)) {
                    return true;
                }
            }
        }

        return false;
    }
}
