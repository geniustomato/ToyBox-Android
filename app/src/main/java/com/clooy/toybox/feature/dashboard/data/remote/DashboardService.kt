package com.clooy.toybox.feature.dashboard.data.remote

import com.clooy.toybox.feature.dashboard.data.model.DashboardDto
import com.clooy.toybox.feature.dashboard.data.model.ExhibitDto

internal class DashboardService {

    // TODO Ideally this should simulate an API call from somewhere
    suspend fun getDashboardResponse() =
         DashboardDto(
            exhibits = listOf(
                ExhibitDto(
                    id = "ExhibitA",
                    name = "Exhibit A",
                    description = "Description",
                    isActive = true,
                ),
                ExhibitDto(
                    id = "ExhibitB",
                    name = "Exhibit B",
                    description = "Description",
                    isActive = true,
                ),
                ExhibitDto(
                    id = "ExhibitC",
                    name = "Exhibit C",
                    description = "Description",
                    isActive = true,
                ),
                ExhibitDto(
                    id = "ExhibitA",
                    name = "Exhibit A",
                    description = "Description",
                    isActive = true,
                ),
                ExhibitDto(
                    id = "ExhibitB",
                    name = "Exhibit B",
                    description = "Description",
                    isActive = true,
                ),
                ExhibitDto(
                    id = "ExhibitC",
                    name = "Exhibit C",
                    description = "Description",
                    isActive = true,
                ),
                ExhibitDto(
                    id = "ExhibitA",
                    name = "Exhibit A",
                    description = "Description",
                    isActive = true,
                ),
                ExhibitDto(
                    id = "ExhibitB",
                    name = "Exhibit B",
                    description = "Description",
                    isActive = true,
                ),
                ExhibitDto(
                    id = "ExhibitC",
                    name = "Exhibit C",
                    description = "Description",
                    isActive = true,
                ),
            ),
            savedViewType = "LIST"
        )
}
