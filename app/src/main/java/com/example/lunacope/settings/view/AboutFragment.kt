package com.example.lunacope.settings.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.lunacope.R
import com.example.lunacope.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {

  private var _binding: FragmentAboutBinding? = null
  private val binding get() = _binding!!

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    _binding = FragmentAboutBinding.inflate(inflater,container,false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding.tbCustom.appToolbar.setNavigationOnClickListener {
      findNavController().navigate(R.id.action_back_to_settings)
    }
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}