package com.example.listofimages.ui.full_screen_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.listofimages.databinding.FragmentFullScreenBinding


class FullScreenFragment : Fragment() {

    private lateinit var mBinding: FragmentFullScreenBinding

    private val args: FullScreenFragmentArgs by navArgs()

    private var mIsInFullScreen = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentFullScreenBinding.inflate(inflater, container, false)
            .apply {
                url = args.url
            }
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.ivImage.setOnClickListener {
            when {
                mIsInFullScreen -> exitFullscreen()
                else -> enterFullscreen()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        enterFullscreen()
    }

    override fun onPause() {
        super.onPause()
        exitFullscreen()
    }

    private fun enterFullscreen() {
        WindowCompat.setDecorFitsSystemWindows(requireActivity().window, false)
        WindowInsetsControllerCompat(requireActivity().window, mBinding.root).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }

        mIsInFullScreen = true
    }


    private fun exitFullscreen() {
        WindowCompat.setDecorFitsSystemWindows(requireActivity().window, true)
        WindowInsetsControllerCompat(
            requireActivity().window,
            mBinding.root
        ).show(WindowInsetsCompat.Type.systemBars())

        mIsInFullScreen = false
    }
}