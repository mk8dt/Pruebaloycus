package com.mk8.pruebaloycus.screen.home

import com.mk8.pruebaloycus.databinding.BaseLayoutBinding
import com.mk8.pruebaloycus.screen.base.BaseActivity

class MainActivity : BaseActivity<BaseLayoutBinding>() {

    override fun initializeBinding(): BaseLayoutBinding {
        binding = BaseLayoutBinding.inflate(layoutInflater)
        return binding
    }

    override fun initView() {

    }

    fun progressBar() = binding.progressBar
}