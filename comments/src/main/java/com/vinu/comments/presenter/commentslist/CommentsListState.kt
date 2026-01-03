package com.vinu.comments.presenter.commentslist

import com.vinu.comments.domain.model.CommentsItem

data class CommentsListState(
    val isLoading: Boolean = false,
    val commentsList: List<CommentsItem>? = emptyList()
)
