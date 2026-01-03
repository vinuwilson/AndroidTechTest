package com.vinu.comments.data.repository

import com.vinu.comments.data.dto.CommentsDtoItem
import javax.inject.Inject

class CommentsService @Inject constructor(

) {

    suspend fun getCommentsList() : Result<List<CommentsDtoItem>> {
        TODO()
    }
}