package com.sazib.weatherapp.utils;

import android.content.Context;
import com.sazib.weatherapp.ui.citylist.view.model.CityListDataModel;
import java.util.ArrayList;

public class AppDataUtils {

  Context context;

  private AppDataUtils() {
  }

  public static ArrayList<CityListDataModel> getCityListData(Context applicationContext) {

    ArrayList<CityListDataModel> arrayList = new ArrayList<>();
    arrayList.add(new CityListDataModel("Dhaka", "Cloudy", "25 C"));
    arrayList.add(new CityListDataModel("Chittagong", "Cloudy", "25 C"));
    arrayList.add(new CityListDataModel("Jhenaidah", "Cloudy", "25 C"));
    arrayList.add(new CityListDataModel("anikgonj", "Cloudy", "25 C"));
    arrayList.add(new CityListDataModel("Boshundhara", "Cloudy", "25 C"));

    return arrayList;
  }
}
