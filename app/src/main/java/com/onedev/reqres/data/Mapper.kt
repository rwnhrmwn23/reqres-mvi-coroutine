package com.onedev.reqres.data

object Mapper {
    fun mapUserResponse(userResponse: UserResponse?): List<User> {
        val mapper: (UserResponse.Data?) -> User = {
            User(
                id = it?.id ?: 0,
                email = it?.email.orEmpty(),
                name = "${it?.first_name.orEmpty()} ${it?.last_name.orEmpty()}"
            )
        }
        return userResponse?.data?.map(mapper).orEmpty()
    }

    fun mapUserResponseToDetail(userDetailResponse: UserDetailResponse?): User {
        return User(
            id = userDetailResponse?.data?.id ?: 0,
            email = userDetailResponse?.data?.email.orEmpty(),
            name = "${userDetailResponse?.data?.first_name.orEmpty()} ${userDetailResponse?.data?.last_name.orEmpty()}"
        )
    }
}