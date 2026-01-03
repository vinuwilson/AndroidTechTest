package com.vinu.comments

import com.vinu.comments.domain.model.CommentsItem
import com.vinu.comments.domain.repository.CommentsRepository
import com.vinu.comments.domain.usecase.GetCommentsList
import com.vinu.comments.utils.BaseUnitTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class GetCommentsListShould : BaseUnitTest() {

    private lateinit var getCommentsList: GetCommentsList
    private val repository: CommentsRepository = mock()
    private val commentsItem: List<CommentsItem> = mock()
    private val expected = Result.success(commentsItem)
    private val exception = RuntimeException("Something went wrong")

    @Test
    fun `invoke comments list repository at least once`() = runTest {

        mockSuccessfulCase()

        getCommentsList.getCommentsList().first()

        verify(repository, times(1)).getCommentsList()
    }

    @Test
    fun `emit comments list received from repository`() = runTest {

        mockSuccessfulCase()

        assertEquals(expected, getCommentsList.getCommentsList().first())
    }

    @Test
    fun `emit error when error received from repository`() = runTest {

        mockFailureCase()

        assertEquals(
            exception.message,
            getCommentsList.getCommentsList().first().exceptionOrNull()!!.message
        )
    }

    private suspend fun mockSuccessfulCase() {

        whenever(repository.getCommentsList()).thenReturn(
            Result.success(commentsItem)
        )

        getCommentsList = GetCommentsList(repository)
    }

    private suspend fun mockFailureCase() {

        whenever(repository.getCommentsList()).thenReturn(
            Result.failure(RuntimeException("Something went wrong"))
        )

        getCommentsList = GetCommentsList(repository)
    }
}