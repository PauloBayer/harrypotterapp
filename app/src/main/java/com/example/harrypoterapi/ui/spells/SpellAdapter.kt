package com.example.harrypoterapi.ui.spells

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.harrypoterapi.data.model.Spell
import com.example.harrypoterapi.databinding.ItemSpellBinding

class SpellAdapter(
    private val spells: List<Spell>,
    private val onClick: (Spell) -> Unit
) : RecyclerView.Adapter<SpellAdapter.VH>() {

    inner class VH(val binding: ItemSpellBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(spell: Spell) = with(binding) {
            tvSpellName.text = spell.name
            root.setOnClickListener { onClick(spell) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemSpellBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(spells[position])
    }

    override fun getItemCount() = spells.size
}