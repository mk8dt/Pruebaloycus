package com.mk8.pruebaloycus.screen.detail

import android.content.Intent
import android.net.Uri
import com.mk8.data.ScreenState
import com.mk8.data.app.EventModel
import com.mk8.data.app.LocationEvent
import com.mk8.pruebaloycus.databinding.DetailLayoutBinding
import com.mk8.pruebaloycus.extension.lazyUnSynchronized
import com.mk8.pruebaloycus.screen.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment<DetailLayoutBinding>() {

    private val detailViewModel by viewModel<DetailViewModel>()
    private val identifier by lazyUnSynchronized {
        arguments?.let { DetailFragmentArgs.fromBundle(it).identifier }
    }

    override fun initializeBinding(): DetailLayoutBinding {
        binding = DetailLayoutBinding.inflate(layoutInflater)
        return binding
    }

    override fun initView() {
        detailViewModel.init()
        identifier?.let { detailViewModel.getEventByIdentifier(it) }
        detailViewModel.detailEventData.observe(viewLifecycleOwner) { manageScreenState(it) }
    }

    private fun manageScreenState(screenState: ScreenState<EventModel, String>) {
        when (screenState) {
            ScreenState.Loading -> showProgressBar()
            is ScreenState.Error -> {
                hideProgressBar()
                showToast(screenState.errorMessage)
            }
            is ScreenState.Success -> {
                hideProgressBar()
                bindEvent(screenState.data)
            }
        }
    }

    private fun bindEvent(event: EventModel) {
        with(binding) {
            tvEventTitle.text = event.title
            tvDescriptionEvent.text = event.description
            tvPrice.text = if (event.price.isEmpty()) "GRATIS" else event.price
            tvStartDate.text = event.dateStart
            tvLocation.text = event.eventLocation
            labelOpenMap.setOnClickListener { openGps(event.location) }
            labelLinkUrl.setOnClickListener { openWebView(event.link) }
        }
    }

    private fun openGps(location: LocationEvent) {
        val uri = "http://maps.google.com/maps?daddr=" + location.latitude + "," + location.longitude
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
        intent.setPackage("com.google.android.apps.maps")
        startActivity(intent)
    }

    private fun openWebView(url: String) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(url)
        }
        startActivity(intent)
    }
}