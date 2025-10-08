package com.example.utils

import com.example.data.repository.CurrencyRepository
import io.ktor.server.application.Application
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.LocalDateTime
import kotlin.time.Duration.Companion.seconds


@OptIn(DelicateCoroutinesApi::class)
fun Application.updateScheduler(repository: CurrencyRepository) {
    GlobalScope.launch {
        while (true){
            val now = LocalDateTime.now()
            val nextMidnight = now.toLocalDate().plusDays(1).atStartOfDay()
            val delaySeconds = Duration.between(now, nextMidnight).seconds
            delay(delaySeconds.seconds)
            repository.refreshRates()
        }
    }
}