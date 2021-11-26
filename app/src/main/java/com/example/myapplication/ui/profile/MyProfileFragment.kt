package com.example.myapplication.ui.profile

import android.os.Bundle
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
import com.example.myapplication.models.entities.User

class MyProfileFragment : Fragment() {
    private lateinit var _binding: FragmentMyProfileBinding
    private var user: User? = null

    private val myProfileViewModel: MyProfileViewModel by viewModels {
        MyProfileViewModelFactory((requireActivity().application as JobHuntApplication).userRepository)
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyProfileBinding.inflate(inflater, container, false)

        binding.fabEditProfile.setOnClickListener {
            findNavController().navigate(
                MyProfileFragmentDirections.actionNavMyProfileToNavRegister(
                    user
                )
            )
        }

        myProfileViewModel.fullUser.observe(viewLifecycleOwner,
            { fullUser ->
                user = fullUser
                binding.tvDisplayName.text = user?.displayName
                binding.tvCity.text = user?.city
                binding.tvEmail.text = user?.email
            })

        myProfileViewModel.getUser(myProfileViewModel.user?.userId ?: "")

        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(this)
            .load(R.mipmap.ic_launcher)
            .circleCrop()
            .into(binding.ivProfilePicture)

        binding.tvDisplayName.text = user?.displayName
        binding.tvCity.text = user?.city
        binding.tvEmail.text = user?.email
    }
}