package com.clooy.toybox.feature.dashboard.domain.repository

import com.clooy.toybox.feature.dashboard.domain.model.Dashboard

internal interface DashboardRepository {
    suspend fun getDashboardData(): Dashboard
}
