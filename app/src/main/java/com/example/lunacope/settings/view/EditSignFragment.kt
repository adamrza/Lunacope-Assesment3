package com.example.lunacope.settings.view

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.lunacope.R
import com.example.lunacope.databinding.BottomSheetSignBinding
import com.example.lunacope.databinding.FragmentEditSignBinding
import com.example.lunacope.viewmodel.SignViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

class EditSignFragment : Fragment() {

  private var _binding: FragmentEditSignBinding? = null
  private val binding get() = _binding!!
  private val signViewModel: SignViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    _binding = FragmentEditSignBinding.inflate(inflater,container,false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val sharedPreferences: SharedPreferences = requireActivity().getSharedPreferences("USER",
      Context.MODE_PRIVATE
    )
    val sign = sharedPreferences.getString("SIGN","").toString()
    binding.etSign.setText(sign)
    bindChooseSign()

    binding.btChange.setOnClickListener {
      if(binding.etSign.text!!.isNotEmpty()) {
        sharedPreferences.edit().putString("SIGN",binding.etSign.text.toString()).apply()
        findNavController().navigate(R.id.action_back_to_settings)
      }
    }

    binding.tbCustom.appToolbar.setNavigationOnClickListener {
      findNavController().navigate(R.id.action_back_to_settings)
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

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}