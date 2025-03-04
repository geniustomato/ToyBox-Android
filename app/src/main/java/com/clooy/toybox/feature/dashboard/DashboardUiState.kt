package com.clooy.toybox.feature.dashboard

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.StringRes
import com.clooy.toybox.R
import com.clooy.toybox.feature.dashboard.exhibit.data.Exhibit
import com.clooy.toybox.feature.dashboard.exhibit.data.ExhibitId

sealed interface DashboardUiState {
    data object Loading : DashboardUiState
    data class Success(val data: DashboardUiData) : DashboardUiState
}

data class DashboardUiData(
    val exhibits: List<Exhibit>,
    val settings: DashboardToolbarData.Settings
)

sealed interface DashboardNavigationEvent {
    data class OnEnterExhibit(val exhibitId: ExhibitId) : DashboardNavigationEvent
}

sealed class DashboardToolbarEvent {
    data class OnDashboardSettingsUpdate(val data: DashboardToolbarData.Settings) :
        DashboardToolbarEvent()
}

sealed class DashboardToolbarData {
    data class Settings(
        val selectedViewType: ViewType,
    ) : DashboardToolbarData(), Parcelable {
        constructor(parcel: Parcel) : this(
            selectedViewType = ViewType.valueOf(parcel.readString() ?: ViewType.LIST.name)
        )

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(selectedViewType.name)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Settings> {
            override fun createFromParcel(parcel: Parcel): Settings {
                return Settings(parcel)
            }

            override fun newArray(size: Int): Array<Settings?> {
                return arrayOfNulls(size)
            }
        }
    }
}

enum class ViewType(@StringRes val label: Int) {
    LIST(R.string.list),
    GALLERY(R.string.gallery)
}