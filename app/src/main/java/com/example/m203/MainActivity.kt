package com.example.m203

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.m203.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var db: DatabaseHelper
    private lateinit var adapter: PersonAdapter
    private val personList = mutableListOf<Person>()

    // Person en cours de modification (null = mode Ajout)
    private var selectedPerson: Person? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DatabaseHelper(this)
        // ....


        // ── Adapter & ListView ──────────────────────────────────────────────
        adapter = PersonAdapter(
            context  = this,
            items    = personList,
            onEdit   = { person -> enterEditMode(person) },
            onDelete = { person -> confirmDelete(person) }
        )
        binding.lvPersonnes.adapter = adapter

        loadPersons()

        // ── Bouton Ajouter ──────────────────────────────────────────────────
        binding.btnAjouter.setOnClickListener {
            val nom    = binding.etNom.text.toString().trim()
            val prenom = binding.etPrenom.text.toString().trim()
            if (nom.isEmpty() || prenom.isEmpty()) {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            db.insert(Person(nom = nom, prenom = prenom))
            Toast.makeText(this, "Personne ajoutée", Toast.LENGTH_SHORT).show()
            clearForm()
            loadPersons()
        }

        // ── Bouton Modifier ─────────────────────────────────────────────────
        binding.btnModifier.setOnClickListener {
            val nom    = binding.etNom.text.toString().trim()
            val prenom = binding.etPrenom.text.toString().trim()
            if (nom.isEmpty() || prenom.isEmpty()) {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            selectedPerson?.let { p ->
                db.update(p.copy(nom = nom, prenom = prenom))
                Toast.makeText(this, "Personne modifiée", Toast.LENGTH_SHORT).show()
                clearForm()
                loadPersons()
            }
        }

        // ── Bouton Annuler ──────────────────────────────────────────────────
        binding.btnAnnuler.setOnClickListener { clearForm() }
    }

    // ── Charger la liste ────────────────────────────────────────────────────
    private fun loadPersons() {
        personList.clear()
        personList.addAll(db.getAll())
        adapter.notifyDataSetChanged()
    }

    // ── Passer en mode édition ──────────────────────────────────────────────
    private fun enterEditMode(person: Person) {
        selectedPerson = person
        binding.etNom.setText(person.nom)
        binding.etPrenom.setText(person.prenom)
        binding.btnAjouter.isEnabled  = false
        binding.btnModifier.isEnabled = true
        binding.btnAnnuler.visibility = View.VISIBLE
    }

    // ── Confirmation de suppression ─────────────────────────────────────────
    private fun confirmDelete(person: Person) {
        AlertDialog.Builder(this)
            .setTitle("Supprimer")
            .setMessage("Supprimer ${person.nom} ${person.prenom} ?")
            .setPositiveButton("Oui") { _, _ ->
                db.delete(person.id)
                Toast.makeText(this, "Personne supprimée", Toast.LENGTH_SHORT).show()
                loadPersons()
            }
            .setNegativeButton("Non", null)
            .show()
    }

    // ── Réinitialiser le formulaire ─────────────────────────────────────────
    private fun clearForm() {
        selectedPerson = null
        binding.etNom.text.clear()
        binding.etPrenom.text.clear()
        binding.btnAjouter.isEnabled  = true
        binding.btnModifier.isEnabled = false
        binding.btnAnnuler.visibility = View.GONE
    }
}