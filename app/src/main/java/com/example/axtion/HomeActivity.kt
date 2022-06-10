package com.example.axtion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import com.example.axtion.databinding.ActivityHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityHomeBinding

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Home"

        val navView = findViewById<BottomNavigationView>(R.id.bottomNavigationMenu)
        navView.background = null

        val btnDictionary: ImageButton = findViewById(R.id.btn_dictionary)
        btnDictionary.setOnClickListener(this)
        btnDictionary.background = null

        auth = FirebaseAuth.getInstance()
    }

    override fun onClick(v: View){
        when(v.id){
            R.id.btn_dictionary ->{
                val moveDictionary = Intent(this, DictionaryActivity::class.java)
                startActivity(moveDictionary)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.btn_favorite -> {
                Intent(this, AboutActivity::class.java).also {
                    startActivity(it)
                }
            }
            R.id.btn_logout -> {
                auth.signOut()
                Intent(this@HomeActivity, LoginActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)
        return true
    }
}