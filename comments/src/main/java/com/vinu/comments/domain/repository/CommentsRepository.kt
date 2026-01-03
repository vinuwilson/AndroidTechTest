package com.vinu.comments.domain.repository

import com.vinu.comments.domain.model.CommentsItem

interface CommentsRepository {

    suspend fun getCommentsList() : Result<List<CommentsItem>>
}