package com.nullptr.chess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.lang.Runtime
import android.widget.Toast
import android.os.Environment
import java.io.File
import android.util.Log
import android.graphics.Canvas
import android.graphics.Paint
import com.nullptr.chess.ChessModel
//import java.io.File

import java.io.FileOutputStream
import java.io.IOException

private const val TAG = "MainActivity"
//private const val PACKAGE_NAME = "com.nullptr.chess"
private var FILE_PATH = ""
public class MainActivity : AppCompatActivity() {
    fun writeToFile(fileName: String, text: String) {
        val file = File(fileName)

        try {
           val fos = FileOutputStream(file)
           fos.write(text.toByteArray())
           fos.close()
        } catch (e: IOException) {
           e.printStackTrace()
       }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
     super.onCreate(savedInstanceState)
     setContentView(R.layout.activity_main)
     
     val externalFilesDir = getExternalFilesDir(null)
     if (externalFilesDir != null) {
         FILE_PATH = externalFilesDir.absolutePath
         //val command = listOf("logcat", "-f", FILE_PATH, "$TAG:V", "*:S")
		 Runtime.getRuntime().exec("logcat -c")
		 try {
			 val canvas = Canvas()
			 val paint = Paint()
			 var chessModel = ChessModel(getApplicationContext(), canvas, paint)
	  	   writeToFile(FILE_PATH + "/chess.txt", chessModel.toString())
			 Log.d(TAG, "Loading...")
		 }
		 catch (e: IOException) {
		  // Runtime.getRuntime().exec("logcat -f " + outputFile.absolutePath)
		 }
         val myToast = Toast.makeText(this, FILE_PATH, Toast.LENGTH_SHORT)
         myToast.show()
     }
	}
}