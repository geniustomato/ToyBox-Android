package com.clooy.toybox.feature.dashboard.presentation.dashboard.mapper

import com.clooy.toybox.feature.dashboard.domain.model.Dashboard
import com.clooy.toybox.feature.dashboard.domain.model.Exhibit
import com.clooy.toybox.feature.dashboard.presentation.dashboard.model.DashboardToolbar
import com.clooy.toybox.feature.dashboard.presentation.dashboard.model.DashboardUiModel
import com.clooy.toybox.feature.dashboard.presentation.dashboard.model.ExhibitId
import com.clooy.toybox.feature.dashboard.presentation.dashboard.model.ExhibitUiModel
import com.clooy.toybox.feature.dashboard.presentation.dashboard.model.ExhibitViewType

internal fun Dashboard.toDashboardUiModel() = DashboardUiModel(
    exhibits = this.exhibits.toExhibitUiModelList(),
    // TODO This might be just something I can handle in shared pref vs getting remotely -- getting chosen preference
    settings = DashboardToolbar.Settings(selectedExhibitViewType = ExhibitViewType.LIST)
)

internal fun List<Exhibit>.toExhibitUiModelList() = this.map {
    ExhibitUiModel(
        id = ExhibitId.valueOf(it.id),
        name = it.name,
        description = it.description,
        isActive = it.isActive,
    )
}
