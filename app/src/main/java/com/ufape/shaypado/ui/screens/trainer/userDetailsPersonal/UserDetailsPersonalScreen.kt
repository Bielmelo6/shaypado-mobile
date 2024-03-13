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
import androidx.navigation.NavController
import com.ufape.shaypado.ui.components.AppButton
import com.ufape.shaypado.ui.components.AppDialog
import com.ufape.shaypado.ui.components.AppHeader
import com.ufape.shaypado.ui.components.AppText
import com.ufape.shaypado.ui.components.ButtonVariant
import com.ufape.shaypado.ui.components.CustomTextField
import com.ufape.shaypado.ui.components.TextType
import com.ufape.shaypado.ui.theme.GoogleImage
import com.ufape.shaypado.ui.theme.WhatsappImage

@Composable
fun UserDetailsPersonalScreen(navController: NavController) {

    var showDeleteDialog by remember { mutableStateOf(false) }

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
                AppHeader(navController = navController) {
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


    Box(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .matchParentSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                AppHeader(navController = navController)
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.Top, modifier = Modifier
                        .fillMaxWidth()
                        .height(96.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .height(96.dp)
                            .width(96.dp), contentAlignment = Alignment.Center
                    ) {
                        FloatingActionButton(
                            onClick = {},
                            modifier = Modifier.matchParentSize(),
                            shape = CircleShape,
                        ) {}
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                            .padding(start = 8.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        AppText(
                            text = "Marco Túlio Alves",
                            textType = TextType.HEADLINE_SMALL,
                            textAlignment = TextAlign.Start,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        AppText(
                            text = "081234-SP",
                            textType = TextType.TITLE_MEDIUM,
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
                                text = "marco.alves@crossfitcampinas.com.br",
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
                                text = "(19) 99834-5678",
                                textType = TextType.BODY_SMALL,
                                textAlignment = TextAlign.Start,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                AppText(
                    text = "Treinamento Funcional e Crossfit",
                    textType = TextType.TITLE_MEDIUM,
                    textAlignment = TextAlign.Start
                )
                Spacer(modifier = Modifier.height(4.dp))
                AppText(
                    text = "34 anos",
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
                        text = "São Paulo - Campinas",
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
                        text = "Crossfit Campinas",
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
        }
    }
}