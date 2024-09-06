package com.zekony.windichat.utility.helperClasses

import android.graphics.Bitmap
import android.util.Base64
import java.io.ByteArrayOutputStream

fun encodeImageToBase64(bitmap: Bitmap): String {
    val byteArrayOutputStream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
    val byteArray = byteArrayOutputStream.toByteArray()
    return Base64.encodeToString(byteArray, Base64.DEFAULT)
}