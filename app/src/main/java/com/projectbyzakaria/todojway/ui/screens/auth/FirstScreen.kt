package com.projectbyzakaria.todojway.ui.screens.auth

import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import kotlin.random.Random
import kotlin.random.nextInt


@Composable
fun FirstScreen(
    modifier: Modifier = Modifier,
    onClickLogin:()->Unit,
    onClickSignUn: () -> Unit,
    windowClass :WindowSizeClass
) {


    if (windowClass.heightSizeClass == WindowHeightSizeClass.Compact){
        TabletFirstScreen(modifier, onClickLogin, onClickSignUn)
    }else{
        SmartPhoneFirstScreen(modifier, onClickLogin, onClickSignUn)
    }

}

@Composable
fun TabletFirstScreen(modifier: Modifier, onClickLogin: () -> Unit, onClickSignUn: () -> Unit) {
    var position1State by remember {
        mutableStateOf(Offset.Zero)
    }
    val sizeOval = Random.nextInt(100..250).toFloat()
    val pathColor = MaterialTheme.colorScheme.primaryContainer
    val pathColor2 = MaterialTheme.colorScheme.primary
    val position1 by animateOffsetAsState(targetValue = position1State, tween(durationMillis = 1500))
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
            .drawBehind {
                val path1 = Path()
                path1.moveTo(size.width, 60f)
                path1.cubicTo(
                    x1 = size.width,
                    y1 = size.height * 0.3f,
                    x2 = size.width * 0.5f,
                    y2 = size.height * 0.6f,
                    x3 = 0f,
                    y3 = size.height * 0.4f
                )
                path1.lineTo(0f, size.height * 0.6f)
                path1.cubicTo(
                    x1 = 0f,
                    y1 = size.height * 0.6f,
                    x2 = size.width * 0.5f,
                    y2 = size.height * 0.7f,
                    x3 = size.width,
                    y3 = size.height * 0.8f
                )
                drawPath(path1, pathColor)

                drawOval(
                    pathColor2,
                    topLeft = Offset(size.width * 0.8f, size.height * 0.5f),
                    size = Size(250f, 260f)
                )
                drawOval(
                    pathColor,
                    topLeft = position1,
                    size = Size(sizeOval, sizeOval)
                )
            }
            .pointerInput(Unit) {
                detectTapGestures(onPress = {
                    position1State = it
                })
            }
    )
    {

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "To Do",
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(8.dp),
                fontSize = 25.sp,
                fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                color = MaterialTheme.colorScheme.background
            )
            Text(
                text = "Welcome In To Do",
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(top = 20.dp, start = 8.dp),
                fontSize = 25.sp,
                fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                color = MaterialTheme.colorScheme.background,
                fontWeight = FontWeight(500)
            )
            Text(
                text = "We can help you organize your tasks. Register now for free",
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(bottom = 40.dp, start = 8.dp),
                fontSize = 16.sp,
                fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                color = MaterialTheme.colorScheme.background,
                fontWeight = FontWeight(400)
            )
        }
        Row(
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 18.dp)
                .fillMaxWidth()
            ,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ElevatedButton(
                onClick = onClickLogin,
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    contentColor = MaterialTheme.colorScheme.primaryContainer
                ),
                modifier = Modifier
                    .weight(1f)
                    .heightIn(min = 45.dp),

                ) {
                Text(
                    text = "Log In",
                    fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                    fontSize = 18.sp,
                    fontWeight = FontWeight(400),
                    fontStyle = FontStyle.Normal
                )
            }

            Spacer(modifier = Modifier.width(8.dp))
            ElevatedButton(
                onClick = onClickSignUn,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .weight(1f)
                    .heightIn(min = 45.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ), border = BorderStroke(1.dp,MaterialTheme.colorScheme.onPrimary)
            ) {
                Text(
                    text = "Sign up",
                    fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                    fontSize = 18.sp,
                    fontWeight = FontWeight(400),
                    fontStyle = FontStyle.Normal
                )
            }

        }


    }
}


@Composable
fun SmartPhoneFirstScreen(
    modifier: Modifier = Modifier,
    onClickLogin:()->Unit,
    onClickSignUn: () -> Unit,
) {
    var position1State by remember {
        mutableStateOf(Offset.Zero)
    }
    val sizeOval = Random.nextInt(100..250).toFloat()
    val pathColor = MaterialTheme.colorScheme.primaryContainer
    val pathColor2 = MaterialTheme.colorScheme.primary
    val position1 by animateOffsetAsState(targetValue = position1State, tween(durationMillis = 1500))
    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
            .drawBehind {
                val path1 = Path()
                path1.moveTo(size.width, 60f)
                path1.cubicTo(
                    x1 = size.width,
                    y1 = size.height * 0.3f,
                    x2 = size.width * 0.5f,
                    y2 = size.height * 0.6f,
                    x3 = 0f,
                    y3 = size.height * 0.4f
                )
                path1.lineTo(0f, size.height * 0.6f)
                path1.cubicTo(
                    x1 = 0f,
                    y1 = size.height * 0.6f,
                    x2 = size.width * 0.5f,
                    y2 = size.height * 0.7f,
                    x3 = size.width,
                    y3 = size.height * 0.8f
                )
                drawPath(path1, pathColor)

                drawOval(
                    pathColor2,
                    topLeft = Offset(size.width * 0.8f, size.height * 0.5f),
                    size = Size(250f, 260f)
                )
                drawOval(
                    pathColor,
                    topLeft = position1,
                    size = Size(sizeOval, sizeOval)
                )
            }
            .pointerInput(Unit) {
                detectTapGestures(onPress = {
                    position1State = it
                })
            }
    ) {
        val (title, column) = createRefs()

        Text(
            text = "To Do",
            textAlign = TextAlign.Start,
            modifier = Modifier
                .constrainAs(title) {
                    start.linkTo(parent.start, 12.dp)
                    top.linkTo(parent.top, 8.dp)
                },
            fontSize = 25.sp,
            fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
            color = MaterialTheme.colorScheme.background
        )

        Column(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .fillMaxWidth()
                .constrainAs(column) {
                    start.linkTo(parent.start, 8.dp)
                    bottom.linkTo(parent.bottom, 20.dp)
                    end.linkTo(parent.end, 8.dp)
                }
        ) {
            Text(
                text = "Welcome In To Do",
                textAlign = TextAlign.Start,
                modifier = Modifier,
                fontSize = 25.sp,
                fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                color = MaterialTheme.colorScheme.background,
                fontWeight = FontWeight(500)
            )
            Text(
                text = "We can help you organize your tasks. Register now for free",
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(bottom = 40.dp),
                fontSize = 16.sp,
                fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                color = MaterialTheme.colorScheme.background,
                fontWeight = FontWeight(400)
            )
            ElevatedButton(
                onClick = onClickLogin,
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    contentColor = MaterialTheme.colorScheme.primaryContainer
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 45.dp),

                ) {
                Text(
                    text = "Log In",
                    fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                    fontSize = 18.sp,
                    fontWeight = FontWeight(400),
                    fontStyle = FontStyle.Normal
                )
            }


            ElevatedButton(
                onClick = onClickSignUn,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth()
                    .heightIn(min = 45.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ), border = BorderStroke(1.dp,MaterialTheme.colorScheme.onPrimaryContainer)
            ) {
                Text(
                    text = "Sign up",
                    fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                    fontSize = 18.sp,
                    fontWeight = FontWeight(400),
                    fontStyle = FontStyle.Normal
                )
            }

        }
    }
}


