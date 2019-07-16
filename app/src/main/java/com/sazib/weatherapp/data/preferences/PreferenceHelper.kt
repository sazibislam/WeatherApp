package com.sazib.weatherapp.data.preferences

interface PreferenceHelper {

  fun getAccessToken(): String

  fun setAccessToken(accessToken: String)

  fun getUserId(): String

  fun setUserId(userId: String)

  fun getAppWeatherId(): String

  fun setAppId(id: String)

}