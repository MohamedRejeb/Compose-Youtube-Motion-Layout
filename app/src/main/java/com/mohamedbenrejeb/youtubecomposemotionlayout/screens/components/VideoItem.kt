package com.mohamedbenrejeb.youtubecomposemotionlayout.screens.components

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mohamedbenrejeb.youtubecomposemotionlayout.model.VideoUiState
import com.mohamedbenrejeb.youtubecomposemotionlayout.ui.theme.YoutubeComposeMotionLayoutTheme

@Composable
fun VideoItem(
    onVideoItemClick: (videoUiState: VideoUiState) -> Unit,
    videoUiState: VideoUiState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clickable { onVideoItemClick(videoUiState) }
    ) {
        VideoItemThumbnail(
            videoUiState = videoUiState,
            modifier = Modifier
                .fillMaxWidth()
        )

        VideoItemDetails(
            videoUiState = videoUiState,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun VideoItemPreview() {
    YoutubeComposeMotionLayoutTheme {
        Surface(modifier = Modifier.width(400.dp)) {
            VideoItem(
                onVideoItemClick = {},
                videoUiState = VideoUiState.demoData.random()
            )
        }
    }
}