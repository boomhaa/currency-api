package com.example.data.repository

import org.w3c.dom.Element
import java.net.URI
import java.util.concurrent.ConcurrentHashMap
import javax.xml.parsers.DocumentBuilderFactory

class CurrencyRepository {
    private val rates = ConcurrentHashMap<String, Double>()

    init {
        refreshRates()
    }

    fun refreshRates(){
        try {
            val url = URI("https://www.cbr.ru/scripts/XML_daily.asp").toURL()
            val doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(url.openStream())
            val nodes = doc.getElementsByTagName("Valute")
            rates.clear()

            for (i in 0 until nodes.length){
                val elem = nodes.item(i) as Element
                val code = elem.getElementsByTagName("CharCode").item(0).textContent
                val nominal = elem.getElementsByTagName("Nominal").item(0).textContent.toDouble()
                val value = elem.getElementsByTagName("Value").item(0).textContent.replace(',', '.').toDouble()
                rates[code] = value / nominal
            }
            rates["RUB"] = 1.0
            println("Currency rates updated successfully.")
        }catch (e: Exception){
            println("Error fetching rates: ${e.message}")
        }
    }

    fun getRate(code: String): Double? = rates[code.uppercase()]
}