package com.vinu.androidtechtest.navigation

import kotlinx.serialization.Serializable

@Serializable
object Comments

@Serializable
object CommentsList

@Serializable
data class CommentDetails(
    val id: Int,
)