package com.dondja.dondja.util

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.View

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
