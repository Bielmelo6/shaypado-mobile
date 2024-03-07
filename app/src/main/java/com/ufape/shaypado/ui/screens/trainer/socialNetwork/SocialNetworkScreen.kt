import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun SocialNetworkScreen(navController: NavController){
    Box(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight().padding(start = 16.dp, end = 16.dp, bottom = 16.dp)){
        Column(modifier = Modifier.matchParentSize()) {
            LazyColumn {
                items(5) { index ->
                    CardSocialNetwork()
                }
            }
        }
    }
}