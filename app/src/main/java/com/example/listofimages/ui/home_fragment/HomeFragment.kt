package com.example.listofimages.ui.home_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.listofimages.databinding.FragmentHomeBinding
import com.example.listofimages.ui.home_fragment.adapter.ImagesListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {
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
        //assign adapter to recyclerview
        mBinding.rvImages.adapter = mAdapter
    }

    private fun loadData() {
        viewLifecycleOwner.lifecycleScope.launch {
            mViewModel.images.collectLatest { pagingData ->
                mAdapter.submitData(pagingData)
            }

        }
    }
}