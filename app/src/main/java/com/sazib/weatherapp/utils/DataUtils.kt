package com.sazib.weatherapp.utils

import com.sazib.weatherapp.ui.citylist.view.model.CityListDataModel
import java.util.*

public class DataUtils {


    fun getCityListData(): ArrayList<CityListDataModel> {
        val arrayList = ArrayList<CityListDataModel>()
        arrayList.add(CityListDataModel("Dhaka", "Cloudy", "25 C"))
        arrayList.add(CityListDataModel("Chittagong", "Cloudy", "25 C"))
        arrayList.add(CityListDataModel("Jhenaidah", "Cloudy", "25 C"))
        arrayList.add(CityListDataModel("Khulna", "Cloudy", "25 C"))
        arrayList.add(CityListDataModel("anikgonj", "Cloudy", "25 C"))
        arrayList.add(CityListDataModel("Boshundhara", "Cloudy", "25 C"))
        return arrayList
    }


}
