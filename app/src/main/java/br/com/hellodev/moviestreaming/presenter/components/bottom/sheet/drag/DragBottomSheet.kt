package br.com.hellodev.moviestreaming.presenter.components.bottom.sheet.drag

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.hellodev.moviestreaming.presenter.theme.MovieStreamingTheme

@Composable
fun DragBottomSheet(
    modifier: Modifier = Modifier
) {
    Spacer(
        modifier = Modifier
            .padding(
                top = 8.dp,
                bottom = 24.dp
            )
            .clip(CircleShape)
            .width(38.dp)
            .height(3.dp)
            .background(MovieStreamingTheme.colorScheme.greyscale300Color)
    )
}

@Preview
@Composable
private fun DragBottomSheetPreview() {
    MovieStreamingTheme {
        DragBottomSheet()
    }
}