package com.ufape.shaypado.ui.screens.trainer.counter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ufape.shaypado.R
import com.ufape.shaypado.ui.components.AddButton
import com.ufape.shaypado.ui.components.AppButton
import com.ufape.shaypado.ui.components.AppText
import com.ufape.shaypado.ui.components.BackButton
import com.ufape.shaypado.ui.components.RemoveButton
import com.ufape.shaypado.ui.components.TextType
import com.ufape.shaypado.ui.theme.Nunito

@Composable
fun CounterBase(
    navController: NavController,
    title: String,
    value: Int = 1,
    decrease: () -> Unit,
    increase: () -> Unit,
    onNextPressed : () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BackButton {
            navController.popBackStack()
        }

        AppText(
            textType = TextType.HEADLINE_MEDIUM,
            textAlignment = TextAlign.Center,
            text = title,
            fillWidth = true
        )
    }

    Column(
        modifier =
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.9f),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Column (
            modifier = Modifier
                .height(232.dp)
                .width(203.dp)
                .fillMaxSize()
                .background(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = RoundedCornerShape(8.dp)
                )
            ,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Row {
                Text(
                    text = value.toString(),
                    fontFamily = Nunito,
                    fontWeight = FontWeight.Black,
                    fontSize = 100.sp,
                    lineHeight = 64.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically

            ) {
                RemoveButton(
                    enabled = value > 1,
                    onClick = { decrease() }
                )
                AddButton(
                    onClick = { increase() },
                    enabled = value < 10
                )
            }
        }
    }

    AppButton(
        text = R.string.button_next,
        onClick = {
            onNextPressed()
        },
    )
}