package com.example.m203

import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.m203.databinding.ActivityMainBinding
import kotlin.jvm.java
import androidx.core.net.toUri

class MainActivity : AppCompatActivity() {
    val receiver = MyBReceiver()
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnOpenWeb.setOnClickListener {
            val uri = "https://www.google.com".toUri()
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        binding.btnOpenMap.setOnClickListener {
            val uri = "geo:37.7749,-122.4194".toUri()
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
        binding.btnOpenDialer.setOnClickListener {
            val uri ="tel:068765433".toUri()
            val intent = Intent(Intent.ACTION_DIAL,uri)
            startActivity(intent)
        }

        binding.btnSendSMS.setOnClickListener {
            val uri = "smsto:068765499".toUri()
            val intent = Intent(Intent.ACTION_SENDTO, uri)
            intent.putExtra("sms_body", "Hello, this is a test message!")
            startActivity(intent)
        }
        binding.btnSendEmail.setOnClickListener {
            val uri = "mailto:test@ofppt.dev".toUri()
            val intent = Intent(Intent.ACTION_SENDTO,uri)
            intent.putExtra(Intent.EXTRA_SUBJECT,"TEST")
            intent.putExtra(Intent.EXTRA_TEXT,"TEST")
            startActivity(intent)
        }


        unregisterReceiver(receiver)


    }

    override fun onStart() {
        super.onStart()

        val filter = IntentFilter(Intent.ACTION_BOOT_COMPLETED)
        registerReceiver(receiver, filter)
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(receiver)
    }
}