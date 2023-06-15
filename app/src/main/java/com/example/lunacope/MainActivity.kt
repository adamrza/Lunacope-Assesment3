package com.example.lunacope

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.forEach
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.lunacope.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
  private lateinit var navController: NavController
  private lateinit var binding: ActivityMainBinding
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    val hostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    navController = hostFragment.navController

    NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)

    navController.addOnDestinationChangedListener { controller, destination, _ ->
      if (destination.id != binding.bottomNavigationView.selectedItemId) {
        controller.backQueue.asReversed().drop(1).forEach { entry ->
          binding.bottomNavigationView.menu.forEach { item ->
            if (entry.destination.id == item.itemId) {
              item.isChecked = true
              return@addOnDestinationChangedListener
            }
          }
        }
      }
    }

  }
}