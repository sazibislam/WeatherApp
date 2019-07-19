package com.sazib.weatherapp.data.preferences

import android.content.Context
import android.content.SharedPreferences
import com.sazib.weatherapp.di.PreferenceInfo
import com.sazib.weatherapp.utils.AppConstants
import javax.inject.Inject

class AppPreferenceHelper @Inject constructor(
  context: Context,
  @PreferenceInfo prefFileName: String = AppConstants.PREF_NAME
) : PreferenceHelper {

  companion object {
    private const val PREF_KEY_LOGGED_IN = "pref_key_logged_in"
    private const val PREF_KEY_ACCESS_TOKEN = "pref_key_access_token"
    private const val PREF_KEY_USER_ID = "pref_key_user_id"
    private const val PREF_KEY_APP_ID = "pref_key_app_id"
    private const val PREF_KEY_APP_LATITUDE = "pref_key_app_latitude"
    private const val PREF_KEY_APP_LONGITUDE = "pref_key_app_longitude"
    private const val PREF_KEY_APP_CITY = "pref_key_app_city"
  }

  private val mPrefs: SharedPreferences = context.getSharedPreferences(
      prefFileName, Context.MODE_PRIVATE
  )

  override fun isLoggedIn(): Boolean = mPrefs.getBoolean(PREF_KEY_LOGGED_IN, false)

  override fun setLoggedIn(isLoggedIn: Boolean) = mPrefs.edit().putBoolean(
      PREF_KEY_LOGGED_IN, isLoggedIn
  ).apply()

  override fun getAccessToken(): String = mPrefs.getString(PREF_KEY_ACCESS_TOKEN, "") ?: ""

  override fun setAccessToken(accessToken: String) = mPrefs.edit().putString(
      PREF_KEY_ACCESS_TOKEN, accessToken
  ).apply()

  override fun getUserId(): String = mPrefs.getString(PREF_KEY_USER_ID, "") ?: ""

  override fun setUserId(userId: String) =
    mPrefs.edit().putString(PREF_KEY_USER_ID, userId).apply()

  override fun getAppId(): String? = mPrefs.getString(PREF_KEY_APP_ID, AppConstants.APPID)

  override fun setAppId(id: String) = mPrefs.edit().putString(PREF_KEY_APP_ID, id).apply()

  override fun getLatitude(): String? = mPrefs.getString(PREF_KEY_APP_LATITUDE, "")

  override fun setLatitude(latitude: String) =
    mPrefs.edit().putString(PREF_KEY_APP_LATITUDE, latitude).apply()

  override fun getLongitude(): String? = mPrefs.getString(PREF_KEY_APP_LONGITUDE, "")

  override fun setLongitude(longitude: String) =
    mPrefs.edit().putString(PREF_KEY_APP_LONGITUDE, longitude).apply()

  override fun getCity(): String? = mPrefs.getString(PREF_KEY_APP_CITY, "")

  override fun setCity(city: String) = mPrefs.edit().putString(PREF_KEY_APP_CITY, city).apply()

}