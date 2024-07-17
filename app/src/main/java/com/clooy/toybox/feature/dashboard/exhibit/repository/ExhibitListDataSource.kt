package com.clooy.toybox.feature.dashboard.exhibit.repository

import com.clooy.toybox.feature.dashboard.exhibit.data.Exhibit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class ExhibitListDataSource(private val exhibitList: List<Exhibit>) {
    val exhibitsStream: Flow<List<Exhibit>> = flowOf(exhibitList)
}
