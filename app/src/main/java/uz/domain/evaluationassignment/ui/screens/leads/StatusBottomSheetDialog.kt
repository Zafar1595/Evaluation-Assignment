@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)

package uz.domain.evaluationassignment.ui.screens.leads

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.semantics.SemanticsProperties.ToggleableState
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun StatusBottomSheetDialog(
    statusID: Int,
    viewModel: LeadsViewModel = LeadsViewModel(),
    onDismissRequest: () -> Unit
) {

    val statuses = viewModel.getStatuses()


    ModalBottomSheet(onDismissRequest = {
        onDismissRequest.invoke()
    }) {

        val radioButtons = remember {
            mutableStateOf(statuses.find { it.id == statusID }?.name)
        }

        LazyColumn(content = {
            item {
                statuses.forEach { status ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 4.dp)
                            .border(
                                1.dp,
                                if (radioButtons.value == status.name)
                                    Color.Black else Color.LightGray,
                                shape = RoundedCornerShape(12.dp)
                            )
                    ) {
                        RadioButton(
                            selected = radioButtons.value == status.name,
                            onClick = {
                                radioButtons.value = status.name
                            },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = Color(status.textColor),
                                unselectedColor = Color(status.textColor)
                            )
                        )
                        Text(
                            text = status.name,
                            color = Color.Black,
                            modifier = Modifier
                                .align(CenterVertically)
                                .clickable {
                                    radioButtons.value = status.name
                                }
                        )
                    }
                }

                Button(
                    onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(12.dp),
                    colors = buttonColors(
                        backgroundColor = Color.Black
                    ),
                    modifier = Modifier
                        .background(Color.Transparent)
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Apply",
                        color = Color.White,
                        modifier = Modifier
                            .align(CenterVertically)
                            .padding(vertical = 16.dp)
                    )
                }
                Spacer(modifier = Modifier.size(16.dp))
            }
        })
    }
}

@Preview
@Composable
fun StatusBottomSheetDialogPreview() {
    StatusBottomSheetDialog(statusID = 1, onDismissRequest = {})
}

