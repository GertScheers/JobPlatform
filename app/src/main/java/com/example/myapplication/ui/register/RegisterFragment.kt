package com.example.myapplication.ui.register

import android.app.Application
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.application.JobHuntApplication
import com.example.myapplication.databinding.FragmentConnectBinding
import com.example.myapplication.databinding.FragmentRegisterBinding
import com.example.myapplication.models.entities.User

class RegisterFragment : Fragment() {

    private lateinit var _binding: FragmentRegisterBinding

    private val mRegisterViewModel: RegisterViewModel by viewModels {
        RegisterViewModelFactory((requireActivity().application as JobHuntApplication).userRepository)
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(this)
            .load(R.mipmap.ic_launcher)
            .circleCrop()
            .into(binding.ivProfilePicture)


        binding.radioCandidate.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                binding.tilCompanyName.visibility = View.GONE
                binding.tilFirstName.visibility = View.VISIBLE
                binding.tilLastName.visibility = View.VISIBLE
            }
        }

        binding.radioCompany.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                binding.tilCompanyName.visibility = View.VISIBLE
                binding.tilFirstName.visibility = View.GONE
                binding.tilLastName.visibility = View.GONE
            }
        }

        binding.btnRegister.setOnClickListener {
            val userType: Int =
                if (binding.rgUserType.checkedRadioButtonId == binding.radioCompany.id)
                    1
                else
                    0

            val firstName: String
            val lastName: String
            val companyName: String

            if (userType == 0) {
                firstName = binding.etFirstName.text.toString()
                lastName = binding.etLastName.text.toString()
                companyName = ""
            } else {
                companyName = binding.etCompanyName.text.toString()
                firstName = ""
                lastName = ""
            }

            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            val confirmPassword = binding.etConfirmPassword.text.toString()

            when {
                TextUtils.isEmpty(firstName) && userType == 0 -> {
                    Toast.makeText(
                        requireActivity(),
                        resources.getString(R.string.err_msg_enter_first_name),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                TextUtils.isEmpty(lastName) && userType == 0 -> {
                    Toast.makeText(
                        requireActivity(),
                        resources.getString(R.string.err_msg_enter_last_name),
                        Toast.LENGTH_SHORT
                    ).show()
                }

                TextUtils.isEmpty(companyName) && userType == 1 -> {
                    Toast.makeText(
                        requireActivity(),
                        resources.getString(R.string.err_msg_enter_company_name),
                        Toast.LENGTH_SHORT
                    ).show()
                }

                TextUtils.isEmpty(email) -> {
                    Toast.makeText(
                        requireActivity(),
                        resources.getString(R.string.err_msg_enter_email),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                TextUtils.isEmpty(password) -> {
                    Toast.makeText(
                        requireActivity(),
                        resources.getString(R.string.err_msg_enter_password),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                TextUtils.isEmpty(confirmPassword) -> {
                    Toast.makeText(
                        requireActivity(),
                        resources.getString(R.string.err_msg_confirm_password),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                password != confirmPassword -> {
                    Toast.makeText(
                        requireActivity(),
                        resources.getString(R.string.err_msg_passwords_no_match),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {
                    val user = User(
                        0,
                        firstName,
                        lastName,
                        companyName,
                        "",
                        null,
                        userType,
                        "",
                        email,
                        password
                    )

                    mRegisterViewModel.insert(user)
                    Toast.makeText(
                        requireActivity(),
                        "Registered successfully!",
                        Toast.LENGTH_SHORT
                    ).show()

                    findNavController().popBackStack()
                }
            }
        }
    }
}