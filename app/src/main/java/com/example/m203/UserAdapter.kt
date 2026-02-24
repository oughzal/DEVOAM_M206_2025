package com.example.m203

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class UserAdapter(context: android.content.Context, items: List<User>) :
    ArrayAdapter<User>(context, R.layout.user_item, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.user_item, parent, false)

        val user = getItem(position)!!

        val firstName: TextView = view.findViewById(R.id.firstName)
        val lastName: TextView = view.findViewById(R.id.lastName)
        val btnEdite: ImageView = view.findViewById(R.id.btnEdite)
        val btnDelete: ImageView = view.findViewById(R.id.btnDelete)

        firstName.text = user.firstName
        lastName.text = user.lastName

        return view
    }
}