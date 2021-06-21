package com.dondja.dondja.util

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Environment
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

fun Date.getFormattedDate(format: String): String? {
    return try {
        SimpleDateFormat(format, Locale.getDefault()).format(this)
    } catch (exp: Throwable) {
        null
    }
}

fun Any.Log(msg: String) {
    android.util.Log.d(this::class.simpleName,msg)
}

fun Fragment.showToast(msg: String) {
    Toast.makeText(this.requireContext(),msg,Toast.LENGTH_SHORT).show()
}

fun Context.getImageFile(): File? {
    val imageFileName = "JPEG_" + System.currentTimeMillis() + "_"
    val storageDir = getExternalFilesDir(Environment.DIRECTORY_DCIM)
    return File.createTempFile(imageFileName, ".jpg", storageDir)
}

@SuppressLint("ClickableViewAccessibility")
fun View.setHasStoryNavigationAction(storyView : StoryView, action : () -> Unit) {
    var longPressed = false
    setOnClickListener {
        action()
    }

    setOnLongClickListener {
        longPressed = true
        storyView.pause()
        true
    }

    setOnTouchListener { v, event ->

        v.onTouchEvent(event)
        if (event.action == MotionEvent.ACTION_UP && longPressed) {
            longPressed = false
            storyView.resume()
        }
        return@setOnTouchListener true
    }
}
