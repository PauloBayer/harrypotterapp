package com.example.harrypoterapi.ui.spells

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.harrypoterapi.databinding.ActivitySpellDetailBinding

class SpellDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySpellDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpellDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvSpellTitle.text = intent.getStringExtra("spellName")
        binding.tvSpellDesc.text = intent.getStringExtra("spellDesc")
    }
}