package com.example.m203

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.m203.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var n = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val value = savedInstanceState?.getString("key") ?: "default"
        Log.d("DEVOAM", "onCreate")
        n = savedInstanceState?.getInt("counter", 0) ?: 0
        val s = "counter : ${0}"
        binding.tvCounter.text = s.format(n)
        binding.btnCount.setOnClickListener {
            n++
            binding.tvCounter.text = n.toString()
        }



    }

    override fun onStart() {
        super.onStart()
        Log.d("DEVOAM", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("DEVOAM", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("DEVOAM", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("DEVOAM", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("DEVOAM", "onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("DEVOAM", "onRestart")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("DEVOAM", "onSaveInstanceState")
        outState.putInt("counter", n)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d("DEVOAM", "onRestoreInstanceState")
        n = savedInstanceState.getInt("counter", 0)
    }


}