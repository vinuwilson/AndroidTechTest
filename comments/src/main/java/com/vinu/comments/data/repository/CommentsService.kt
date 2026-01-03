package com.vinu.comments.data.repository

import com.vinu.comments.data.api.CommentsApi
import com.vinu.comments.data.dto.CommentsDtoItem
import javax.inject.Inject

class CommentsService @Inject constructor(
    private val api: CommentsApi
) {

    suspend fun getCommentsList(): Result<List<CommentsDtoItem>> {
        return try {
            Result.success(api.getComments())
        } catch (ex: Exception) {
            Result.failure(RuntimeException("Something went wrong", ex))
        }
    }
}