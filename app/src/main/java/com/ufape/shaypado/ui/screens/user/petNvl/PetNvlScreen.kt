import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ufape.shaypado.R
import com.ufape.shaypado.ui.components.AppText
import com.ufape.shaypado.ui.components.TextType
import com.ufape.shaypado.ui.theme.PetAxolotImage

@Composable
fun PetNvlScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight().padding(top = 16.dp)
    ) {
        Column(
            modifier = Modifier.matchParentSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
                AppText(
                    text = "PandaSonhador - Lv1",
                    textType = TextType.TITLE_MEDIUM,
                    textAlignment = TextAlign.Center
                )
                DeterminateProgressBar()
                Box(contentAlignment = Alignment.CenterEnd, modifier = Modifier.fillMaxWidth()){
                    FilledIconButton(
                        modifier = Modifier
                            .height(40.dp)
                            .width(40.dp),
                        shape = RoundedCornerShape(8.dp),
                        enabled = true,
                        onClick = {},
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = MaterialTheme.colorScheme.tertiaryContainer
                        )
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_trophy),
                            contentDescription = "Trophy",
                            tint = MaterialTheme.colorScheme.onTertiaryContainer
                        )
                    }
                }
            }
            Column(verticalArrangement = Arrangement.Bottom) {
                Box(
                    modifier = Modifier
                        .height(325.dp)
                        .fillMaxWidth(), contentAlignment = Alignment.BottomCenter
                ) {
                    Box {
                        HorizontalDivider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(80.dp)
                                .background(MaterialTheme.colorScheme.primary)
                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(65.dp),
                        contentAlignment = Alignment.TopCenter
                    ) {

                        Canvas(
                            modifier = Modifier
                                .width(225.dp)
                                .height(20.dp)
                        ) {
                            drawIntoCanvas {
                                val width = 500f
                                val height = 50f
                                val centerX = size.width / 2
                                val centerY = size.height / 2
                                val color =  // Cor da elipse

                                    // Desenha a elipse
                                    it.drawOval(
                                        centerX - (width / 2),
                                        centerY - (height / 2),
                                        centerX + (width / 2),
                                        centerY + (height / 2),
                                        paint = Paint().apply { Color(0xFF171D1A) })
                            }
                        }
                    }
                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .background(Color.Transparent),
                        contentAlignment = Alignment.TopCenter
                    ) { Box(contentAlignment = Alignment.TopCenter) { PetAxolotImage() } }
                }
            }
        }
    }
}