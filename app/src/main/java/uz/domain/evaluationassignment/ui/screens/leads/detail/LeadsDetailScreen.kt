package uz.domain.evaluationassignment.ui.screens.leads.detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import org.koin.androidx.compose.koinViewModel
import uz.domain.evaluationassignment.R
import uz.domain.evaluationassignment.models.Country
import uz.domain.evaluationassignment.models.Intention
import uz.domain.evaluationassignment.models.Language
import uz.domain.evaluationassignment.models.Lead
import uz.domain.evaluationassignment.ui.screens.leads.LeadsViewModel
import uz.domain.evaluationassignment.ui.screens.leads.ViewError
import uz.domain.evaluationassignment.ui.screens.leads.ViewLoading
import uz.domain.evaluationassignment.ui.utils.ResourceState

@Composable
fun LeadsDetailScreen(
    leadId: Int?,
    viewModel: LeadDetailViewModel = koinViewModel<LeadDetailViewModel>(),
    navController: NavHostController
) {
    leadId ?: return

    viewModel.getLeadById(leadId)

    val leadState = viewModel.leadById.observeAsState()

    when (leadState.value?.status) {
        ResourceState.LOADING -> ViewLoading()
        ResourceState.SUCCESS -> {
            val lead = leadState.value?.data
            lead ?: return
            val intention = viewModel.intentionById.value
            val country = viewModel.countryById.value
            val language = viewModel.languageById.value

            ShowViews(lead, intention, country, language, navController)
        }

        ResourceState.ERROR -> {
            ViewError(leadState.value?.message)
        }

        else -> ViewError(message = "Unknown error")
    }

}

@Composable
fun ShowViews(
    lead: Lead,
    intention: Intention?,
    country: Country?,
    language: Language?,
    navController: NavHostController
) {
    Scaffold(
        contentColor = Color.Transparent,
        topBar = {
            TopBar(navController = navController)
        },
        content = { padding ->
            Content(padding, lead, intention, country, language)
        },
        bottomBar = {
            BottomBar()
        })
}

@Composable
fun TopBar(navController: NavHostController) {
    Row(modifier = Modifier.fillMaxWidth()) {

        Box(
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp)
                .background(
                    color = colorResource(id = R.color.grey),
                    shape = RoundedCornerShape(8.dp)
                )
                .size(40.dp)
                .weight(1f, false)
                .clickable {
                    navController.popBackStack()
                }
        ) {
            Image(
                painter = painterResource(id = R.drawable.chevron_left),
                contentDescription = "Back",
                modifier = Modifier.padding(10.dp),
                alignment = androidx.compose.ui.Alignment.Center,
            )
        }

        Text(
            text = stringResource(id = R.string.leads_detail),
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            color = Color.Black,
            style = TextStyle(fontWeight = androidx.compose.ui.text.font.FontWeight.Bold),
            modifier = Modifier
                .padding(16.dp)
                .padding(vertical = 8.dp)
                .weight(3.4f)
        )

        Box(
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp)
                .background(
                    color = colorResource(id = R.color.grey),
                    shape = RoundedCornerShape(8.dp)
                )
                .size(40.dp)
                .weight(1f, false)
        ) {
            Image(
                painter = painterResource(id = R.drawable.refresh_ccw),
                contentDescription = "Back",
                modifier = Modifier.padding(10.dp),
                alignment = androidx.compose.ui.Alignment.Center,
            )
        }

        Box(
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp)
                .background(
                    color = colorResource(id = R.color.grey),
                    shape = RoundedCornerShape(8.dp)
                )
                .size(40.dp)
                .weight(1f, false)
        ) {
            Image(
                painter = painterResource(id = R.drawable.pen_square),
                contentDescription = "Back",
                modifier = Modifier.padding(10.dp),
                alignment = androidx.compose.ui.Alignment.Center,
            )
        }
    }
}

