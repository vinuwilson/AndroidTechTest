package com.vinu.comments.presenter.commentslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vinu.comments.domain.usecase.GetCommentsList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommentsViewModel @Inject constructor(
    private val getCommentsList: GetCommentsList
) : ViewModel() {

    private val _commentsList = MutableStateFlow(CommentsListState())
    val commentsList = _commentsList.asStateFlow()

    init {
        getCommentsList()
    }

    private fun getCommentsList() {
        viewModelScope.launch {
            _commentsList.update {
                it.copy(
                    isLoading = true
                )
            }
            _commentsList.update {
                val commentsList = getCommentsList
                    .getCommentsList()
                    .firstOrNull()
                    ?.getOrDefault(emptyList())
                    ?: emptyList()
                it.copy(
                    isLoading = false,
                    commentsList = commentsList
                )
            }
        }
    }
}