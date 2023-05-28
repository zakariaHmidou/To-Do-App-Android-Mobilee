package com.projectbyzakaria.todojway.ui.screens.auth

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.projectbyzakaria.todojway.R
import com.projectbyzakaria.todojway.ui.components.ErrorDialog
import com.projectbyzakaria.todojway.ui.components.Input
import com.projectbyzakaria.todojway.ui.components.ProgressDialog
import com.projectbyzakaria.todojway.ui.viewmodels.AuthViewModel
import com.projectbyzakaria.todojway.utils.AuthStatus
import com.projectbyzakaria.todojway.utils.FormValidation

@Composable
fun LoginScreen(
    modifier: Modifier,
    authViewModel: AuthViewModel,
    onClickBack: () -> Unit = {},
    windowClass: WindowSizeClass,
    onClickSignUp: () -> Unit,
    onClickLoginButton: () -> Unit,
    onSuccessLogIn: () -> Unit
) {

    val pathColor = MaterialTheme.colorScheme.primary
    val pathColor2 = MaterialTheme.colorScheme.primaryContainer
    val loginState = remember{ authViewModel.logInState }
    val positionX = remember {
        Animatable(-3000f)
    }
    ConstraintLayout(
        modifier = modifier
            .background(MaterialTheme.colorScheme.onPrimary)
            .drawBehind {
                if (windowClass.heightSizeClass != WindowHeightSizeClass.Compact) {
                    val path = Path()
                    path.moveTo(0f, 0f)
                    path.lineTo(0f, size.height * 0.5f)
                    path.cubicTo(
                        x1 = size.width * 0.1f,
                        y1 = size.height * 0.3f,
                        x2 = size.width * 0.5f,
                        y2 = size.height * 0.5f,
                        x3 = size.width,
                        y3 = size.height * 0.4f
                    )
                    path.lineTo(size.width, 0f)
                    drawPath(path, pathColor)
                    val path2 = Path()
                    path2.moveTo(0f, size.height * 0.1f)
                    path2.lineTo(0f, size.height * 0.3f)
                    path2.cubicTo(
                        x1 = size.width * 0.1f,
                        y1 = size.height * 0.2f,
                        x2 = size.width * 0.5f,
                        y2 = size.height * 0.4f,
                        x3 = size.width * 0.3f,
                        y3 = size.height * 0.1f
                    )
                    path2.cubicTo(
                        x1 = size.width * 0.3f,
                        y1 = size.height * 0.1f,
                        x2 = size.width * 0.2f,
                        y2 = 0f,
                        x3 = 0f,
                        y3 = size.height * 0.1f
                    )
                    drawPath(path2, pathColor2)

                    drawOval(
                        pathColor2,
                        Offset(size.width * 0.5f, size.height * 0.3f),
                        size = Size(200f, 230f)
                    )
                    drawOval(
                        pathColor2,
                        Offset(size.width * 0.8f, size.height * 0.2f),
                        size = Size(500f, 430f)
                    )
                } else {

                    val path = Path()
                    path.moveTo(0f, 0f)
                    path.lineTo(0f, size.height)
                    path.lineTo(size.width * 0.5f, size.height)
                    path.cubicTo(
                        x1 = size.width * 0.5f,
                        y1 = size.height * 0.8f,
                        x2 = size.width * 0.4f,
                        y2 = size.height * 0.5f,
                        x3 = size.width * 0.5f,
                        y3 = 0f
                    )
                    drawPath(path, pathColor)

                    drawOval(
                        pathColor2,
                        Offset(size.width * 0.2f, size.height * 0.3f),
                        size = Size(200f, 230f)
                    )
                    drawOval(
                        pathColor2,
                        Offset(size.width * 0.1f, size.height * 0.2f),
                        size = Size(500f, 430f)
                    )
                }

            }

    ) {



        if (windowClass.heightSizeClass == WindowHeightSizeClass.Compact){
            val (back,  form) = createRefs()
            IconButton(
                onClick = onClickBack,
                modifier = Modifier.constrainAs(back) {
                    top.linkTo(parent.top, 10.dp)
                    start.linkTo(parent.start, 8.dp)
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.back),
                    contentDescription = "back button",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(form) {
                        top.linkTo(back.bottom, goneMargin = 0.dp)
                        start.linkTo(parent.start, 8.dp)
                    }
                    .graphicsLayer {
                        this.translationX = positionX.value
                    }
            ) {
                Text(
                    text = "Welcome \nBack",
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(1f),
                    fontSize = 25.sp,
                    fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                    color = MaterialTheme.colorScheme.background,
                    fontWeight = FontWeight(500)
                )


                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                        .weight(1f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                )
                {

                    InputEmail(
                        modifier = Modifier.fillMaxWidth(),
                        loginState = loginState
                    )
                    InputPassword(
                        modifier = Modifier.fillMaxWidth(),
                        loginState = loginState
                    )


                    Spacer(modifier = Modifier.height(18.dp))
                    ElevatedButton(
                        onClick = {
                            if (!FormValidation.isEmailValid(loginState.email)) {
                                loginState.throwErrorForEmail(true)
                                return@ElevatedButton
                            }
                            if (!FormValidation.isPasswordValid(loginState.password)) {
                                loginState.throwErrorForPassword(true)
                                return@ElevatedButton
                            }


                            onClickLoginButton()
                        },
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        ),
                        enabled = loginState.loginIsEnable,
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

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Divider(modifier = Modifier.weight(1f))
                        Text(
                            text = "OR",
                            modifier = Modifier.padding(horizontal = 18.dp, vertical = 4.dp),
                            fontSize = 14.sp
                        )
                        Divider(modifier = Modifier.weight(1f))
                    }
                    ElevatedButton(
                        onClick = onClickSignUp,
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 45.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
                            contentColor = MaterialTheme.colorScheme.secondary
                        ), border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary)
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

        }else{
            val (back, title, form) = createRefs()
        IconButton(
            onClick = onClickBack,
            modifier = Modifier.constrainAs(back) {
                top.linkTo(parent.top, 10.dp)
                start.linkTo(parent.start, 8.dp)
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.back),
                contentDescription = "back button",
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }


        Text(
            text = "Welcome \nBack",
            textAlign = TextAlign.Start,
            modifier = Modifier
                .constrainAs(title) {
                    top.linkTo(back.bottom, goneMargin = 0.dp)
                    start.linkTo(parent.start, 8.dp)
                    bottom.linkTo(form.top, margin = 10.dp, goneMargin = 0.dp)
                }
                .graphicsLayer {
                    this.translationX = positionX.value
                },
            fontSize = 25.sp,
            fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
            color = MaterialTheme.colorScheme.background,
            fontWeight = FontWeight(500)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .constrainAs(form) {
                    bottom.linkTo(parent.bottom, 20.dp)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                }
                .graphicsLayer {
                    this.translationX = positionX.value
                },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {

            InputEmail(
                modifier = Modifier.fillMaxWidth(),
                loginState = loginState
            )
            InputPassword(
                modifier = Modifier.fillMaxWidth(),
                loginState = loginState
            )


            Spacer(modifier = Modifier.height(18.dp))
            ElevatedButton(
                onClick = {
                    if (!FormValidation.isEmailValid(loginState.email)) {
                        loginState.throwErrorForEmail(true)
                        return@ElevatedButton
                    }
                    if (!FormValidation.isPasswordValid(loginState.password)) {
                        loginState.throwErrorForPassword(true)
                        return@ElevatedButton
                    }
                    onClickLoginButton()
                },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                enabled = loginState.loginIsEnable,
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

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Divider(modifier = Modifier.weight(1f))
                Text(
                    text = "OR",
                    modifier = Modifier.padding(horizontal = 18.dp, vertical = 4.dp),
                    fontSize = 14.sp
                )
                Divider(modifier = Modifier.weight(1f))
            }
            ElevatedButton(
                onClick = onClickSignUp,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 45.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    contentColor = MaterialTheme.colorScheme.secondary
                ), border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary)
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


        DialogForLoginScreen(authViewModel,onSuccessLogIn)

        LaunchedEffect(key1 = true ){
            positionX.animateTo(0f)
        }

}


}

@Composable
fun DialogForLoginScreen(
    authViewModel: AuthViewModel,
    onSuccessLogIn: () -> Unit
) {
    val loginScreenState by authViewModel.loginStateUser.collectAsState()
    when(loginScreenState){
        is AuthStatus.Nating -> {}
        is AuthStatus.Error -> {
            (loginScreenState as AuthStatus.Error).let {
                ErrorDialog(
                    title = it.message ?: "Error",
                    message = it.errors.joinToString("\n"),
                    onDismissRequest = {
                        authViewModel.markLoginScreenStateToNating()
                    }
                )
            }
        }
        is AuthStatus.Loading -> {
            ProgressDialog(modifier = Modifier, stroke = 3.dp)
        }
        is AuthStatus.Success -> {
            onSuccessLogIn()
        }
    }
}

@Composable
fun InputPassword(modifier: Modifier, loginState: AuthViewModel.LoginState) {
    val endIcon = when {
        loginState.isPasswordCorrect && !loginState.isPasswordHaveError -> R.drawable.done
        loginState.isPasswordHaveError && !loginState.isPasswordCorrect -> R.drawable.warning
        loginState.isPasswordVisible -> R.drawable.visibel
        else -> R.drawable.not_visible
    }
    val colorIcon = when {
        loginState.isPasswordCorrect && !loginState.isPasswordHaveError -> Color(0xFF12B400)
        loginState.isPasswordHaveError && !loginState.isPasswordCorrect -> Color(0xFFFF5A5A)
        else -> MaterialTheme.colorScheme.primary
    }
    Input(
        hint = "password",
        text = { loginState.password },
        onTextChanged = loginState::changePassword,
        isError = { loginState.isPasswordHaveError },
        label = { "Password" },
        endIcon = endIcon,
        startIcon = R.drawable.lock,
        onClickEndIcon = {
            if (endIcon == R.drawable.not_visible) {
                loginState.changeVisibilttyOfPassword(true)
            }
            if (endIcon == R.drawable.visibel) {
                loginState.changeVisibilttyOfPassword(false)
            }
        },
        colorIcons = colorIcon,
        modifier = modifier,
        visualTransformation = if (!loginState.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Words,
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        colors = TextFieldDefaults.colors(
            cursorColor = colorIcon,
            errorContainerColor = MaterialTheme.colorScheme.onPrimaryContainer,
            focusedLabelColor = colorIcon,
            focusedIndicatorColor = colorIcon,
            disabledContainerColor = MaterialTheme.colorScheme.onPrimaryContainer,
            focusedContainerColor = MaterialTheme.colorScheme.onPrimaryContainer,
            unfocusedContainerColor = MaterialTheme.colorScheme.onPrimaryContainer,
            disabledLabelColor = colorIcon,
            errorLabelColor = colorIcon,
            unfocusedLabelColor = colorIcon,
            unfocusedIndicatorColor = if (loginState.isPasswordCorrect) colorIcon else MaterialTheme.colorScheme.primary,
            unfocusedTextColor = MaterialTheme.colorScheme.onSecondary,
            focusedTextColor =  MaterialTheme.colorScheme.onSecondary,
            )
    )
}


@Composable
fun InputEmail(
    modifier: Modifier = Modifier,
    loginState: AuthViewModel.LoginState,
) {
    val endIcon = when {
        loginState.isEmailCorrect && !loginState.isEmailHaveError -> R.drawable.done
        loginState.isEmailHaveError && !loginState.isEmailCorrect -> R.drawable.warning
        else -> R.drawable.close
    }
    val colorIcon = when {
        loginState.isEmailCorrect && !loginState.isEmailHaveError -> Color(0xFF12B400)
        loginState.isEmailHaveError && !loginState.isEmailCorrect -> Color(0xFFFF5A5A)
        else -> MaterialTheme.colorScheme.primary
    }
    Input(
        hint = "email",
        text = { loginState.email },
        onTextChanged = loginState::changeEmail,
        isError = { loginState.isEmailHaveError },
        label = { "Email" },
        endIcon = endIcon,
        startIcon = R.drawable.email,
        onClickEndIcon = {
            if (endIcon == R.drawable.close) {
                loginState.changeEmail("")
            }
        },
        colorIcons = colorIcon,
        modifier = modifier,
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Words,
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Done
        ),
        colors = TextFieldDefaults.colors(
            cursorColor = colorIcon,
            errorContainerColor = MaterialTheme.colorScheme.onPrimaryContainer,
            focusedLabelColor = colorIcon,
            focusedIndicatorColor = colorIcon,
            disabledContainerColor = MaterialTheme.colorScheme.onPrimaryContainer,
            focusedContainerColor = MaterialTheme.colorScheme.onPrimaryContainer,
            unfocusedContainerColor = MaterialTheme.colorScheme.onPrimaryContainer,
            disabledLabelColor = colorIcon,
            errorLabelColor = colorIcon,
            unfocusedLabelColor = colorIcon,
            unfocusedIndicatorColor = if (loginState.isEmailCorrect) colorIcon else MaterialTheme.colorScheme.primary,
            unfocusedTextColor = MaterialTheme.colorScheme.onSecondary,
            focusedTextColor =  MaterialTheme.colorScheme.onSecondary
        )
    )
}

