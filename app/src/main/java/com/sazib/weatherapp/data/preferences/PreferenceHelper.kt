package com.sazib.weatherapp.data.preferences

interface PreferenceHelper {

  fun isLoggedIn(): Boolean

  fun setLoggedIn(isLoggedIn: Boolean)

  fun getAccessToken(): String

  fun setAccessToken(accessToken: String)

  fun getUserId(): String

  fun setUserId(userId: String)

  fun getAppId(): String

  fun setAppId(id: String)

}