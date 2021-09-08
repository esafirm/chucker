package com.chucker.tracker.internal.data.repository

import android.content.Context
import com.chucker.tracker.internal.data.room.TrackerLogDatabase

internal object TrackerLogRepositoryProvider {

    private var trackerLogRepositoryBuilder: (() -> TrackerLogRepository)? = null
    private var trackerLogRepository: TrackerLogRepository? = null

    fun get(): TrackerLogRepository {
        if (trackerLogRepository == null) {
            trackerLogRepository = trackerLogRepositoryBuilder?.invoke()
        }
        return checkNotNull(trackerLogRepository) {
            "You can't access the tracker log repository if you don't initialize it!"
        }
    }

    fun initialize(context: Context) {
        if (trackerLogRepositoryBuilder == null) {
            trackerLogRepositoryBuilder = {
                TrackerLogRepositoryImpl(TrackerLogDatabase.create(context))
            }
        }
    }
}