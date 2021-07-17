package com.dondja.dondja.ui.fragment.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.navigation.fragment.navArgs
import com.airbnb.mvrx.*
import com.dondja.dondja.R
import com.dondja.dondja.data.entity.User
import com.dondja.dondja.databinding.FragmentProfileBinding
import com.dondja.dondja.util.showToast
import com.dondja.dondja.util.ui.setImageUrl

class ProfileFragment : Fragment(R.layout.fragment_profile), MavericksView {
    private val binding by viewBinding<FragmentProfileBinding>()
    private val viewModel: ProfileViewModel by fragmentViewModel()
    private val args by navArgs<ProfileFragmentArgs>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getData(args.userUid)
    }

    private fun bindData(user: User) {
        binding.run {
            imageProfile.setImageUrl(user.profileUrl)
            name.text = user.displayName
            followers.text = user.followersNumber.toString()
            followings.text = user.followingNumber.toString()
        }
    }

    override fun invalidate() = withState(viewModel) {
        when(it.user) {
            is Loading -> {

            }

            is Success -> {
                bindData(it.user.invoke())
            }
            is Fail -> {
                showToast(it.user.error.message.toString())
            }
        }
    }
}