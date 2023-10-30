package com.example.phones.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.MyApplication
import com.example.phones.R
import com.example.phones.databinding.ActivityMainBinding
import com.example.phones.utils.collectLatestLifeCycleFlow
import com.example.phones.utils.collectLifeCycleFlow
import com.example.phones.utils.visibleIf
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val phonesScreenViewModel: PhonesScreenViewModel by lazy { ViewModelProvider(this,(application as MyApplication).dependenciesProvider.myViewModelFactory)[PhonesScreenViewModel::class.java] }
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
}