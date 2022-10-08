package com.example.recyclerview.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.provider.Settings.Global.getString
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recyclerview.R
import com.example.recyclerview.Skills
import com.example.recyclerview.databinding.ItemSkillsLayoutBinding
import com.google.android.material.snackbar.Snackbar

/**
 * Created by Oscar Arce
 * on 05/10/2022.
 */
class SkillsViewHolder(holder: View) : RecyclerView.ViewHolder(holder) {
    private val binding = ItemSkillsLayoutBinding.bind(holder)

    fun render(
        skillsModel: Skills,
        onClickListener: (Skills) -> Unit,
        onClickDelete: (Int) -> Unit
    ) {
        initValues(skillsModel)

        // Listeners image, button delete and item
        binding.ivImage.setOnClickListener { clickImage(skillsModel)}
        binding.btnDelete.setOnClickListener { onClickDelete(bindingAdapterPosition) }
        itemView.setOnClickListener { onClickListener(skillsModel) }
    }

    @SuppressLint("SetTextI18n")
    private fun initValues(skillsModel: Skills) {
        binding.tvName.text = skillsModel.skillName
        binding.tvType.text = "Type: ${skillsModel.type}"
        binding.tvLevel.text = "Level: ${skillsModel.level}"
        // Load image
        Glide.with(binding.ivImage.context).load(skillsModel.image).into(binding.ivImage)
    }

    private fun clickImage(skills: Skills) {
        val snackbar = Snackbar.make(binding.ivImage, skills.skillName, Snackbar.LENGTH_LONG)

        snackbar.setAction("UNDO", clickUndoAction())
        snackbar.setActionTextColor(Color.RED)
        snackbar.setTextColor(Color.WHITE)
        snackbar.duration = Snackbar.LENGTH_LONG
        snackbar.animationMode = Snackbar.ANIMATION_MODE_SLIDE
        snackbar.setBackgroundTint(ContextCompat.getColor(binding.ivImage.context, R.color.black))
        snackbar.show()
    }

    private fun clickUndoAction() = View.OnClickListener {
        Toast.makeText(binding.ivImage.context, "Esto es una prueba", Toast.LENGTH_LONG)
            .show()
    }
}