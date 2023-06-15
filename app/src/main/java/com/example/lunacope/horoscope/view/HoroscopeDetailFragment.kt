package com.example.lunacope.horoscope.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.lunacope.viewmodel.HoroscopeViewModel
import com.example.lunacope.R
import com.example.lunacope.databinding.FragmentHoroscopeDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat

@AndroidEntryPoint
class HoroscopeDetailFragment : Fragment() {

  private var _binding: FragmentHoroscopeDetailBinding? = null
  private val binding get() = _binding!!
  private val viewModel : HoroscopeViewModel by viewModels()
  private val args : HoroscopeDetailFragmentArgs by navArgs()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    _binding = FragmentHoroscopeDetailBinding.inflate(inflater,container,false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding.progressBar.isVisible = true
    getHoroscope()
    observeHoroscope()

    binding.tbCustom.appToolbar.setNavigationOnClickListener {
      findNavController().navigate(R.id.action_back_to_horoscope)
    }
  }

  private fun observeHoroscope() {

    viewModel.horoscopeResponse.observe(viewLifecycleOwner, {
      if(it != null) {
        binding.tvSignTitle.setText(it.sign)
        Glide.with(requireActivity())
          .load(it.icon)
          .into(binding.ivSign)
        binding.tvHoroscope.setText(it.horoscope)

        val date = SimpleDateFormat("yyyy-MM-dd").parse(it.date)
        val formatter = SimpleDateFormat("EEE, dd MMM yyyy")
        binding.tvDate.setText(formatter.format(date))

        binding.progressBar.isVisible = false
      }
    })
  }

  private fun getHoroscope() {
    val sign = args.sign
    val date = args.date
    viewModel.getHoroscopeRespone(sign.lowercase(), date)
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}