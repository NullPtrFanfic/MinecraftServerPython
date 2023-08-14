package com.nullptr.chess

import com.nullptr.chess.ChessRank
import com.nullptr.chess.ChessPlayer

data class ChessPiece(val col: Int, val row: Int, var player: ChessPlayer, val rank: ChessRank) {
	
}