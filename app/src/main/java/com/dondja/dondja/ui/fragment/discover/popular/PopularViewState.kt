package com.dondja.dondja.ui.fragment.discover.popular

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized
import com.dondja.dondja.data.entity.Post

data class PopularViewState (
        val posts : Async<List<Post>> = Uninitialized
) : MavericksState