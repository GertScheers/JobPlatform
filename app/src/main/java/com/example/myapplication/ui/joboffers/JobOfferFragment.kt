package com.example.myapplication.ui.joboffers

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.application.JobHuntApplication
import com.example.myapplication.databinding.FragmentMyProfileBinding
import com.example.myapplication.databinding.JobOfferFragmentBinding
import com.example.myapplication.models.entities.JobOffer
import com.example.myapplication.models.entities.User
import com.example.myapplication.ui.connect.ConnectFragmentDirections
import com.example.myapplication.ui.profile.MyProfileFragmentDirections
import com.example.myapplication.ui.profile.MyProfileViewModel
import com.example.myapplication.ui.profile.MyProfileViewModelFactory

class JobOfferFragment : Fragment() {
    private lateinit var _binding: JobOfferFragmentBinding
    private var user: User? = null

    private val jobOfferViewModel: JobOfferViewModel by viewModels {
        JobOfferViewModelFactory(
            (requireActivity().application as JobHuntApplication).userRepository,
            (requireActivity().application as JobHuntApplication).jobOfferRepository
        )
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = JobOfferFragmentBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun showJobOfferDetails(jobOffer: JobOffer) {
        //findNavController().navigate(JobOfferFragmentDirections)
    }
}