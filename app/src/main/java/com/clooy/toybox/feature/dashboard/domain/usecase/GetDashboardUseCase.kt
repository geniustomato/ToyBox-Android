package com.clooy.toybox.feature.dashboard.domain.usecase

import com.clooy.toybox.feature.dashboard.domain.repository.DashboardRepository

internal class GetDashboardUseCase(private val dashboardRepository: DashboardRepository) {
    suspend operator fun invoke() = dashboardRepository.getDashboardData()
}
