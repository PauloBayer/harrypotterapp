package com.example.harrypoterapi.ui.character

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.harrypoterapi.data.model.Character
import com.example.harrypoterapi.databinding.ActivityCharacterBinding
import com.example.harrypoterapi.repository.HpRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharacterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCharacterBinding
    private val repo = HpRepository()
    private lateinit var fullList: List<Character>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharacterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // buscar todos os personagens logo que a tela abre
        lifecycleScope.launch {
            showLoading(true)
            runCatching { repo.allCharacters() }
                .onSuccess { characters: List<Character> ->
                    fullList = characters.sortedBy { c -> c.name }
                    configAutoComplete(fullList)
                }
                .onFailure { e: Throwable ->
                    Toast.makeText(
                        this@CharacterActivity,
                        "Falha ao carregar lista: ${e.localizedMessage}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            showLoading(false)
        }
    }

    // pra configurar o drop-down e o tratamento de clique
    private fun configAutoComplete(characters: List<Character>) = with(binding) {
        val names = characters.map { it.name }
        actCharacter.setAdapter(
            ArrayAdapter(
                this@CharacterActivity,
                android.R.layout.simple_list_item_1,
                names
            )
        )

        actCharacter.setOnItemClickListener { _, _, pos, _ ->
            val selected = characters[pos]
            loadCharacter(selected.id)
        }
    }

    // faz a requisição de detalhes usando o ID recebido
    private fun loadCharacter(id: String) {
        lifecycleScope.launch {
            showLoading(true)
            runCatching { repo.character(id) }
                .onSuccess { result: List<Character> ->
                    if (result.isNotEmpty()) showCharacter(result.first())
                    else Toast.makeText(
                        this@CharacterActivity,
                        "Personagem não encontrado",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .onFailure { e: Throwable ->
                    Toast.makeText(
                        this@CharacterActivity,
                        "Erro: ${e.localizedMessage}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            showLoading(false)
        }
    }

    private fun showCharacter(c: Character) = with(binding) {
        imgCharacter.load(c.image)
        tvInfo.text = """
            Nome: ${c.name}
            Espécie: ${c.species ?: "—"}
            Casa: ${c.house?.takeIf { it.isNotBlank() } ?: "—"}
            Patrono: ${c.patronus?.takeIf { it.isNotBlank() } ?: "—"}
            Ator(a): ${c.actor ?: "—"}
        """.trimIndent()
    }

    private fun showLoading(isLoading: Boolean) = with(binding) {
        progress.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}