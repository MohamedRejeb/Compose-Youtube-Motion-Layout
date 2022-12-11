package com.mohamedbenrejeb.youtubecomposemotionlayout.screens.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mohamedbenrejeb.youtubecomposemotionlayout.model.VideoUiState
import com.mohamedbenrejeb.youtubecomposemotionlayout.ui.theme.YoutubeComposeMotionLayoutTheme

@Composable
fun VideoItemThumbnail(
    videoUiState: VideoUiState,
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(id = videoUiState.thumbnailResId),
        contentDescription = videoUiState.title,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .aspectRatio(16f / 9f)
    )
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun VideoItemThumbnailPreview() {
    YoutubeComposeMotionLayoutTheme {
        Surface(modifier = Modifier.width(400.dp)) {
            VideoItemThumbnail(
                videoUiState = VideoUiState.demoData.random(),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}