package com.clooy.toybox.dashboard.exhibit.repository

import com.clooy.toybox.dashboard.exhibit.data.ExhibitItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class ExhibitListDataSource(private val exhibitList: List<ExhibitItem>) {
    val exhibitsStream: Flow<List<ExhibitItem>> = flowOf(exhibitList)
}
