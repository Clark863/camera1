package com.user.camera1// com.user.camera1.MainActivity.kt
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputText = findViewById<EditText>(R.id.inputText)
        val saveButton = findViewById<Button>(/* id = */ R.id.saveButton)

        saveButton.setOnClickListener {
            val textToSave = inputText.text.toString()
            saveTextToFile(textToSave)
        }
    }

    private fun saveTextToFile(text: String) {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val fileName = "text_$timeStamp.txt"
        readTextFromFile(fileName)
        val file = File(filesDir, fileName)
        FileOutputStream(file).use {
            it.write(text.toByteArray())
        }
    }

    private fun readTextFromFile(fileName: String): String {
        val file = File(filesDir, fileName)
        val stringBuilder = StringBuilder()

        try {
            FileInputStream(file).use { inputStream ->
                inputStream.bufferedReader().forEachLine {
                    stringBuilder.append(it).append("\n")
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return stringBuilder.toString()
    }


}



