package com.clooy.toybox.feature.dashboard.exhibit.data

data class Exhibit(
    val id: ExhibitId,
    val name: String,
    val description: String,
    val isActive: Boolean,
)

sealed class ExhibitId {
    object ExhibitA : ExhibitId()
    object ExhibitB : ExhibitId()
    object ExhibitC : ExhibitId()
}