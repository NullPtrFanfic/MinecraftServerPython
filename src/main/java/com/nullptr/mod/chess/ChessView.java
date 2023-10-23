package com.nullptr.mod.chess;

import com.nullptr.mod.chess.ChessPiece;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.input.Mouse;

import java.util.List;
import org.apache.commons.lang3.tuple.Pair;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ChessView {
    private static final float CELL_SIDE = 20f;
    private static float ORIGIN_X = 10f;
    private static float ORIGIN_Y = 200f;
    public static final int BOARD_SIZE = 8;

    private int selectedRow = 0;
    private int selectedColumn = 0;
    private boolean drawAvailableMoves = false;
    public static boolean boardActive = true;
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void doTheOtherThing(RenderGameOverlayEvent event) {
      if (boardActive == true) {
        Minecraft mc = Minecraft.getMinecraft();
        ScaledResolution scaledRes = new ScaledResolution(mc);
        int screenWidth = scaledRes.getScaledWidth();
        int screenHeight = scaledRes.getScaledHeight();
    
        ORIGIN_X = (screenWidth - (CELL_SIDE * BOARD_SIZE)) / 2;
        ORIGIN_Y = (screenHeight - (CELL_SIDE * BOARD_SIZE)) / 2;
        // Draw chess board
        for (int j = 0; j < BOARD_SIZE; j++) {
            for (int i = 0; i < BOARD_SIZE; i++) {
                float left = ORIGIN_X + i * CELL_SIDE;
                float top = ORIGIN_Y + j * CELL_SIDE;
                float right = left + CELL_SIDE;
                float bottom = top + CELL_SIDE;
                int color = (i + j) % 2 == 0 ? 0xFFC0C0C0 : 0xFF808080; // Light gray and dark gray
                Gui.drawRect((int) left, (int) top, (int) right, (int) bottom, color);
            }
        }

        // Highlight selected square
        ChessModel chessModel = new ChessModel();
        ChessPiece piece = chessModel.pieceAt(selectedColumn, selectedRow);
	chessModel.getAvailableMoves();
	//mc.player.sendMessage(new TextComponentString(String.valueOf(chessModel.availableMoves)));
        List<List<Pair<Integer, Integer>>> availableMoves = chessModel.availableMoves;

        if (drawAvailableMoves && piece != null) {
            for (List<Pair<Integer, Integer>> moveList : availableMoves) {
                for (Pair<Integer, Integer> move : moveList) {
                    if (Pair.of(selectedRow, selectedColumn).equals(move)) {
                        float left = ORIGIN_X + move.getRight() * CELL_SIDE;
                        float top = ORIGIN_Y + move.getLeft() * CELL_SIDE;
                        float right = left + CELL_SIDE;
                        float bottom = top + CELL_SIDE;
                        Gui.drawRect((int) left, (int) top, (int) right, (int) bottom, 0x800080FF); // Semi-transparent blue
                    }
                }
            }
        }
      }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onMouseClick(GuiScreenEvent.MouseInputEvent event) {
        Minecraft mc = Minecraft.getMinecraft();
        ScaledResolution scaledRes = new ScaledResolution(mc);

        int mouseX = (int) Mouse.getX();
        int mouseY = (int) Mouse.getY();

        int column = (mouseX - (int) ORIGIN_X) / 20;
        int row = (mouseY - (int) ORIGIN_Y) / 20;
	//Minecraft.getMinecraft().player.sendMessage(new TextComponentString(String.valueOf(column)+" "+String.valueOf(row)));

        if (column >= 0 && column < BOARD_SIZE && row >= 0 && row < BOARD_SIZE) {
            selectedColumn = column;
            selectedRow = row;
            drawAvailableMoves = !drawAvailableMoves;
	 //   Minecraft.getMinecraft().player.sendMessage(new TextComponentString("Вкл/выкл подсветку доступных ходов.."));
        }
    }
}
/*
package com.nullptr.mod.chess

import com.nullptr.mod.chess.ChessModel;
import com.nullptr.mod.chess.ChessMoves;
import com.nullptr.mod.Main;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
@Mod.EventBusSubscriber(Side.CLIENT)
public class ChessView {
	public final float cellSide = 80f
	public final float originX = 10f
	public final float originY = 200f
	public int Row = 0
	public int Column = 0
        public boolean drawAvailableMoves = false
        public int DIMENSION = 8
        public int SQUARE_SIZE = 80

    @SubscribeEvent
    public void doTheOtherThing(RenderGameOverlayEvent event) {
        Minecraft mc = Minecraft.getMinecraft();
        ScaledResolution scaledRes = new ScaledResolution(mc);
	// Установка прозрачности текстуры
        mc.getTextureManager().bindTexture(ChessModel.IMAGE[piece);
       // mc.ingameGUI.drawTexturedModalRect(scaledRes.getScaledWidth() / 2 - 29, scaledRes.getScaledHeight() / 2 - 4, 0, 0, 59, 8);
	//mc.getTextureManager().bindTexture(Gui.ICONS);
        Gui.drawRect(scaledRes.getScaledWidth() / 2 - 4, scaledRes.getScaledHeight() / 2 - 4, scaledRes.getScaledWidth() / 2 + 4, scaledRes.getScaledHeight() / 2 + 4, 0xFF0000FF);
	int textureWidth = 56; // Ширина текстуры в пикселях
        int textureHeight = 56; // Высота текстуры в пикселях
        int xPos = scaledRes.getScaledWidth() - textureWidth - 10; // Координата X для отображения текстуры
        int yPos = 10; // Координата Y для отображения текстуры
        mc.ingameGUI.drawTexturedModalRect(xPos, yPos, 0, 0, textureWidth, textureHeight);
	for (j in 0..7) { // Пройти по всем строкам доски
        for (i in 0..7) { // Пройти по всем столбцам доски
            paint.color = if ((i + j) % 2 == 0) Color.LTGRAY else Color.DKGRAY // Смена цветов клеток
            float left = originX + i * cellSide
            float top = originY + j * cellSide
            float right = left + cellSide
            float bottom = top + cellSide
            canvas?.drawRect(left, top, right, bottom, paint)
        }
    }
	//ChessModel.saveCanvasAndPaint(CANVAS, PAINT)
	//val cmodel = ChessModel(context, canvas, paint)
	//Log.d("ChessView", "${cmodel.availableMoves[0]}")
	//Log.d("ChessView", "${cmodel.availableMoves[1]}")
	//invalidate()
	List<Pair> piece = ChessModel.pieceAt(Column, Row)
	ChessModel.getAvailableMoves()
	if (drawAvailableMoves == true && piece != null) {
	for (i in 0 until ChessModel.availableMoves.size)
	{
		if (Pair(Row, Column) in ChessModel.availableMoves[i]) {
		   List<Pair> move = ChessModel.availableMoves[i][1]
			   // Log.d("ChessView", "${move}")
              //  for ((Row, Column) in move) {
               // val yellowPaint = Paint().apply { color = Color.parseColor("#80FFFF00") }
		//val skyBluePaint = Paint().apply { color = Color.parseColor("#800080FF") }
	           // Log.d("ChessView", "Drawing available moves..")
		float moveleft = originX + move.second * cellSide
                float movetop = originY + move.first * cellSide
                float moveright = moveleft + cellSide
                float movebottom = movetop + cellSide
                float moverect = Rect(moveleft.toInt(), movetop.toInt(), moveright.toInt(), movebottom.toInt())
                canvas.drawRect(moverect, yellowPaint)
		float pieceleft = originX + Column * cellSide
                float piecetop = originY + Row * cellSide
                float pieceright = pieceleft + cellSide
                float piecebottom = piecetop + cellSide
                float piecerect = Rect(pieceleft.toInt(), piecetop.toInt(), pieceright.toInt(), piecebottom.toInt())
                canvas.drawRect(piecerect, skyBluePaint)
              //  }
		}
	}
	}
    }
   @SubscribeEvent
   public void onMouseClick(MouseInputEvent event) {
    if (event.getButton() == 0 && event.isButtonDown()) { // Левая кнопка мыши нажата
        Minecraft mc = Minecraft.getMinecraft();
        ScaledResolution scaledRes = new ScaledResolution(mc);
        int mouseX = Mouse.getEventX() * scaledRes.getScaledWidth() / mc.displayWidth;
        int mouseY = scaledRes.getScaledHeight() - Mouse.getEventY() * scaledRes.getScaledHeight() / mc.displayHeight - 1;

        int column = (x / cellSide).toInt()
        int row = ((y - originY) / cellSide).toInt()
	//	val chessModel = ChessModel(context, canvas, paint)
        
        drawAvailableMoves = !drawAvailableMoves
	Row = row
	Column = column
    }
   }
}
*/
