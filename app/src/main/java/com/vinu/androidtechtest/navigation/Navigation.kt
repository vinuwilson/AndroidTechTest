package com.vinu.androidtechtest.navigation

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.vinu.comments.presenter.commentslist.CommentDetailsScreen
import com.vinu.comments.presenter.commentslist.CommentsList
import com.vinu.comments.presenter.commentslist.CommentsViewModel
import com.vinu.comments.utils.sharedViewModel

@Composable
fun Navigation() {

    val navController = rememberNavController()

    Scaffold(
        contentWindowInsets = WindowInsets(0)
    ) { innerPadding ->
        NavHost(
            modifier = Modifier
                .padding(innerPadding)
                .consumeWindowInsets(innerPadding),
            navController = navController,
            startDestination = Comments
        ) {

            navigation<Comments>(
                startDestination = CommentsList
            ) {
                composable<CommentsList> { entry ->

                    val viewModel =
                        entry.sharedViewModel<CommentsViewModel>(navController = navController)
                    val commentsState = viewModel.commentsList.collectAsStateWithLifecycle()

                    CommentsList(
                        state = commentsState.value
                    ) { id ->
                        navController.navigate(CommentDetails(id))
                    }
                }

                composable<CommentDetails> { entry ->

                    val viewModel =
                        entry.sharedViewModel<CommentsViewModel>(navController = navController)
                    val commentsState = viewModel.commentsList.collectAsStateWithLifecycle()

                    val commentId = entry.toRoute<CommentDetails>().id

                    val selectedComment =
                        commentsState.value.commentsList?.firstOrNull { it.id == commentId }

                    CommentDetailsScreen(
                        comment = selectedComment
                    ) {
                        navController.navigateUp()
                    }
                }
            }
        }
    }

}