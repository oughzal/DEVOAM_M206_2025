package com.example.m203

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.m203.databinding.ActivityMainBinding
import com.example.m203.model.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val countries = RetrofitClient.apiService
                    .getCountries()
                    .map { it.toCountry() }
                    .toMutableList()

                // gréer l'adapter et le recycler view
                withContext(Dispatchers.Main){
                    val adapter = CountryAdapter(this@MainActivity,countries)
                    binding.lvCountry.adapter = adapter
                }


            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }
}