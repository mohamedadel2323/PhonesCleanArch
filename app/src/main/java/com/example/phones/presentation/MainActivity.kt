package com.example.phones.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phones.R
import com.example.phones.data.remote.PhonesApiClient
import com.example.phones.data.repository.RepositoryImp
import com.example.phones.databinding.ActivityMainBinding
import com.example.phones.domain.usecase.PhonesUseCase
import com.example.phones.utils.MyViewModelFactory
import com.example.phones.utils.visibleIf

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var phonesScreenViewModel: PhonesScreenViewModel
    private lateinit var adapter: PhonesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        phonesScreenViewModel = ViewModelProvider(
            this,
            MyViewModelFactory(PhonesUseCase(RepositoryImp(PhonesApiClient())))
        )[PhonesScreenViewModel::class.java]

        initUi()
        observers()
    }

    private fun observers() {
        phonesScreenViewModel.phonesScreenState.observe(this) {
            if (it.phones.isNotEmpty()) {
                Log.e("blabla", it.phones.toString())
                adapter.submitList(it.phones)
            }
            binding.progressBar visibleIf it.loading
        }
    }

    private fun initUi() {
        adapter = PhonesAdapter()
        binding.phonesRv.adapter = adapter
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.phonesRv.layoutManager = layoutManager
    }
}