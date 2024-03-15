import android.graphics.drawable.Icon
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.Download
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ufape.shaypado.ui.components.AppButton
import com.ufape.shaypado.ui.components.AppDialog
import com.ufape.shaypado.ui.components.AppHeader
import com.ufape.shaypado.ui.components.AppText
import com.ufape.shaypado.ui.components.ButtonVariant
import com.ufape.shaypado.ui.components.CustomTextField
import com.ufape.shaypado.ui.components.TextType
import com.ufape.shaypado.ui.screens.shimmers.ErrorScreen
import com.ufape.shaypado.ui.screens.shimmers.TrainerHomeShimmer
import com.ufape.shaypado.ui.screens.user.personalList.PersonalListViewModel
import com.ufape.shaypado.ui.theme.GoogleImage
import com.ufape.shaypado.ui.theme.PerfilShaypado2Icon
import com.ufape.shaypado.ui.theme.WhatsappImage
import com.ufape.shaypado.util.Result

@Composable
fun UserDetailsPersonalScreen(
    navController: NavController,
    personalId : String
) {

    val viewModel = hiltViewModel<PersonalListViewModel>()

    val personalData = viewModel.personalData.collectAsState(initial = Result.Loading)

    LaunchedEffect(Unit) {
        viewModel.fetchPersonal(personalId)
    }

    if (personalData.value is Result.Error) {
        return ErrorScreen {
            viewModel.fetchPersonal(personalId)
        }
    }

    if (personalData.value is Result.Loading) {
        return TrainerHomeShimmer()
    }

    val personal = (personalData.value as Result.Success).data

    var showDeleteDialog by remember { mutableStateOf(false) }


    AppHeader(navController = navController)
    Spacer(modifier = Modifier.height(8.dp))

    Column {
        Column (
            modifier = Modifier
                .weight(1f)
        ) {
            Row(
                verticalAlignment = Alignment.Top, modifier = Modifier
                    .fillMaxWidth()
                    .height(96.dp)
            ) {
                Card(
                    modifier = Modifier
                        .width(96.dp)
                        .height(96.dp),
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
                        if (personal.profilePicture != null) {
                            AsyncImage(
                                model = personal.profilePicture,
                                contentDescription = null,
                            )
                        }else  {
                            PerfilShaypado2Icon()
                        }
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .padding(start = 8.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    AppText(
                        text = personal.fullName,
                        textType = TextType.HEADLINE_SMALL,
                        textAlignment = TextAlign.Start,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    Row {
                        Icon(
                            imageVector = Icons.Outlined.Email,
                            contentDescription = "email",
                            modifier = Modifier
                                .height(18.dp)
                                .width(18.dp)
                                .padding(
                                    end = 2.dp,
                                )
                        )
                        AppText(
                            text =  personal.contactEmail,
                            textType = TextType.BODY_SMALL,
                            textAlignment = TextAlign.Start,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                    Row {
                        Icon(
                            imageVector = Icons.Outlined.Phone,
                            contentDescription = "phone",
                            modifier = Modifier
                                .height(18.dp)
                                .width(18.dp)
                                .padding(
                                    end = 2.dp,
                                )
                        )
                        AppText(
                            text =  personal.contactPhone,
                            textType = TextType.BODY_SMALL,
                            textAlignment = TextAlign.Start,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            AppText(
                text =  personal.specialties,
                textType = TextType.TITLE_MEDIUM,
                textAlignment = TextAlign.Start
            )
            Spacer(modifier = Modifier.height(4.dp))
            AppText(
                text = "${personal.age} anos",
                textType = TextType.BODY_LARGE,
                textAlignment = TextAlign.Start
            )
            Spacer(modifier = Modifier.height(4.dp))
            HorizontalDivider(
                modifier = Modifier
                    .height(2.dp)
                    .fillMaxWidth(), color = MaterialTheme.colorScheme.surface
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row {
                Icon(
                    imageVector = Icons.Outlined.Home,
                    contentDescription = "home",
                    modifier = Modifier
                        .height(24.dp)
                        .width(24.dp)
                        .padding(
                            end = 2.5.dp,
                        )
                )
                AppText(
                    text =  personal.city + " - " + personal.state,
                    textType = TextType.BODY_LARGE,
                    textAlignment = TextAlign.Start,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            Row {
                Icon(
                    imageVector = Icons.Outlined.LocationOn,
                    contentDescription = "location",
                    modifier = Modifier
                        .height(24.dp)
                        .width(24.dp)
                        .padding(
                            end = 2.5.dp,
                        )
                )
                AppText(
                    text =  personal.workLocation ?: "",
                    textType = TextType.BODY_LARGE,
                    textAlignment = TextAlign.Start,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            HorizontalDivider(
                modifier = Modifier
                    .height(2.dp)
                    .fillMaxWidth(), color = MaterialTheme.colorScheme.surface
            )
            Spacer(modifier = Modifier.height(12.dp))
            AppButton(
                text = "Baixar os planos do profissional",
                variant = ButtonVariant.PRIMARY,
                onClick = {},
            ) {
                //Icons.Outlined.Download
                //GoogleImage()
                Icon(
                    imageVector = Icons.Default.Download,
                    contentDescription = "download",
                )
            }
        }

        Column (
            modifier = Modifier
                .weight(1f),
            verticalArrangement = Arrangement.Bottom
        ) {
            Column {
                AppButton(
                    text = "Código de amizade",
                    variant = ButtonVariant.SECONDARY_CONTAINER,
                    onClick = {
                        showDeleteDialog = true
                    },
                )
                Spacer(modifier = Modifier.height(12.dp))
                AppButton(
                    text = "Whatsapp",
                    variant = ButtonVariant.PRIMARY,
                    onClick = {},
                ) {
                    WhatsappImage()
                }
                Spacer(modifier = Modifier.height(12.dp))
                AppButton(
                    text = "E-mail",
                    variant = ButtonVariant.TERTIARY,
                    onClick = {},
                ) {
                    GoogleImage()
                }
            }

            AppDialog(
                onDismiss = { showDeleteDialog = false },
                isDialogVisible = showDeleteDialog
            ) {
                Column {
                    Box(
                        modifier = Modifier
                            .height(80.dp)
                            .fillMaxWidth(), contentAlignment = Alignment.TopCenter
                    ) {
                        AppHeader(navController = navController, onBackPressed = { showDeleteDialog = false }) {
                            Box(modifier = Modifier.padding(top = 8.dp)) {
                                AppText(
                                    text = "Insira seu código de amizade",
                                    textType = TextType.TITLE_LARGE,
                                    textAlignment = TextAlign.Center
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Card(
                        modifier = Modifier
                            .height(115.dp)
                            .width(326.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                    ) {
                        //TextField(value = {}, onValueChange = {}, visualTransformation = VisualTransformation { })
                    }
                }
            }
        }
    }

}