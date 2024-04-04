package com.clooy.toybox.dashboard.exhibit.repository

class ExhibitListRepository(private val exhibitListDataSource: ExhibitListDataSource) {
    val exhibitsStream = exhibitListDataSource.exhibitsStream
}
