package com.onedev.reqres.data

data class UserDetailResponse(
    val `data`: Data?
) {
    data class Data(
        val avatar: String?,
        val email: String?,
        val first_name: String?,
        val id: Int?,
        val last_name: String?
    )
}