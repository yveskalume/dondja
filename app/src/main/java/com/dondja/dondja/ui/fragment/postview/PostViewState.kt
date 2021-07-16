package com.dondja.dondja.ui.fragment.postview

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized
import com.dondja.dondja.data.entity.Post

data class PostViewState(
    val post: Async<Post> = Uninitialized
) : MavericksState