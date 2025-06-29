package com.example.harrypoterapi.ui.spells

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.harrypoterapi.databinding.ActivitySpellsBinding
import com.example.harrypoterapi.repository.HpRepository
import kotlinx.coroutines.launch

class SpellsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySpellsBinding
    private val repo = HpRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpellsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvSpells.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            val spells = repo.spells()
            binding.rvSpells.adapter = SpellAdapter(spells) { spell ->
                startActivity(
                    Intent(this@SpellsActivity, SpellDetailActivity::class.java)
                        .putExtra("spellName", spell.name)
                        .putExtra("spellDesc", spell.description ?: "Sem descrição")
                )
            }
        }
    }
}