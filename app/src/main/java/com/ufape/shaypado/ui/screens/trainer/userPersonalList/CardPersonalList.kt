import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ufape.shaypado.ui.components.AppText
import com.ufape.shaypado.ui.components.TextType

@Composable
fun CardPersonalList() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp).shadow(elevation = 6.dp ,spotColor = Color.Black).clickable{},
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier
                .height(100.dp)
                .width(120.dp), contentAlignment = Alignment.Center) {
                FloatingActionButton(
                    onClick = {},
                    modifier = Modifier
                        .height(86.dp)
                        .width(86.dp),
                    shape = CircleShape,
                ){}}
                Column(modifier = Modifier
                    .fillMaxHeight()
                    .width(250.dp)
                    .padding(top = 8.dp, bottom = 8.dp)) {
                    AppText(
                        text = "Marco Túlio Alves",
                        textType = TextType.BODY_LARGE,
                        textAlignment = TextAlign.Start,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    AppText(
                        text = "081234-SP",
                        textType = TextType.BODY_LARGE,
                        textAlignment = TextAlign.Start,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    AppText(
                        text = "Campinas - SP",
                        textType = TextType.BODY_LARGE,
                        textAlignment = TextAlign.Start,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    AppText(
                        text = "Treinamento Funcional e Crossfit",
                        textType = TextType.BODY_LARGE,
                        textAlignment = TextAlign.Start,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
