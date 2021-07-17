package com.dondja.dondja.ui.fragment.profile

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized
import com.dondja.dondja.data.entity.User

data class ProfileViewState (
        val user : Async<User> = Uninitialized
) : MavericksState