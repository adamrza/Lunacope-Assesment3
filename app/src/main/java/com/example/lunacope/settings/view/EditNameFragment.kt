package com.example.lunacope.settings.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.lunacope.R
import com.example.lunacope.databinding.FragmentEditNameBinding

class EditNameFragment : Fragment() {
  private var _binding: FragmentEditNameBinding? = null
  private val binding get() = _binding!!

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    _binding = FragmentEditNameBinding.inflate(inflater,container,false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val sharedPreferences: SharedPreferences = requireActivity().getSharedPreferences("USER",
      Context.MODE_PRIVATE
    )
    val username = sharedPreferences.getString("NAME","").toString()
    binding.etName.setText(username)

    binding.btChange.setOnClickListener {
      if(binding.etName.text!!.isNotEmpty()) {
        sharedPreferences.edit().putString("NAME",binding.etName.text.toString()).apply()
        findNavController().navigate(R.id.action_back_to_settings)
      }
    }

    binding.tbCustom.appToolbar.setNavigationOnClickListener {
      findNavController().navigate(R.id.action_back_to_settings)
    }
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }

}