package com.example.myapplication.ui.connect

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.application.JobHuntApplication
import com.example.myapplication.databinding.FragmentConnectBinding
import com.example.myapplication.models.entities.User
import com.example.myapplication.models.entities.UserType
import com.example.myapplication.ui.adapters.ProfilesAdapter

class ConnectFragment : Fragment() {

    private lateinit var _binding: FragmentConnectBinding

    private val connectViewModel: ConnectViewModel by viewModels {
        ConnectViewModelFactory((requireActivity().application as JobHuntApplication).userRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentConnectBinding.inflate(inflater, container, false)

        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding.rvProfiles.layoutManager = GridLayoutManager(requireActivity(), 2)

        val profilesAdapter = ProfilesAdapter(this)

        _binding.rvProfiles.adapter = profilesAdapter
        _binding.rvProfiles.addItemDecoration(
            MarginItemDecoration(
                resources.getDimension(R.dimen._5sdp).toInt()
            )
        )

        connectViewModel.user.observe(viewLifecycleOwner) { user ->
            if (user == null) return@observe

            if (user.userType == UserType.Company.ordinal) {
                connectViewModel.candidates.observe(viewLifecycleOwner) { candidates ->
                    candidates.let {
                        if (it.isNotEmpty()) {
                            profilesAdapter.profilesList(it)
                        }
                    }
                }
            } else {
                connectViewModel.companies.observe(viewLifecycleOwner) { companies ->
                    companies.let {
                        if (it.isNotEmpty()) {
                            profilesAdapter.profilesList(it)
                        }
                    }
                }
            }
        }
    }

    fun showProfileDetails(user: User) {
        findNavController().navigate(ConnectFragmentDirections.actionNavConnectToProfileDetails(user))
    }
}

class MarginItemDecoration(private val spaceHeight: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State
    ) {
        with(outRect) {
            if (parent.getChildAdapterPosition(view) == 0 || parent.getChildAdapterPosition(view) == 1) {
                top = spaceHeight
            }
            left = spaceHeight
            right = spaceHeight
            bottom = spaceHeight
        }
    }
}