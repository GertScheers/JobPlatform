package com.example.myapplication.ui.register

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentProfileBinding
import com.example.myapplication.databinding.RegisterFragmentBinding
import com.example.myapplication.ui.profile.ProfileViewModel

class RegisterFragment : Fragment() {

    private lateinit var galleryViewModel: RegisterViewModel
    private var _binding: RegisterFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var viewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.register_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(this)
            .load(R.mipmap.ic_launcher)
            .circleCrop()
            .into(binding.ivProfilePicture)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}