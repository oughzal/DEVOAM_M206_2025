package com.example.m203

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class MyBReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        when(intent?.action) {
            Intent.ACTION_BOOT_COMPLETED -> {

            }

        }
    }
}