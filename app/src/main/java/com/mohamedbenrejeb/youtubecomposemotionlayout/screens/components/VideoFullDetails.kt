package com.mohamedbenrejeb.youtubecomposemotionlayout.screens.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.*
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
fun VideoFullDetails(
    videoUiState: VideoUiState,
    modifier: Modifier = Modifier,
) {
    val colorScheme = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography

    Column(
        modifier = modifier
            .padding(all = 10.dp)
    ) {
        Text(
            text = videoUiState.title,
            color = colorScheme.onBackground,
            style = typography.titleMedium
        )

        Spacer(modifier = Modifier.height(6.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(vertical = 10.dp)
        ) {
            Image(
                painter = painterResource(id = videoUiState.authorImageResId),
                contentDescription = videoUiState.author,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(38.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = videoUiState.author,
                color = colorScheme.onBackground,
                style = typography.titleSmall
            )

            Spacer(modifier = Modifier.width(6.dp))

            Text(
                text = videoUiState.viewsCount,
                color = colorScheme.onBackground.copy(alpha = 0.6f),
                style = typography.bodySmall
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
        ) {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.ThumbUp,
                    contentDescription = "ThumbUp",
                    tint = colorScheme.onBackground
                )
            }

            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = "Share",
                    tint = colorScheme.onBackground
                )
            }

            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Favorite",
                    tint = colorScheme.onBackground
                )
            }
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun VideoFullDetailsPreview() {
    YoutubeComposeMotionLayoutTheme {
        Surface(modifier = Modifier.width(400.dp)) {
            VideoFullDetails(
                videoUiState = VideoUiState.demoData.random()
            )
        }
    }
}