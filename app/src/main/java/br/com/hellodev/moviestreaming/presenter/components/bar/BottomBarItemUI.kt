package br.com.hellodev.moviestreaming.presenter.components.bar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import br.com.hellodev.moviestreaming.core.helper.NoRippleInteractionSource
import br.com.hellodev.moviestreaming.core.navigation.bar.BottomAppBarItems
import br.com.hellodev.moviestreaming.presenter.theme.MovieStreamingTheme
import br.com.hellodev.moviestreaming.presenter.theme.UrbanistFamily

@Composable
fun RowScope.BottomBarItemUI(
    modifier: Modifier = Modifier,
    item: BottomAppBarItems,
    isSelect: Boolean = false,
    onClick: () -> Unit
) {
    NavigationBarItem(
        selected = isSelect,
        onClick = onClick,
        icon = {
            if (isSelect) {
                Icon(
                    painter = painterResource(item.selectedIcon),
                    contentDescription = stringResource(item.label),
                    tint = MovieStreamingTheme.colorScheme.defaultColor
                )
            } else {
                Icon(
                    painter = painterResource(item.unselectedIcon),
                    contentDescription = stringResource(item.label),
                    tint = MovieStreamingTheme.colorScheme.greyscale500Color
                )
            }
        },
        modifier = modifier,
        label = {
            Text(
                text = stringResource(item.label),
                style = TextStyle(
                    fontSize = 10.sp,
                    fontFamily = UrbanistFamily,
                    fontWeight = FontWeight.Bold,
                    color = if (isSelect) {
                        MovieStreamingTheme.colorScheme.defaultColor
                    } else {
                        MovieStreamingTheme.colorScheme.greyscale500Color
                    },
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.2.sp
                )
            )
        },
        colors = NavigationBarItemDefaults.colors(
            indicatorColor = MovieStreamingTheme.colorScheme.transparentColor
        ),
        interactionSource = NoRippleInteractionSource()
    )
}

@Preview
@Composable
private fun BottomBarItemUIPreview() {
    MovieStreamingTheme {
        BottomAppBar(
            actions = {
                BottomAppBarItems.items.forEach { item ->
                    BottomBarItemUI(
                        item = item,
                        isSelect = item == BottomAppBarItems.Home,
                        onClick = {}
                    )
                }
            },
            containerColor = MovieStreamingTheme.colorScheme.backgroundColor
        )
    }
}