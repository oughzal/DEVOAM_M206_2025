package com.example.m203

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.m203.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),HomeFragment.OnMessageSendListener {
    override fun onMessageSend(message: String) {
        Toast.makeText(
            this,
            message,
            Toast.LENGTH_SHORT
        ).show()
    }

    lateinit var binding: ActivityMainBinding
    lateinit var dashboard : DashboardFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnHome.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("message", "Hello from MainActivity")
            val fragment = HomeFragment()
            fragment.arguments = bundle
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .addToBackStack(null)
                .commit()

        }

        binding.btnDashboard.setOnClickListener {
            dashboard = DashboardFragment()
            val bundle = Bundle()
            bundle.putString("message", "Hello from MainActivity")
            dashboard.arguments = bundle
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer,dashboard)
                .addToBackStack(null)
                .commit()
        }
            binding.btnSend.setOnClickListener {
                val message = binding.etMessage.text.toString()
                dashboard.getMessageFromHome(message)
            }

    }


}