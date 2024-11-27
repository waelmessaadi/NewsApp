package com.instant.newsapp.data.sync

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.instant.newsapp.domain.repository.Repository

/**
 * A Worker that synchronizes news between the local database (Room) and the remote API in the background.
 * To be used in others case in same context
 */
class SyncArticlesWorker(
    context: Context,
    workerParams: WorkerParameters,
    private val repository: Repository
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result {
        return try {
            repository.syncNews()
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }
}