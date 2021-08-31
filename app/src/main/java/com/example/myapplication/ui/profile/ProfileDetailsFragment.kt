package com.example.myapplication.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentProfileDetailsBinding

class ProfileDetailsFragment : Fragment() {

    private var _binding: FragmentProfileDetailsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProfileDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: ProfileDetailsFragmentArgs by navArgs()

        Glide.with(this)
            .load(R.mipmap.ic_launcher)
            .circleCrop()
            .into(binding.ivProfilePicture)

        binding.tvDisplayName.text = args.profileDetails.displayName
        binding.tvCity.text = args.profileDetails.city
        binding.tvEmail.text = args.profileDetails.email
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}