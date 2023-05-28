package com.projectbyzakaria.todojway.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog


@Composable
fun ProgressDialog(
    modifier: Modifier = Modifier,
    stroke: Dp = 5.dp,
) {
    Dialog(
        onDismissRequest = { }
    )
    {
        Column(
            modifier = modifier.clip(RoundedCornerShape(8.dp)).background(MaterialTheme.colorScheme.background).padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Please wait",
                modifier = Modifier.padding(8.dp),
                fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )
            CircularProgressIndicator(
                modifier = Modifier
                    .padding(20.dp)
                    .size(50.dp), color = MaterialTheme.colorScheme.primary, strokeWidth = stroke
            )
        }
    }
}