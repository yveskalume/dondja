package com.dondja.dondja.ui.fragment.feed.feedall

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized

data class FeedAllViewState(
    val data: Async<FeedAllData> = Uninitialized
) : MavericksState
