package com.wonjong.eventdispatcher.presenter.mvvm_intent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.wonjong.eventdispatcher.databinding.FragmentMvvmIntentBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by leewonjong@29cm.co.kr on 2023-01-03
 */
@AndroidEntryPoint
class MvvmIntentFragment : Fragment() {
    private var _binding: FragmentMvvmIntentBinding? = null
    private val binding: FragmentMvvmIntentBinding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMvvmIntentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}