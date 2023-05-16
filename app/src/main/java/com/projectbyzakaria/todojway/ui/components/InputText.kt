package com.projectbyzakaria.todojway.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.projectbyzakaria.todojway.ui.theme.ToDoJwayTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Input(
    hint:String,
    text:()->String,
    onTextChanged:(String)->Unit,
    isError:()->Boolean,
    label:()->String,
    keyboardOptions: KeyboardOptions =KeyboardOptions(keyboardType = KeyboardType.Email),
    keyboardActions: KeyboardActions = KeyboardActions(),
    supportingText:String? = null,
    visualTransformation:VisualTransformation=VisualTransformation.None,
    endIcon:ImageVector,
    startIcon:ImageVector,
    onClickEndIcon:()->Unit,
    colorIcons:Color,
    modifier: Modifier

) {
    val inputEndIcon = if (isError()) Icons.Default.Warning else endIcon;
    val iconsColor = if (isError()) Color.Red else colorIcons;


    OutlinedTextField(
        value = text(),
        onValueChange = onTextChanged,
        placeholder = { Text(text = hint)},
        isError = isError(),
        colors = TextFieldDefaults.outlinedTextFieldColors(textColor = Color.Black, cursorColor = MaterialTheme.colorScheme.primary),
        label = {Text(label())},
        singleLine = true,
        maxLines = 1,
        trailingIcon = { IconButton(onClick = onClickEndIcon) {
            Icon(imageVector = inputEndIcon, contentDescription = null, tint = iconsColor)
        }},
        leadingIcon = {Icon(imageVector = startIcon, contentDescription = null, tint = iconsColor)},
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        supportingText = { supportingText?.let { Text(text = it) }},
        visualTransformation = visualTransformation,
        modifier  =modifier
    )
}




