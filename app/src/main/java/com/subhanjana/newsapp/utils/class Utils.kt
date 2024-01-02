package com.subhanjana.newsapp.utils

import com.subhanjana.newsapp.data.model.Country

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
    }
}