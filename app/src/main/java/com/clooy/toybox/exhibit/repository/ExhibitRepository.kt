package com.clooy.toybox.exhibit.repository

class ExhibitRepository(private val exhibitDataSource: ExhibitDataSource) {

    val exhibitsStream = exhibitDataSource.exhibitsStream
}
