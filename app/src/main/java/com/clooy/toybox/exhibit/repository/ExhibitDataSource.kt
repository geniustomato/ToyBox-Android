package com.clooy.toybox.exhibit.repository

import com.clooy.toybox.exhibit.data.Exhibit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class ExhibitDataSource(private val exhibitList: List<Exhibit>) {
    val exhibitsStream: Flow<List<Exhibit>> = flowOf(exhibitList)
}
