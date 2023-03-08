package com.rastete.recipesapp.presentation.util

import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.os.Parcelable
import android.widget.TextView
import org.jsoup.Jsoup

fun TextView.escapeHtml(text: String) {
    val plainText = Jsoup.parse(text).text()
    this.text = plainText
}


inline fun <reified T : Parcelable> Bundle.parcelable(key: String): T? = when {
    SDK_INT >= 33 -> getParcelable(key, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelable(key) as? T
}
