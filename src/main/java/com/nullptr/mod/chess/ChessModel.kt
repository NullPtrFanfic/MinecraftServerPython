package com.nullptr.mod.chess

import com.nullptr.mod.chess.ChessRank
import com.nullptr.mod.chess.ChessPiece
import com.nullptr.mod.chess.ChessPlayer
import com.nullptr.mod.chess.ChessView

public class ChessModel {
	public var IMAGES: MutableMap<String, Bitmap?> = mutableMapOf()
	public var piecesBox = mutableSetOf<ChessPiece>()
	public val availableMoves = mutableListOf<List<Pair<Int, Int>>>() // Массив доступных ходов
	init {
        loadImages(context)
        GAMECANVAS = CANVAS
        GAMEPAINT = PAINT
        Log.d("ChessModel", "Init")
		reset()
		var strboard = toString()
        Log.d("ChessModel", strboard)
		getAvailableMoves()
    }
	
    public fun getAvailableMoves() {
        // Очистка массива доступных ходов
        availableMoves.clear()
        var strboard = toString()
        val rows = strboard.trimIndent().split("\n").map { it.trim() }
        val board = Array(8) { i ->
        val brow = rows[i].split(" ")
        Array(8) { j ->
        brow[j]
        }
        }
		Log.d("ChessModel", board.contentDeepToString())
		
        for (rowIndex in board.indices) {
        for (colIndex in board[rowIndex].indices) {
        val piece = board[rowIndex][colIndex]
        val color = piece[0] // первый символ - цвет фигуры
        val type = piece[1] // второй символ - тип фигуры
        
        when (color) {
            'b' -> {
                when (type) {
                    'R' -> {
                        // обработка черного ладьи
						val directions = listOf(Pair(-1, 0), Pair(0, -1), Pair(1, 0), Pair(0, 1)) 
// diagonals: up/left, up/right, down/right, down/left
                        for (direction in directions) {
                             for (i in 0..7) {  // Change the loop range to start from 1 instead of 0
                                  val end_row = rowIndex + direction.first * i
                                  val end_col = colIndex + direction.second * i
                                  if (end_row in 0..7 && end_col in 0..7) {  // Use 'in' keyword to check range
                                      val end_piece = board[end_row][end_col]
                                      if (end_piece == "eM") {  // empty space is valid
                                          availableMoves.add(listOf(Pair(rowIndex, colIndex), Pair(end_row, end_col)))
                                      } else if (end_piece[0] == 'w') { // capture enemy piece
                                        availableMoves.add(listOf(Pair(rowIndex, colIndex), Pair(end_row, end_col)))
                                      }
                                  }
                             }
                        }
						drawPieces("bR", colIndex, rowIndex)
                    }
                    'K' -> {
                        // обработка черного короля
                    }
                    'B' -> {
                        // обработка черного слона
						val directions = listOf(Pair(-1, -1), Pair(-1, 1), Pair(1, 1), Pair(1, -1))
// diagonals: up/left, up/right, down/right, down/left
                        for (direction in directions) {
                             for (i in 0..7) {  // Change the loop range to start from 1 instead of 0
                                  val end_row = rowIndex + direction.first * i
                                  val end_col = colIndex + direction.second * i
                                  if (end_row in 0..7 && end_col in 0..7) {  // Use 'in' keyword to check range
                                      val end_piece = board[end_row][end_col]
                                      if (end_piece == "eM") {  // empty space is valid
                                          availableMoves.add(listOf(Pair(rowIndex, colIndex), Pair(end_row, end_col)))
                                      } else if (end_piece[0] == 'w') { // capture enemy piece
                                        availableMoves.add(listOf(Pair(rowIndex, colIndex), Pair(end_row, end_col)))
                                      }
                                  }
                             }
                        }
						drawPieces("bB", colIndex, rowIndex)
                    }
                    'Q' -> {
                        // обработка черной королевы
                    }
                    'H' -> {
                        // обработка черного коня
                    }
                    'P' -> {
                        // обработка черной пешки
                        if (rowIndex + 1 < board.size && board[rowIndex + 1][colIndex] == "eM") {
							if (rowIndex==1) {
								Log.d("ChessModel", "bP avaible moves - ${rowIndex + 2} $colIndex")
								availableMoves.add(listOf(Pair(rowIndex, colIndex), Pair(rowIndex + 2, colIndex)))
							}
                            Log.d("ChessModel", "bP avaible moves - ${rowIndex + 1} $colIndex")
							availableMoves.add(listOf(Pair(rowIndex, colIndex), Pair(rowIndex + 1, colIndex)))
                        }
                    }
                }
            }
            'w' -> {
                when (type) {
                    'R' -> {
                        // обработка белой ладьи
						val directions = listOf(Pair(-1, 0), Pair(0, -1), Pair(1, 0), Pair(0, 1)) 
// diagonals: up/left, up/right, down/right, down/left
                        for (direction in directions) {
                             for (i in 0..7) {  // Change the loop range to start from 1 instead of 0
                                  val end_row = rowIndex + direction.first * i
                                  val end_col = colIndex + direction.second * i
                                  if (end_row in 0..7 && end_col in 0..7) {  // Use 'in' keyword to check range
                                      val end_piece = board[end_row][end_col]
                                      if (end_piece == "eM") {  // empty space is valid
                                          availableMoves.add(listOf(Pair(rowIndex, colIndex), Pair(end_row, end_col)))
                                      } else if (end_piece[0] == 'b') { // capture enemy piece
                                        availableMoves.add(listOf(Pair(rowIndex, colIndex), Pair(end_row, end_col)))
                                      }
                                  }
                             }
                        }
						drawPieces("wR", colIndex, rowIndex)
                    }
                    'K' -> {
                        // обработка белого короля
                    }
                    'B' -> {
                        // обработка белого слона
						val directions = listOf(Pair(-1, -1), Pair(-1, 1), Pair(1, 1), Pair(1, -1))
// diagonals: up/left, up/right, down/right, down/left
                        for (direction in directions) {
                             for (i in 0..7) {  // Change the loop range to start from 1 instead of 0
                                  val end_row = rowIndex + direction.first * i
                                  val end_col = colIndex + direction.second * i
                                  if (end_row in 0..7 && end_col in 0..7) {  // Use 'in' keyword to check range
                                      val end_piece = board[end_row][end_col]
                                      if (end_piece == "eM") {  // empty space is valid
                                          availableMoves.add(listOf(Pair(rowIndex, colIndex), Pair(end_row, end_col)))
                                      } else if (end_piece[0] == 'b') { // capture enemy piece
                                        availableMoves.add(listOf(Pair(rowIndex, colIndex), Pair(end_row, end_col)))
                                      }
                                  }
                             }
                        }
						drawPieces("wB", colIndex, rowIndex)
                    }
                    'Q' -> {
                        // обработка белой королевы
                    }
                    'H' -> {
                        // обработка белого коня
                    }
                    'P' -> {
                        // обработка белой пешки
						if (rowIndex - 1 < board.size && board[rowIndex - 1][colIndex] == "eM") {
							if (rowIndex==6) {
								Log.d("ChessModel", "wP avaible moves - ${rowIndex - 2} $colIndex")
								availableMoves.add(listOf(Pair(rowIndex, colIndex), Pair(rowIndex - 2, colIndex)))
							}
                            Log.d("ChessModel", "wP avaible moves - ${rowIndex - 1} $colIndex")
							availableMoves.add(listOf(Pair(rowIndex, colIndex), Pair(rowIndex - 1, colIndex)))
                        }
                    }
                }
            }
        }
        }
        }
    }
	fun loadImages(context: Context?) {
        val pieces = arrayOf("wP", "wR", "wH", "wB", "wK", "wQ", "bP", "bR", "bH", "bB", "bK", "bQ")
        val resources = context?.resources // Get your resources reference here
        val SQUARE_SIZE = 80
        for (piece in pieces) {
        val resourceId = resources?.getIdentifier(piece, "drawable", context?.packageName)
       // val bitmap = BitmapFactory.decodeResource(resources, resourceId)
	    val bitmap = resourceId?.let { BitmapFactory.decodeResource(resources, it) }
		//IMAGES[piece] = bitmap?.let { Bitmap.createScaledBitmap(it, SQUARE_SIZE, SQUARE_SIZE, false) }
		if (bitmap != null) {
        IMAGES[piece] = bitmap?.let { it ->
        Bitmap.createScaledBitmap(it, SQUARE_SIZE, SQUARE_SIZE, false)
        } ?: run {
        Log.d("ChessModel", "Failed to create scaled bitmap.")
		System.exit(0)
        null
        }
        } else {
        Log.d("ChessModel", "Bitmap is null.")
		System.exit(0)
        }
		Log.d("ChessModel", "Bitmap loaded succes!")
		}
    }
	fun drawPieces(piece: String, column: Int, row: Int) {
		val SQUARE_SIZE = 80
		val left = column * SQUARE_SIZE.toFloat()
		val t = 200
        val top = row * SQUARE_SIZE.toFloat() + t.toFloat()
        val right = left + SQUARE_SIZE.toFloat()
		val b = 20
        val bottom = top + SQUARE_SIZE.toFloat() - b.toFloat()
        val rect = Rect(left.toInt(), top.toInt(), right.toInt(), bottom.toInt())
		GAMECANVAS.drawBitmap(IMAGES[piece]!!, null, rect, GAMEPAINT)
		Log.d("ChessModel", "Drawing..")
	}
    private fun reset() {
		piecesBox.removeAll(piecesBox)
		for (i in 0..1) {
	     	piecesBox.add(ChessPiece(0+i*7, 0, ChessPlayer.WHITE, ChessRank.ROOK))
	     	piecesBox.add(ChessPiece(0+i*7, 7, ChessPlayer.BLACK, ChessRank.ROOK))
			 
			 piecesBox.add(ChessPiece(1+i*5, 0, ChessPlayer.WHITE, ChessRank.KNIGHT))
		     piecesBox.add(ChessPiece(1+i*5, 7, ChessPlayer.BLACK, ChessRank.KNIGHT))
			 
			 piecesBox.add(ChessPiece(2+i*3, 0, ChessPlayer.WHITE, ChessRank.BISHOP))
	     	piecesBox.add(ChessPiece(2+i*3, 7, ChessPlayer.BLACK, ChessRank.BISHOP))
		}
		for (i in 0..7) {
			piecesBox.add(ChessPiece(i, 1, ChessPlayer.WHITE, ChessRank.PAWN))
			piecesBox.add(ChessPiece(i, 6, ChessPlayer.BLACK, ChessRank.PAWN))
		}
		piecesBox.add(ChessPiece(3, 0, ChessPlayer.WHITE, ChessRank.QUEEN))
		piecesBox.add(ChessPiece(3, 7, ChessPlayer.BLACK, ChessRank.QUEEN))
		piecesBox.add(ChessPiece(4, 0, ChessPlayer.WHITE, ChessRank.KING))
		piecesBox.add(ChessPiece(4, 7, ChessPlayer.BLACK, ChessRank.KING))
	}

	public fun pieceAt(col: Int, row: Int) : ChessPiece? {
		for (piece in piecesBox) {
			if (col == piece.col && row == piece.row) {
				return piece
			}
		}
		return null
	}
	override fun toString(): String {
    var desc = ""
    for (row in 7 downTo 0) {
		//desc+=desc+" /n"
        for (col in 0..7) {
			//desc=""
            val piece = pieceAt(col, row)
            if (piece == null) {
                desc += " eM"
            } else {
                val white = piece.player == ChessPlayer.WHITE
                desc += " "
                val piece = when (piece.rank) {
                    ChessRank.KING -> if (white) "wK" else "bK"
                    ChessRank.QUEEN -> if (white) "wQ" else "bQ"
                    ChessRank.BISHOP -> if (white) "wB" else "bB"
                    ChessRank.ROOK -> if (white) "wR" else "bR"
                    ChessRank.KNIGHT -> if (white) "wH" else "bH"
                    ChessRank.PAWN -> if (white) "wP" else "bP"
                }
				//drawPieces(piece, col, row)
				//Log.d("ChessModel", "GAME CAMVAS AND GAMEPAINT == NULL")
				desc+=piece
            }
        }
        desc += "\n"
    }
    return desc
    }
}
