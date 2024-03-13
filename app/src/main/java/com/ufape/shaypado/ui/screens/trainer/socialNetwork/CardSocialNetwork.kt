import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ufape.shaypado.ui.components.AppText
import com.ufape.shaypado.ui.components.TextType
import com.ufape.shaypado.ui.theme.MedalImage
import com.ufape.shaypado.ui.theme.PerfilShaypado2Icon

@Composable
fun CardSocialNetwork() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .padding(top = 16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Row {
            PerfilShaypado2Icon()
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier
                    .width(190.dp)
                    .fillMaxHeight(), horizontalAlignment = Alignment.Start
            ) {
                AppText(
                    text = "EstrelinhaDoce",
                    textType = TextType.TITLE_MEDIUM,
                    textAlignment = TextAlign.Start
                )
                StyleText()
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedButton(
                    onClick = {},
                    modifier = Modifier
                        .width(110.dp)
                        .height(28.dp),
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(2.dp, color = MaterialTheme.colorScheme.primaryContainer)
                ) {
                    AppText(
                        text = "Parabéns!!",
                        textType = TextType.BODY_SMALL,
                        textAlignment = TextAlign.Center,
                        color = MaterialTheme.colorScheme.primaryContainer
                    )
                }
            }
            Spacer(modifier = Modifier.width(18.dp))
            MedalImage()
        }
    }
    HorizontalDivider(
        modifier = Modifier
            .height(2.dp)
            .fillMaxWidth(),
        color = MaterialTheme.colorScheme.surface
    )
}

@Composable
fun StyleText() {
    val textoEstilizado = buildAnnotatedString() {
        withStyle(style = SpanStyle(fontSize = 10.sp)) {
            append("EstrelinhaDoce acabou de adquirir a conquista ")
        }
        withStyle(style = SpanStyle(fontSize = 12.sp, fontWeight = FontWeight.Bold)) {
            append("'Primeiro dia de Academia'")
        }
        withStyle(style = SpanStyle(fontSize = 10.sp)) {
            append(". Dê os parabéns para ele!")
        }
    }
    Text(
        text = textoEstilizado, maxLines = 3, modifier = Modifier
            .fillMaxWidth(), textAlign = TextAlign.Justify
    )
}
