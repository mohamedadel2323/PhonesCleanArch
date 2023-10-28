package com.example

import com.example.phones.data.remote.PhonesApiClient
import com.example.phones.data.repository.RepositoryImp
import com.example.phones.domain.usecase.PhonesUseCase
import com.example.phones.utils.MyViewModelFactory

class DependenciesProvider {
    private val remoteSource = PhonesApiClient()
    private val repository = RepositoryImp(remoteSource)
    private val phonesUseCase = PhonesUseCase(repository)
    val myViewModelFactory = MyViewModelFactory(phonesUseCase)
}