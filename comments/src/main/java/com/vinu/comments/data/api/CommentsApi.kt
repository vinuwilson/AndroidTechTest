package com.vinu.comments.data.api

import com.vinu.comments.data.dto.CommentsDtoItem
import retrofit2.http.GET

interface CommentsApi {

    @GET("comments")
    suspend fun getComments(): List<CommentsDtoItem>
}