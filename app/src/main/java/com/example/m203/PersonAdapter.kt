package com.example.m203

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView

class PersonAdapter(
    context: Context,
    private val items: MutableList<Person>,
    private val onEdit: (Person) -> Unit,
    private val onDelete: (Person) -> Unit
) : ArrayAdapter<Person>(context, R.layout.person_item, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.person_item, parent, false)

        val person = getItem(position)!!

        view.findViewById<TextView>(R.id.tvNom).text    = person.nom
        view.findViewById<TextView>(R.id.tvPrenom).text = person.prenom

        view.findViewById<Button>(R.id.btnModifier).setOnClickListener { onEdit(person) }
        view.findViewById<Button>(R.id.btnSupprimer).setOnClickListener { onDelete(person) }

        return view
    }
}

