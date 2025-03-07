package com.clooy.toybox.feature.dashboard.data.di

import com.clooy.toybox.feature.dashboard.data.remote.DashboardService
import com.clooy.toybox.feature.dashboard.data.repository.DashboardRepositoryImpl
import com.clooy.toybox.feature.dashboard.domain.repository.DashboardRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    internal abstract fun dashboardRepository(dashboardRepositoryImpl: DashboardRepositoryImpl): DashboardRepository

    companion object {
        @Provides
        @Singleton
        internal fun dashboardService(): DashboardService = DashboardService()
    }
}
