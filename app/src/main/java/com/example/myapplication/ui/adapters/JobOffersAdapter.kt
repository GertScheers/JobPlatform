package com.example.myapplication.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemCandidateLayoutBinding
import com.example.myapplication.databinding.ItemJobofferLayoutBinding
import com.example.myapplication.models.entities.JobOffer
import com.example.myapplication.ui.connect.ConnectFragment
import com.example.myapplication.ui.joboffers.JobOfferFragment


class JobOffersAdapter(private val fragment: Fragment) :
        RecyclerView.Adapter<JobOffersAdapter.ViewHolder>() {

    private var jobOffers: List<JobOffer> = listOf()

    class ViewHolder(view: ItemJobofferLayoutBinding) : RecyclerView.ViewHolder(view.root) {
        val tvTitle = view.tvTitle
        val tvCompany = view.tvCompany
        val tvCity = view.tvCity
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemJobofferLayoutBinding = ItemJobofferLayoutBinding.inflate(
            LayoutInflater.from(fragment.context),
            parent,
            false
        )
        return JobOffersAdapter.ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //TODO Implement setting the image + click handler
        val jobOffer = jobOffers[position]
        holder.tvTitle.text = jobOffer.title
        holder.tvCity.text = jobOffer.city
        holder.tvCompany.text = jobOffer.companyId.toString()

        holder.itemView.setOnClickListener {
            if (fragment is JobOfferFragment)
                fragment.showJobOfferDetails(jobOffer)
        }
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}