@Composable
fun Content(
    padding: PaddingValues,
    lead: Lead?,
    intention: Intention?,
    country: Country?,
    language: Language?
) {
    if (lead == null) return

    LazyColumn(modifier = Modifier.padding(padding)) {
        item {
            Column(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = rememberAsyncImagePainter(lead.adSource),
                    contentDescription = "Avatar",
                    modifier = Modifier
                        .align(alignment = androidx.compose.ui.Alignment.CenterHorizontally)
                        .size(56.dp)
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = lead.firstName + " " + lead.lastName,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Black,
                    modifier = Modifier
                        .align(alignment = androidx.compose.ui.Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    text = "ID: " + lead.id.toString(),
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    color = colorResource(id = R.color.grey600),
                    modifier = Modifier
                        .align(alignment = androidx.compose.ui.Alignment.CenterHorizontally)
                )
            }
        }

        item {
            LeadStatus(intention)
            LeadQuality(intention)
            Spacer(modifier = Modifier.size(24.dp))
            LeadInfo("Info") { }
            GeneralInfo(
                lead,
                intention,
                country,
                language
            )

        }
    }
}

@Composable
fun LeadStatus(status: Intention?) {
    status ?: return

    var bottomSheetShow by remember {
        mutableStateOf(false)
    }

    if (bottomSheetShow) {
        StatusBottomSheetDialog(statusID = status.id) {
            bottomSheetShow = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .border(1.dp, Color.LightGray, shape = RoundedCornerShape(8.dp))
    ) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp, bottom = 0.dp, start = 12.dp, end = 12.dp)
        ) {
            Text(
                text = stringResource(id = R.string.lead_status),
                fontSize = 14.sp,
                color = Color.Black,
                style = TextStyle(fontWeight = androidx.compose.ui.text.font.FontWeight.Bold),
                modifier = Modifier
            )

            Row(modifier = Modifier.align(alignment = Alignment.CenterVertically)) {
                Image(
                    painter = painterResource(id = R.drawable.dot), contentDescription = null,
                    modifier = Modifier.align(alignment = Alignment.CenterVertically),
                    colorFilter = ColorFilter.tint(Color(status.statusColor))
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    text = status.name,
                    fontSize = 12.sp,
                    color = colorResource(id = R.color.grey600),
                    style = TextStyle(fontWeight = androidx.compose.ui.text.font.FontWeight.Bold),
                    modifier = Modifier
                        .clickable { bottomSheetShow = true }
                        .align(alignment = Alignment.CenterVertically),
                    textAlign = TextAlign.End
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp, top = 8.dp, bottom = 12.dp)
        ) {
            repeat(10) { i ->
                Spacer(modifier = Modifier.size(1.dp))
                Box(
                    modifier = Modifier
                        .background(
                            color = if (i < status.statusCount) Color(status.statusColor)
                            else colorResource(id = R.color.grey),
                            shape = RoundedCornerShape(4.dp)
                        )
                        .height(12.dp)
                        .weight(1f)
                )
                Spacer(modifier = Modifier.size(1.dp))
            }
        }
    }
}

@Composable
fun LeadQuality(status: Intention?) {
    status ?: return
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .border(1.dp, Color.LightGray, shape = RoundedCornerShape(8.dp))
    ) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp, bottom = 0.dp, start = 12.dp, end = 12.dp)
        ) {
            Text(
                text = stringResource(id = R.string.lead_quality),
                fontSize = 14.sp,
                color = Color.Black,
                style = TextStyle(fontWeight = androidx.compose.ui.text.font.FontWeight.Bold),
                modifier = Modifier
            )

            Text(
                text = status.statusPercent.toString() + "%",
                fontSize = 12.sp,
                color = colorResource(id = R.color.black),
                style = TextStyle(fontWeight = androidx.compose.ui.text.font.FontWeight.Bold),
                modifier = Modifier
                    .align(alignment = Alignment.CenterVertically),
                textAlign = TextAlign.End
            )

        }

        LinearProgressIndicator(
            progress = status.statusPercent / 100f,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp, horizontal = 12.dp)
                .height(12.dp)
                .clip(RoundedCornerShape(99.dp)),
            color = Color.Black,
            backgroundColor = colorResource(id = R.color.grey200)
        )
    }
}

