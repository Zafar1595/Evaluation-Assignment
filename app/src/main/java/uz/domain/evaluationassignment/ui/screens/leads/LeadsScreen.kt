package uz.domain.evaluationassignment.ui.screens.leads

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.compose.koinViewModel
import uz.domain.evaluationassignment.Screen
import uz.domain.evaluationassignment.models.Lead
import uz.domain.evaluationassignment.models.LeadT


@Composable
fun LeadsScreen(
    viewModel: LeadsViewModel = koinViewModel<LeadsViewModel>(),
    navController: NavController
) {

    val data = viewModel.getLeads()
    LazyColumn(modifier = Modifier.padding(horizontal = 8.dp)) {
        data.forEach {
            item {
                ItemLead(it) {
                    navController.navigate("${Screen.LeadsDetailScreen.route}/${it.id}")
                }
            }
        }
    }
}


@Composable
fun ItemLead(lead: LeadT, onItenClick: () -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .clickable {
                onItenClick.invoke()
            },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Image(
                painter = painterResource(id = lead.avatar),
                contentDescription = lead.fullName,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(40.dp)
            )
            Text(
                text = lead.fullName,
                fontSize = 16.sp,
                color = Color.Black,
                modifier = Modifier
                    .align(alignment = androidx.compose.ui.Alignment.CenterVertically)
                    .padding(start = 8.dp)
            )
            Image(
                painter = painterResource(id = lead.flag),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .padding(start = 8.dp)
                    .size(16.dp)
                    .align(alignment = androidx.compose.ui.Alignment.CenterVertically)
            )
        }

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(100.dp))
                .background(color = Color(lead.status.backgroundColor))
                .align(alignment = androidx.compose.ui.Alignment.CenterVertically)
        ) {
            Text(
                text = lead.status.name,
                fontSize = 12.sp,
                color = Color(lead.status.textColor),
                modifier = Modifier
                    .align(alignment = androidx.compose.ui.Alignment.Center)
                    .padding(horizontal = 8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LeadsScreenPreview() {
    LeadsScreen(navController = rememberNavController())
}