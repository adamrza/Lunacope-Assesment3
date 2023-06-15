package com.example.lunacope.dashboard.view

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.lunacope.viewmodel.HoroscopeViewModel
import com.example.lunacope.databinding.FragmentDashboardBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Date

@AndroidEntryPoint
class DashboardFragment : Fragment() {

  private var _binding: FragmentDashboardBinding? = null
  private val binding get() = _binding!!
  private lateinit var username: String
  private lateinit var sign: String
  private val viewModel : HoroscopeViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
  }
  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    _binding = FragmentDashboardBinding.inflate(inflater,container,false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val sharedPreferences: SharedPreferences = requireActivity().getSharedPreferences("USER", MODE_PRIVATE)
    username = sharedPreferences.getString("NAME","").toString()
    sign = sharedPreferences.getString("SIGN","").toString()

    bindData()
    getDailyHoroscope()
    observeHoroscope()

  }

  private fun bindData() {
    val formatter = SimpleDateFormat("EEE, dd MMM yyyy")
    binding.tvUsername.setText(username)
    binding.tvTodayDate.setText(formatter.format(Date()))
  }

  private fun observeHoroscope() {

    viewModel.horoscopeResponse.observe(viewLifecycleOwner, {
      if(it != null) {
        binding.tvSignTitle.setText(it.sign)
        Glide.with(requireActivity())
          .load(it.icon)
          .into(binding.ivSign)
        binding.tvHoroscope.setText(it.horoscope)
        binding.progressBar.isVisible = false
      }
    })
  }

  private fun getDailyHoroscope() {
    val formatter = SimpleDateFormat("yyyy-MM-dd")
    viewModel.getHoroscopeRespone(sign.lowercase(), formatter.format(Date()))
    binding.progressBar.isVisible = true
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}