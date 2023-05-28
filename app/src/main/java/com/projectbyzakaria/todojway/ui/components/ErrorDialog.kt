package com.projectbyzakaria.todojway.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.projectbyzakaria.todojway.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ErrorDialog(
    title: String,
    message: String,
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column {
            Row {
                Icon(
                    painter = painterResource(id = R.drawable.warning),
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(start = 8.dp,top = 8.dp, bottom = 8.dp)
                )
                Text(
                    text = title,
                    modifier = Modifier.padding(8.dp),
                    fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                    textAlign = TextAlign.Start,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
            }

            Text(
                text = message,
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 8.dp)
                    .padding(bottom = 20.dp),
                fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                textAlign = TextAlign.Start,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold
            )
        }
    }

}

