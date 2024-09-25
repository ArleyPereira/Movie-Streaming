package br.com.hellodev.moviestreaming.presenter.components.bar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.hellodev.moviestreaming.core.helper.NoRippleInteractionSource
import br.com.hellodev.moviestreaming.core.navigation.bar.BottomAppBarItems
import br.com.hellodev.moviestreaming.presenter.theme.MovieStreamingTheme
import br.com.hellodev.moviestreaming.presenter.theme.UrbanistFamily

@Composable
fun RowScope.BottomNavItemUI(
    item: BottomAppBarItems,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    NavigationBarItem(
        selected = isSelected,
        onClick = onClick,
        icon = {
            if (isSelected) {
                Icon(
                    painter = painterResource(id = item.selectedIcon),
                    contentDescription = stringResource(id = item.label),
                    tint = MovieStreamingTheme.colorScheme.defaultColor
                )
            } else {
                Icon(
                    painter = painterResource(id = item.unselectedIcon),
                    contentDescription = stringResource(id = item.label),
                    tint = MovieStreamingTheme.colorScheme.greyscale500Color
                )
            }
        },
        label = {
            Text(
                text = stringResource(item.label),
                style = TextStyle(
                    fontSize = 10.sp,
                    fontFamily = UrbanistFamily,
                    fontWeight = FontWeight.Bold,
                    color = if (isSelected) {
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
            indicatorColor = Color.Transparent,
            selectedIconColor = Color.Transparent
        ),
        interactionSource = NoRippleInteractionSource()
    )
}

@Preview
@Composable
private fun BottomNavItemUIPreview() {
    BottomAppBar(
        modifier = Modifier
            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
        actions = {
            BottomAppBarItems.items.forEach { item ->
                BottomNavItemUI(
                    item = item,
                    isSelected = item == BottomAppBarItems.Home,
                    onClick = { }
                )
            }
        },
        containerColor = MovieStreamingTheme.colorScheme.backgroundColor,
    )
}