package com.onedev.reqres.repository

import com.onedev.reqres.data.User
import com.onedev.reqres.event.StateEventManager

interface UserRepository {

    val userStateEventManager: StateEventManager<List<User>>

    suspend fun getUsers(page: Int = 1)

}