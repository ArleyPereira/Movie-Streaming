package br.com.hellodev.moviestreaming.presenter.components.bottom.sheet.select_image

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.hellodev.moviestreaming.R
import br.com.hellodev.moviestreaming.presenter.components.button.SecondaryButton
import br.com.hellodev.moviestreaming.presenter.theme.MovieStreamingTheme
import br.com.hellodev.moviestreaming.presenter.theme.UrbanistFamily

@Composable
fun SelectImageBottomSheet(
    modifier: Modifier = Modifier,
    onCameraClick: () -> Unit,
    onGalleryClick: () -> Unit,
    onCancelClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MovieStreamingTheme.colorScheme.secondaryBackgroundColor)
            .padding(
                start = 24.dp,
                end = 24.dp,
                bottom = 48.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.label_title_select_image_bottom_sheet),
            style = TextStyle(
                fontSize = 24.sp,
                lineHeight = 28.8.sp,
                fontFamily = UrbanistFamily,
                fontWeight = FontWeight.Bold,
                color = MovieStreamingTheme.colorScheme.textColor,
                textAlign = TextAlign.Center
            )
        )

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            color = MovieStreamingTheme.colorScheme.dividerColor
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onCameraClick() },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .background(
                        color = MovieStreamingTheme.colorScheme.transparentColor,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = MovieStreamingTheme.colorScheme.borderColor,
                        shape = RoundedCornerShape(12.dp)
                    )
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_camera),
                    contentDescription = stringResource(R.string.label_camera_select_image_bottom_sheet),
                    modifier = Modifier
                        .padding(12.dp),
                    tint = MovieStreamingTheme.colorScheme.iconColor
                )
            }

            Text(
                text = stringResource(R.string.label_camera_select_image_bottom_sheet),
                style = TextStyle(
                    lineHeight = 21.6.sp,
                    fontFamily = UrbanistFamily,
                    fontWeight = FontWeight(600),
                    color = MovieStreamingTheme.colorScheme.textColor
                )
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onGalleryClick() },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .background(
                        color = MovieStreamingTheme.colorScheme.transparentColor,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = MovieStreamingTheme.colorScheme.borderColor,
                        shape = RoundedCornerShape(12.dp)
                    )
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_gallery),
                    contentDescription = stringResource(R.string.label_gallery_select_image_bottom_sheet),
                    modifier = Modifier
                        .padding(12.dp),
                    tint = MovieStreamingTheme.colorScheme.iconColor
                )
            }

            Text(
                text = stringResource(R.string.label_gallery_select_image_bottom_sheet),
                style = TextStyle(
                    lineHeight = 21.6.sp,
                    fontFamily = UrbanistFamily,
                    fontWeight = FontWeight(600),
                    color = MovieStreamingTheme.colorScheme.textColor
                )
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        SecondaryButton(
            modifier = Modifier
                .fillMaxWidth(),
            text = stringResource(R.string.label_cancel_select_image_bottom_sheet),
            onClick = onCancelClick
        )
    }
}

@PreviewLightDark
@Composable
private fun SelectImageBottomSheetPreview() {
    MovieStreamingTheme {
        SelectImageBottomSheet(
            onCameraClick = {},
            onGalleryClick = {},
            onCancelClick = {}
        )
    }
}