package com.example

import com.example.routes.currencyRoutes
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {

    routing {
        route("/api/v1") {
            currencyRoutes()
        }
    }
}
