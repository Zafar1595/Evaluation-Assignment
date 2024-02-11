package uz.domain.evaluationassignment.ui.screens.leads

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uz.domain.Result
import uz.domain.evaluationassignment.models.Country
import uz.domain.evaluationassignment.models.Intention
import uz.domain.evaluationassignment.models.Lead
import uz.domain.evaluationassignment.models.mapper.toAppModel
import uz.domain.evaluationassignment.models.mapper.toEntity
import uz.domain.evaluationassignment.ui.utils.Resource
import uz.domain.usecase.country.GetAllCountrysUseCase
import uz.domain.usecase.intention.GetAllIntentionsUseCase
import uz.domain.usecase.lead.AddLeadUseCase
import uz.domain.usecase.lead.GetLeadsListUseCase

class LeadsViewModel(
    private val getLeadsListUseCase: GetLeadsListUseCase,
    private val addLeadUseCase: AddLeadUseCase,
    private val getAllIntentionsUseCase: GetAllIntentionsUseCase,
    private val getAllCountrysUseCase: GetAllCountrysUseCase,
) : ViewModel() {

    val leadsList: MutableLiveData<Resource<List<Lead>>> = MutableLiveData()

    fun getLeadsList() {
        leadsList.value = Resource.loading()
        viewModelScope.launch {
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
         viewModelScope.launch {
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

}