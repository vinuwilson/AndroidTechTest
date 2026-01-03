package com.vinu.comments.utils

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext

@Composable
fun ErrorView(
    error: String
) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        Toast.makeText(
            context,
            error,
            Toast.LENGTH_SHORT
        ).show()
    }
}