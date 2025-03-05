package com.clooy.toybox.feature.dashboard.data.model

internal data class DashboardDto(
    val exhibits: List<ExhibitDto>,
    val savedViewType: String,
)
