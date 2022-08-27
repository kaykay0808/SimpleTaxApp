package com.kay.simpletaxapp.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kay.simpletaxapp.ui.theme.LARGE_PADDING

@Composable
fun MainContent() {

    Column(
        modifier = Modifier.padding(all = LARGE_PADDING)
    ) {
        Text(text = "Test")
    }
}

@Preview
@Composable
fun MainContentPreview() {
    MainContent()
}