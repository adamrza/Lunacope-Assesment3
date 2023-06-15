package com.example.lunacope.settings.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.lunacope.R
import com.example.lunacope.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {
  private var _binding: FragmentSettingsBinding? = null
  private val binding get() = _binding!!

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    _binding = FragmentSettingsBinding.inflate(inflater,container,false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    bindSettings()
  }

  private fun bindSettings() {
    with(binding) {
      clSettingsName.setOnClickListener {
        findNavController().navigate(R.id.action_to_edit_name)
      }
      clSettingsSign.setOnClickListener {
        findNavController().navigate(R.id.action_to_edit_sign)
      }
      clSettingsAbout.setOnClickListener {
        findNavController().navigate(R.id.action_to_about)
      }
    }
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}