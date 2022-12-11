package com.mohamedbenrejeb.youtubecomposemotionlayout.screens.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mohamedbenrejeb.youtubecomposemotionlayout.model.VideoUiState
import com.mohamedbenrejeb.youtubecomposemotionlayout.ui.theme.YoutubeComposeMotionLayoutTheme

@Composable
fun VideoItemDetails(
    videoUiState: VideoUiState,
    modifier: Modifier = Modifier
) {
    val colorScheme = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography

    Row(
        verticalAlignment = Alignment.Top,
        modifier = modifier
            .padding(all = 10.dp)
    ) {
        Image(
            painter = painterResource(id = videoUiState.authorImageResId),
            contentDescription = videoUiState.author,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(42.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(10.dp))

        Column(
            modifier = Modifier
        ) {
            Text(
                text = videoUiState.title,
                color = colorScheme.onBackground,
                style = typography.titleSmall
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "${videoUiState.author} ${videoUiState.viewsCount} ${videoUiState.publishDate}",
                color = colorScheme.onBackground.copy(alpha = 0.6f),
                style = typography.bodySmall
            )
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun VideoItemDetailsPreview() {
    YoutubeComposeMotionLayoutTheme {
        Surface(modifier = Modifier.width(400.dp)) {
            VideoItemDetails(
                videoUiState = VideoUiState.demoData.random()
            )
        }
    }
}