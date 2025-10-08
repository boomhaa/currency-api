package com.example.routes

import com.example.domain.usecase.ConvertCurrencyUseCase
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.application
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import org.koin.ktor.ext.inject

fun Route.currencyRoutes() {

    val convertUseCase: ConvertCurrencyUseCase by application.inject()

    route("/convert") {
        get {
            val currencyFrom = call.request.queryParameters["from"]
            val currencyTo = call.request.queryParameters["to"]
            val amount = call.request.queryParameters["amount"]?.toDoubleOrNull()

            if (currencyFrom == null || currencyTo == null || amount == null) {
                call.respond(mapOf("error" to "Неверные параметры запроса"))
                return@get
            }
            val result = convertUseCase.execute(currencyFrom, currencyTo, amount)

            result.fold(
                onSuccess = {rate ->
                    call.respond(
                        mapOf(
                            "status" to "success",
                            "from" to currencyFrom,
                            "to" to currencyTo,
                            "amount" to amount.toString(),
                            "result" to rate.toString()
                        )
                    )
                },
                onFailure = { e ->
                    call.respond(mapOf("status" to "error","message" to (e.message ?: "Ошибка конвертации")))
                }
            )
        }
    }
}