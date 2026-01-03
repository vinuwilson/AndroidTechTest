package com.vinu.comments.presenter.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.vinu.comments.domain.model.CommentsItem
import com.vinu.comments.utils.AppDimens

@Composable
fun SingleCommentView(
    comment: CommentsItem
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(AppDimens.appPadding)
            .clickable {

            },
        elevation = CardDefaults
            .elevatedCardElevation(AppDimens.cardElevation),
        colors = CardDefaults
            .cardColors(containerColor = MaterialTheme.colorScheme.onPrimary)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(AppDimens.appPadding)
        ) {
            Text(
                text = comment.name,
                fontSize = AppDimens.largeFontSize,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground,
                style = TextStyle(
                    platformStyle = PlatformTextStyle(
                        includeFontPadding = false
                    )
                )
            )

            Text(
                text = comment.body,
                modifier = Modifier.padding(top = AppDimens.smallPadding),
                fontSize = AppDimens.mediumFontSize,
                color = MaterialTheme.colorScheme.onBackground,
                style = TextStyle(
                    platformStyle = PlatformTextStyle(
                        includeFontPadding = false
                    )
                )
            )
        }
    }
}