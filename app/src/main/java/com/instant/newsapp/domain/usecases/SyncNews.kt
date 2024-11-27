package com.instant.newsapp.domain.usecases

import com.instant.newsapp.domain.repository.Repository
import javax.inject.Inject

/**
 * Delete news (isFavorite=false),
 * Retrieve news from Api,
 * Insert data in database,
 * To Ensures that the database updated with the latest news data.
 * In some cases, news data can be compared with database news before syncing.
 */
class SyncNews @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke() {
        repository.syncNews()
    }
}