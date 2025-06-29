package com.example.harrypoterapi.ui.staff

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.harrypoterapi.databinding.ActivityStaffBinding
import com.example.harrypoterapi.repository.HpRepository
import kotlinx.coroutines.launch

class StaffActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStaffBinding
    private val repo = HpRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStaffBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnFiltrar.setOnClickListener {
            val filter = binding.etNome.text.toString().trim()

            lifecycleScope.launch {
                val staffList = repo.staff()
                val result = if (filter.isEmpty()) staffList
                else staffList.filter { it.name.contains(filter, ignoreCase = true) }

                binding.tvStaff.text = result.joinToString("\n\n") { member ->
                    val altNames = if (member.alternateNames.isEmpty())
                        "—"
                    else
                        member.alternateNames.joinToString()

                    """
                    Nome: ${member.name}
                    Espécie: ${member.species}
                    Casa: ${member.house ?: "—"}
                    Nomes alternativos: $altNames
                    """.trimIndent()
                }
            }
        }
    }
}