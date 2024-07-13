package com.clooy.toybox.dashboard.exhibit.data

data class ExhibitItem(
    val exhibit: ExhibitName,
    val description: String,
    val isActive: Boolean,
)

// TODO Why not convert all from above to below -- it seems like the string would be hardcoded anyway
sealed class ExhibitName(val name: String) {
    object ExhibitA : ExhibitName("Exhibit A")
    object ExhibitB : ExhibitName("Exhibit B")
    object ExhibitC : ExhibitName("Exhibit C")
}
