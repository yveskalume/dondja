package com.dondja.dondja.ui.fragment.message.messageall

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import com.airbnb.epoxy.carousel
import com.dondja.dondja.*
import com.dondja.dondja.databinding.FragmentMessageAllBinding

class MessageAllFragment : Fragment(R.layout.fragment_message_all) {
    private val binding by viewBinding<FragmentMessageAllBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecycler()
    }

    private fun setUpRecycler() {
        val onlineUsers: ArrayList<ConnectedUserBindingModel_> = arrayListOf()
        val messages = arrayListOf<ConversationBindingModel_>()

        for(i in 1..8) {
            val model = ConnectedUserBindingModel_()
                    .id(i)
            onlineUsers.add(model)
        }

            binding.rV.withModels {
                headerMessageActivity {
                    id("online")
                }

                carousel {
                    id("online-user")
                    models(onlineUsers)
                }

                headerMessagePinned {
                    id("pinned")
                }
                for (j in 1..2) {
                    conversation {
                        id(j)
                    }
                }

                headerNofication {
                    id("today")
                }

                for (j in 1..6) {
                    conversation {
                        id(j)
                    }
                }
        }
    }
}