package com.lexwilliam.risuto.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun GuestScreen(
    navToLogin: () -> Unit,
    onDismiss: (() -> Unit)? = null
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(32.dp, Alignment.CenterVertically)
    ) {
        Icon(
            modifier = Modifier
                .size(80.dp),
            imageVector = Icons.Default.Warning,
            contentDescription = null,
            tint = Color.LightGray
        )
        Text(
            text = "You are in guest mode, login to use this feature",
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.onBackground,
            textAlign = TextAlign.Center
        )
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { navToLogin() }
        ) {
            Text(text = "Login", style = MaterialTheme.typography.button, fontWeight = FontWeight.SemiBold)
        }
        if (onDismiss != null) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onDismiss() }
            ) {
                Text(text = "Go Back", style = MaterialTheme.typography.button, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}