package com.vinu.androidtechtest.navigation

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vinu.comments.presenter.commentslist.CommentsList
import com.vinu.comments.presenter.commentslist.CommentsViewModel

@Composable
fun Navigation() {

    val navController = rememberNavController()

    Scaffold { innerPadding ->
        NavHost(
            modifier = Modifier
                .padding(innerPadding)
                .consumeWindowInsets(innerPadding),
            navController = navController,
            startDestination = Comments
        ) {

            composable<Comments> {

                val viewModel = hiltViewModel<CommentsViewModel>()
                val commentsState = viewModel.commentsList.collectAsStateWithLifecycle()

                CommentsList(
                    state = commentsState.value
                )
            }
        }
    }

}