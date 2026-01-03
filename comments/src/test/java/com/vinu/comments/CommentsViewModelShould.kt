package com.vinu.comments

import com.vinu.comments.domain.model.CommentsItem
import com.vinu.comments.domain.usecase.GetCommentsList
import com.vinu.comments.presenter.commentslist.CommentsListState
import com.vinu.comments.presenter.commentslist.CommentsViewModel
import com.vinu.comments.utils.BaseUnitTest
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class CommentsViewModelShould : BaseUnitTest() {

    private lateinit var viewModel: CommentsViewModel
    private val getCommentsList: GetCommentsList = mock()
    private val commentsItem: List<CommentsItem> = mock()
    private val expected = CommentsListState(false, emptyList())
    private val exception = CommentsListState()

    @Test
    fun `invoke comments list at least once`() = runTest {

        mockSuccessfulCase()

        viewModel.commentsList.first()

        verify(getCommentsList, times(1)).getCommentsList()
    }

    @Test
    fun `emit comments list when received from use case`() = runTest {

        mockSuccessfulCase()

        assertEquals(expected, viewModel.commentsList.value)
    }

    @Test
    fun `emit error when error received from use case`() = runTest {

        mockFailureCase()

        assertEquals(exception, viewModel.commentsList.first())
    }

    private fun mockSuccessfulCase() {
        whenever(getCommentsList.getCommentsList()).thenReturn(
            flow {
                emit(Result.success(commentsItem))
            }
        )

        viewModel = CommentsViewModel(getCommentsList)
    }

    private fun mockFailureCase() {
        whenever(getCommentsList.getCommentsList()).thenReturn(
            flow {
                emit(Result.failure(RuntimeException("Something went wrong")))
            }
        )

        viewModel = CommentsViewModel(getCommentsList)
    }
}