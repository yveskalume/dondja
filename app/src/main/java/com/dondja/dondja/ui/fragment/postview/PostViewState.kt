package com.dondja.dondja.ui.fragment.postview

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized

data class PostViewState(
    val data: Async<Any> = Uninitialized
) : MavericksState