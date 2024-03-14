import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.SelfImprovement
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ufape.shaypado.ui.components.AppText
import com.ufape.shaypado.ui.components.TextType
import com.ufape.shaypado.ui.screens.user.homeUserLogado.HomeUserViewModel

@Composable
fun CardHomeLogado(
    title : String,
    description : String,
) {
    Spacer(modifier = Modifier.height(8.dp))
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(
                76.dp
            )
            .clickable {},
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, bottom = 14.dp, top = 14.dp)) {
            Box(
                modifier = Modifier
                    .height(48.dp)
                    .width(48.dp)
                    .clip(CircleShape)
                    .background(color = Color.Transparent, CircleShape)
                    .border(3.dp, Color.Black, shape = CircleShape)
                    .padding(4.dp),
                //.background(color = Color.Transparent)
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier
                        .height(30.dp)
                        .width(30.dp),
                    imageVector = Icons.Outlined.SelfImprovement,
                    contentDescription = "SelfImprovement"
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                AppText(
                    text = "Mobilidade - Tornozelo",
                    textType = TextType.TITLE_MEDIUM,
                    textAlignment = TextAlign.Start
                )
                AppText(
                    text = "2 x 10",
                    textType = TextType.TITLE_SMALL,
                    textAlignment = TextAlign.Start
                )
            }
        }
    }
}