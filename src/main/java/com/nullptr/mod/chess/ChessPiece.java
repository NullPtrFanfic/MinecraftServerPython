package com.nullptr.mod.chess;

import com.nullptr.mod.chess.ChessRank;
import com.nullptr.mod.chess.ChessPlayer;

public class ChessPiece {
    public int col;
    public int row;
    public ChessPlayer player;
    public ChessRank rank;

    public ChessPiece(int col, int row, ChessPlayer player, ChessRank rank) {
        this.col = col;
        this.row = row;
        this.player = player;
        this.rank = rank;
    }

    // Дополнительные методы, геттеры и сеттеры могут быть добавлены по необходимости
}
