package com.vinu.comments.presenter.commentslist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.vinu.comments.R
import com.vinu.comments.domain.model.CommentsItem
import com.vinu.comments.utils.AppDimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentDetailsScreen(
    comment: CommentsItem?,
    onBackPressed: () -> Unit
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
                        text = stringResource(R.string.details)
                    )
                },
                windowInsets = WindowInsets(0),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.onPrimary
                ),
                navigationIcon = {
                    IconButton(
                        modifier = Modifier
                            .padding(vertical = AppDimens.extraLargeAppPadding),
                        onClick = {
                            onBackPressed()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.back_arrow_icon)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        if (comment != null) {
            Surface(
                modifier = Modifier
                    .padding(innerPadding),
                color = MaterialTheme.colorScheme.surface
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(AppDimens.largeAppPadding),
                    elevation = CardDefaults
                        .elevatedCardElevation(AppDimens.cardElevation),
                    colors = CardDefaults
                        .cardColors(containerColor = MaterialTheme.colorScheme.onPrimary)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(AppDimens.appPadding)
                    ) {
                        Text(
                            text = comment.name,
                            fontSize = AppDimens.largeFontSize,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onBackground,
                        )

                        Text(
                            text = comment.body,
                            modifier = Modifier.padding(top = AppDimens.appPadding),
                            fontSize = AppDimens.mediumFontSize,
                            color = MaterialTheme.colorScheme.onBackground,
                            style = TextStyle(
                                platformStyle = PlatformTextStyle(
                                    includeFontPadding = false
                                )
                            )
                        )

                        HorizontalDivider(
                            thickness = AppDimens.dividerThickness,
                            modifier = Modifier
                                .padding(vertical = AppDimens.appPadding),
                            color = Color.LightGray
                        )

                        DetailsRow(stringResource(R.string.id), comment.id.toString())

                        HorizontalDivider(
                            thickness = AppDimens.dividerThickness,
                            modifier = Modifier
                                .padding(vertical = AppDimens.appPadding),
                            color = Color.LightGray
                        )

                        DetailsRow(stringResource(R.string.email), comment.email)
                    }
                }
            }
        }
    }
}

@Composable
fun DetailsRow(name: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = name,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Start
        )
        Text(
            text = value,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.End
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CommentDetailsScreenPreview() {
    CommentDetailsScreen(
        comment = CommentsItem(
            postId = 1,
            id = 1,
            name = "id labore ex et quam laborum",
            email = "Eliseo@gardner.biz",
            body = "laudantium enim quasi est quidem magnam voluptate ipsam eos\ntempora quo necessitatibus\ndolor quam autem quasi\nreiciendis et nam sapiente accusantium"
        ),
        onBackPressed = {}
    )
}