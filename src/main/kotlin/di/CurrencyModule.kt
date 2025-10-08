package com.example.di

import com.example.data.repository.CurrencyRepository
import com.example.domain.usecase.ConvertCurrencyUseCase
import org.koin.dsl.module

val currencyModule = module{
    single { CurrencyRepository() }
    single { ConvertCurrencyUseCase(get()) }

    single { CurrencyRepoHolder(get()) }
}

object CurrencyRepoHolder {
    lateinit var repo: CurrencyRepository
    operator fun invoke(repository: CurrencyRepository) {
        repo = repository
    }
}