package com.baharudin.animex.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.baharudin.animex.R
import com.baharudin.animex.databinding.ActivityBaseBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BaseActivity : AppCompatActivity() {

    private lateinit var navController : NavController
    private lateinit var binding : ActivityBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navigationGraph = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_main) as NavHostFragment
        navController = navigationGraph.findNavController()
    }
}