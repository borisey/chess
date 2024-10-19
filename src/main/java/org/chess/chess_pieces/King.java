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
            // WHITE KING
            if (getColor().equals("White")) {
                //horse - work
                if (board.board[line + 2][column - 1] != null && board.board[line + 2][column - 1].getSymbol().equals("H")
                        && board.board[line + 2][column - 1].getColor().equals("Black")) return true;
                if (board.board[line + 2][column + 1] != null && board.board[line + 2][column + 1].getSymbol().equals("H")
                        && board.board[line + 2][column + 1].getColor().equals("Black")) return true;
                if (board.board[line + 1][column + 2] != null && board.board[line + 1][column + 2].getSymbol().equals("H")
                        && board.board[line + 1][column + 2].getColor().equals("Black")) return true;
                if (board.board[line + 1][column - 2] != null && board.board[line + 1][column - 2].getSymbol().equals("H")
                        && board.board[line + 1][column - 2].getColor().equals("Black")) return true;

                //if king protected by own pawns
                if (board.board[line + 1][column - 2] != null && board.board[line + 1][column - 2].getSymbol().equals("P")
                        && board.board[line + 1][column - 2].getColor().equals("White")
                        &&
                        board.board[line + 1][column - 1] != null && board.board[line + 1][column - 1].getSymbol().equals("P")
                        && board.board[line + 1][column - 1].getColor().equals("White")
                        &&
                        board.board[line + 1][column] != null && board.board[line + 1][column].getSymbol().equals("P")
                        && board.board[line + 1][column].getColor().equals("White")
                        &&
                        board.board[line + 1][column + 1] != null && board.board[line + 1][column + 1].getSymbol().equals("P")
                        && board.board[line + 1][column + 1].getColor().equals("White")) {
                    return false;
                }

                //rook or queen - work
                if (board.board[line + 1][column] == null || (board.board[line + 1][column] != null && (board.board[line + 1][column].getSymbol().equals("Q") || board.board[line + 1][column].getSymbol().equals("R")) &&
                        (board.board[line + 1][column].getColor().equals("Black")))) {
                    boolean queenOrRook = false;
                    for (int i = 1; i < 8; i++) {
                        if (board.board[i][column] != null &&
                                (board.board[i][column].getSymbol().equals("Q") || board.board[i][column].getSymbol().equals("R")) &&
                                (board.board[i][column].getColor().equals("Black"))) queenOrRook = true;
                    }
                    if (queenOrRook) return true;
                }

                //bishop or queen - work
                if (board.board[line + 1][column - 1] != null &&
                        (board.board[line + 1][column - 1].getSymbol().equals("Q") || board.board[line + 1][column - 1].getSymbol().equals("B")) &&
                        board.board[line + 1][column - 1].getColor().equals("Black")) return true;
                if (board.board[line + 2][column - 2] != null && board.board[line + 1][column - 1] == null &&
                        (board.board[line + 2][column - 2].getSymbol().equals("Q") || board.board[line + 2][column - 2].getSymbol().equals("B")) &&
                        board.board[line + 2][column - 2].getColor().equals("Black")) return true;
                if (board.board[line + 1][column + 1] != null &&
                        (board.board[line + 1][column + 1].getSymbol().equals("Q") || board.board[line + 1][column + 1].getSymbol().equals("B")) &&
                        board.board[line + 1][column + 1].getColor().equals("Black")) return true;
                if (board.board[line + 1][column + 1] == null) {
                    boolean queenOrBishop = false;
                    for (int i = 1; i < 7; i++) {
                        for (int k = 3; k < 8; k++) {
                            if (board.board[i][k] != null &&
                                    (board.board[i][k].getSymbol().equals("Q") || board.board[i][k].getSymbol().equals("B")) &&
                                    board.board[i][k].getColor().equals("Black")) queenOrBishop = true;
                        }
                        if (queenOrBishop) return true;
                    }
                }
                //pawns - work
                if (board.board[line + 1][column - 1] != null &&
                        board.board[line + 1][column - 1].getSymbol().equals("P") &&
                        board.board[line + 1][column - 1].getColor().equals("Black")
                ) return true;
                if (board.board[line + 1][column + 1] != null &&
                        board.board[line + 1][column + 1].getSymbol().equals("P") &&
                        board.board[line + 1][column + 1].getColor().equals("Black")
                ) return true;
            }


            // BLACK KING
            if (getColor().equals("Black")) {
                //horse - work
                if (board.board[line - 2][column - 1] != null && board.board[line - 2][column - 1].getSymbol().equals("H")
                        && board.board[line - 2][column - 1].getColor().equals("White")) return true;
                if (board.board[line - 2][column + 1] != null && board.board[line - 2][column + 1].getSymbol().equals("H")
                        && board.board[line - 2][column + 1].getColor().equals("White")) return true;
                if (board.board[line - 1][column - 2] != null && board.board[line - 1][column - 2].getSymbol().equals("H")
                        && board.board[line - 1][column - 2].getColor().equals("White")) return true;

                //if king protected by own pawns
                if (board.board[line - 1][column - 2] != null && board.board[line - 1][column - 2].getSymbol().equals("P")
                        && board.board[line - 1][column - 2].getColor().equals("Black")
                        &&
                        board.board[line - 1][column - 1] != null && board.board[line - 1][column - 1].getSymbol().equals("P")
                        && board.board[line - 1][column - 1].getColor().equals("Black")
                        &&
                        board.board[line - 1][column] != null && board.board[line - 1][column].getSymbol().equals("P")
                        && board.board[line - 1][column].getColor().equals("Black")
                        &&
                        board.board[line - 1][column + 1] != null && board.board[line - 1][column + 1].getSymbol().equals("P")
                        && board.board[line - 1][column + 1].getColor().equals("Black")) return false;

                //rook or queen - work
                if (board.board[line - 1][column] == null || ((board.board[line - 1][column].getSymbol().equals("Q") || board.board[line - 1][column].getSymbol().equals("R")) &&
                        (board.board[line - 1][column].getColor().equals("White")))) {
                    boolean queenOrRook = false;
                    for (int i = 6; i >= 0; i--) {
                        if (board.board[i][column] != null &&
                                (board.board[i][column].getSymbol().equals("Q") || board.board[i][column].getSymbol().equals("R")) &&
                                (board.board[i][column].getColor().equals("White"))) queenOrRook = true;
                    }
                    if (queenOrRook) return true;
                }

                //bishop or queen - work
                if (board.board[line - 1][column - 1] != null &&
                        (board.board[line - 1][column - 1].getSymbol().equals("Q") || board.board[line - 1][column - 1].getSymbol().equals("B")) &&
                        board.board[line - 1][column - 1].getColor().equals("White")) return true;
                if (board.board[line - 2][column - 2] != null && board.board[line - 1][column - 1] == null &&
                        (board.board[line - 2][column - 2].getSymbol().equals("Q") || board.board[line - 2][column - 2].getSymbol().equals("B")) &&
                        board.board[line - 2][column - 2].getColor().equals("White")) return true;
                if (board.board[line - 1][column + 1] != null &&
                        (board.board[line - 1][column + 1].getSymbol().equals("Q") || board.board[line - 1][column + 1].getSymbol().equals("B")) &&
                        board.board[line - 1][column + 1].getColor().equals("White")) return true;
                if (board.board[line - 1][column + 1] == null) {
                    boolean queenOrBishop = false;
                    for (int i = 6; i >= 0; i--) {
                        for (int k = 3; k < 8; k++) {
                            if (board.board[i][k] != null &&
                                    (board.board[i][k].getSymbol().equals("Q") || board.board[i][k].getSymbol().equals("B")) &&
                                    board.board[i][k].getColor().equals("White")) queenOrBishop = true;
                        }
                        if (queenOrBishop) return true;
                    }
                }
                //pawns -work
                if (board.board[line - 1][column - 1] != null &&
                        board.board[line - 1][column - 1].getSymbol().equals("P") &&
                        board.board[line - 1][column - 1].getColor().equals("White")
                ) return true;
                if (board.board[line - 1][column + 1] != null &&
                        board.board[line - 1][column + 1].getSymbol().equals("P") &&
                        board.board[line - 1][column + 1].getColor().equals("White")
                ) return true;
            }

            return false;
        }


        if (board.castling7) {
            if (getColor().equals("White")) {
                // WHITE KING
                //horse - work
                if (board.board[line + 2][column - 1] != null && board.board[line + 2][column - 1].getSymbol().equals("H")
                        && board.board[line + 2][column - 1].getColor().equals("Black")) return true;
                if (board.board[line + 2][column + 1] != null && board.board[line + 2][column + 1].getSymbol().equals("H")
                        && board.board[line + 2][column + 1].getColor().equals("Black")) return true;
                if (board.board[line + 1][column - 2] != null && board.board[line + 1][column - 2].getSymbol().equals("H")
                        && board.board[line + 1][column - 2].getColor().equals("Black")) return true;

                //if king protected by own pawns - ?
                if (board.board[line + 1][column - 1] != null && board.board[line + 1][column - 1].getSymbol().equals("P")
                        && board.board[line + 1][column - 1].getColor().equals("White")
                        &&
                        board.board[line + 1][column] != null && board.board[line + 1][column].getSymbol().equals("P")
                        && board.board[line + 1][column].getColor().equals("White")
                        &&
                        board.board[line + 1][column + 1] != null && board.board[line + 1][column + 1].getSymbol().equals("P")
                        && board.board[line + 1][column + 1].getColor().equals("White")) {
                    return false;
                }

                //rook or queen - work
                if (board.board[line + 1][column] == null ||((board.board[line + 1][column].getSymbol().equals("Q") || board.board[line + 1][column].getSymbol().equals("R")) &&
                        (board.board[line + 1][column].getColor().equals("Black")))) {
                    boolean queenOrRook = false;
                    for (int i = 2; i < 8; i++) {
                        if (board.board[i][column] != null &&
                                (board.board[i][column].getSymbol().equals("Q") || board.board[i][column].getSymbol().equals("R")) &&
                                (board.board[i][column].getColor().equals("Black"))) queenOrRook = true;
                    }
                    if (queenOrRook) return true;
                }

                //bishop or queen -
                if (board.board[line + 1][column + 1] != null &&
                        (board.board[line + 1][column + 1].getSymbol().equals("Q") || board.board[line + 1][column + 1].getSymbol().equals("B")) &&
                        board.board[line + 1][column + 1].getColor().equals("Black")) return true;
                if (board.board[line + 1][column - 1] == null) {
                    boolean queenOrBishop = false;
                    for (int i = 1; i < 7; i++) {
                        for (int k = 6; k >= 0; k--) {
                            if (board.board[i][k] != null &&
                                    (board.board[i][k].getSymbol().equals("Q") || board.board[i][k].getSymbol().equals("B")) &&
                                    board.board[i][k].getColor().equals("Black")) queenOrBishop = true;
                        }
                        if (queenOrBishop) return true;
                    }
                }

                //pawns - work
                if (board.board[line + 1][column - 1] != null &&
                        board.board[line + 1][column - 1].getSymbol().equals("P") &&
                        board.board[line + 1][column - 1].getColor().equals("Black")
                ) return true;
                if (board.board[line + 1][column + 1] != null &&
                        board.board[line + 1][column + 1].getSymbol().equals("P") &&
                        board.board[line + 1][column + 1].getColor().equals("Black")
                ) return true;
            }
        }
        
        if (isHorseAttack(board, line, column)) return true;
        if (isDiagonalAttack(board, line, column)) return true;


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

        // Проверяю по диагонали вниз влево
        while (!isPieceMoveOutBoard(line - 1, column - 1)) {
            --line;
            --column;

            if (board.board[line][column] != null && (!board.board[line][column].getSymbol().equals("Q") || !board.board[line][column].getSymbol().equals("B"))) {
                if ((board.board[line][column].getSymbol().equals("Q") || board.board[line][column].getSymbol().equals("B")) && board.board[line][column].getColor().equals(colorAttackPiece)) {
                    return true;
                }
            }
        }

        // Проверяю по диагонали вниз и вправо
        while (!isPieceMoveOutBoard(line - 1, column + 1)) {
            --line;
            ++column;

            if (board.board[line][column] != null && (!board.board[line][column].getSymbol().equals("Q") || !board.board[line][column].getSymbol().equals("B"))) {
                if ((board.board[line][column].getSymbol().equals("Q") || board.board[line][column].getSymbol().equals("B")) && board.board[line][column].getColor().equals(colorAttackPiece)) {
                    return true;
                }
            }
        }

        // Проверяю по диагонали вверх и влево
        while (!isPieceMoveOutBoard(line + 1, column - 1)) {
            ++line;
            --column;

            if (board.board[line][column] != null && (!board.board[line][column].getSymbol().equals("Q") || !board.board[line][column].getSymbol().equals("B"))) {
                if ((board.board[line][column].getSymbol().equals("Q") || board.board[line][column].getSymbol().equals("B")) && board.board[line][column].getColor().equals(colorAttackPiece)) {
                    return true;
                }
            }
        }

        // Проверяю по диагонали вверх и вправо
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


}
