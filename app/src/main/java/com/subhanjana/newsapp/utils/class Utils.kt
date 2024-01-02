package com.subhanjana.newsapp.utils

import com.subhanjana.newsapp.data.model.Country
import com.subhanjana.newsapp.data.model.Language

class Utils {
    companion object {
        fun getCountries() : List<Country>{
            return listOf(Country("ae", "UAE"),
                Country("ar","Argentina"),
                Country("at","Austria"),
                Country("au","Australia"),
                Country("be","Belgium"),
                Country("bg","Bulgaria"),
                Country("br","Brazil"),
                Country("ca","Canada"),
                Country("ch","Switzerland"),
                Country("cn","China"),
                Country("co","Columbia"),
                Country("cu","Cuba"),
                Country("cn","China"),
                Country("cz","Czechia"),
                Country("de","Germany"),
                Country("eg","Egypt"),
                Country("fr","France"),
                Country("gb","United Kingdom")
            )
        }

        fun getLanguages(): List<Language> {
            return listOf( Language("ar","Arabic"),
                Language("de","German"),
                Language("en","English"),
                Language("es","Spanish"),
                Language("fr","French"),
                Language("he","Hebrew"),
                Language("it","Italian"),
                Language("nl","Dutch"),
                Language("no","Norwegian"),
                Language("pt","Portuguese"),
                Language("ru","Russian"),
                Language("sv","Swedish")
            )
        }
    }
}