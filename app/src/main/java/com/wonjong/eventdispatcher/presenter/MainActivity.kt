package com.wonjong.eventdispatcher.presenter

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.google.android.material.tabs.TabLayoutMediator
import com.wonjong.eventdispatcher.databinding.ActivityMainBinding
import com.wonjong.eventdispatcher.presenter.tabs.TabFragmentFactory
import com.wonjong.eventdispatcher.presenter.tabs.TabPageAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = _binding!!

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.fragmentFactory = TabFragmentFactory()
        setUpUi()
        collectFlows()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setUpUi() {
        binding.pager.adapter = TabPageAdapter(this, viewModel.tabs)
    }

    private fun collectFlows() {
        viewModel.tabs.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED).onEach { tabTypes ->
            TabLayoutMediator(binding.tabs, binding.pager, false) { tab, position ->
                tab.text = getString(tabTypes[position].tabName)
                tab.view.setOnLongClickListener { true }
            }.attach()
        }.launchIn(lifecycleScope)
    }
}