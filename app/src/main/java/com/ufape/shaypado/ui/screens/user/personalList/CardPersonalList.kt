import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.ufape.shaypado.R
import com.ufape.shaypado.ui.components.AppText
import com.ufape.shaypado.ui.components.TextType
import com.ufape.shaypado.ui.model.TrainerProfileData
import com.ufape.shaypado.ui.routes.MobileNavigationScreen
import com.ufape.shaypado.ui.theme.PerfilShaypado2Icon
import java.io.File

@Composable
fun CardPersonalList(
    navController: NavController,
    trainerData: TrainerProfileData
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .shadow(elevation = 6.dp, spotColor = Color.Black)
            .clickable {
                navController.navigate(MobileNavigationScreen.PersonalProfile.shortName + "/${trainerData.friendshipCode}")
            },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .height(100.dp)
                    .width(120.dp), contentAlignment = Alignment.Center
            ) {
                Card(
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp),
                    shape = RoundedCornerShape(50.dp),
                    colors = CardDefaults.cardColors(
                        contentColor = Color.Red
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        if (trainerData.profilePicture != null) {
                            AsyncImage(
                                model = trainerData.profilePicture,
                                contentDescription = null,
                            )
                        }else  {
                            PerfilShaypado2Icon()
                        }
                    }
                }

            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(250.dp)
                    .padding(top = 8.dp, bottom = 8.dp)
            ) {
                AppText(
                    text =  trainerData.fullName,
                    textType = TextType.BODY_LARGE,
                    textAlignment = TextAlign.Start,
                    color = MaterialTheme.colorScheme.onSurface
                )
                AppText(
                    text = trainerData.city + " - " + trainerData.state ,
                    textType = TextType.TITLE_MEDIUM,
                    textAlignment = TextAlign.Start,
                    color = MaterialTheme.colorScheme.onSurface
                )
                AppText(
                    text = trainerData.specialties,
                    textType = TextType.BODY_LARGE,
                    textAlignment = TextAlign.Start,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
}
