package com.example.m203

import com.example.m203.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.m203.databinding.ActivityMainBinding
import androidx.core.content.edit
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.io.File


@Serializable
data class User(
    val nom: String,
    val age: Int
)

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pref = getSharedPreferences("setting",MODE_PRIVATE)
        pref.edit {
            putString("name","DEV")
            putInt("age",22)
            putBoolean("present",true)
        }
        pref.getString("name","1234")

        //files
        val file = File(cacheDir,"file1.txt")
        file.writeText("Hello")
        val text = file.readText()


        val test = assets.open("file1.text")
            .bufferedReader()
            .use {
            it.readText()
        }
        val raw = resources.openRawResource(R.raw.data)
            .bufferedReader()
            .use { it.readText() }






// Sauvegarde fichier
        binding.btnSaveFile.setOnClickListener {

            val text = binding.etText.text.toString()

            openFileOutput("notes.txt", MODE_PRIVATE).use {
                it.write(text.toByteArray())
            }
        }

        // Lecture fichier
        binding.btnReadFile.setOnClickListener {

            val text = openFileInput("notes.txt")
                .bufferedReader()
                .use { it.readText() }

            binding.etText.setText(text)
        }

        // Sauvegarde SharedPreferences
        binding.btnSavePrefs.setOnClickListener {

            val prefs = getSharedPreferences("settings", MODE_PRIVATE)

            prefs.edit {
                putString("savedText", binding.etText.text.toString())
            }
        }

        // Lecture SharedPreferences
        binding.btnReadPrefs.setOnClickListener {

            val prefs = getSharedPreferences("settings", MODE_PRIVATE)

            val text = prefs.getString("savedText", "")

            binding.etText.setText(text)
        }





    }
}