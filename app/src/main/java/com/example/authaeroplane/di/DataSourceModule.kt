package com.binar.gosky.di


import com.binar.gosky.data.network.datasource.AuthRemoteDataSource
import com.binar.gosky.data.network.datasource.AuthRemoteDataSourceImpl
import com.example.authaeroplane.data.local.datasource.UserLocalDataSource
import com.example.authaeroplane.data.local.datasource.UserLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun provideAuthRemoteDataSource(authRemoteDataSourceImpl: AuthRemoteDataSourceImpl): AuthRemoteDataSource

    @Binds
    abstract fun provideUserLocalDataSource(userLocalDataSourceImpl: UserLocalDataSourceImpl): UserLocalDataSource
}