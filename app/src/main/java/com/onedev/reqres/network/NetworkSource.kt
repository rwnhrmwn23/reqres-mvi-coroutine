package com.onedev.reqres.network

import com.onedev.reqres.data.Mapper
import com.onedev.reqres.data.User
import com.onedev.reqres.utils.FlowState
import com.onedev.reqres.utils.asFlowStateEvent
import org.koin.core.annotation.Single

@Single
class NetworkSource(private val webServiceProvider: WebServiceProvider) {

    suspend fun getList(page: Int): FlowState<List<User>> {
        return webServiceProvider.get().getList(page).asFlowStateEvent {
            Mapper.mapUserResponse(it)
        }
    }
}