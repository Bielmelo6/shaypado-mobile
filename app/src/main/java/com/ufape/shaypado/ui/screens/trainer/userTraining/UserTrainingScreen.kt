import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ufape.shaypado.ui.components.AppButton
import com.ufape.shaypado.ui.components.AppText
import com.ufape.shaypado.ui.components.ButtonVariant
import com.ufape.shaypado.ui.components.TextType
import com.ufape.shaypado.ui.theme.PerfilShaypado2Icon

@Composable
fun UserTrainingScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.matchParentSize()){
            Card(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(end = 8.dp, start = 8.dp)
                ) {
                    Column(modifier = Modifier
                        .width(277.dp)
                        .fillMaxHeight(), verticalArrangement = Arrangement.Center) {
                        AppText(
                            text = "Iniciante - Inferiores",
                            textType = TextType.HEADLINE_SMALL,
                            textAlignment = TextAlign.Start,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        AppText(
                            text = "17 x 60",
                            textType = TextType.TITLE_SMALL,
                            textAlignment = TextAlign.Start,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                    Box(
                        modifier = Modifier
                            .height(60.dp)
                            .width(60.dp)
                            .background(color = MaterialTheme.colorScheme.errorContainer)
                    ) { PerfilShaypado2Icon() }
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            LazyColumn {
                items(5) { index ->
                    CardUserTraining(navController)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            AppButton(onClick = {}, variant = ButtonVariant.PRIMARY, text = "Finalizar")
        }
    }
}