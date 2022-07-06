package com.onedev.reqres.network

import org.koin.core.annotation.Single

@Single
class WebServiceProvider {
    fun get(): WebService {
        return WebService.build()
    }
}