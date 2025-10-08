package com.example.plugin

import com.example.di.currencyModule
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger
import io.ktor.server.application.*

fun Application.configureKoin() {
    install(Koin) {
        slf4jLogger()
        modules(currencyModule)
    }
}
