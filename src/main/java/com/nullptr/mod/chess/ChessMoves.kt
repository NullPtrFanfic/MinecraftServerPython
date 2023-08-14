package com.nullptr.chess

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Color
import android.graphics.Rect
import android.os.AsyncTask
import android.util.Log
import com.nullptr.chess.ChessPiece
import com.nullptr.chess.ChessModel

class ChessMoves(
    private val context: Context,
    private val canvas: Canvas,
    private val paint: Paint,
    private val column: Int,
    private val row: Int
) : AsyncTask<Void, Void, Unit>() {

    private val cellSide = 80f
    private val originX = 10f
    private val originY = 200f

    override fun doInBackground(vararg params: Void?) {
		Log.d("ChessMoves", "Asynchronous1...")
		Log.d("ChessView", "Preparing..")
    }

    override fun onPostExecute(result: Unit?) {
		Log.d("ChessMoves", "Asynchronous2...")
    }
}