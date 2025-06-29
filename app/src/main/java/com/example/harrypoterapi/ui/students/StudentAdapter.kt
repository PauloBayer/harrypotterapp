package com.example.harrypoterapi.ui.students

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.harrypoterapi.databinding.ItemStudentBinding
import com.example.harrypoterapi.data.model.Character

class StudentAdapter(
    private val students: List<Character>
) : RecyclerView.Adapter<StudentAdapter.VH>() {

    inner class VH(private val binding: ItemStudentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(c: Character) {
            binding.tvStudentName.text = c.name
            binding.tvStudentHouse.text = c.house ?: "Sem casa"
            binding.imgStudent.load(c.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val b = ItemStudentBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return VH(b)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(students[position])
    }

    override fun getItemCount() = students.size
}