package com.dondja.dondja.util

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.View
import jp.shts.android.storiesprogressview.StoriesProgressView

@SuppressLint("ClickableViewAccessibility")
fun View.setHasStoryNavigationAction(storyView : StoriesProgressView, action : () -> Unit) {
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
