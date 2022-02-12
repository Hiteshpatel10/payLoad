package com.geekaid.payload.util

fun stateCityList(state: String): List<String> {

    return when (state) {
        "Andhra Pradesh" -> return CityList.andraPradeshCityList
        "Arunachal Pradesh" -> return CityList.arunachalPradeshCityList
        "Assam" -> return CityList.assamCityList
        "Bihar" -> return CityList.biharCityList
        "Chandigarh" -> return listOf()
        "Chhattisgarh" -> return CityList.chhattisgarhCityList
        "Dadra and Nagar Haveli" -> return CityList.dadraAndNagarHaveliPradeshCityList
        "Daman and Diu" -> return CityList.damanAndDiuCityList
        "Delhi" -> return CityList.delhiCityList
        "Gao" -> return CityList.goaCityList
        "Gujarat" -> return CityList.gujratCityList
        "Haryana" -> return CityList.haryanaCityList
        "Himachal Pradesh" -> return CityList.himachalPradeshCityList
        "Jammu and Kashmir" -> return CityList.jammuAndKashmirCityList
        "Jharkhand" -> return CityList.jharkhandCityList
        "Karnataka" -> return CityList.karnatakaCityList
        "Kerala" -> return CityList.kerelaCityList
        "Madhya Pradesh" -> return CityList.madhyaPradeshCityList
        "Maharashtra" -> return CityList.maharasthraCityList
        "Manipur" -> return CityList.manipurCityList
        "Meghalaya" -> return CityList.meghalayaCityList
        "Mizoram" -> return CityList.mizoramCityList
        "Nagaland" -> return CityList.nagalandCityList
        "Orissa" -> return CityList.orissaCityList
        "Pondicherry" -> return CityList.puducherryCityList
        "Punjab" -> return CityList.panjabCityList
        "Rajasthan" -> return CityList.rajasthanCityList
        "Sikkim" -> return CityList.sikkimCityList
        "Tamil Nadu" -> return CityList.tamilNaduCityList
        "Tripura" -> return CityList.tripuraCityList
        "Uttaranchal" -> return CityList.uttrakhandCityList
        "Uttar Pradesh" -> return CityList.uttarPradeshCityList
        "West Bengal" -> return CityList.westBengalCityList
        else -> listOf()
    }
}