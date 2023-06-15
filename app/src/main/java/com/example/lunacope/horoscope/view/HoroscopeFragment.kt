package com.example.lunacope.horoscope.view

import android.app.DatePickerDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.lunacope.R
import com.example.lunacope.databinding.BottomSheetSignBinding
import com.example.lunacope.databinding.FragmentHoroscopeBinding
import com.example.lunacope.viewmodel.SignViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.text.SimpleDateFormat
import java.util.Calendar

class HoroscopeFragment : Fragment() {
  private var _binding: FragmentHoroscopeBinding? = null
  private val binding get() = _binding!!
  private val signViewModel: SignViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    _binding = FragmentHoroscopeBinding.inflate(inflater,container,false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    bindChooseSign()
    bindChooseDate()
    bindEnterBtn()
  }

  private fun bindChooseDate() {
    binding.etDate.setOnClickListener {
      val calendar = Calendar.getInstance()
      val year = calendar.get(Calendar.YEAR)
      val month = calendar.get(Calendar.MONTH)
      val day = calendar.get(Calendar.DAY_OF_MONTH)


      val datePickerDialog = DatePickerDialog(requireActivity(), DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

        calendar.set(year, monthOfYear, dayOfMonth)
        val formatter = SimpleDateFormat("MMM dd, yyyy")
        binding.etDate.setText(formatter.format(calendar.time))

      }, year, month, day)

      calendar.set(year, month, day)
      datePickerDialog.datePicker.maxDate = calendar.timeInMillis
      calendar.set(year, month-1, day)
      datePickerDialog.datePicker.minDate = calendar.timeInMillis

      datePickerDialog.show()
    }
  }

  private fun bindChooseSign() {
    binding.etSign.setOnClickListener {
      val chooseSign = BottomSheetDialog(requireActivity())
      val inflater = LayoutInflater.from(requireActivity())
      val layoutBinding = BottomSheetSignBinding.inflate(inflater)
      chooseSign.setContentView(layoutBinding.root)
      chooseSign.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
      chooseSign.behavior.isDraggable = false
      chooseSign.behavior.setState(BottomSheetBehavior.STATE_EXPANDED)
      val signList = signViewModel.getSignList()
      val adapter = ArrayAdapter(requireActivity(), R.layout.item_sign_layout, signList)
      layoutBinding.lvBottomSheet.adapter = adapter
      chooseSign.show()
      layoutBinding.ivCross.setOnClickListener {
        chooseSign.dismiss()
      }
      layoutBinding.lvBottomSheet.setOnItemClickListener { parent, view, position, id ->
        val selectedSign = signList.get(position)
        binding.etSign.setText(selectedSign)
        chooseSign.dismiss()
      }
    }
  }

  private fun bindEnterBtn() {
    binding.btEnter.setOnClickListener {
      with(binding) {
        if(etDate.text!!.isNotEmpty() && etSign.text!!.isNotEmpty()) {
          val date = SimpleDateFormat("MMM dd, yyyy").parse(etDate.text.toString())
          val formatter = SimpleDateFormat("yyyy-MM-dd")
          val direction = HoroscopeFragmentDirections.actionToHoroscopeDetail(etSign.text.toString(),formatter.format(date))
          findNavController().navigate(direction)
        }
      }
    }
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }

}