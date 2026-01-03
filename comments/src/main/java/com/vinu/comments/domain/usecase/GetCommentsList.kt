package com.vinu.comments.domain.usecase

import com.vinu.comments.domain.model.CommentsItem
import com.vinu.comments.domain.repository.CommentsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCommentsList @Inject constructor(
    private val repository: CommentsRepository
) {

    fun getCommentsList(): Flow<Result<List<CommentsItem>>> {
        return flow {
            emit(repository.getCommentsList())
        }.catch {
            emit(Result.failure(RuntimeException("Something went wrong")))
        }
    }
}