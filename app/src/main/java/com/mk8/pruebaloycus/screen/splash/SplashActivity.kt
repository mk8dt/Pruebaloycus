package com.mk8.pruebaloycus.screen.splash

import com.mk8.pruebaloycus.databinding.SplashLayoutBinding
import com.mk8.pruebaloycus.screen.base.BaseActivity

class SplashActivity : BaseActivity<SplashLayoutBinding>() {

    override fun initializeBinding(): SplashLayoutBinding {
        binding = SplashLayoutBinding.inflate(layoutInflater)
        return binding
    }

    override fun initView() {

    }
}