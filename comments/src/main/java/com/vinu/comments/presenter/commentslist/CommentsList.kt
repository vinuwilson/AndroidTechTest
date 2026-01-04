package com.vinu.comments.presenter.commentslist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.vinu.comments.R
import com.vinu.comments.domain.model.CommentsItem
import com.vinu.comments.presenter.components.SingleCommentView
import com.vinu.comments.utils.AppDimens
import com.vinu.comments.utils.ErrorView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentsList(
    state: CommentsListState,
    onItemClicked: (Int) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        modifier = Modifier
                            .padding(vertical = AppDimens.extraLargeAppPadding),
                        fontSize = AppDimens.largeFontSize,
                        fontWeight = FontWeight.Bold,
                        text = stringResource(R.string.title)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.onPrimary
                ),
                windowInsets = WindowInsets(0)
            )
        }
    ) { innerPadding ->
        if (state.isLoading) {
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .background(MaterialTheme.colorScheme.onPrimary)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.onBackground)
            }
        } else {
            if (!state.commentsList.isNullOrEmpty()) {
                CommentsListView(
                    modifier = Modifier.padding(innerPadding),
                    comments = state.commentsList
                ) { id ->
                    onItemClicked(id)
                }
            } else {
                ErrorView(stringResource(R.string.error_no_internet))
            }
        }
    }
}

@Composable
fun CommentsListView(
    modifier: Modifier,
    comments: List<CommentsItem>,
    onItemClicked: (Int) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        items(
            items = comments,
            key = { commentId -> commentId.id }
        ) { comment ->
            SingleCommentView(
                comment = comment
            ) { id ->
                onItemClicked(id)
            }
        }
    }
}