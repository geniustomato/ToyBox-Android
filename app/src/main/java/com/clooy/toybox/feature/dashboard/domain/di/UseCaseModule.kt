package com.clooy.toybox.feature.dashboard.domain.di

import com.clooy.toybox.feature.dashboard.domain.repository.DashboardRepository
import com.clooy.toybox.feature.dashboard.domain.usecase.GetDashboardUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {

    @Provides
    internal fun getDashboardUseCase(dashboardRepository: DashboardRepository) =
        GetDashboardUseCase(dashboardRepository = dashboardRepository)
}
