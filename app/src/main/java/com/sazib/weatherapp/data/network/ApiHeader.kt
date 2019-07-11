package com.sazib.weatherapp.data.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.sazib.weatherapp.di.AppKey
import com.sazib.weatherapp.di.PackageName
import com.sazib.weatherapp.di.VersionName
import javax.inject.Inject

class ApiHeader @Inject constructor(internal val authApiHeader: AuthApiHeader) {

  data class AuthApiHeader @Inject constructor(
    @AppKey @Expose @SerializedName("app_key") val _app_key: String,
    @PackageName @Expose @SerializedName("package") val _package: String,
    @VersionName @Expose @SerializedName("version") val _version: String
  )
}