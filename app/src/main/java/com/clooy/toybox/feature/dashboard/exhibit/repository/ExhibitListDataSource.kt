package com.clooy.toybox.feature.dashboard.exhibit.repository

import com.clooy.toybox.feature.dashboard.exhibit.data.Exhibit
import com.clooy.toybox.feature.dashboard.exhibit.data.ExhibitId.ExhibitA
import com.clooy.toybox.feature.dashboard.exhibit.data.ExhibitId.ExhibitB
import com.clooy.toybox.feature.dashboard.exhibit.data.ExhibitId.ExhibitC
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

// TODO Ideally this should just simulate an API call from somewhere
class ExhibitListDataSource {
    val exhibitsStream: Flow<List<Exhibit>> = flowOf(
        listOf(
            Exhibit(
                id = ExhibitA,
                name = "Exhibit A",
                description = "Description",
                isActive = true,
            ),
            Exhibit(
                id = ExhibitB,
                name = "Exhibit B",
                description = "Description",
                isActive = true,
            ),
            Exhibit(
                id = ExhibitC,
                name = "Exhibit C",
                description = "Description",
                isActive = true,
            ),
            Exhibit(
                id = ExhibitA,
                name = "Exhibit A",
                description = "Description",
                isActive = true,
            ),
            Exhibit(
                id = ExhibitB,
                name = "Exhibit B",
                description = "Description",
                isActive = true,
            ),
            Exhibit(
                id = ExhibitC,
                name = "Exhibit C",
                description = "Description",
                isActive = true,
            ),
            Exhibit(
                id = ExhibitA,
                name = "Exhibit A",
                description = "Description",
                isActive = true,
            ),
            Exhibit(
                id = ExhibitB,
                name = "Exhibit B",
                description = "Description",
                isActive = true,
            ),
            Exhibit(
                id = ExhibitC,
                name = "Exhibit C",
                description = "Description",
                isActive = true,
            ),
        )
    )
}
