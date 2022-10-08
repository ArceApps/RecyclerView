package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerview.adapter.SkillsAdapter
import com.example.recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var skillsMutableList: MutableList<Skills> =
        SkillsProvider.skillsLists.toMutableList()
    private lateinit var adapter: SkillsAdapter
    private val layoutManager = LinearLayoutManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        clickListener()
    }

    private fun initRecyclerView() {
        adapter = SkillsAdapter(
            skillsList = skillsMutableList,
            onClickListener = { skills -> onItemSelected(skills) },
            onClickDelete = { position -> onDeletedItem(position) })
        binding.recyclerViewMain.layoutManager = layoutManager
        //binding.recyclerViewMain.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewMain.adapter = adapter

        //binding.recyclerViewMain.addItemDecoration(itemDecorator(layoutManager))
    }

    private fun clickListener() {
        binding.btnAdd.setOnClickListener { createSkills() }
    }

    private fun onItemSelected(skills: Skills) {
        Toast.makeText(this, skills.skillName, Toast.LENGTH_LONG).show()
    }

    private fun createSkills() {
        val skills = Skills(
            "Kotlin Master",
            "Languages",
            "10/10",
            "https://user-images.githubusercontent.com/61239577/194725655-be927201-47ca-4828-8ecb-cd0b62babbb6.png"
        )
        skillsMutableList.add(skills)
        val position = skillsMutableList.lastIndex
        adapter.notifyItemInserted(position)
        layoutManager.scrollToPositionWithOffset(position, 10)
    }

    private fun onDeletedItem(position: Int) {
        skillsMutableList.removeAt(position)
        adapter.notifyItemRemoved(position)
    }

    private fun itemDecorator(layoutManager: LinearLayoutManager): DividerItemDecoration {
        val recyclerView = binding.recyclerViewMain.context
        val decorator = DividerItemDecoration(recyclerView, layoutManager.orientation)
        val divider = ContextCompat.getDrawable(recyclerView, R.drawable.divider)
        decorator.setDrawable(divider!!)
        return decorator
    }
}