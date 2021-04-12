package com.dondja.dondja.ui.fragment.notification.notificationmention

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import com.dondja.dondja.*
import com.dondja.dondja.databinding.FragmentNotificationMentionBinding

class NotificationMentionFragment : Fragment(R.layout.fragment_notification_mention) {

    private val binding by viewBinding<FragmentNotificationMentionBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecycler()
    }

    private fun setUpRecycler() {
        binding.rV.withModels {
            for (i in 1..8) {
                headerNofication {
                    id(i)
                    showDemands(true)
                }
                notificationMention {
                    id(i)
                    showValidationButtons(true)
                }
            }
        }
    }

}