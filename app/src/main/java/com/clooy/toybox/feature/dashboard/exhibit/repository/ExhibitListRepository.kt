package com.clooy.toybox.feature.dashboard.exhibit.repository

class ExhibitListRepository(private val exhibitListDataSource: ExhibitListDataSource) {
    val exhibitsStream = exhibitListDataSource.exhibitsStream
}
