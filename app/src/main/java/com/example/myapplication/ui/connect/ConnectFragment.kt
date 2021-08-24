package com.example.myapplication.ui.connect

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.application.JobHuntApplication
import com.example.myapplication.databinding.FragmentConnectBinding
import com.example.myapplication.ui.adapters.CandidateAdapter

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
        _binding.rvCandidates.layoutManager = GridLayoutManager(requireActivity(), 2)
        val candidatesAdapter = CandidateAdapter(this)

        _binding.rvCandidates.adapter = candidatesAdapter

        connectViewModel.candidates.observe(viewLifecycleOwner) { candidates ->
            candidates.let {
                if (it.isNotEmpty()) {
                    candidatesAdapter.candidateslist(it)
                }
            }
        }
    }
}