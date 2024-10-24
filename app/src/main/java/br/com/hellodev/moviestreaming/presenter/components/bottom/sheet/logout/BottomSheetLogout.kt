package br.com.hellodev.moviestreaming.presenter.components.bottom.sheet.logout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.hellodev.moviestreaming.R
import br.com.hellodev.moviestreaming.presenter.components.button.PrimaryButton
import br.com.hellodev.moviestreaming.presenter.components.button.SecondaryButton
import br.com.hellodev.moviestreaming.presenter.theme.MovieStreamingTheme
import br.com.hellodev.moviestreaming.presenter.theme.UrbanistFamily

@Composable
fun BottomSheetLogout(
    modifier: Modifier = Modifier,
    onCancelClick: () -> Unit,
    onConfirmClick: () -> Unit
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
            text = stringResource(R.string.label_title_bottom_sheet_logout),
            style = TextStyle(
                fontSize = 24.sp,
                lineHeight = 28.8.sp,
                fontFamily = UrbanistFamily,
                fontWeight = FontWeight.Bold,
                color = MovieStreamingTheme.colorScheme.alertColor,
                textAlign = TextAlign.Center
            )
        )

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            color = MovieStreamingTheme.colorScheme.dividerColor
        )

        Text(
            text = stringResource(R.string.label_message_bottom_sheet_logout),
            style = TextStyle(
                fontSize = 20.sp,
                lineHeight = 24.sp,
                fontFamily = UrbanistFamily,
                fontWeight = FontWeight.Bold,
                color = MovieStreamingTheme.colorScheme.textColor,
                textAlign = TextAlign.Center
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            SecondaryButton(
                modifier = Modifier
                    .weight(1f),
                text = stringResource(R.string.label_cancel_button_bottom_sheet_logout),
                onClick = onCancelClick
            )

            PrimaryButton(
                modifier = Modifier
                    .weight(1f),
                text = stringResource(R.string.label_confirm_button_bottom_sheet_logout),
                onClick = onConfirmClick
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun BottomSheetLogoutPreview() {
    MovieStreamingTheme {
        BottomSheetLogout(
            onCancelClick = {},
            onConfirmClick = {}
        )
    }
}