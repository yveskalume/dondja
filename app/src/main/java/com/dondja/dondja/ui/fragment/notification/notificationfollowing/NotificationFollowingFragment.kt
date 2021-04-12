package com.dondja.dondja.ui.fragment.notification.notificationfollowing

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import com.dondja.dondja.*
import com.dondja.dondja.databinding.FragmentNotificationFollowingBinding

class NotificationFollowingFragment : Fragment(R.layout.fragment_notification_following) {
    private val binding by viewBinding<FragmentNotificationFollowingBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecycler()
    }

    private fun setUpRecycler() {
        binding.rV.withModels {
            headerNofication {
                id("header")
            }
            for (i in 1..4) {
                notificationFollow {
                    id(i)
                }
            }
        }
    }

}