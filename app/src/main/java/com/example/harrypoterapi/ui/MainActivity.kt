package com.example.harrypoterapi.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.harrypoterapi.R
import com.example.harrypoterapi.databinding.ActivityMainBinding
import com.example.harrypoterapi.ui.spells.SpellsActivity
import com.example.harrypoterapi.ui.character.CharacterActivity
import com.example.harrypoterapi.ui.staff.StaffActivity
import com.example.harrypoterapi.ui.students.StudentsActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCharacter.setOnClickListener {
            startActivity(Intent(this, CharacterActivity::class.java))
        }
        binding.btnStaff.setOnClickListener {
            startActivity(Intent(this, StaffActivity::class.java))
        }
        binding.btnStudents.setOnClickListener {
            startActivity(Intent(this, StudentsActivity::class.java))
        }
        binding.btnSpells.setOnClickListener {
            startActivity(Intent(this, SpellsActivity::class.java))
        }
        binding.btnExit.setOnClickListener { finishAffinity() }
    }
}