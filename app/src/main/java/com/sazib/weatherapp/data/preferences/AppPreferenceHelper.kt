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

  override fun setUserId(userId: String) = mPrefs.edit().putString(PREF_KEY_USER_ID, userId).apply()

  override fun getAppId(): String =
    mPrefs.getString(PREF_KEY_APP_ID, "e384f9ac095b2109c751d95296f8ea76")
        ?: "e384f9ac095b2109c751d95296f8ea76"

  override fun setAppId(id: String) =
    mPrefs.edit().putString(PREF_KEY_APP_ID, "e384f9ac095b2109c751d95296f8ea76").apply()

}