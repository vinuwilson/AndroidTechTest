package com.vinu.comments.data.repository

import com.vinu.comments.domain.model.CommentsItem
import com.vinu.comments.domain.repository.CommentsRepository
import javax.inject.Inject

class CommentsRepositoryImp @Inject constructor(
    private val service: CommentsService,
    private val mapper: CommentsMapper
) : CommentsRepository {
    override suspend fun getCommentsList(): Result<List<CommentsItem>> {
        return try {
            val commentsList = service.getCommentsList()
            if (commentsList.isSuccess) {
                val commentsDto = commentsList.getOrElse { emptyList() }
                Result.success(mapper(commentsDto))
            } else {
                Result.failure(RuntimeException("Something went wrong"))
            }
        } catch (ex: Exception) {
            Result.failure(RuntimeException("Something went wrong", ex))
        }
    }
}