package com.instant.newsapp.domain.usecases

/**
 * Used to simplify access to usecases
 */
data class NewUseCase(val getNewsFromDb: GetNewsFromDb,
                       val getFavoriteNews: GetFavoriteNews,
                       val deleteNew: DeleteNew,
                       val insertFavoriteNew: InsertFavoriteNew,
                       val syncNews: SyncNews)