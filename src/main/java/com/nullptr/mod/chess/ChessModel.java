//Here's the corrected and fully expanded code without abbreviations:

//```java
package com.nullptr.mod.chess;

import com.nullptr.mod.chess.ChessPiece;
import com.nullptr.mod.chess.ChessPlayer;
import com.nullptr.mod.chess.ChessRank;
import com.nullptr.mod.chess.ChessView;
import com.nullptr.mod.Main;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.tuple.Pair;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessModel {
    public Map<String, ResourceLocation> IMAGES = new HashMap<>();
    public static List<ChessPiece> piecesBox = new ArrayList<>();
    public List<List<Pair<Integer, Integer>>> availableMoves = new ArrayList<>();

    public ChessModel() {
        loadImages();
        reset();
        String strBoard = toString();
        getAvailableMoves();
    }

    public void getAvailableMoves() {
        availableMoves.clear();
        String strBoard = toString();
        String[] rows = strBoard.trim().split("\n");
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
                                List<Pair<Integer, Integer>> directionsR = Arrays.asList(
                                        Pair.of(-1, 0), Pair.of(0, -1), Pair.of(1, 0), Pair.of(0, 1)
                                );
                                for (Pair<Integer, Integer> direction : directionsR) {
                                    for (int i = 1; i <= 7; i++) {
                                        int endRow = rowIndex + direction.getKey() * i;
                                        int endCol = colIndex + direction.getValue() * i;
                                        if (endRow >= 0 && endRow < 8 && endCol >= 0 && endCol < 8) {
                                            String endPiece = board[endRow][endCol];
                                            if (endPiece.equals("eM")) {
                                                availableMoves.add(Arrays.asList(Pair.of(rowIndex, colIndex), Pair.of(endRow, endCol)));
                                            } else if (endPiece.charAt(0) == 'w') {
                                                availableMoves.add(Arrays.asList(Pair.of(rowIndex, colIndex), Pair.of(endRow, endCol)));
                                            }
                                        }
                                    }
                                }
                                drawPieces("br", colIndex, rowIndex);
                                break;
                            case 'B':
                                List<Pair<Integer, Integer>> directionsB = Arrays.asList(
                                        Pair.of(-1, -1), Pair.of(-1, 1), Pair.of(1, 1), Pair.of(1, -1)
                                );
                                for (Pair<Integer, Integer> direction : directionsB) {
                                    for (int i = 1; i <= 7; i++) {
                                        int endRow = rowIndex + direction.getKey() * i;
                                        int endCol = colIndex + direction.getValue() * i;
                                        if (endRow >= 0 && endRow < 8 && endCol >= 0 && endCol < 8) {
                                            String endPiece = board[endRow][endCol];
                                            if (endPiece.equals("eM")) {
                                                availableMoves.add(Arrays.asList(Pair.of(rowIndex, colIndex), Pair.of(endRow, endCol)));
                                            } else if (endPiece.charAt(0) == 'w') {
                                                availableMoves.add(Arrays.asList(Pair.of(rowIndex, colIndex), Pair.of(endRow, endCol)));
                                            }
                                        }
                                    }
                                }
                                drawPieces("bb", colIndex, rowIndex);
                                break;
                            case 'P':
                                if (rowIndex - 1 >= 0 && board[rowIndex - 1][colIndex].equals("eM")) {
                                    availableMoves.add(Arrays.asList(Pair.of(rowIndex, colIndex), Pair.of(rowIndex - 1, colIndex)));
                                }
                                if (rowIndex == 6 && board[rowIndex - 1][colIndex].equals("eM") && board[rowIndex - 2][colIndex].equals("eM")) {
                                    availableMoves.add(Arrays.asList(Pair.of(rowIndex, colIndex), Pair.of(rowIndex - 2, colIndex)));
                                }
                                if (colIndex - 1 >= 0 && rowIndex - 1 >= 0 && board[rowIndex - 1][colIndex - 1].charAt(0) == 'w') {
                                    availableMoves.add(Arrays.asList(Pair.of(rowIndex, colIndex), Pair.of(rowIndex - 1, colIndex - 1)));
                                }
                                if (colIndex + 1 < 8 && rowIndex - 1 >= 0 && board[rowIndex - 1][colIndex + 1].charAt(0) == 'w') {
                                    availableMoves.add(Arrays.asList(Pair.of(rowIndex, colIndex), Pair.of(rowIndex - 1, colIndex + 1)));
                                }
				drawPieces("bp", colIndex, rowIndex);
                                break;
			    case 'K':
				drawPieces("bk", colIndex, rowIndex);
				break;
			    case 'Q':
				drawPieces("bq", colIndex, rowIndex);
				break;
			    case 'H':
				drawPieces("bh", colIndex, rowIndex);
				break;
                        }
                        break;
                    case 'w':
                        switch (type) {
                            case 'R':
                                // Handle white rook
                                List<Pair<Integer, Integer>> directionsR = Arrays.asList(
                                        Pair.of(-1, 0), Pair.of(0, -1), Pair.of(1, 0), Pair.of(0, 1)
                                );
                                for (Pair<Integer, Integer> direction : directionsR) {
                                    for (int i = 1; i <= 7; i++) {
                                        int endRow = rowIndex + direction.getKey() * i;
                                        int endCol = colIndex + direction.getValue() * i;
                                        if (endRow >= 0 && endRow < 8 && endCol >= 0 && endCol < 8) {
                                            String endPiece = board[endRow][endCol];
                                            if (endPiece.equals("eM")) {
                                                availableMoves.add(Arrays.asList(Pair.of(rowIndex, colIndex), Pair.of(endRow, endCol)));
                                            } else if (endPiece.charAt(0) == 'b') {
                                                availableMoves.add(Arrays.asList(Pair.of(rowIndex, colIndex), Pair.of(endRow, endCol)));
                                            }
                                        }
                                    }
                                }
                                drawPieces("wr", colIndex, rowIndex);
                                break;
                            case 'B':
                                // Handle white bishop
                                List<Pair<Integer, Integer>> directionsB = Arrays.asList(
                                        Pair.of(-1, -1), Pair.of(-1, 1), Pair.of(1, 1), Pair.of(1, -1)
                                );
                                for (Pair<Integer, Integer> direction : directionsB) {
                                    for (int i = 1; i <= 7; i++) {
                                        int endRow = rowIndex + direction.getKey() * i;
                                        int endCol = colIndex + direction.getValue() * i;
                                        if (endRow >= 0 && endRow < 8 && endCol >= 0 && endCol < 8) {
                                            String endPiece = board[endRow][endCol];
                                            if (endPiece.equals("eM")) {
                                                availableMoves.add(Arrays.asList(Pair.of(rowIndex, colIndex), Pair.of(endRow, endCol)));
                                            } else if (endPiece.charAt(0) == 'b') {
                                                availableMoves.add(Arrays.asList(Pair.of(rowIndex, colIndex), Pair.of(endRow, endCol)));
                                            }
                                        }
                                    }
                                }
                                drawPieces("wb", colIndex, rowIndex);
                                break;
                            case 'P':
                                // Handle white pawn
                                if (rowIndex + 1 < 8 && board[rowIndex + 1][colIndex].equals("eM")) {
                                    availableMoves.add(Arrays.asList(Pair.of(rowIndex, colIndex), Pair.of(rowIndex + 1, colIndex)));
                                }
                                if (rowIndex == 1 && board[rowIndex + 1][colIndex].equals("eM") && board[rowIndex + 2][colIndex].equals("eM")) {
                                    availableMoves.add(Arrays.asList(Pair.of(rowIndex, colIndex), Pair.of(rowIndex + 2, colIndex)));
                                }
                                if (colIndex - 1 >= 0 && rowIndex + 1 < 8 && board[rowIndex + 1][colIndex - 1].charAt(0) == 'b') {
                                    availableMoves.add(Arrays.asList(Pair.of(rowIndex, colIndex), Pair.of(rowIndex + 1, colIndex - 1)));
                                }
                                if (colIndex + 1 < 8 && rowIndex + 1 < 8 && board[rowIndex + 1][colIndex + 1].charAt(0) == 'b') {
                                    availableMoves.add(Arrays.asList(Pair.of(rowIndex, colIndex), Pair.of(rowIndex + 1, colIndex + 1)));
                                }
				drawPieces("wp", colIndex, rowIndex);
                                break;
			    case 'K':
				drawPieces("wk", colIndex, rowIndex);
				break;
			    case 'Q':
				drawPieces("wq", colIndex, rowIndex);
				break;
			    case 'H':
				drawPieces("wh", colIndex, rowIndex);
				break;
                        }
                        break;
                }
            }
        }
    }

    private void loadImages() {
        String[] pieces = {"wp", "wr", "wh", "wb", "wk", "wq", "bp", "br", "bh", "bb", "bk", "bq"};
        for (String piece : pieces) {
            IMAGES.put(piece, new ResourceLocation(Main.MODID, "textures/chess/" + piece + ".png"));
        }
    }

    private void drawPieces(String piece, int column, int row) {
	Minecraft mc = Minecraft.getMinecraft();
        // Implementation of drawPieces method
        int SQUARE_SIZE = 20;
	ScaledResolution scaledRes = new ScaledResolution(mc);
        int screenWidth = scaledRes.getScaledWidth();
        int screenHeight = scaledRes.getScaledHeight();
    
        float ORIGIN_X = (float) ((screenWidth - (SQUARE_SIZE * ChessView.BOARD_SIZE)) / 2);
        float ORIGIN_Y = (float) ((screenHeight - (SQUARE_SIZE * ChessView.BOARD_SIZE)) / 2);
	float left = ORIGIN_X + (float) column * (float) SQUARE_SIZE;
        float top = ORIGIN_Y + (float) row * (float) SQUARE_SIZE;
        //val right = left + SQUARE_SIZE.toFloat()
	//val b = 20
        //val bottom = top + SQUARE_SIZE.toFloat() - b.toFloat()
        mc.getTextureManager().bindTexture(IMAGES.get(piece));
	Gui.drawModalRectWithCustomSizedTexture((int) left, (int) top, 0, 0, 20, 20, 20, 20);
    }

    private void reset() {
        piecesBox.clear();
        for (int i = 0; i < 2; i++) {
            piecesBox.add(new ChessPiece(0 + i * 7, 0, ChessPlayer.WHITE, ChessRank.ROOK));
            piecesBox.add(new ChessPiece(0 + i * 7, 7, ChessPlayer.BLACK, ChessRank.ROOK));

            piecesBox.add(new ChessPiece(1 + i * 5, 0, ChessPlayer.WHITE, ChessRank.KNIGHT));
            piecesBox.add(new ChessPiece(1 + i * 5, 7, ChessPlayer.BLACK, ChessRank.KNIGHT));

            piecesBox.add(new ChessPiece(2 + i * 3, 0, ChessPlayer.WHITE, ChessRank.BISHOP));
            piecesBox.add(new ChessPiece(2 + i * 3, 7, ChessPlayer.BLACK, ChessRank.BISHOP));
        }
        for (int i = 0; i < 8; i++) {
            piecesBox.add(new ChessPiece(i, 1, ChessPlayer.WHITE, ChessRank.PAWN));
            piecesBox.add(new ChessPiece(i, 6, ChessPlayer.BLACK, ChessRank.PAWN));
        }
        piecesBox.add(new ChessPiece(3, 0, ChessPlayer.WHITE, ChessRank.QUEEN));
        piecesBox.add(new ChessPiece(3, 7, ChessPlayer.BLACK, ChessRank.QUEEN));
        piecesBox.add(new ChessPiece(4, 0, ChessPlayer.WHITE, ChessRank.KING));
        piecesBox.add(new ChessPiece(4, 7, ChessPlayer.BLACK, ChessRank.KING));
    }

    public static ChessPiece pieceAt(int col, int row) {
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
//```

//Please note that I've replaced the use of `Pair<>(...)` with `Pair.of(...)` due to potential compatibility issues with the Java version you're using.
