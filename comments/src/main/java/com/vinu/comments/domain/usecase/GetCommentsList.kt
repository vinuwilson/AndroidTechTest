package com.vinu.comments.domain.usecase

import com.vinu.comments.domain.model.CommentsItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCommentsList @Inject constructor(

){

    fun getCommentsList(): Flow<Result<List<CommentsItem>>> {
        return flow {  }
    }
}