package uz.domain.evaluationassignment.ui.screens.leads

import android.graphics.Color
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uz.domain.Result
import uz.domain.evaluationassignment.R
import uz.domain.evaluationassignment.models.Country
import uz.domain.evaluationassignment.models.Intention
import uz.domain.evaluationassignment.models.Language
import uz.domain.evaluationassignment.models.Lead
import uz.domain.evaluationassignment.models.LeadT
import uz.domain.evaluationassignment.models.Status
import uz.domain.evaluationassignment.models.mapper.toAppModel
import uz.domain.evaluationassignment.models.mapper.toEntity
import uz.domain.evaluationassignment.ui.base.BaseViewModel
import uz.domain.evaluationassignment.ui.utils.Resource
import uz.domain.usecase.country.GetAllCountrysUseCase
import uz.domain.usecase.intention.GetAllIntentionsUseCase
import uz.domain.usecase.intention.GetIntentionByIdUseCase
import uz.domain.usecase.language.GetLanguageByIdUseCase
import uz.domain.usecase.lead.AddLeadUseCase
import uz.domain.usecase.lead.GetLeadByIdUseCase
import uz.domain.usecase.lead.GetLeadsListUseCase


class LeadsViewModel(
    private val getLeadsListUseCase: GetLeadsListUseCase,
    private val addLeadUseCase: AddLeadUseCase,
    private val getAllIntentionsUseCase: GetAllIntentionsUseCase,
    private val getAllCountrysUseCase: GetAllCountrysUseCase,
) : BaseViewModel() {

    val leadsList: MutableLiveData<Resource<List<Lead>>> = MutableLiveData()

    fun getLeadsList() {
        leadsList.value = Resource.loading()
        job = viewModelScope.launch {
            when (val result = getLeadsListUseCase()) {
                is Result.Success -> {
                    getIntentions {
                    }
                    getAllCountrys()

                    leadsList.value = Resource.success(result.data?.map { it.toAppModel() })
                }

                is Result.Error -> {
                    Log.d("DaoRequest", "getLeadsList: ${result.errorMessage}")
                    leadsList.value = Resource.error(result.errorMessage)
                }
            }
        }
    }

    val allIntentions: MutableLiveData<List<Intention>> = MutableLiveData()

    private suspend fun getIntentions(res: () -> Unit) {

        when (val result = getAllIntentionsUseCase()) {
            is Result.Success -> {
                allIntentions.value = result.data?.map { it.toAppModel() }
                res.invoke()
            }

            is Result.Error -> {
                Log.d("DaoRequest", "getIntentions: ${result.errorMessage}")
            }
        }

    }

    val allCountrys: MutableLiveData<List<Country>> = MutableLiveData()

    private suspend fun getAllCountrys() {
        when (val result = getAllCountrysUseCase()) {
            is Result.Success -> {
                allCountrys.value = result.data?.map { it.toAppModel() }
            }

            is Result.Error -> {
                Log.d("DaoRequest", "getAllCountrys: ${result.errorMessage}")
            }
        }
    }

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