package com.example.recyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.R
import com.example.recyclerview.Skills

/**
 * Created by Oscar Arce
 * on 05/10/2022.
 */
class SkillsAdapter(
    private val skillsList: List<Skills>,
    private val onClickListener: (Skills) -> Unit,
    private val onClickDelete: (Int) -> Unit
) : RecyclerView.Adapter<SkillsViewHolder>() {

    // Draw list of item of skills by item layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkillsViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        return SkillsViewHolder(
            layoutInflater.inflate(
                R.layout.item_skills_layout,
                parent,
                false
            )
        )
    }

    // llama a skills render
    override fun onBindViewHolder(holder: SkillsViewHolder, position: Int) {
        val item = skillsList[position]
        holder.render(item, onClickListener, onClickDelete)
    }

    // return size of RecyclerView
    override fun getItemCount(): Int {
        return skillsList.size
    }
}