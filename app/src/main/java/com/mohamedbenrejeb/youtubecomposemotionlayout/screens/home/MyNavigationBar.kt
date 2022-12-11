package com.mohamedbenrejeb.youtubecomposemotionlayout.screens.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mohamedbenrejeb.youtubecomposemotionlayout.ui.theme.YoutubeComposeMotionLayoutTheme

val navigationBarItems = listOf(
    "Home" to Icons.Default.Home,
    "Shorts" to Icons.Default.PlayArrow,
    "Add" to Icons.Default.Add,
    "Library" to Icons.Default.AccountBox
)

@Composable
fun MyNavigationBar(
    modifier: Modifier = Modifier,
    items: List<Pair<String, ImageVector>> = navigationBarItems,
) {
    val colorScheme = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography

    Column(
        modifier = modifier
    ) {
        Divider(
            thickness = 1.dp,
            color = colorScheme.onBackground.copy(alpha = 0.2f)
        )

        NavigationBar(
            windowInsets = NavigationBarDefaults.windowInsets,
            containerColor = colorScheme.background,
            contentColor = colorScheme.onBackground,
            tonalElevation = 0.dp,
        ) {
            items.forEach { (name, icon) ->
                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = icon,
                            contentDescription = name,
                            modifier = Modifier
                        )
                    },
                    label = {
                        Text(
                            text = name,
                            style = typography.labelMedium
                        )
                    },
                    alwaysShowLabel = true,
                    selected = false,
                    onClick = {  }
                )
            }
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun MyNavigationBarPreview() {
    YoutubeComposeMotionLayoutTheme {
        MyNavigationBar()
    }
}