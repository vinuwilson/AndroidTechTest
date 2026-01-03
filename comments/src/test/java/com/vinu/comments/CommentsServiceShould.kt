package com.vinu.comments

import com.vinu.comments.data.api.CommentsApi
import com.vinu.comments.data.dto.CommentsDtoItem
import com.vinu.comments.data.repository.CommentsService
import com.vinu.comments.utils.BaseUnitTest
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class CommentsServiceShould : BaseUnitTest() {

    private lateinit var service: CommentsService
    private val api: CommentsApi = mock()
    private val commentsDtoItem: List<CommentsDtoItem> = mock()
    private val expected = Result.success(commentsDtoItem)
    private val exception = RuntimeException("Something went wrong")

    @Test
    fun `invoke comments list api at least once`() = runTest {

        mockSuccessfulCase()

        service.getCommentsList()

        verify(api, times(1)).getComments()
    }

    @Test
    fun `emit comments list received from api`() = runTest {

        mockSuccessfulCase()

        assertEquals(expected, service.getCommentsList())
    }

    @Test
    fun `emit error when error received from api`() = runTest {

        mockFailureCase()

        assertEquals(exception.message, service.getCommentsList().exceptionOrNull()!!.message)
    }

    private suspend fun mockSuccessfulCase() {

        whenever(api.getComments()).thenReturn(commentsDtoItem)

        service = CommentsService(api)
    }

    private suspend fun mockFailureCase() {

        whenever(api.getComments()).thenThrow(
            RuntimeException("Back end exception")
        )

        service = CommentsService(api)
    }
}