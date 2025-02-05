package com.clooy.toybox.feature.dashboard

import android.os.Parcel
import android.os.Parcelable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clooy.toybox.feature.dashboard.exhibit.data.Exhibit
import com.clooy.toybox.feature.dashboard.exhibit.repository.ExhibitListDataSource
import com.clooy.toybox.feature.dashboard.exhibit.repository.ExhibitListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DashboardViewModel(
    // TODO I think this needs some DI magic w/ Hilt
    private val exhibitListRepository: ExhibitListRepository = ExhibitListRepository(
        exhibitListDataSource = ExhibitListDataSource()
    )
) : ViewModel() {
    private val data: Flow<List<Exhibit>> = exhibitListRepository.exhibitsStream

    val uiState: StateFlow<DashboardUiState> =
        data.map { exhibits ->
            DashboardUiState.Success(
                data = DashboardUiData(
                    exhibits = exhibits
                )
            )
        }.stateIn(
            scope = viewModelScope,
            initialValue = DashboardUiState.Loading,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000),
        )
}

data class SettingsData(
    val selectedViewType: ViewType,
    val showSettingsDialog: Boolean,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        selectedViewType = ViewType.valueOf(parcel.readString() ?: ViewType.LIST.name),
        showSettingsDialog = parcel.readByte() != 0.toByte()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(selectedViewType.name)
        parcel.writeByte(if (showSettingsDialog) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SettingsData> {
        override fun createFromParcel(parcel: Parcel): SettingsData {
            return SettingsData(parcel)
        }

        override fun newArray(size: Int): Array<SettingsData?> {
            return arrayOfNulls(size)
        }
    }
}
