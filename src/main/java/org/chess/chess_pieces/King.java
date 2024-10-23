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

    public boolean isUnderAttack(ChessBoard board, int toLine, int toColumn) {
        if (isHorseAttack(board, toLine, toColumn)) return true;
        if (isDiagonalAttack(board, toLine, toColumn)) return true;
        if (isVerticalAttack(board, toLine, toColumn)) return true;
        if (isHorizontalAttack(board, toLine, toColumn)) return true;
        if (isPawnAttack(board, toLine, toColumn)) return true;

        return false;
    }

    /**
     * Проверяю атаку коня
     */
    public boolean isHorseAttack(ChessBoard board, int toLine, int toColumn) {
        if (!isPieceMoveOutBoard(toLine - 2, toColumn - 1) && board.board[toLine - 2][toColumn - 1] != null && board.board[toLine - 2][toColumn - 1].getSymbol().equals("H") && board.board[toLine - 2][toColumn - 1].getColor().equals("White")) return true;
        if (!isPieceMoveOutBoard(toLine - 2, toColumn + 1) && board.board[toLine - 2][toColumn + 1] != null && board.board[toLine - 2][toColumn + 1].getSymbol().equals("H") && board.board[toLine - 2][toColumn + 1].getColor().equals("White")) return true;
        if (!isPieceMoveOutBoard(toLine + 2, toColumn - 1) && board.board[toLine + 2][toColumn - 1] != null && board.board[toLine + 2][toColumn - 1].getSymbol().equals("H") && board.board[toLine + 2][toColumn - 1].getColor().equals("White")) return true;
        if (!isPieceMoveOutBoard(toLine + 2, toColumn + 1) && board.board[toLine + 2][toColumn + 1] != null && board.board[toLine + 2][toColumn + 1].getSymbol().equals("H") && board.board[toLine + 2][toColumn + 1].getColor().equals("White")) return true;
        if (!isPieceMoveOutBoard(toLine - 1, toColumn - 1) && board.board[toLine - 1][toColumn - 2] != null && board.board[toLine - 1][toColumn - 2].getSymbol().equals("H") && board.board[toLine - 1][toColumn - 2].getColor().equals("White")) return true;
        if (!isPieceMoveOutBoard(toLine - 1, toColumn + 2) && board.board[toLine - 1][toColumn + 2] != null && board.board[toLine - 1][toColumn + 2].getSymbol().equals("H") && board.board[toLine - 1][toColumn + 2].getColor().equals("White")) return true;
        if (!isPieceMoveOutBoard(toLine + 1, toColumn - 2) && board.board[toLine + 1][toColumn - 2] != null && board.board[toLine + 1][toColumn - 2].getSymbol().equals("H") && board.board[toLine + 1][toColumn - 2].getColor().equals("White")) return true;
        if (!isPieceMoveOutBoard(toLine + 1, toColumn + 2) && board.board[toLine + 1][toColumn + 2] != null && board.board[toLine + 1][toColumn + 2].getSymbol().equals("H") && board.board[toLine + 1][toColumn + 2].getColor().equals("White")) return true;

        return false;
    }

    /**
     * Проверяю атаку по диагонали
     */
    public boolean isDiagonalAttack(ChessBoard board, int toLine, int toColumn) {
        if (isDownLeftAttack(board, toLine, toColumn)) return true;
        if (isDownRightAttack(board, toLine, toColumn)) return true;
        if (isUpLeftAttack(board, toLine, toColumn)) return true;
        if (isUpRightAttack(board, toLine, toColumn)) return true;

        return false;
    }

    public boolean isDownLeftAttack(ChessBoard board, int toLine, int toColumn) {
        // Вниз влево
        while (!isPieceMoveOutBoard(toLine - 1, toColumn - 1)) {
            --toLine;
            --toColumn;

            if (board.board[toLine][toColumn] != null) {
                if ((!board.board[toLine][toColumn].getSymbol().equals("Q") || !board.board[toLine][toColumn].getSymbol().equals("B"))) {
                    break;
                }

                if (board.board[toLine][toColumn].getColor().equals("White")) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isDownRightAttack(ChessBoard board, int toLine, int toColumn) {
        // Вниз и вправо
        while (!isPieceMoveOutBoard(toLine - 1, toColumn + 1)) {
            --toLine;
            ++toColumn;

            if (board.board[toLine][toColumn] != null) {
                if ((!board.board[toLine][toColumn].getSymbol().equals("Q") || !board.board[toLine][toColumn].getSymbol().equals("B"))) {
                    break;
                }

                if (board.board[toLine][toColumn].getColor().equals("White")) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isUpLeftAttack(ChessBoard board, int toLine, int toColumn) {
        // Вверх и влево
        while (!isPieceMoveOutBoard(toLine + 1, toColumn - 1)) {
            ++toLine;
            --toColumn;

            if (board.board[toLine][toColumn] != null) {
                if ((!board.board[toLine][toColumn].getSymbol().equals("Q") || !board.board[toLine][toColumn].getSymbol().equals("B"))) {
                    break;
                }

                if (board.board[toLine][toColumn].getColor().equals("White")) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isUpRightAttack(ChessBoard board, int toLine, int toColumn) {
        // Вверх и вправо
        while (!isPieceMoveOutBoard(toLine + 1, toColumn + 1)) {
            ++toLine;
            ++toColumn;

            if (board.board[toLine][toColumn] != null) {
                if ((!board.board[toLine][toColumn].getSymbol().equals("Q") || !board.board[toLine][toColumn].getSymbol().equals("B"))) {
                    break;
                }

                if (board.board[toLine][toColumn].getColor().equals("White")) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Проверяю атаку по вертикали
     */
    public boolean isVerticalAttack(ChessBoard board, int toLine, int toColumn) {
        // Вверх
        while (!isPieceMoveOutBoard(toLine + 1, toColumn)) {
            ++toLine;

            if (board.board[toLine][toColumn] != null) {
                // Если на пути другая фигура - выходим из цикла
                if ((!board.board[toLine][toColumn].getSymbol().equals("Q") || !board.board[toLine][toColumn].getSymbol().equals("R"))) {
                    break;
                }

                if (board.board[toLine][toColumn].getColor().equals("White")) {
                    return true;
                }
            }
        }

        // Вниз
        while (!isPieceMoveOutBoard(toLine - 1, toColumn)) {
            --toLine;

            if (board.board[toLine][toColumn] != null) {
                System.out.println(toLine);
                System.out.println(toColumn);
                System.out.println(board.board[toLine][toColumn]);
                // Если на пути другая фигура - выходим из цикла
//                if ((!board.board[toLine][toColumn].getSymbol().equals("Q"))) {
//                    System.out.println("Exit");
//                    break;
//                }

                if (board.board[toLine][toColumn].getSymbol().equals("Q") && board.board[toLine][toColumn].getColor().equals("White")) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Проверяю атаку по горизонтали
     */
    public boolean isHorizontalAttack(ChessBoard board, int toLine, int toColumn) {
        int counter = 0;

        // Вправо
        while (!isPieceMoveOutBoard(toLine, toColumn + 1)) {
            ++toColumn;

            if (board.board[toLine][toColumn] != null) {
                if ((!board.board[toLine][toColumn].getSymbol().equals("Q") || !board.board[toLine][toColumn].getSymbol().equals("R"))) {
                    break;
                }

                if (board.board[toLine][toColumn].getColor().equals("White")) {
                    return true;
                }
            }
        }

        // Влево
        while (!isPieceMoveOutBoard(toLine, toColumn - 1)) {
            ++counter;
            --toColumn;

            if (counter == 1) {
                continue;
            }

            if (board.board[toLine][toColumn] != null) {
                if ((!board.board[toLine][toColumn].getSymbol().equals("Q") || !board.board[toLine][toColumn].getSymbol().equals("R"))) {
                    break;
                }

                if (board.board[toLine][toColumn].getColor().equals("White")) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Проверяю атаку пешки
     */
    public boolean isPawnAttack(ChessBoard board, int toLine, int toColumn) {
        if (getColor().equals("Black")) {
            if (!isPieceMoveOutBoard(toLine - 1, toColumn - 1) && board.board[toLine - 1][toColumn - 1] != null && board.board[toLine - 1][toColumn - 1].getSymbol().equals("P") && board.board[toLine - 1][toColumn - 1].getColor().equals("White")) return true;
            if (!isPieceMoveOutBoard(toLine - 1, toColumn + 1) && board.board[toLine - 1][toColumn + 1] != null && board.board[toLine - 1][toColumn + 1].getSymbol().equals("P") && board.board[toLine - 1][toColumn + 1].getColor().equals("White")) return true;
        } else {
            if (!isPieceMoveOutBoard(toLine + 1, toColumn - 1) && board.board[toLine + 1][toColumn - 1] != null && board.board[toLine + 1][toColumn - 1].getSymbol().equals("P") && board.board[toLine + 1][toColumn - 1].getColor().equals("White")) return true;
            if (!isPieceMoveOutBoard(toLine + 1, toColumn + 1) && board.board[toLine + 1][toColumn + 1] != null && board.board[toLine + 1][toColumn + 1].getSymbol().equals("P") && board.board[toLine + 1][toColumn + 1].getColor().equals("White")) return true;
        }
        return false;
    }
}
