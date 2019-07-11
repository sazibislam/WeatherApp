package com.sazib.weatherapp.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.pm.Signature
import com.sazib.weatherapp.utils.logger.AppLogger
import java.io.ByteArrayInputStream
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.security.cert.CertificateEncodingException
import java.security.cert.CertificateException
import java.security.cert.CertificateFactory
import java.security.cert.X509Certificate

object CertSHA1 {

  private const val X509 = "X509"
  private const val SHA1 = "SHA1"

  @SuppressLint("PackageManagerGetSignatures")
  fun getCertificateSHA1(
    argContext: Context?
  ): String {
    if (argContext == null) return "Context is NULL"

    val packageManager = argContext.packageManager
    val packageName = argContext.packageName
    val flags = PackageManager.GET_SIGNATURES

    var packageInfo: PackageInfo? = null
    try {
      packageInfo = packageManager.getPackageInfo(packageName, flags)
    } catch (e: PackageManager.NameNotFoundException) {
      AppLogger.e("getCertificateSHA1: " + e.message)
    }

    var signatures = arrayOfNulls<Signature>(0)
    if (packageInfo != null) {
      signatures = packageInfo.signatures
    }
    val cert = signatures[0]?.toByteArray()
    val input = ByteArrayInputStream(cert)

    var certificateFactory: CertificateFactory? = null
    try {
      certificateFactory = CertificateFactory.getInstance(X509)
    } catch (e: CertificateException) {
      AppLogger.e("getCertificateSHA1: " + e.message)
    }

    var c: X509Certificate? = null
    try {
      if (certificateFactory != null) {
        c = certificateFactory.generateCertificate(input) as X509Certificate
      }
    } catch (e: CertificateException) {
      AppLogger.e("getCertificateSHA1: " + e.message)
    }

    var hexString = AppConstants.NULL
    try {
      val md = MessageDigest.getInstance(SHA1)
      var publicKey = ByteArray(0)
      if (c != null) {
        publicKey = md.digest(c.encoded)
      }
      hexString = byte2HexFormatted(publicKey)
    } catch (e1: NoSuchAlgorithmException) {
      AppLogger.e("getCertificateSHA1: " + e1.message)
    } catch (e1: CertificateEncodingException) {
      AppLogger.e("getCertificateSHA1: " + e1.message)
    }

    return hexString
  }

  private fun byte2HexFormatted(byteArray: ByteArray): String {
    val stringBuilder = StringBuilder(byteArray.size * 2)
    for (i in byteArray.indices) {
      var h = Integer.toHexString(byteArray[i].toInt())
      val l = h.length
      if (l == 1) h = "0$h"
      if (l > 2) h = h.substring(l - 2, l)
      stringBuilder.append(h.toUpperCase())
      if (i < byteArray.size - 1) stringBuilder.append(':')
    }
    return stringBuilder.toString()
  }
}