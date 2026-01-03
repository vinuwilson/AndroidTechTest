package com.vinu.comments.data.repository

import com.vinu.comments.data.dto.CommentsDtoItem
import com.vinu.comments.data.dto.toCommentsItem
import com.vinu.comments.domain.model.CommentsItem
import javax.inject.Inject

class CommentsMapper @Inject constructor() : Function1<List<CommentsDtoItem>, List<CommentsItem>> {
    override fun invoke(comments: List<CommentsDtoItem>): List<CommentsItem> {
        return comments.map {
            it.toCommentsItem()
        }
    }
}