package com.mohamedbenrejeb.youtubecomposemotionlayout.model

import com.mohamedbenrejeb.youtubecomposemotionlayout.R
import java.util.UUID

data class VideoUiState(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val author: String,
    val authorImageResId: Int,
    val viewsCount: String,
    val publishDate: String,
    val thumbnailResId: Int
) {
    companion object {
        val demoData = listOf(
            VideoUiState(
                title = "Top 5 Server-Side Frameworks for Kotlin in 2022",
                author = "Kotlin by JetBrains",
                authorImageResId = R.drawable.image5,
                viewsCount = "12k views",
                publishDate = "9 months ago",
                thumbnailResId = R.drawable.image1
            ),
            VideoUiState(
                title = "Learn ML with TensorFlow",
                author = "TenserFlow",
                authorImageResId = R.drawable.image4,
                viewsCount = "213k views",
                publishDate = "4 months ago",
                thumbnailResId = R.drawable.image2
            ),
            VideoUiState(
                title = "Test your android app - Full guide",
                author = "Mo Coding",
                authorImageResId = R.drawable.image3,
                viewsCount = "22k views",
                publishDate = "2 days ago",
                thumbnailResId = R.drawable.image3
            ),
            VideoUiState(
                title = "Build Youtube Motion Layout Animation With Jetpack Compose - Android Studio Tutorial",
                author = "Mo Coding",
                authorImageResId = R.drawable.image2,
                viewsCount = "13k views",
                publishDate = "3 days ago",
                thumbnailResId = R.drawable.image4
            ),
            VideoUiState(
                title = "How to Implement Pagination With Jetpack Compose - Android Studio Tutorial",
                author = "Philipp Lackner",
                authorImageResId = R.drawable.image1,
                viewsCount = "16k views",
                publishDate = "8 months ago",
                thumbnailResId = R.drawable.image5
            ),
        )
    }
}
