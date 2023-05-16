package com.projectbyzakaria.todojway.ui.screens

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.projectbyzakaria.todojway.R
import com.projectbyzakaria.todojway.ui.components.Input
import com.projectbyzakaria.todojway.ui.theme.ToDoJwayTheme

@Composable
fun LoginScreen(

) {

    val color = MaterialTheme.colorScheme.primary
    ConstraintLayout(modifier = Modifier
        .fillMaxSize()
        .drawBehind {
            val path = Path()
            path.moveTo(0f, size.height * 0.95f)
            path.cubicTo(
                size.width * 0.25f,
                size.height * 0.98f,
                size.width * 0.60f,
                size.height * 0.92f,
                size.width,
                size.height * 0.98f
            )
            path.lineTo(size.width, size.height)
            path.lineTo(0f, size.height)
            drawPath(path = path, color = color)
        }) {

        val (title, inputs, imageLogo, desc, button, text, account) = createRefs();

        Text(
            text = "Log In",
            fontSize = 40.sp,
            modifier = Modifier
                .constrainAs(title) {
                    top.linkTo(parent.top, 20.dp)
                    start.linkTo(parent.start, 8.dp)
                },
            fontStyle = MaterialTheme.typography.titleMedium.fontStyle,
        )
        Text(
            text = "please login to continue",
            fontSize = 18.sp,
            modifier = Modifier
                .constrainAs(desc) {
                    top.linkTo(title.bottom)
                    start.linkTo(parent.start, 8.dp)
                },
            fontStyle = MaterialTheme.typography.bodySmall.fontStyle,
            color = Color(0xFF8B8B8B),
            fontWeight = FontWeight.Light
        )
        Image(
            painter = painterResource(id = R.drawable.logotodo),
            contentDescription = "",
            modifier = Modifier
                .clip(
                    CircleShape
                )
                .size(170.dp)
                .constrainAs(imageLogo) {
                    top.linkTo(desc.bottom, 20.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            contentScale = ContentScale.Crop,

            )


        Column(modifier = Modifier
            .fillMaxWidth()
            .constrainAs(inputs) {
                top.linkTo(imageLogo.bottom, 20.dp)
            }) {
            Input(
                hint = "email",
                text = { "" },
                onTextChanged = {},
                isError = { false },
                label = { "Email" },
                endIcon = Icons.Default.Clear,
                startIcon = Icons.Default.Email,
                onClickEndIcon = { },
                colorIcons = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Input(
                hint = "password",
                text = { "" },
                onTextChanged = {},
                isError = { false },
                label = { "Password" },
                endIcon = Icons.Default.Clear,
                startIcon = Icons.Default.Lock,
                onClickEndIcon = { },
                colorIcons = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            )
        }

        ElevatedButton(
            onClick = { }, modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .constrainAs(button) {
                    top.linkTo(inputs.bottom, 10.dp)
                }, shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "Log In", fontSize = 20.sp,
                fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
            )
        }


        ClickableText(text = buildAnnotatedString {
            withStyle(
                SpanStyle(
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontFamily = MaterialTheme.typography.labelSmall.fontFamily,
                    fontWeight = FontWeight.Medium
                )
            ) {
                append("I don't have an account :  ")
            }
            withStyle(
                SpanStyle(
                    color = Color(0xFF009719),
                    fontSize = 17.sp,
                    fontFamily = MaterialTheme.typography.labelSmall.fontFamily,
                    fontWeight = FontWeight.Bold,
                    textDecoration = TextDecoration.Underline
                )
            ) {
                append("Sign Up")
            }
        }, onClick = {
            if (it >= 27) {

            }
        }, modifier = Modifier.constrainAs(account) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(button.bottom, 10.dp)
        })


        Text(
            text = "jway services",
            fontSize = 12.sp,
            modifier = Modifier
                .padding(8.dp)
                .constrainAs(text) {
                    start.linkTo(parent.start, 8.dp)
                    end.linkTo(parent.end, 8.dp)
                    bottom.linkTo(parent.bottom, 8.dp)
                },
            fontStyle = MaterialTheme.typography.titleMedium.fontStyle,
            color = Color.White,
        )
    }

}

@Preview(name = "login dark", showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun LoginScreenDarkPreview() {
    ToDoJwayTheme {
        LoginScreen()
    }
}

@Preview(name = "login ", showBackground = true, uiMode = UI_MODE_NIGHT_NO)
@Composable
fun LoginScreenPreview() {
    ToDoJwayTheme {
        LoginScreen()
    }
}