package com.sumeyra.mybio.ui.splash

import android.animation.Animator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.sumeyra.mybio.R
import com.sumeyra.mybio.databinding.FragmentSplashBinding
import com.sumeyra.mybio.delegate.viewBinding
import com.sumeyra.mybio.utils.extension.sent


class SplashFragment : Fragment(R.layout.fragment_splash) {

    private val binding by viewBinding(FragmentSplashBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lottieSplash.addAnimatorListener(object : Animator.AnimatorListener {

            override fun onAnimationEnd(animation: Animator) {
                Navigation.sent(view,R.id.action_splashFragment_to_homeFragment)

            }

            override fun onAnimationStart(animation: Animator) = Unit
            override fun onAnimationCancel(animation: Animator) = Unit
            override fun onAnimationRepeat(animation: Animator) = Unit
        })
    }
}