package com.clooy.toybox.feature.dashboard.data.mapper

import com.clooy.toybox.feature.dashboard.data.model.DashboardDto
import com.clooy.toybox.feature.dashboard.data.model.ExhibitDto
import com.clooy.toybox.feature.dashboard.domain.model.Dashboard
import com.clooy.toybox.feature.dashboard.domain.model.Exhibit

internal fun DashboardDto.toDashboard() = Dashboard(
    exhibits = this.exhibits.toExhibitList()
)

internal fun List<ExhibitDto>.toExhibitList() = this.map {
    Exhibit(
        id = it.id,
        name = it.name,
        description = it.description,
        isActive = it.isActive
    )
}
