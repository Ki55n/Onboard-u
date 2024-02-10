package com.example.wifimodule.di.repository

import com.example.wifimodule.data.remote_service.MLService
import com.example.wifimodule.di.NetworkModule
import com.example.wifimodule.domain.repository.ml.MLRepository
import com.example.wifimodule.domain.repository.ml.MLRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Dagger module that provides dependencies for address management feature.
 * This module includes the [NetworkModule] to provide the [Retrofit] instance.
 */
@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class MLModule {

    /**
     * Provides the [AddressManagementService] dependency.
     *
     * @param retrofit The [Retrofit] instance used for network operations.
     * @return The [AddressManagementService] implementation.
     */
    @Singleton
    @Provides
    fun provideMLApi(@NetworkModule.RetrofitTwo retrofit: Retrofit): MLService {
        return retrofit.create(MLService::class.java)
    }

    /**
     * Provides the [AddressManagementRepository] dependency.
     *
     * @param addressManagementService The [AddressManagementService] implementation.
     * @return The [AddressManagementRepository] implementation.
     */
    @Singleton
    @Provides
    fun provideMLRepository(MLService: MLService): MLRepository {
        return MLRepositoryImpl(MLService)
    }
}