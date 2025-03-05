package com.clooy.toybox.feature.dashboard.presentation.dashboard.model

import android.os.Parcelable
import androidx.annotation.StringRes
import com.clooy.toybox.R
import kotlinx.parcelize.Parcelize

internal class DashboardUiModel(
    val exhibits: List<ExhibitUiModel>,
    val settings: DashboardToolbar.Settings,
)

internal data class ExhibitUiModel(
    val id: ExhibitId,
    val name: String,
    val description: String,
    val isActive: Boolean,
)

internal sealed class ExhibitId {
    data object ExhibitA : ExhibitId()
    data object ExhibitB : ExhibitId()
    data object ExhibitC : ExhibitId()

    companion object {
        fun valueOf(name: String): ExhibitId {
            return when (name) {
                "ExhibitA" -> ExhibitA
                "ExhibitB" -> ExhibitB
                "ExhibitC" -> ExhibitC
                else -> throw Exception("Unsupported exhibit: $name")
            }
        }
    }
}

internal sealed class DashboardToolbar {
    @Parcelize
    data class Settings(
        val selectedExhibitViewType: ExhibitViewType,
    ) : DashboardToolbar(), Parcelable
}

internal enum class ExhibitViewType(@StringRes val label: Int) {
    LIST(R.string.list),
    GALLERY(R.string.gallery)
}

