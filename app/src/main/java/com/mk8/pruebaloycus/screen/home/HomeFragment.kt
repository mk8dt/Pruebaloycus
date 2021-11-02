package com.mk8.pruebaloycus.screen.home

import android.view.ViewGroup
import com.mk8.data.ScreenState
import com.mk8.data.app.EventModel
import com.mk8.pruebaloycus.databinding.EventItemViewBinding
import com.mk8.pruebaloycus.databinding.HomeLayoutBinding
import com.mk8.pruebaloycus.extension.initVerticalRecycler
import com.mk8.pruebaloycus.screen.base.BaseFragment
import com.mk8.pruebaloycus.screen.base.BaseRecyclerView
import com.mk8.pruebaloycus.screen.base.BaseViewHolder
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<HomeLayoutBinding>() {

    private val homeViewModel by viewModel<HomeViewModel>()
    private val eventAdapter = EventAdapter { routeToEventDetail(it) }

    override fun initializeBinding(): HomeLayoutBinding {
        binding = HomeLayoutBinding.inflate(layoutInflater)
        return binding
    }

    override fun initView() {
        homeViewModel.init()
        binding.rvEvents.initVerticalRecycler(eventAdapter)
        homeViewModel.eventState.observe(viewLifecycleOwner) { manageScreenState(it) }
    }

    private fun manageScreenState(state: ScreenState<List<EventModel>, String>) {
        when (state) {
            ScreenState.Loading -> showProgressBar()
            is ScreenState.Error -> {
                hideProgressBar()
                showToast(state.errorMessage)
            }
            is ScreenState.Success -> {
                hideProgressBar()
                eventAdapter.updateList(state.data)
            }
        }
    }

    private fun routeToEventDetail(identifier: String) {
        navController.navigate(HomeFragmentDirections.routeToDetail(identifier))
    }

    inner class EventAdapter(private val onClick: (String) -> Unit) : BaseRecyclerView<EventModel, EventAdapter.EventViewHolder>() {

        override fun getViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder =
            EventViewHolder(EventItemViewBinding.inflate(layoutInflater, parent, false))

        inner class EventViewHolder(private val binding: EventItemViewBinding) : BaseViewHolder<EventModel>(binding) {

            override fun bindData(item: EventModel) {
                with(binding) {

                    tvEventTitle.text = item.title
                    tvEventLocation.text = item.eventLocation
                    tvEventPrice.text = if (item.price.isEmpty()) "GRATIS" else item.price
                    root.setOnClickListener { onClick.invoke(item.id) }
                }
            }

        }
    }
}