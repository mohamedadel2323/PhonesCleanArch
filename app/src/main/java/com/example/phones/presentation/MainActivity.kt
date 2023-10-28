package com.example.phones.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.MyApplication
import com.example.phones.R
import com.example.phones.databinding.ActivityMainBinding
import com.example.phones.utils.visibleIf
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val phonesScreenViewModel: PhonesScreenViewModel by lazy {
        (application as MyApplication).dependenciesProvider.myViewModelFactory.create(
            PhonesScreenViewModel::class.java
        )
    }
    private lateinit var adapter: PhonesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initUi()
        observers()
    }
    private fun initUi() {
        adapter = PhonesAdapter()
        binding.phonesRv.adapter = adapter
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.phonesRv.layoutManager = layoutManager
    }
    private fun observers() {
        collectLatestLifeCycleFlow(phonesScreenViewModel.phonesScreenState) {
            if (it.phones.isNotEmpty()) { adapter.submitList(it.phones) }
            binding.progressBar visibleIf it.loading
        }

        collectLifeCycleFlow(phonesScreenViewModel.errorState) { errorState ->
            errorState.errorMessage.let { Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT).show() }
        }
    }
    private fun <T> collectLatestLifeCycleFlow(flow: Flow<T>, collect: suspend (T) -> Unit) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) { flow.collectLatest(collect) }
        }
    }

    private fun <T> collectLifeCycleFlow(flow: Flow<T>, collect: suspend (T) -> Unit) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) { flow.collect(collect) }
        }
    }
}