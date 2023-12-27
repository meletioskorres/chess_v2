package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;

import java.util.Collection;
import java.util.Objects;

public abstract class Piece {
    protected final PieceType pieceType;
    protected final int piecePosition;
    protected final Alliance pieceAlliance;
    private final int cachedHashCode;
    protected final boolean isFirstMove;

    Piece(final PieceType pieceType, final int piecePosition, final Alliance pieceAlliance, boolean isFirstMove) {
        this.pieceType = pieceType;
        this.piecePosition = piecePosition;
        this.pieceAlliance = pieceAlliance;
        this.isFirstMove = isFirstMove;
        this.cachedHashCode = computeHashCode();
    }

    private int computeHashCode() {
       return Objects.hash(pieceType, piecePosition, pieceAlliance, isFirstMove);
    }

    public Alliance getPieceAlliance() {
        return pieceAlliance;
    }
    public boolean isFirstMove() {
        return isFirstMove;
    }
    public abstract Collection<Move> calculateLegalMoves(final Board board);
    public abstract Piece movePiece(Move move);
    public Integer getPiecePosition() {
        return this.piecePosition;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return getPiecePosition() == piece.getPiecePosition() && isFirstMove() == piece.isFirstMove()
                && getPieceType() == piece.getPieceType() && getPieceAlliance() == piece.getPieceAlliance();
    }

    @Override
    public int hashCode() {
        return this.cachedHashCode;
    }

    public int getPieceValue() {
        return pieceType.getPieceValue();
    }

    public enum PieceType {
        PAWN("P",100) {
            @Override
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isRook() {
                return false;
            }
        },
        KNIGHT("N",300) {
            @Override
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isRook() {
                return false;
            }
        },
        BISHOP("B",300) {
            @Override
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isRook() {
                return false;
            }
        },
        ROOK("R",500) {
            @Override
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isRook() {
                return true;
            }
        },
        QUEEN("Q",900) {
            @Override
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isRook() {
                return false;
            }
        },
        KING("K",10000) {
            @Override
            public boolean isKing() {
                return true;
            }

            @Override
            public boolean isRook() {
                return false;
            }
        };
        private final String pieceName;
        private final int pieceValue;

        PieceType(String pieceName, int pieceValue) {
            this.pieceName = pieceName;
            this.pieceValue = pieceValue;
        }

        @Override
        public String toString() {
            return this.pieceName;
        }

        public abstract boolean isKing();
        public abstract boolean isRook();
        public int getPieceValue(){
            return this.pieceValue;
        }
    }
}
