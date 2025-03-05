package com.clooy.toybox.feature.dashboard.data.repository

import com.clooy.toybox.feature.dashboard.data.mapper.toDashboard
import com.clooy.toybox.feature.dashboard.data.remote.DashboardService
import com.clooy.toybox.feature.dashboard.domain.repository.DashboardRepository

internal class DashboardRepositoryImpl(private val dashboardService: DashboardService): DashboardRepository {
    override suspend fun getDashboardData() = dashboardService.getDashboardResponse().toDashboard()
}
