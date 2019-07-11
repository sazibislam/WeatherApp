package com.sazib.weatherapp.utils

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.provider.ContactsContract
import androidx.core.graphics.drawable.toDrawable
import com.sazib.weatherapp.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object CommonUtil {

  fun notNull(data: String?): String {
    return data ?: ""
  }

  fun getTimeStamp(): String {
    return SimpleDateFormat(AppConstants.TIMESTAMP_FORMAT, Locale.getDefault()).format(
        Date()
    )
  }

  fun pickContact(): Intent {
    return Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI)
  }

  fun showLoadingDialog(context: Context): Dialog {
    val dialog = Dialog(context)
    dialog.show()
    dialog.window?.setBackgroundDrawable(Color.TRANSPARENT.toDrawable())
    dialog.setContentView(R.layout.progress_dialog)
    dialog.setCancelable(false)
    dialog.setCanceledOnTouchOutside(false)
    return dialog
  }

}