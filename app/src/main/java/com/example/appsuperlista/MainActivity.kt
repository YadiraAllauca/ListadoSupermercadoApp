package com.example.appsuperlista

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appsuperlista.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val newFragment =InitFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.lyfrag2, newFragment).commit()
        botones()
    }


    fun botones() {
        binding.btnPresupuesto.setOnClickListener {
            val newFragment =PresupuestoFragment()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.lyfrag2, newFragment).commit()
        }
        binding.btnListado.setOnClickListener {
            val newFragment = ListadoFragment()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.lyfrag2, newFragment).commit()
        }

    }


    }
