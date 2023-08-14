package com.nullptr.mod.chess

import com.nullptr.mod.chess.ChessModel
import com.nullptr.mod.chess.ChessMoves
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
	GlStateManager.glBegin(GL_QUADS);
	GlStateManager.pushMatrix();
        GlStateManager.enableAlpha();
        GlStateManager.enableBlend();
       // RenderSystem.defaultBlendFunc();
       // RenderSystem.defaultAlphaFunc();
        mc.getTextureManager().bindTexture(new ResourceLocation(Main.MODID, "textures/items/ico.png"));
       // mc.ingameGUI.drawTexturedModalRect(scaledRes.getScaledWidth() / 2 - 29, scaledRes.getScaledHeight() / 2 - 4, 0, 0, 59, 8);
	//mc.getTextureManager().bindTexture(Gui.ICONS);
        Gui.drawRect(scaledRes.getScaledWidth() / 2 - 4, scaledRes.getScaledHeight() / 2 - 4, scaledRes.getScaledWidth() / 2 + 4, scaledRes.getScaledHeight() / 2 + 4, 0xFF0000FF);
	int textureWidth = 56; // Ширина текстуры в пикселях
        int textureHeight = 56; // Высота текстуры в пикселях
        int xPos = scaledRes.getScaledWidth() - textureWidth - 10; // Координата X для отображения текстуры
        int yPos = 10; // Координата Y для отображения текстуры
        mc.ingameGUI.drawTexturedModalRect(xPos, yPos, 0, 0, textureWidth, textureHeight);
	GlStateManager.disableAlpha();
        GlStateManager.disableBlend();
	GlStateManager.popMatrix();
	GlStateManager.glEnd();
    }
    val paint = Paint()
    for (j in 0..7) { // Пройти по всем строкам доски
        for (i in 0..7) { // Пройти по всем столбцам доски
            paint.color = if ((i + j) % 2 == 0) Color.LTGRAY else Color.DKGRAY // Смена цветов клеток
            val left = originX + i * cellSide
            val top = originY + j * cellSide
            val right = left + cellSide
            val bottom = top + cellSide
            canvas?.drawRect(left, top, right, bottom, paint)
        }
    }
	//ChessModel.saveCanvasAndPaint(CANVAS, PAINT)
	val cmodel = ChessModel(context, canvas, paint)
	//Log.d("ChessView", "${cmodel.availableMoves[0]}")
	//Log.d("ChessView", "${cmodel.availableMoves[1]}")
	invalidate()
	val piece = cmodel.pieceAt(Column, Row)
	cmodel.getAvailableMoves()
	if (drawAvailableMoves == true && piece != null) {
	for (i in 0 until cmodel.availableMoves.size)
	{
		if (Pair(Row, Column) in cmodel.availableMoves[i]) {
			    val move = cmodel.availableMoves[i][1]
			   // Log.d("ChessView", "${move}")
              //  for ((Row, Column) in move) {
                val yellowPaint = Paint().apply { color = Color.parseColor("#80FFFF00") }
				val skyBluePaint = Paint().apply { color = Color.parseColor("#800080FF") }
	           // Log.d("ChessView", "Drawing available moves..")
		        val moveleft = originX + move.second * cellSide
                val movetop = originY + move.first * cellSide
                val moveright = moveleft + cellSide
                val movebottom = movetop + cellSide
                val moverect = Rect(moveleft.toInt(), movetop.toInt(), moveright.toInt(), movebottom.toInt())
                canvas.drawRect(moverect, yellowPaint)
				val pieceleft = originX + Column * cellSide
                val piecetop = originY + Row * cellSide
                val pieceright = pieceleft + cellSide
                val piecebottom = piecetop + cellSide
                val piecerect = Rect(pieceleft.toInt(), piecetop.toInt(), pieceright.toInt(), piecebottom.toInt())
                canvas.drawRect(piecerect, skyBluePaint)
              //  }
		}
	}
	}
	val chessModel = ChessModel(context, canvas, paint)
	chessModel.GAMEPAINT = paint
	chessModel.GAMECANVAS = canvas
	//loadImages()
	//ChessModel.CANVAS = canvas
	//ChessModel.PAINT = paint
	//drawPieces(canvas)
	}
	override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x.toInt()
        val y = event.y.toInt()

        val column = (x / cellSide).toInt()
        val row = ((y - originY) / cellSide).toInt()
	//	val chessModel = ChessModel(context, canvas, paint)
        
        drawAvailableMoves = !drawAvailableMoves
		Row = row
		Column = column
	//Log.d("ChessView", "${Row} ${Column} ${piece}")
    /*	val handler = Handler()
        val refresh = object : Runnable {
        override fun run() {
            try {
                // Ваш код здесь
                ChessMoves(context, chessModel.GAMECANVAS, chessModel.GAMEPAINT, column, row).execute()
            } catch (e: NullPointerException) {
                Log.d("ChessView", "$e")
            }
            handler.postDelayed(this, 1000)
        }
        } 
    	handler.postDelayed(refresh, 1000)
		}*/
       // Log.d("ChessView", "${row} ${column} ${drawAvailableMoves}")
        return super.onTouchEvent(event)
    }
}
