package com.example

import com.example.data.repository.CurrencyRepository
import com.example.plugin.configureKoin
import com.example.plugin.configureSerialization
import com.example.utils.updateScheduler
import io.ktor.server.application.*
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.koin.ktor.ext.inject

fun main(args: Array<String>) {
    embeddedServer(Netty, host = "0.0.0.0", port = 8080){
        configureKoin()
        configureSerialization()
        configureRouting()

        val repo: CurrencyRepository by inject()

        updateScheduler(repo)
    }.start(wait = true)
}

fun Application.module() {
    configureKoin()
    configureSerialization()
    configureRouting()
}
