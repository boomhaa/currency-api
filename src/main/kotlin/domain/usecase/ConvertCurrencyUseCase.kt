package com.example.domain.usecase

import com.example.data.repository.CurrencyRepository

class ConvertCurrencyUseCase(
    private val repository: CurrencyRepository
) {
    fun execute(from: String, to: String, amount: Double): Result<Double>{
        val fromRate = repository.getRate(from)
        val toRate = repository.getRate(to)

        return if (fromRate != null && toRate != null){
            val res = amount / fromRate * toRate
            Result.success(res)
        } else{
            Result.failure(IllegalArgumentException("Invalid currency code"))
        }
    }
}