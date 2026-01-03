package com.vinu.comments.data.dto

import com.vinu.comments.domain.model.CommentsItem

data class CommentsDtoItem(
    val body: String = "",
    val email: String = "",
    val id: Int = 0,
    val name: String = "",
    val postId: Int = 0
)

fun CommentsDtoItem.toCommentsItem() = CommentsItem(
    body = body,
    email = email,
    id = id,
    name = name,
    postId = postId
)