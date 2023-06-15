package com.example.lunacope.onboarding.view

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.lunacope.MainActivity
import com.example.lunacope.R
import com.example.lunacope.databinding.ActivityWelcomeBinding
import com.example.lunacope.databinding.BottomSheetSignBinding
import com.example.lunacope.viewmodel.SignViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WelcomeActivity : AppCompatActivity() {
  private lateinit var binding : ActivityWelcomeBinding
  private val signViewModel: SignViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityWelcomeBinding.inflate(layoutInflater)
    setContentView(binding.root)
    bindChooseSign()
    bindEnterBtn()
  }

  private fun bindChooseSign() {
    binding.etSign.setOnClickListener {
      val chooseSign = BottomSheetDialog(this@WelcomeActivity)
      val inflater = LayoutInflater.from(this)
      val layoutBinding = BottomSheetSignBinding.inflate(inflater)
      chooseSign.setContentView(layoutBinding.root)
      chooseSign.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
      chooseSign.behavior.isDraggable = false
      chooseSign.behavior.setState(BottomSheetBehavior.STATE_EXPANDED)
      val signList = signViewModel.getSignList()
      val adapter = ArrayAdapter(this@WelcomeActivity, R.layout.item_sign_layout, signList)
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
        if(etName.text!!.isNotEmpty() && etSign.text!!.isNotEmpty()) {
          val sharedPreferences: SharedPreferences = getSharedPreferences("USER", MODE_PRIVATE)
          sharedPreferences.edit().putString("NAME",etName.text.toString()).apply()
          sharedPreferences.edit().putString("SIGN", etSign.text.toString()).apply()
          startActivity(Intent(this@WelcomeActivity, MainActivity::class.java))
        }
      }
    }
  }

}