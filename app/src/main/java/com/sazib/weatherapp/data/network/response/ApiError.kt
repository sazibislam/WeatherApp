package com.sazib.weatherapp.data.network.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ApiError(
  var errorCode: Int, @field:Expose @field:SerializedName(
      "status"
  ) var statusCode: String?, @field:Expose @field:SerializedName(
      "message"
  ) var message: String?
)
