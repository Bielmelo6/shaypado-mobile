package com.ufape.shaypado.ui.theme

import androidx.compose.foundation.Image
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
        painter = painterResource(id = R.drawable.ic_eye_show),
        contentDescription = "Password"
    )
}

@Composable
@Preview
fun EyeSlashIcon(){
    Icon(
        tint = textSecondary,
        painter = painterResource(id = R.drawable.ic_eye_hide),
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
fun EmailIcon(){
    Icon(
        tint = textSecondary,
        painter = painterResource(id = R.drawable.ic_email),
        contentDescription = "Email"
    )
}

@Composable
@Preview
fun PersonIcon(){
    Icon(
        tint = textSecondary,
        painter = painterResource(id = R.drawable.ic_person),
        contentDescription = "Email"
    )
}

@Composable
@Preview
fun UserIcon(){
    Icon(
        tint = textSecondary,
        painter = painterResource(id = R.drawable.ic_user),
        contentDescription = "Username"
    )
}

@Composable
@Preview
fun UserOutlinedIcon(){
    Icon(
        tint = textSecondary,
        painter = painterResource(id = R.drawable.ic_user_outlined),
        contentDescription = "Password"
    )
}

@Composable
@Preview
fun SmilingFaceIcon(){
    Icon(
        tint = textSecondary,
        painter = painterResource(id = R.drawable.ic_smilling_face),
        contentDescription = "Smiling Face"
    )
}

@Composable
@Preview
fun BackIcon(){
    Icon(
        tint = textSecondary,
        painter = painterResource(id = R.drawable.ic_back),
        contentDescription = "Back"
    )
}

@Composable
@Preview
fun ShaypadoImage(){
    Image(
        painter = painterResource(id = R.drawable.ic_shaypado_name),
        contentDescription = "Shaypado"
    )
}

@Composable
@Preview
fun GoogleImage(){
    Image(
        painter = painterResource(id = R.drawable.ic_google),
        contentDescription = "Google"
    )
}

@Composable
@Preview
fun WhatsappImage(){
    Image(
        painter = painterResource(id = R.drawable.whatsapp),
        contentDescription = "Whatsapp"
    )
}

@Composable
@Preview
fun ShaypadoPetImage(){
    Image(
        painter = painterResource(id = R.drawable.brand_logo),
        contentDescription = "Shaypado logo"
    )
}

@Composable
@Preview
fun InfoIcon(){
    Icon(
        painter = painterResource(id = R.drawable.ic_info),
        contentDescription = "Info"
    )
}

@Composable
@Preview
fun BarbellIcon(){
    Icon(
        painter = painterResource(id = R.drawable.ic_barbell),
        contentDescription = "Info"
    )
}

@Composable
@Preview
fun TrainingImage() {
    Image(
        painter = painterResource(id = R.drawable.ic_training),
        contentDescription = "Training",
    )
}

@Composable
@Preview
fun StudentImage() {
    Image(
        painter = painterResource(id = R.drawable.ic_student),
        contentDescription = "Student",
    )
}

