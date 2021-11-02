package com.mk8.pruebaloycus.screen.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mk8.data.ScreenState
import com.mk8.data.app.EventModel
import com.mk8.data.fold
import com.mk8.domain.usecase.GetEventListUseCase
import com.mk8.pruebaloycus.screen.base.BaseViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

typealias EVENT_STATE = ScreenState<List<EventModel>, String>

class HomeViewModel(
    private val getEventListUseCase: GetEventListUseCase
) : BaseViewModel() {

    private val _eventMutableState = MutableLiveData<EVENT_STATE>()
    val eventState: LiveData<EVENT_STATE>
        get() = _eventMutableState

    override fun onCreate() {
        super.onCreate()
        getEventList()
    }

    private fun getEventList() {
        updateUI(ScreenState.Loading)
        uiScope.launch {
            val result = uiScope.async { withContext(ioContext) { getEventListUseCase.execute() } }
            result.await().fold(
                { updateUI(ScreenState.Error(it)) },
                { updateUI(ScreenState.Success(it)) }
            )
        }
    }

    private fun updateUI(state: EVENT_STATE) {
        _eventMutableState.postValue(state)
    }
}