package com.example.m203

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class DashboardFragment : Fragment() {
    lateinit var tvMessage : TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       tvMessage = view.findViewById<TextView>(R.id.tvMessage)
        val message = this.arguments?.getString("message") ?: "Aucun message reçu"
        tvMessage.text = message

    }

    fun getMessageFromHome(message: String){
        tvMessage.text = message
    }
}