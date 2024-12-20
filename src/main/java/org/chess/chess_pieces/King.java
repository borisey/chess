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
        if (!isPieceMoveOutBoard(toLine - 2, toColumn - 1) && board.board[toLine - 2][toColumn - 1] != null && board.board[toLine - 2][toColumn - 1].getSymbol().equals("H") && board.board[toLine - 2][toColumn - 1].getColor().equals(getAttackPieceColor(board))) return true;
        if (!isPieceMoveOutBoard(toLine - 2, toColumn + 1) && board.board[toLine - 2][toColumn + 1] != null && board.board[toLine - 2][toColumn + 1].getSymbol().equals("H") && board.board[toLine - 2][toColumn + 1].getColor().equals(getAttackPieceColor(board))) return true;
        if (!isPieceMoveOutBoard(toLine + 2, toColumn - 1) && board.board[toLine + 2][toColumn - 1] != null && board.board[toLine + 2][toColumn - 1].getSymbol().equals("H") && board.board[toLine + 2][toColumn - 1].getColor().equals(getAttackPieceColor(board))) return true;
        if (!isPieceMoveOutBoard(toLine + 2, toColumn + 1) && board.board[toLine + 2][toColumn + 1] != null && board.board[toLine + 2][toColumn + 1].getSymbol().equals("H") && board.board[toLine + 2][toColumn + 1].getColor().equals(getAttackPieceColor(board))) return true;
        if (!isPieceMoveOutBoard(toLine - 1, toColumn - 1) && board.board[toLine - 1][toColumn - 2] != null && board.board[toLine - 1][toColumn - 2].getSymbol().equals("H") && board.board[toLine - 1][toColumn - 2].getColor().equals(getAttackPieceColor(board))) return true;
        if (!isPieceMoveOutBoard(toLine - 1, toColumn + 2) && board.board[toLine - 1][toColumn + 2] != null && board.board[toLine - 1][toColumn + 2].getSymbol().equals("H") && board.board[toLine - 1][toColumn + 2].getColor().equals(getAttackPieceColor(board))) return true;
        if (!isPieceMoveOutBoard(toLine + 1, toColumn - 2) && board.board[toLine + 1][toColumn - 2] != null && board.board[toLine + 1][toColumn - 2].getSymbol().equals("H") && board.board[toLine + 1][toColumn - 2].getColor().equals(getAttackPieceColor(board))) return true;
        if (!isPieceMoveOutBoard(toLine + 1, toColumn + 2) && board.board[toLine + 1][toColumn + 2] != null && board.board[toLine + 1][toColumn + 2].getSymbol().equals("H") && board.board[toLine + 1][toColumn + 2].getColor().equals(getAttackPieceColor(board))) return true;

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
                if (!board.board[toLine][toColumn].getSymbol().equals("Q")
                        && !board.board[toLine][toColumn].getSymbol().equals("B")
                        && (!board.board[toLine][toColumn].getSymbol().equals("K") && !board.board[toLine][toColumn].getColor().equals(board.nowPlayerColor()))
                ) {
                    break;
                }

                if (board.board[toLine][toColumn].getColor().equals(getAttackPieceColor(board))) {
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
                if (!board.board[toLine][toColumn].getSymbol().equals("Q")
                        && !board.board[toLine][toColumn].getSymbol().equals("B")
                        && (!board.board[toLine][toColumn].getSymbol().equals("K") && !board.board[toLine][toColumn].getColor().equals(board.nowPlayerColor()))
                ) {
                    break;
                }

                if (board.board[toLine][toColumn].getColor().equals(getAttackPieceColor(board))) {
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
                if (!board.board[toLine][toColumn].getSymbol().equals("Q")
                        && !board.board[toLine][toColumn].getSymbol().equals("B")
                        && (!board.board[toLine][toColumn].getSymbol().equals("K") && !board.board[toLine][toColumn].getColor().equals(board.nowPlayerColor()))
                ) {
                    break;
                }

                if (board.board[toLine][toColumn].getColor().equals(getAttackPieceColor(board))) {
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
                if (!board.board[toLine][toColumn].getSymbol().equals("Q")
                        && !board.board[toLine][toColumn].getSymbol().equals("B")
                        && (!board.board[toLine][toColumn].getSymbol().equals("K") && !board.board[toLine][toColumn].getColor().equals(board.nowPlayerColor()))
                ) {
                    break;
                }

                if (board.board[toLine][toColumn].getColor().equals(getAttackPieceColor(board))) {
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
        int checkedLine   = toLine;
        int checkedColumn = toColumn;

        // Вверх
        while (!isPieceMoveOutBoard(checkedLine + 1, checkedColumn)) {
            ++checkedLine;

            if (board.board[checkedLine][checkedColumn] != null) {
                if (!board.board[checkedLine][checkedColumn].getSymbol().equals("Q")
                        && !board.board[checkedLine][checkedColumn].getSymbol().equals("R")
                        && (!board.board[checkedLine][checkedColumn].getSymbol().equals("K") && !board.board[checkedLine][checkedColumn].getColor().equals(board.nowPlayerColor()))
                ) {
                    break;
                }

                if (board.board[checkedLine][checkedColumn].getColor().equals(getAttackPieceColor(board))
                    && checkedLine != toLine  // Король должен иметь возможность съесть угрожающую фигуру
                ) {
                    return true;
                }
            }
        }

        // Вниз
        while (!isPieceMoveOutBoard(checkedLine - 1, checkedColumn)) {
            --checkedLine;

            if (board.board[checkedLine][checkedColumn] != null) {
                if (!board.board[checkedLine][checkedColumn].getSymbol().equals("Q")
                        && !board.board[checkedLine][checkedColumn].getSymbol().equals("R")
                        && (!board.board[checkedLine][checkedColumn].getSymbol().equals("K") && !board.board[checkedLine][checkedColumn].getColor().equals(board.nowPlayerColor()))
                ) {
                    break;
                }

                if (board.board[checkedLine][checkedColumn].getColor().equals(getAttackPieceColor(board))
                        && checkedLine != toLine // Король должен иметь возможность съесть угрожающую фигуру
                ) {
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
        int checkedColumn = toColumn;
        int checkedLine   = toLine;

        // Вправо
        while (!isPieceMoveOutBoard(checkedLine, checkedColumn + 1)) {
            ++checkedColumn;

            if (board.board[checkedLine][checkedColumn] != null) {
                if (!board.board[checkedLine][checkedColumn].getSymbol().equals("Q")
                        && !board.board[checkedLine][checkedColumn].getSymbol().equals("R")
                        && (!board.board[checkedLine][checkedColumn].getSymbol().equals("K") && !board.board[checkedLine][checkedColumn].getColor().equals(board.nowPlayerColor()))
                ) {
                    break;
                }

                if (board.board[checkedLine][checkedColumn].getColor().equals(getAttackPieceColor(board))
                        && checkedColumn != toColumn  // Король должен иметь возможность съесть угрожающую фигуру
                ) {
                    return true;
                }
            }
        }

        // Влево
        while (!isPieceMoveOutBoard(checkedLine, checkedColumn - 1)) {
            --checkedColumn;

            if (board.board[checkedLine][checkedColumn] != null) {
                if (!board.board[checkedLine][checkedColumn].getSymbol().equals("Q")
                        && !board.board[checkedLine][checkedColumn].getSymbol().equals("R")
                        && (!board.board[checkedLine][checkedColumn].getSymbol().equals("K") && !board.board[checkedLine][checkedColumn].getColor().equals(board.nowPlayerColor()))
                ) {
                    break;
                }

                if (board.board[checkedLine][checkedColumn].getColor().equals(getAttackPieceColor(board))
                        && checkedColumn != toColumn  // Король должен иметь возможность съесть угрожающую фигуру
                ) {
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
            if (!isPieceMoveOutBoard(toLine - 1, toColumn - 1) && board.board[toLine - 1][toColumn - 1] != null && board.board[toLine - 1][toColumn - 1].getSymbol().equals("P") && board.board[toLine - 1][toColumn - 1].getColor().equals(getAttackPieceColor(board))) return true;
            if (!isPieceMoveOutBoard(toLine - 1, toColumn + 1) && board.board[toLine - 1][toColumn + 1] != null && board.board[toLine - 1][toColumn + 1].getSymbol().equals("P") && board.board[toLine - 1][toColumn + 1].getColor().equals(getAttackPieceColor(board))) return true;
        } else {
            if (!isPieceMoveOutBoard(toLine + 1, toColumn - 1) && board.board[toLine + 1][toColumn - 1] != null && board.board[toLine + 1][toColumn - 1].getSymbol().equals("P") && board.board[toLine + 1][toColumn - 1].getColor().equals(getAttackPieceColor(board))) return true;
            if (!isPieceMoveOutBoard(toLine + 1, toColumn + 1) && board.board[toLine + 1][toColumn + 1] != null && board.board[toLine + 1][toColumn + 1].getSymbol().equals("P") && board.board[toLine + 1][toColumn + 1].getColor().equals(getAttackPieceColor(board))) return true;
        }
        return false;
    }
}
