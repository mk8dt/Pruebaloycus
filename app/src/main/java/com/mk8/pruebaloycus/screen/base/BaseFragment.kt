package com.mk8.pruebaloycus.screen.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.viewbinding.ViewBinding
import com.mk8.pruebaloycus.R
import com.mk8.pruebaloycus.extension.gone
import com.mk8.pruebaloycus.extension.visible
import com.mk8.pruebaloycus.screen.home.MainActivity

abstract class BaseFragment<BINDING : ViewBinding> : Fragment() {

    protected lateinit var binding: BINDING

    protected val navController by lazy { requireActivity().findNavController(R.id.nav_host) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        initializeBinding().root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    abstract fun initializeBinding(): BINDING

    abstract fun initView()

    private fun mainActivity() = (activity as MainActivity)

    protected fun showProgressBar() {
        mainActivity().progressBar().visible()
    }

    protected fun hideProgressBar() {
        mainActivity().progressBar().gone()
    }

    protected fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}