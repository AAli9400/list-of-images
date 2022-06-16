package com.example.listofimages.ui.home_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.listofimages.databinding.FragmentHomeBinding
import com.example.listofimages.ui.home_fragment.adapter.ImagesListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(), ImagesListAdapter.Helper {
    private lateinit var mBinding: FragmentHomeBinding

    @Inject
    lateinit var mAdapter: ImagesListAdapter
    private val mViewModel: ImagesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        loadData()
    }

    private fun initRecyclerView() {
        //set adapter on click listener
        mAdapter.setHelper(this)

        //assign adapter to recyclerview
        mBinding.rvImages.apply {
            adapter = mAdapter
        }
    }

    private fun loadData() {
        viewLifecycleOwner.lifecycleScope.launch {
            mViewModel.images.collectLatest { pagingData ->
                mAdapter.submitData(pagingData)
            }

        }
    }

    override fun onItemClick(url: String) {

        findNavController().navigate(
            HomeFragmentDirections.toFullScreenFragment(
                url,
            )
        )
    }
}