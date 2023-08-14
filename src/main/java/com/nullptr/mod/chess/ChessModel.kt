package com.nullptr.mod.chess;
import com.nullptr.mod.ChessPiece;
import com.nullptr.mod.ChessRank;
import com.nullptr.mod.ChessPlayer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessModel {
    //public Map<String, Bitmap> IMAGES = new HashMap<>();
    public List<ChessPiece> piecesBox = new ArrayList<>();
    public List<List<Pair<Integer, Integer>>> availableMoves = new ArrayList<>();

    public ChessModel(Context context) {
        loadImages(context);
        reset();
        String strboard = toString();
        //Log.d("ChessModel", strboard);
        getAvailableMoves();
    }

    public void getAvailableMoves() {
        availableMoves.clear();
        String strboard = toString();
        String[] rows = strboard.trim().split("\n");
        String[][] board = new String[8][8];
        for (int i = 0; i < 8; i++) {
            board[i] = rows[i].trim().split(" ");
        }

        for (int rowIndex = 0; rowIndex < board.length; rowIndex++) {
            for (int colIndex = 0; colIndex < board[rowIndex].length; colIndex++) {
                String piece = board[rowIndex][colIndex];
                char color = piece.charAt(0);
                char type = piece.charAt(1);

                switch (color) {
                    case 'b':
                        switch (type) {
                            case 'R':
                                // Handle black rook
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
                                break;
                            case 'B':
                                // Handle black king
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
                                break;
                             'P' -> {
                        // обработка черной пешки
                        if (rowIndex + 1 < board.size && board[rowIndex + 1][colIndex] == "eM") {
			    if (rowIndex==1) {
								//Log.d("ChessModel", "bP avaible moves - ${rowIndex + 2} $colIndex")
				availableMoves.add(listOf(Pair(rowIndex, colIndex), Pair(rowIndex + 2, colIndex)))
		            }
                           // Log.d("ChessModel", "bP avaible moves - ${rowIndex + 1} $colIndex")
			   availableMoves.add(listOf(Pair(rowIndex, colIndex), Pair(rowIndex + 1, colIndex)))
                        }
                            // Handle other black pieces
                        break;
                    case 'w':
                        switch (type) {
                            case 'R':
                                // Handle white king
                                // ...
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
				  drawPieces("wR", colIndex, rowIndex)
                                  break;
                             }
			     case 'B':
                                // Handle black king
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
				drawPieces("wB", colIndex, rowIndex)
                                break;
			   case 'P':
                        // обработка черной пешки
                        if (rowIndex + 1 < board.size && board[rowIndex + 1][colIndex] == "eM") {
			    if (rowIndex==1) {
								//Log.d("ChessModel", "bP avaible moves - ${rowIndex + 2} $colIndex")
				availableMoves.add(listOf(Pair(rowIndex, colIndex), Pair(rowIndex + 2, colIndex)))
		            }
                           // Log.d("ChessModel", "bP avaible moves - ${rowIndex + 1} $colIndex")
			   availableMoves.add(listOf(Pair(rowIndex, colIndex), Pair(rowIndex + 1, colIndex)))
                        }
                            // Handle other black pieces
                        break;
                        }
                        break;
                }
            }
        }
    }

    private void loadImages(Context context) {
        String[] pieces = {"wP", "wR", "wH", "wB", "wK", "wQ", "bP", "bR", "bH", "bB", "bK", "bQ"};
        int SQUARE_SIZE = 80;
        for (String piece : pieces) {
          //  int resourceId = context.getResources().getIdentifier(piece, "drawable", context.getPackageName());
           // Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resourceId);
            //if (bitmap != null) {
             //   IMAGES.put(piece, Bitmap.createScaledBitmap(bitmap, SQUARE_SIZE, SQUARE_SIZE, false));
                //Log.d("ChessModel", "Bitmap loaded successfully!");
            //} else {
             //   Log.d("ChessModel", "Failed to load bitmap.");
               // System.exit(0);
            //}
        }
    }

    private void drawPieces(String piece, int column, int row) {
        int SQUARE_SIZE = 80;
        float left = column * SQUARE_SIZE;
        float top = row * SQUARE_SIZE;
        float right = left + SQUARE_SIZE;
        float bottom = top + SQUARE_SIZE;
      //  Rect rect = new Rect((int) left, (int) top, (int) right, (int) bottom);
        // GAMECANVAS.drawBitmap(IMAGES.get(piece), null, rect, GAMEPAINT);
       // Log.d("ChessModel", "Drawing...");
    }

    private void reset() {
        piecesBox.clear();
        // ...
        // Initialize piecesBox with the initial positions of chess pieces
        // ...
    }

    public ChessPiece pieceAt(int col, int row) {
        for (ChessPiece piece : piecesBox) {
            if (col == piece.col && row == piece.row) {
                return piece;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder desc = new StringBuilder();
        for (int row = 7; row >= 0; row--) {
            for (int col = 0; col < 8; col++) {
                ChessPiece piece = pieceAt(col, row);
                if (piece == null) {
                    desc.append(" eM");
                } else {
                    char color = piece.player == ChessPlayer.WHITE ? 'w' : 'b';
                    char type = piece.rank.toString().charAt(0);
                    desc.append(" ").append(color).append(type);
                }
            }
            desc.append("\n");
        }
        return desc.toString();
    }
}
