package com.dondja.dondja.ui.fragment.notification.notificationall

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import com.dondja.dondja.*
import com.dondja.dondja.databinding.FragmentNotificationAllBinding


class NotificationAllFragment : Fragment(R.layout.fragment_notification_all) {
    private val binding by viewBinding<FragmentNotificationAllBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecycler()
    }

    private fun setUpRecycler() {
        binding.rV.withModels {
            for (i in 1..2) {
                headerNofication {
                    id(i)
                }
                notification {
                    id(i)
                }
                notificationWithPicture {
                    id(i)
                }
                notificationMention {
                    id(i)
                }
                notificationFollow {
                    id(i)
                }
            }
        }
    }
}