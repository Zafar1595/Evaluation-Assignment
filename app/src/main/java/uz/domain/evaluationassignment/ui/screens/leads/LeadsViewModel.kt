package uz.domain.evaluationassignment.ui.screens.leads

import android.graphics.Color
import android.util.Log
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uz.domain.Result
import uz.domain.evaluationassignment.R
import uz.domain.evaluationassignment.models.Lead
import uz.domain.evaluationassignment.models.LeadT
import uz.domain.evaluationassignment.models.Status
import uz.domain.evaluationassignment.models.mapper.toEntity
import uz.domain.evaluationassignment.ui.base.BaseViewModel
import uz.domain.usecase.lead.AddLeadUseCase
import uz.domain.usecase.lead.GetLeadByIdUseCase
import uz.domain.usecase.lead.GetLeadsListUseCase


class LeadsViewModel(
    private val getLeadsListUseCase: GetLeadsListUseCase,
    private val getLeadByIdUseCase: GetLeadByIdUseCase,
    private val addLeadUseCase: AddLeadUseCase
) : BaseViewModel() {

    private val leads = listOf(
        LeadT(
            id = 234234,
            fullName = "Jane Cooper",
            flag = uz.domain.evaluationassignment.R.drawable.af,
            status = Status(
                id = 1,
                name = "New",
                textColor = Color.parseColor("#276EF1"),
                backgroundColor = Color.parseColor("#EEF3FE"),
                statusCount = 3,
                statusColor = Color.parseColor("#42A5F5"),
                statusPercent = 35
            ),
            avatar = R.drawable.jane_cooper
        ),
        LeadT(
            id = 234235,
            fullName = "Albert Flores",
            flag = R.drawable.ae,
            status = Status(
                id = 2, name = "Unsuccessful",
                textColor = Color.parseColor("#545454"),
                backgroundColor = Color.parseColor("#F6F6F6"),
                statusCount = 5,
                statusColor = Color.parseColor("#BDBDBD"),
                statusPercent = 54
            ),
            avatar = R.drawable.albert_flores
        ),
        LeadT(
            id = 234233,
            fullName = "Jerome Bell",
            flag = R.drawable.af,
            status = Status(
                id = 3, name = "Junk",
                textColor = Color.parseColor("#D44333"),
                backgroundColor = Color.parseColor("#FDF0EF"),
                statusCount = 2,
                statusColor = Color.parseColor("#EF5350"),
                statusPercent = 22
            ),
            avatar = R.drawable.jerome_bell
        ),
        LeadT(
            id = 234234,
            fullName = "Guy Hawkins",
            flag = R.drawable.uz,
            status = Status(
                id = 4, name = "Customer",
                textColor = Color.parseColor("#3AA76D"),
                backgroundColor = Color.parseColor("#F0F9F4"),
                statusCount = 8,
                statusColor = Color.parseColor("#9CCC65"),
                statusPercent = 80
            ),
            avatar = R.drawable.guy_awkins
        ),
        LeadT(
            id = 234235,
            fullName = "Annette Black",
            flag = R.drawable.ua,
            status = Status(
                id = 5, name = "No Answer",
                textColor = Color.parseColor("#545454"),
                backgroundColor = Color.parseColor("#F6F6F6"),
                statusCount = 1,
                statusColor = Color.parseColor("#EC407A"),
                statusPercent = 19
            ),
            avatar = R.drawable.annette_black
        ),
        LeadT(
            id = 234236,
            fullName = "Courtney Henry",
            flag = R.drawable.us,
            status = Status(
                id = 6, name = "Option Sent",
                textColor = Color.parseColor("#ED6E33"),
                backgroundColor = Color.parseColor("#FEF3EF"),
                statusCount = 4,
                statusColor = Color.parseColor("#FFB74D"),
                statusPercent = 35
            ),
            avatar = R.drawable.courtney_henry
        ),
        LeadT(
            id = 234237,
            fullName = "Kristin Watson",
            flag = R.drawable.es,
            status = Status(
                id = 3, name = "Junk",
                textColor = Color.parseColor("#D44333"),
                backgroundColor = Color.parseColor("#FDF0EF"),
                statusCount = 2,
                statusColor = Color.parseColor("#EF5350"),
                statusPercent = 28
            ),
            avatar = R.drawable.kristin_watson
        ),
        LeadT(
            id = 234238,
            fullName = "Savannah Nguyen",
            flag = R.drawable.it,
            status = Status(
                id = 4, name = "Customer",
                textColor = Color.parseColor("#3AA76D"),
                backgroundColor = Color.parseColor("#F0F9F4"),
                statusCount = 8,
                statusColor = Color.parseColor("#9CCC65"),
                statusPercent = 87
            ),
            avatar = R.drawable.savannah_nguyen
        ),
        LeadT(
            id = 234239,
            fullName = "Cameron Williamson",
            flag = R.drawable.kz,
            status = Status(
                id = 6, name = "Option Sent",
                textColor = Color.parseColor("#ED6E33"),
                backgroundColor = Color.parseColor("#FEF3EF"),
                statusCount = 4,
                statusColor = Color.parseColor("#FFB74D"),
                statusPercent = 35
            ),
            avatar = R.drawable.cameron_williamson
        )
    )



    fun addLead(lead: Lead) {
        job = viewModelScope.launch {
            val result = addLeadUseCase(lead.toEntity())
            when (result) {
                is Result.Success -> {
                    Log.d("DaoTest", "addLead: ${result.data}")
                }

                is Result.Error -> {
                    Log.d("DaoTest", "addLead: ${result.errorMessage}")
                }
            }
        }
    }


    fun getLeads(): List<LeadT> {
//        getLeadsListUseCase
        return leads
    }

    fun getLeadById(leadId: Int?): LeadT? {
//        getLeadByIdUseCase
        return leads.find { it.id == leadId }
    }

    fun getStatuses(): List<Status> {
        return listOf(
            Status(
                id = 1,
                name = "New",
                textColor = Color.parseColor("#276EF1"),
                backgroundColor = Color.parseColor("#EEF3FE"),
                statusCount = 3,
                statusColor = Color.parseColor("#42A5F5"),
                statusPercent = 35
            ),
            Status(
                id = 11, name = "No answer",
                textColor = Color.parseColor("#EC407A"),
                backgroundColor = Color.parseColor("#EC407A"),
                statusCount = 5,
                statusColor = Color.parseColor("#EC407A"),
                statusPercent = 54
            ),
            Status(
                id = 2, name = "Unsuccessful",
                textColor = Color.parseColor("#545454"),
                backgroundColor = Color.parseColor("#F6F6F6"),
                statusCount = 5,
                statusColor = Color.parseColor("#BDBDBD"),
                statusPercent = 54
            ),
            Status(
                id = 3, name = "Junk",
                textColor = Color.parseColor("#D44333"),
                backgroundColor = Color.parseColor("#FDF0EF"),
                statusCount = 2,
                statusColor = Color.parseColor("#EF5350"),
                statusPercent = 22
            ),
            Status(
                id = 12, name = "Warm",
                textColor = Color.parseColor("#D4E157"),
                backgroundColor = Color.parseColor("#D4E157"),
                statusCount = 8,
                statusColor = Color.parseColor("#D4E157"),
                statusPercent = 80
            ),
            Status(
                id = 13, name = "Hot",
                textColor = Color.parseColor("#AB47BC"),
                backgroundColor = Color.parseColor("#AB47BC"),
                statusCount = 8,
                statusColor = Color.parseColor("#AB47BC"),
                statusPercent = 80
            ),
            Status(
                id = 4, name = "Customer",
                textColor = Color.parseColor("#3AA76D"),
                backgroundColor = Color.parseColor("#F0F9F4"),
                statusCount = 8,
                statusColor = Color.parseColor("#9CCC65"),
                statusPercent = 80
            ),
            Status(
                id = 5, name = "No Answer",
                textColor = Color.parseColor("#545454"),
                backgroundColor = Color.parseColor("#F6F6F6"),
                statusCount = 1,
                statusColor = Color.parseColor("#EC407A"),
                statusPercent = 19
            ),
            Status(
                id = 6, name = "Option Sent",
                textColor = Color.parseColor("#ED6E33"),
                backgroundColor = Color.parseColor("#FEF3EF"),
                statusCount = 4,
                statusColor = Color.parseColor("#FFB74D"),
                statusPercent = 35
            )
        )
    }
}