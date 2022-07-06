package com.onedev.reqres.repository

import com.onedev.reqres.data.User
import com.onedev.reqres.event.StateEventManager
import com.onedev.reqres.network.NetworkSource
import com.onedev.reqres.utils.default
import org.koin.core.annotation.Single

@Single
class UserRepositoryImpl(
    private val networkSource: NetworkSource
): UserRepository {

    private val _userStateEventManager = default<List<User>>()
    override val userStateEventManager: StateEventManager<List<User>>
        get() = _userStateEventManager

    override suspend fun getUsers(page: Int) {
        networkSource.getList(page)
            .collect(_userStateEventManager)
    }
}