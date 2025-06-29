package com.example.harrypoterapi.ui.students

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.harrypoterapi.R
import com.example.harrypoterapi.databinding.ActivityStudentsBinding
import com.example.harrypoterapi.repository.HpRepository
import com.google.android.material.chip.Chip
import kotlinx.coroutines.launch

class StudentsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStudentsBinding
    private val repo = HpRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvStudents.layoutManager = LinearLayoutManager(this)

        binding.btnListar.setOnClickListener {
            val checkedId = binding.cgHouses.checkedChipId
            if (checkedId == -1) {
                Toast.makeText(this, "Escolha uma casa", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            binding.btnListar.isEnabled = false
            binding.btnListar.text = "Buscando…"

            val chipText  = findViewById<Chip>(checkedId).text.toString()
            val houseSlug = when (chipText.lowercase()) {
                "grifinória" -> "gryffindor"
                "sonserina"  -> "slytherin"
                "corvinal"   -> "ravenclaw"
                "lufa-lufa"  -> "hufflepuff"
                else -> ""
            }

            lifecycleScope.launch {
                runCatching { repo.students(houseSlug) }
                    .onSuccess { list ->
                        binding.rvStudents.adapter = StudentAdapter(list)
                    }
                    .onFailure { e ->
                        Toast.makeText(
                            this@StudentsActivity,
                            "Erro ao buscar alunos: ${e.localizedMessage}",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                binding.btnListar.isEnabled = true
                binding.btnListar.text = getString(R.string.buscar)
            }
        }
    }
}