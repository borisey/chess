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
    
    public String getAttackPieceColor(ChessBoard chessBoard) {
        if (chessBoard.nowPlayerColor().equals("White")) {
            return "Black";
        }
        
        return "White";
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
        if (isPawnAttack(board, line, column)) return true;

        return false;
    }

    /**
     * Проверяю атаку коня
     */
    public boolean isHorseAttack(ChessBoard board, int line, int column) {
        if (!isPieceMoveOutBoard(line - 2, column - 1) && board.board[line - 2][column - 1] != null && board.board[line - 2][column - 1].getSymbol().equals("H") && board.board[line - 2][column - 1].getColor().equals("White")) return true;
        if (!isPieceMoveOutBoard(line - 2, column + 1) && board.board[line - 2][column + 1] != null && board.board[line - 2][column + 1].getSymbol().equals("H") && board.board[line - 2][column + 1].getColor().equals("White")) return true;
        if (!isPieceMoveOutBoard(line + 2, column - 1) && board.board[line + 2][column - 1] != null && board.board[line + 2][column - 1].getSymbol().equals("H") && board.board[line + 2][column - 1].getColor().equals("White")) return true;
        if (!isPieceMoveOutBoard(line + 2, column + 1) && board.board[line + 2][column + 1] != null && board.board[line + 2][column + 1].getSymbol().equals("H") && board.board[line + 2][column + 1].getColor().equals("White")) return true;
        if (!isPieceMoveOutBoard(line - 1, column - 1) && board.board[line - 1][column - 2] != null && board.board[line - 1][column - 2].getSymbol().equals("H") && board.board[line - 1][column - 2].getColor().equals("White")) return true;
        if (!isPieceMoveOutBoard(line - 1, column + 2) && board.board[line - 1][column + 2] != null && board.board[line - 1][column + 2].getSymbol().equals("H") && board.board[line - 1][column + 2].getColor().equals("White")) return true;
        if (!isPieceMoveOutBoard(line + 1, column - 2) && board.board[line + 1][column - 2] != null && board.board[line + 1][column - 2].getSymbol().equals("H") && board.board[line + 1][column - 2].getColor().equals("White")) return true;
        if (!isPieceMoveOutBoard(line + 1, column + 2) && board.board[line + 1][column + 2] != null && board.board[line + 1][column + 2].getSymbol().equals("H") && board.board[line + 1][column + 2].getColor().equals("White")) return true;

        return false;
    }

    /**
     * Проверяю атаку по диагонали
     */
    public boolean isDiagonalAttack(ChessBoard board, int line, int column) {
        if (isDownLeftAttack(board, line, column)) return true;
        if (isDownRightAttack(board, line, column)) return true;
        if (isUpLeftAttack(board, line, column)) return true;
        if (isUpRightAttack(board, line, column)) return true;

        return false;
    }

    public boolean isDownLeftAttack(ChessBoard board, int line, int column) {
        // Вниз влево
        while (!isPieceMoveOutBoard(line - 1, column - 1)) {
            --line;
            --column;

            if (board.board[line][column] != null) {
                if ((!board.board[line][column].getSymbol().equals("Q") && !board.board[line][column].getSymbol().equals("B"))) {
                    break;
                }

                if (board.board[line][column].getColor().equals("White")) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isDownRightAttack(ChessBoard board, int line, int column) {
        // Вниз и вправо
        while (!isPieceMoveOutBoard(line - 1, column + 1)) {
            --line;
            ++column;

            if (board.board[line][column] != null) {
                if ((!board.board[line][column].getSymbol().equals("Q") && !board.board[line][column].getSymbol().equals("B"))) {
                    break;
                }

                if (board.board[line][column].getColor().equals("White")) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isUpLeftAttack(ChessBoard board, int line, int column) {
        // Вверх и влево
        while (!isPieceMoveOutBoard(line + 1, column - 1)) {
            ++line;
            --column;

            if (board.board[line][column] != null) {
                if ((!board.board[line][column].getSymbol().equals("Q") && !board.board[line][column].getSymbol().equals("B"))) {
                    break;
                }

                if (board.board[line][column].getColor().equals("White")) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isUpRightAttack(ChessBoard board, int line, int column) {
        // Вверх и вправо
        while (!isPieceMoveOutBoard(line + 1, column + 1)) {
            ++line;
            ++column;

            if (board.board[line][column] != null) {
                if ((!board.board[line][column].getSymbol().equals("Q") && !board.board[line][column].getSymbol().equals("B"))) {
                    break;
                }

                if (board.board[line][column].getColor().equals("White")) {
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
        // Вверх
        while (!isPieceMoveOutBoard(line + 1, column)) {
            ++line;

            if (board.board[line][column] != null) {
                // Если на пути другая фигура - выходим из цикла
                if ((!board.board[line][column].getSymbol().equals("Q") && !board.board[line][column].getSymbol().equals("R"))) {
                    break;
                }

                if (board.board[line][column].getColor().equals("White")) {
                    return true;
                }
            }
        }

        // Вниз
        while (!isPieceMoveOutBoard(line - 1, column)) {
            --line;

            if (board.board[line][column] != null) {
                // Если на пути другая фигура - выходим из цикла
                if ((!board.board[line][column].getSymbol().equals("Q") && !board.board[line][column].getSymbol().equals("R"))) {
                    break;
                }

                if (board.board[line][column].getColor().equals("White")) {
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
        // Вправо
        while (!isPieceMoveOutBoard(line, column + 1)) {
            ++column;

            if (board.board[line][column] != null) {
                if ((!board.board[line][column].getSymbol().equals("Q") && !board.board[line][column].getSymbol().equals("R"))) {
                    break;
                }

                if (board.board[line][column].getColor().equals("White")) {
                    return true;
                }
            }
        }

        // Влево
        while (!isPieceMoveOutBoard(line, column - 1)) {
            --column;

            if (board.board[line][column] != null) {
                if ((!board.board[line][column].getSymbol().equals("Q") && !board.board[line][column].getSymbol().equals("R"))) {
                    break;
                }

                if (board.board[line][column].getColor().equals("White")) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Проверяю атаку пешки
     */
    public boolean isPawnAttack(ChessBoard board, int line, int column) {
        if (getColor().equals("Black")) {
            if (!isPieceMoveOutBoard(line - 1, column - 1) && board.board[line - 1][column - 1] != null && board.board[line - 1][column - 1].getSymbol().equals("P") && board.board[line - 1][column - 1].getColor().equals("White")) return true;
            if (!isPieceMoveOutBoard(line - 1, column + 1) && board.board[line - 1][column + 1] != null && board.board[line - 1][column + 1].getSymbol().equals("P") && board.board[line - 1][column + 1].getColor().equals("White")) return true;
        } else {
            if (!isPieceMoveOutBoard(line + 1, column - 1) && board.board[line + 1][column - 1] != null && board.board[line + 1][column - 1].getSymbol().equals("P") && board.board[line + 1][column - 1].getColor().equals("White")) return true;
            if (!isPieceMoveOutBoard(line + 1, column + 1) && board.board[line + 1][column + 1] != null && board.board[line + 1][column + 1].getSymbol().equals("P") && board.board[line + 1][column + 1].getColor().equals("White")) return true;
        }
        return false;
    }
}
