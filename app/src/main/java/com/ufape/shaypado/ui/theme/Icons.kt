package com.ufape.shaypado.ui.theme

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.ufape.shaypado.R

@Composable
@Preview
fun KeyIcon(){
    Icon(
        tint = textSecondary,
        painter = painterResource(id = R.drawable.ic_key),
        contentDescription = "Password"
    )
}

@Composable
@Preview
fun EyeIcon(){
    Icon(
        tint = textSecondary,
        painter = painterResource(id = R.drawable.ic_eye),
        contentDescription = "Password"
    )
}

@Composable
@Preview
fun EyeSlashIcon(){
    Icon(
        tint = textSecondary,
        painter = painterResource(id = R.drawable.ic_eye_slash),
        contentDescription = "Password"
    )
}



@Composable
@Preview
fun AtIcon(){
    Icon(
        tint = textSecondary,
        painter = painterResource(id = R.drawable.ic_at),
        contentDescription = "Email"
    )
}

@Composable
@Preview
fun UserIcon(){
    Icon(
        painter = painterResource(id = R.drawable.ic_user),
        contentDescription = "Username"
    )
}