package br.com.hellodev.moviestreaming.presenter.components.dialog.download

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import br.com.hellodev.moviestreaming.R
import br.com.hellodev.moviestreaming.core.extensions.calculateFileSize
import br.com.hellodev.moviestreaming.presenter.components.button.SecondaryButton
import br.com.hellodev.moviestreaming.presenter.components.divider.HorizontalDividerUI
import br.com.hellodev.moviestreaming.presenter.theme.MovieStreamingTheme
import br.com.hellodev.moviestreaming.presenter.theme.UrbanistFamily

@Composable
fun DownloadDialogUI(
    modifier: Modifier = Modifier,
    progress: Int,
    downloadedSize: Float = 0f,
    downloadSize: Float = 0f,
    onDismissRequest: () -> Unit
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(40.dp),
            colors = CardDefaults.cardColors(
                containerColor = MovieStreamingTheme.colorScheme.secondaryBackgroundColor
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 32.dp,
                        start = 24.dp,
                        end = 24.dp,
                        bottom = 24.dp
                    )
            ) {
                Text(
                    text = stringResource(R.string.text_title_dialog_downloading),
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = TextStyle(
                        fontSize = 24.sp,
                        lineHeight = 28.8.sp,
                        fontFamily = UrbanistFamily,
                        fontWeight = FontWeight(700),
                        color = MovieStreamingTheme.colorScheme.defaultColor,
                        textAlign = TextAlign.Center
                    )
                )

                Spacer(modifier = Modifier.padding(top = 16.dp))

                Text(
                    text = stringResource(R.string.text_message_dialog_downloading),
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 22.4.sp,
                        fontFamily = UrbanistFamily,
                        color = MovieStreamingTheme.colorScheme.textColor,
                        textAlign = TextAlign.Center,
                        letterSpacing = 0.2.sp,
                    )
                )

                HorizontalDividerUI(
                    modifier = Modifier
                        .padding(vertical = 32.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(
                            R.string.text_downloaded_size_dialog_downloading,
                            downloadedSize.calculateFileSize(),
                            downloadSize.calculateFileSize()
                        ),
                        style = TextStyle(
                            lineHeight = 19.6.sp,
                            fontFamily = UrbanistFamily,
                            fontWeight = FontWeight(700),
                            color = MovieStreamingTheme.colorScheme.textColor,
                            letterSpacing = 0.2.sp
                        )
                    )

                    Text(
                        text = stringResource(
                            R.string.text_download_progress_dialog_downloading,
                            progress
                        ),
                        style = TextStyle(
                            lineHeight = 19.6.sp,
                            fontFamily = UrbanistFamily,
                            fontWeight = FontWeight(700),
                            color = MovieStreamingTheme.colorScheme.defaultColor,
                            letterSpacing = 0.2.sp
                        )
                    )
                }

                Spacer(modifier = Modifier.padding(top = 8.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween // Consider removing SpaceBetween if icon is on the right
                ) {
                    LinearProgressIndicator(
                        progress = { progress / 100f }, // Convert Int progress (0-100) to Float (0.0-1.0)
                        modifier = Modifier
                            .weight(1f)
                            .clip(CircleShape)
                            .height(8.dp),
                        color = MovieStreamingTheme.colorScheme.defaultColor,
                        trackColor = Color(0xFF35383f),
                        strokeCap = StrokeCap.Butt,
                        gapSize = 0.dp,
                        drawStopIndicator = {}
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.ic_close),
                        contentDescription = null,
                        tint = MovieStreamingTheme.colorScheme.iconColor,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }

                Spacer(modifier = Modifier.padding(top = 24.dp))

                SecondaryButton(
                    text = stringResource(R.string.text_hide_dialog_downloading),
                    isLoading = false,
                    enabled = true,
                    onClick = onDismissRequest
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun DownloadDialogUIPreview() {
    MovieStreamingTheme {
        DownloadDialogUI(
            progress = 50,
            downloadedSize = 90f,
            downloadSize = 180f,
            onDismissRequest = {}
        )
    }
}
