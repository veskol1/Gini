package com.example.ginitask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ginitask.adapter.GiniAdapter
import com.example.ginitask.databinding.ActivityMainBinding
import com.example.ginitask.viewmodel.GiniViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val giniViewModel by viewModels<GiniViewModel>()
    private val giniAdapter = GiniAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerview.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 3, GridLayoutManager.VERTICAL, false)
            adapter = giniAdapter
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                giniViewModel.uiState.collect { state ->
                    when (state.status) {
                        GiniViewModel.Status.DONE -> {
                            binding.progressBar.visibility = View.GONE
                            giniAdapter.items = state.giniNumbersList
                        }
                        GiniViewModel.Status.ERROR -> {
                            binding.progressBar.visibility = View.GONE
                        }
                        GiniViewModel.Status.LOADING -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }
    }
}