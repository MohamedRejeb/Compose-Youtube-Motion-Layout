package com.mohamedbenrejeb.youtubecomposemotionlayout.screens.home

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import com.mohamedbenrejeb.youtubecomposemotionlayout.R
import com.mohamedbenrejeb.youtubecomposemotionlayout.model.VideoUiState
import com.mohamedbenrejeb.youtubecomposemotionlayout.screens.components.VideoColumn
import com.mohamedbenrejeb.youtubecomposemotionlayout.screens.components.VideoFullDetails
import com.mohamedbenrejeb.youtubecomposemotionlayout.ui.theme.YoutubeComposeMotionLayoutTheme
import kotlinx.coroutines.launch
import java.lang.Float.min
import kotlin.math.max

@OptIn(ExperimentalMotionApi::class, ExperimentalMaterialApi::class)
@Composable
fun HomeScreen() {
    val colorScheme = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography
    val videoUiStateList by remember {
        mutableStateOf(VideoUiState.demoData)
    }

    val context = LocalContext.current
    val density = LocalDensity.current
    val scope = rememberCoroutineScope()
    val configuration = LocalConfiguration.current

    // Calculate screen height
    val screenHeight = with(density) { configuration.screenHeightDp.dp.toPx() }

    // Set the swipe are that we want ( Here I choose it as screenHeight - 400 )
    // Which is approximately the height from the top of collapsed video item to the top of the screen
    // Which is the swipe are that gives us a realistic swipe animation
    val swipeAreaHeight = screenHeight - 400

    // Initialize the selected video state
    var videoUiState by remember {
        mutableStateOf<VideoUiState?>(null)
    }

    // Initialize motion scene content from json5 file
    val motionSceneContent = remember {
        context.resources
            .openRawResource(R.raw.motion_scene)
            .readBytes()
            .decodeToString()
    }

    // Initialize swipeableState
    val swipeableState = rememberSwipeableState(0)

    // Swipe start point and end point
    val anchors = mapOf(0f to 0, -swipeAreaHeight to 1)

    // Calculate swipe progress -> swipe offset / max swipe offset
    val swipeProgress = swipeableState.offset.value / -swipeAreaHeight

    // Limit swipe progress between 0f and 1f
    // ( Swipe progress could be < 0f and > 1f and this may cause some problems )
    val motionProgress = max(min(swipeProgress, 1f), 0f)

    // Start open video animation on video selected
    LaunchedEffect(key1 = videoUiState) {
        if (videoUiState != null && swipeableState.currentValue == 0) {
            swipeableState.animateTo(1)
        }
    }

    // Initialize List state
    val listState = rememberLazyListState()

    // Scroll to selected item when selection changes
    LaunchedEffect(key1 = videoUiState) {
        videoUiStateList.indexOf(videoUiState).let { index ->
            if (index > -1)
                listState.animateScrollToItem(index)
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        MotionLayout(
            motionScene = MotionScene(content = motionSceneContent),
            progress = motionProgress,
            modifier = Modifier.fillMaxSize()
        ) {
            VideoColumn(
                listState = listState,
                onVideoItemClick = {
                    if (swipeableState.currentValue == 0) {
                        if (videoUiState != it) {
                            videoUiState = it
                        } else {
                            scope.launch {
                                swipeableState.animateTo(1)
                            }
                        }
                    }
                },
                videoUiStateList = videoUiStateList,
                modifier = Modifier
                    .padding(
                        bottom = if (
                            swipeableState.progress.from == 0
                            && swipeableState.progress.to == 0
                            && videoUiState != null
                        ) 60.dp else 0.dp
                    )
                    .alpha(max(1f - min(motionProgress * 2, 1f), 0.7f))
                    .fillMaxWidth()
                    .layoutId("videoColumn")
            )

            Image(
                painter = painterResource(id = videoUiState?.thumbnailResId ?: R.drawable.image1),
                contentDescription = videoUiState?.title ?: "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clickable {
                        scope.launch { swipeableState.animateTo(1) }
                    }
                    .alpha(if (videoUiState == null) 0f else 1f)
                    .zIndex(if (videoUiState == null) -1f else 1f)
                    .swipeable(
                        state = swipeableState,
                        anchors = anchors,
                        thresholds = { _, _ -> FractionalThreshold(0.3f) },
                        orientation = Orientation.Vertical,
                        enabled = videoUiState != null,
                    )
                    .layoutId("thumbnail")
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clickable {
                        scope.launch { swipeableState.animateTo(1) }
                    }
                    .alpha(if (videoUiState == null) 0f else 1f)
                    .zIndex(if (videoUiState == null) -1f else 1f)
                    .fillMaxWidth()
                    .swipeable(
                        state = swipeableState,
                        anchors = anchors,
                        thresholds = { _, _ -> FractionalThreshold(0.3f) },
                        orientation = Orientation.Vertical,
                        enabled = videoUiState != null,
                    )
                    .background(colorScheme.background)
                    .padding(all = 10.dp)
                    .layoutId("details")
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .alpha(1f - min(motionProgress * 2, 1f))
                ) {
                    Text(
                        text = videoUiState?.title ?: "",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = typography.bodyMedium,
                        color = colorScheme.onBackground
                    )
                    Text(
                        text = videoUiState?.author ?: "",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = typography.bodyMedium,
                        color = colorScheme.onBackground.copy(alpha = 0.6f)
                    )
                }

                IconButton(
                    onClick = {
                        scope.launch {
                            swipeableState.animateTo(1)
                        }
                    },
                    modifier = Modifier
                        .alpha(1f - min(motionProgress * 2, 1f))
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Play",
                        tint = colorScheme.onBackground
                    )
                }

                IconButton(
                    onClick = { videoUiState = null },
                    modifier = Modifier
                        .alpha(1f - min(motionProgress * 2, 1f))
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close",
                        tint = colorScheme.onBackground
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorScheme.background)
                    .zIndex(if (videoUiState == null) -1f else 1f)
                    .layoutId("content")
            ) {
                VideoFullDetails(
                    videoUiState = videoUiState ?: VideoUiState.demoData.first(),
                    modifier = Modifier
                        .alpha(min(motionProgress, 1f))
                        .fillMaxWidth()
                )

                VideoColumn(
                    onVideoItemClick = { videoUiState = it },
                    videoUiStateList = VideoUiState.demoData,
                    modifier = Modifier
                        .alpha(min(motionProgress, 1f))
                        .fillMaxWidth()
                )
            }

            MyNavigationBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .zIndex(1f)
                    .layoutId("navigationBar")
            )
        }
    }
}

@Preview(
    device = Devices.PIXEL_4
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    device = Devices.PIXEL_4
)
@Composable
fun HomeScreenPreview() {
    YoutubeComposeMotionLayoutTheme {
        HomeScreen()
    }
}