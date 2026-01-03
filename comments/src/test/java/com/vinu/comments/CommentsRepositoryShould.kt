package com.vinu.comments

import com.vinu.comments.data.dto.CommentsDtoItem
import com.vinu.comments.data.repository.CommentsMapper
import com.vinu.comments.data.repository.CommentsRepositoryImp
import com.vinu.comments.data.repository.CommentsService
import com.vinu.comments.domain.model.CommentsItem
import com.vinu.comments.domain.repository.CommentsRepository
import com.vinu.comments.utils.BaseUnitTest
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class CommentsRepositoryShould : BaseUnitTest() {

    private lateinit var repository: CommentsRepository
    private val service: CommentsService = mock()
    private val mapper: CommentsMapper = mock()
    private val commentsDtoItem: List<CommentsDtoItem> = mock()
    private val commentsItem: List<CommentsItem> = mock()
    private val expected = Result.success(commentsItem)
    private val exception = RuntimeException("Something went wrong")

    @Test
    fun `invoke comments list service at least once`() = runTest {

        mockSuccessfulCase()

        service.getCommentsList()

        verify(service, times(1)).getCommentsList()
    }

    @Test
    fun `emit comments list received from service`() = runTest {

        mockSuccessfulCase()

        assertEquals(expected, repository.getCommentsList())
    }

    @Test
    fun `emit error when error received from service`() = runTest {

        mockFailureCase()

        assertEquals(exception.message, repository.getCommentsList().exceptionOrNull()!!.message)
    }

    private suspend fun mockSuccessfulCase() {

        whenever(service.getCommentsList()).thenReturn(
            Result.success(commentsDtoItem)
        )

        whenever(mapper(commentsDtoItem)).thenReturn(commentsItem)

        repository = CommentsRepositoryImp(service, mapper)
    }

    private suspend fun mockFailureCase() {

        whenever(service.getCommentsList()).thenReturn(
            Result.failure(RuntimeException("Something went wrong"))
        )

        repository = CommentsRepositoryImp(service, mapper)
    }
}