package com.baharudin.animex.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.baharudin.animex.R
import com.baharudin.animex.databinding.FragmentFavoritBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritFragment : Fragment(R.layout.fragment_favorit) {

    private var _binding : FragmentFavoritBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentFavoritBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
    }
}