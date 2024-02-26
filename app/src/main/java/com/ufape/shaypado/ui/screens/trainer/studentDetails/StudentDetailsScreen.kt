package com.ufape.shaypado.ui.screens.trainer.studentDetails

import android.widget.Space
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ufape.shaypado.R
import com.ufape.shaypado.ui.components.AppText
import com.ufape.shaypado.ui.components.BackButton
import com.ufape.shaypado.ui.components.TextType
import com.ufape.shaypado.ui.screens.trainer.home.TrainingDetailsRenderItem
import com.ufape.shaypado.ui.screens.trainer.home.UserDetailsRenderItem
import com.ufape.shaypado.ui.theme.StudentImage
import com.ufape.shaypado.ui.theme.TrainingImage

@Composable
fun StudentDetailsScreen(
    navController: NavController
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
            text = R.string.student_details
        )
    }

    Spacer(modifier = Modifier.height(16.dp))

    UserDetailsRenderItem(
        trailingIcon = { StudentImage() }
    )

    Spacer(modifier = Modifier.height(12.dp))


    TrainingDetailsRenderItem()

    Spacer(modifier = Modifier.height(20.dp))


    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(10) {
            UserDetailsRenderItem(
                leadingIcon = { TrainingImage() }
            )
        }
    }

}