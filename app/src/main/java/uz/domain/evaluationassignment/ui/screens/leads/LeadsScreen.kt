package uz.domain.evaluationassignment.ui.screens.leads

import android.util.Log
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import org.koin.androidx.compose.koinViewModel
import uz.domain.evaluationassignment.Screen
import uz.domain.evaluationassignment.models.Country
import uz.domain.evaluationassignment.models.Intention
import uz.domain.evaluationassignment.models.Lead
import uz.domain.evaluationassignment.ui.utils.ResourceState


@Composable
fun LeadsScreen(
    viewModel: LeadsViewModel = koinViewModel<LeadsViewModel>(),
    navController: NavController
) {
    val viewState = viewModel.leadsList.observeAsState()

    LaunchedEffect(key1 = viewState, block = {
        viewModel.getLeadsList()
    })

    val state = viewState.value
    when (state?.status) {
        ResourceState.LOADING -> ViewLoading()

        ResourceState.SUCCESS -> {
            LazyColumn(modifier = Modifier.padding(horizontal = 8.dp)) {
                state.data?.forEach {
                    item {
                        ItemLead(
                            viewModel.allIntentions.value?.find { i -> i.id == it.leadIntentionId },
                            viewModel.allCountrys.value?.find { c -> c.id == it.countryId },
                            it
                        ) {
                            navController.navigate("${Screen.LeadsDetailScreen.route}/${it.id}")
                        }
                    }
                }
            }
        }

        ResourceState.ERROR -> ViewError(state.message)

        else -> ViewError("Unknown error")
    }
}

@Composable
fun ViewLoading() {
    Log.d("DaoTest", "LeadsScreen: view loading")
}

@Composable
fun ViewError(message: String?) {
    Log.d("DaoTest", "LeadsScreen: view error $message")
}

@Composable
fun ItemLead(intention: Intention?, country: Country?, lead: Lead, onItenClick: () -> Unit) {

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
                painter = rememberAsyncImagePainter(lead.adSource),
                contentDescription = lead.firstName,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(40.dp)
            )
            Text(
                text = "${lead.firstName} ${lead.lastName}",
                fontSize = 16.sp,
                color = Color.Black,
                modifier = Modifier
                    .align(alignment = androidx.compose.ui.Alignment.CenterVertically)
                    .padding(start = 8.dp)
            )
            country?.let {
                Image(
                    painter = rememberAsyncImagePainter(country.flag), //TODO: change to status image
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(CircleShape)
                        .padding(start = 8.dp)
                        .size(16.dp)
                        .align(alignment = androidx.compose.ui.Alignment.CenterVertically)
                )
            }
        }

        intention?.let {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(100.dp))
                    .background(color = Color(intention.backgroundColor))
                    .align(alignment = androidx.compose.ui.Alignment.CenterVertically)
            ) {
                Text(
                    text = intention.name,
                    fontSize = 12.sp,
                    color = Color(intention.textColor),
                    modifier = Modifier
                        .align(alignment = androidx.compose.ui.Alignment.Center)
                        .padding(horizontal = 8.dp)
                )
            }
        }
    }
}