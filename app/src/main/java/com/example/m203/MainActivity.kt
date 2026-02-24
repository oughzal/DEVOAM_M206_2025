package com.example.m203

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.viewModelScope
import com.example.m203.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    val vm: UserViewModel by viewModels()
    lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = UserAdapter(this, emptyList())
        binding.lvPersonnes.adapter = adapter

        vm.userList.observe(this) { users ->
            adapter.clear()
            adapter.addAll(users)
            adapter.notifyDataSetChanged()
        }

        binding.btnAjouter.setOnClickListener {
            val prenom = binding.etPrenom.text.toString().trim()
            val nom = binding.etNom.text.toString().trim()
            if (prenom.isNotEmpty() && nom.isNotEmpty()) {
                val user = User(0, prenom, nom)
                vm.viewModelScope.launch(Dispatchers.IO) {
                    vm.userDao?.insertUser(user)
                    vm.getUsers()
                }
            }
        }
    }
}