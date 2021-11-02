package com.mk8.pruebaloycus.screen.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mk8.data.ScreenState
import com.mk8.data.app.EventModel
import com.mk8.data.fold
import com.mk8.domain.usecase.GetEventByIdentifierUseCase
import com.mk8.pruebaloycus.screen.base.BaseViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

typealias DETAIL_EVENT_STATE = ScreenState<EventModel, String>

class DetailViewModel(
    private val getEventByIdentifierUseCase: GetEventByIdentifierUseCase
) : BaseViewModel() {

    private val _detailEventMutable = MutableLiveData<DETAIL_EVENT_STATE>()
    val detailEventData: LiveData<DETAIL_EVENT_STATE>
        get() = _detailEventMutable

    fun getEventByIdentifier(identifier: String) {
        updateUI(ScreenState.Loading)
        uiScope.launch {
            val result = uiScope.async { withContext(ioContext) { getEventByIdentifierUseCase.execute(identifier.toLong()) } }
            result.await().fold(
                { updateUI(ScreenState.Error(it)) },
                { updateUI(ScreenState.Success(it)) }
            )
        }
    }

    private fun updateUI(state: DETAIL_EVENT_STATE) {
        _detailEventMutable.postValue(state)
    }
}