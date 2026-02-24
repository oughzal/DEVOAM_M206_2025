package com.example.m203

import android.content.Context
import android.widget.ArrayAdapter

class PersonAdapter(context: Context) : ArrayAdapter<Person>(context, 0) {
        override fun getView(position: Int, convertView: android.view.View?, parent: android.view.ViewGroup): android.view.View {
            val view = convertView ?: android.view.LayoutInflater.from(context).inflate(R.layout.person_item, parent, false)
            val person = getItem(position)
            val tvPrenom = view.findViewById<android.widget.TextView>(R.id.tvPrenom)
            val tvNom = view.findViewById<android.widget.TextView>(R.id.tvNom)
            tvPrenom.text = person?.prenom ?: ""
            tvNom.text = person?.nom ?: ""
            return view
        }
}