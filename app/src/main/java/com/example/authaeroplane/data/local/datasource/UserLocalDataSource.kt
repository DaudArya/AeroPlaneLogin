package com.example.authaeroplane.data.local.datasource


import com.example.authaeroplane.data.local.preference.UserDataStoreManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface UserLocalDataSource {
    suspend fun setUserLogin(isLogin: Boolean)

    fun getUserLoginStatus(): Flow<Boolean>
}

class UserLocalDataSourceImpl @Inject constructor(
    private val userDataStore: UserDataStoreManager
) : UserLocalDataSource {

    override suspend fun setUserLogin(isLogin: Boolean) {
        userDataStore.setUserLogin(isLogin)
    }

    override fun getUserLoginStatus(): Flow<Boolean> {
        return userDataStore.getUserLoginStatus()
    }
}