@Composable
fun LeadInfo(type: String, onClick: () -> Unit) {
    val types: List<String> = listOf("Info", "Activity", "Analytics")
    Box(
        modifier = Modifier.padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .background(
                    color = colorResource(id = R.color.grey),
                    shape = RoundedCornerShape(7.dp)
                )
                .height(40.dp)
                .fillMaxWidth(),
        ) {
            Spacer(modifier = Modifier.size(2.dp))
            types.forEach { it ->
                Box(
                    modifier = Modifier
                        .clickable(onClick = onClick)
                        .background(
                            color = if (type == it) Color.Black else colorResource(id = R.color.grey),
                            shape = RoundedCornerShape(7.dp)
                        )
                        .weight(1f)
                        .height(36.dp)
                        .align(alignment = Alignment.CenterVertically),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = it,
                        color = if (it == type) Color.White else colorResource(id = R.color.grey700),
                        fontSize = 14.sp,
                        modifier = Modifier.padding(vertical = 6.dp)
                    )
                }
                Spacer(modifier = Modifier.size(2.dp))
            }
        }
    }
}

@Composable
fun GeneralInfo(lead: Lead, intention: Intention?, country: Country?, language: Language?) {

    val mapList: MutableMap<String, String> = mutableMapOf(
        "Lead intention" to (intention?.name ?: "Select"),
        "AD Source" to "Select",
        "Country" to (country?.name ?: "Select"),
        "City" to "Select",
        "Language" to (language?.name ?: "Select")
    )

    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            text = "General info",
            fontSize = 20.sp,
            color = Color.Black,
            style = TextStyle(fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
        )
        Spacer(modifier = Modifier.size(16.dp))
        mapList.forEach {
            Text(text = it.key, fontSize = 12.sp, color = Color.Black)
            Spacer(modifier = Modifier.size(4.dp))
            OutlinedTextField(
                value = it.value,
                onValueChange = { },
                modifier = Modifier.fillMaxWidth(),
                enabled = false,
                textStyle = TextStyle(
                    color = colorResource(id = R.color.grey700),
                    fontSize = 14.sp
                ),
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = colorResource(id = R.color.grey200),
                    unfocusedBorderColor = colorResource(id = R.color.grey200),
                    disabledBorderColor = colorResource(id = R.color.grey200),
                    cursorColor = colorResource(id = R.color.grey),
                    textColor = colorResource(id = R.color.grey),
                    backgroundColor = colorResource(id = R.color.grey)
                ),
            )
            Spacer(modifier = Modifier.size(12.dp))
        }
    }


}

@Composable
fun BottomBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 16.dp)
            .size(80.dp)
            .background(colorResource(id = R.color.black85), shape = RoundedCornerShape(20.dp)),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(id = R.color.transparent),
                contentColor = Color.Black
            ),
            border = BorderStroke(1.dp, colorResource(id = R.color.selected_color)),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .align(alignment = androidx.compose.ui.Alignment.CenterVertically)
                .weight(1f, true)
                .padding(start = 16.dp, end = 8.dp, top = 16.dp, bottom = 16.dp),
            contentPadding = PaddingValues(vertical = 12.dp, horizontal = 16.dp),
        ) {
            Image(
                painter = painterResource(id = R.drawable.messages_square),
                contentDescription = null,
                colorFilter = ColorFilter.tint(colorResource(id = R.color.selected_color)),
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(24.dp)
                    .align(alignment = androidx.compose.ui.Alignment.CenterVertically)
            )

            Text(
                text = stringResource(id = R.string.chat),
                fontSize = 16.sp,
                color = colorResource(id = R.color.selected_color),
                style = TextStyle(fontWeight = androidx.compose.ui.text.font.FontWeight.Normal)
            )

            Image(
                painter = painterResource(id = R.drawable.chevron_down),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .size(24.dp)
                    .align(alignment = androidx.compose.ui.Alignment.CenterVertically)
            )
        }

        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(id = R.color.selected_color),
                contentColor = Color.Black
            ),
            border = BorderStroke(1.dp, colorResource(id = R.color.selected_color)),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .weight(1f, true)
                .align(alignment = androidx.compose.ui.Alignment.CenterVertically)
                .padding(start = 8.dp, end = 16.dp, top = 16.dp, bottom = 16.dp),
            contentPadding = PaddingValues(vertical = 12.dp, horizontal = 16.dp),
        ) {
            Image(
                painter = painterResource(id = R.drawable.phone),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color.Black),
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(24.dp)
                    .align(alignment = androidx.compose.ui.Alignment.CenterVertically)
            )

            Text(
                text = stringResource(id = R.string.call),
                fontSize = 16.sp,
                color = colorResource(id = R.color.black85),
                style = TextStyle(fontWeight = androidx.compose.ui.text.font.FontWeight.Normal)
            )
        }
    }
}