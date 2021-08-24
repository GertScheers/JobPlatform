package com.example.myapplication.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemCandidateLayoutBinding
import com.example.myapplication.models.entities.User

class CandidateAdapter(private val fragment: Fragment) :
    RecyclerView.Adapter<CandidateAdapter.ViewHolder>() {

    private var candidates: List<User> = listOf()

    class ViewHolder(view: ItemCandidateLayoutBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemCandidateLayoutBinding = ItemCandidateLayoutBinding.inflate(
            LayoutInflater.from(fragment.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //TODO Implement setting the image + click handler
    }

    override fun getItemCount(): Int {
        return candidates.size
    }

    fun candidateslist(list: List<User>) {
        candidates = list
        notifyDataSetChanged()
    }
}