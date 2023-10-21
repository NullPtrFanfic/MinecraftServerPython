package com.nullptr.mod.chess;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ChessView {
    private static final float CELL_SIDE = 80f;
    private static final float ORIGIN_X = 10f;
    private static final float ORIGIN_Y = 200f;

    private int selectedRow = 0;
    private int selectedColumn = 0;
    private boolean drawAvailableMoves = false;

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void doTheOtherThing(RenderGameOverlayEvent event) {
      if (drawAvailableMoves == true) {
        Minecraft mc = Minecraft.getMinecraft();
        ScaledResolution scaledRes = new ScaledResolution(mc);

        // Draw chess board
        for (int j = 0; j < 7; j++) {
            for (int i = 0; i < 7; i++) {
                float left = ORIGIN_X + i * CELL_SIDE;
                float top = ORIGIN_Y + j * CELL_SIDE;
                float right = left + CELL_SIDE;
                float bottom = top + CELL_SIDE;
                int color = (i + j) % 2 == 0 ? 0xFFC0C0C0 : 0xFF808080; // Light gray and dark gray
                Gui.drawRect((int) left, (int) top, (int) right, (int) bottom, color);
            }
        }

        // Highlight selected square
	List<Pair> piece = ChessModel.pieceAt(Column, Row)
	ChessModel.getAvailableMoves()
	if (drawAvailableMoves == true && piece != null) {
	for (i in 0 until ChessModel.availableMoves.size)
	{
		if (Pair(selectedRow, selectedColumn) in ChessModel.availableMoves[i]) {
		   List<Pair> move = ChessModel.availableMoves[i][1]
                   float left = ORIGIN_X + move.second * CELL_SIDE;
                   float top = ORIGIN_Y + move.first * CELL_SIDE;
                   float right = left + CELL_SIDE;
                   float bottom = top + CELL_SIDE;
                   Gui.drawRect((int) left, (int) top, (int) right, (int) bottom, 0x800080FF); // Semi-transparent blue
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

        int mouseX = Mouse.getX() * scaledRes.getScaledWidth() / mc.displayWidth;
        int mouseY = scaledRes.getScaledHeight() - Mouse.getY() * scaledRes.getScaledHeight() / mc.displayHeight - 1;

        int column = (int) ((mouseX - ORIGIN_X) / CELL_SIDE);
        int row = (int) ((mouseY - ORIGIN_Y) / CELL_SIDE);

        if (column >= 0 && column < 7 && row >= 0 && row < 7) {
            selectedColumn = column;
            selectedRow = row;
            drawAvailableMoves = !drawAvailableMoves;
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
