package com.dondja.dondja.ui.fragment.discover.foryou

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized

data class ForYouViewState (
        val data : Async<List<*>> = Uninitialized
) : MavericksState