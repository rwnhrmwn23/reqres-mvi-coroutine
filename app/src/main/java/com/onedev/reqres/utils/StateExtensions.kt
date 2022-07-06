package com.onedev.reqres.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onedev.reqres.event.MutableStateEventManager
import com.onedev.reqres.event.StateEvent
import com.onedev.reqres.event.StateEventManager
import com.onedev.reqres.event.StateEventSubscriber
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import retrofit2.Response

typealias FlowState<T> = Flow<StateEvent<T>>

fun <T> default() = MutableStateEventManager<T>()
fun <T> ViewModel.convertEventToSubscriber(
    eventManager: StateEventManager<T>,
    subscriber: StateEventSubscriber<T>
) {
    eventManager.subscribe(
        scope = viewModelScope,
        onIdle = subscriber::onIdle,
        onLoading = subscriber::onLoading,
        onFailure = subscriber::onFailure,
        onSuccess = subscriber::onSuccess
    )
}

fun <T, U> Response<T>.asFlowStateEvent(mapper: (T) -> U): FlowState<U> {
    return flow {
        emit(StateEvent.Loading())
//        delay(2000)
        val response = try {
            val body = body()
            if (isSuccessful && body != null) {
                val dataMapper = mapper.invoke(body)
                StateEvent.Success(dataMapper)
            } else {
                val exception = Throwable(message())
                StateEvent.Failure(exception)
            }
        } catch (e: Throwable) {
            StateEvent.Failure(e)
        }
        emit(response)
    }
}