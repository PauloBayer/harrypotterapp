package com.example.harrypoterapi.ui.character

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.harrypoterapi.databinding.ActivityCharacterBinding
import com.example.harrypoterapi.repository.HpRepository
import kotlinx.coroutines.launch
import kotlin.collections.firstOrNull as firstOrNull1

class CharacterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCharacterBinding
    private val repo = HpRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharacterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBuscar.setOnClickListener {
            val idText = binding.etId.text.toString()
            if (idText.isBlank()) {
                Toast.makeText(this, "Digite um ID", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val id = idText.toInt()

            lifecycleScope.launch {
                runCatching { repo.character(id) }
                    .onSuccess { list ->
                        val c = list.firstOrNull1()
                        if (c != null) {
                            binding.imgCharacter.load(c.image)
                            binding.tvInfo.text = """
                                Nome: ${c.name}
                                Espécie: ${c.species}
                                Casa: ${c.house ?: "—"}
                            """.trimIndent()
                        } else {
                            Toast.makeText(
                                this@CharacterActivity,
                                "Personagem não encontrado",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                    .onFailure { e ->
                        Toast.makeText(
                            this@CharacterActivity,
                            "Erro: ${e.localizedMessage}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
            }
        }
    }
}
