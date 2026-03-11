package com.example.m203

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class HomeFragment : Fragment() {
    lateinit var tvWelcome: TextView
    lateinit var etMessage: EditText
    lateinit var btnSend: Button

    fun updateMessage(text: String) {
        tvWelcome.text = text
    }
    interface OnMessageSendListener {
        fun onMessageSend(message: String)
    }
    val messageSendListener: OnMessageSendListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        tvWelcome = view.findViewById(R.id.tvWelcome)
        etMessage = view.findViewById(R.id.etMessage)
        btnSend = view.findViewById(R.id.btnSend)

        val message = arguments?.getString("message") ?: "Aucun message reçu"

        tvWelcome.text = message
        btnSend.setOnClickListener {

            val message = etMessage.text.toString()

            messageSendListener?.onMessageSend(message)
        }
    }


}
