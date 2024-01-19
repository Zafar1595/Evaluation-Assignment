package uz.domain.evaluationassignment.ui.screens.leads.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uz.domain.Result
import uz.domain.evaluationassignment.models.Country
import uz.domain.evaluationassignment.models.Intention
import uz.domain.evaluationassignment.models.Language
import uz.domain.evaluationassignment.models.Lead
import uz.domain.evaluationassignment.models.mapper.toAppModel
import uz.domain.evaluationassignment.ui.base.BaseViewModel
import uz.domain.evaluationassignment.ui.utils.Resource
import uz.domain.usecase.country.GetCountryByIdUseCase
import uz.domain.usecase.intention.GetAllIntentionsUseCase
import uz.domain.usecase.intention.GetIntentionByIdUseCase
import uz.domain.usecase.language.GetLanguageByIdUseCase
import uz.domain.usecase.lead.GetLeadByIdUseCase

class LeadDetailViewModel(
    private val getLeadByIdUseCase: GetLeadByIdUseCase,
    private val getIntentionByIdUseCase: GetIntentionByIdUseCase,
    private val getLanguageByIdUseCase: GetLanguageByIdUseCase,
    private val getCountryByIdUseCase: GetCountryByIdUseCase,
    private val getAllIntentionsUseCase: GetAllIntentionsUseCase
) : BaseViewModel() {

    val leadById: MutableLiveData<Resource<Lead>> = MutableLiveData()
    fun getLeadById(id: Int) {

        job = viewModelScope.launch {
            when (val result = getLeadByIdUseCase(id)) {
                is Result.Success -> {
                    getLanguageById(result.data?.languageId ?: 0)
                    getIntentionById(result.data?.leadIntentionId ?: 0)
                    getCountryById(result.data?.countryId ?: 0)
                    leadById.value = Resource.success(result.data?.toAppModel())
                }

                is Result.Error -> {
                    Log.d("DaoTest", "getLeadById: ${result.errorMessage}")
                }
            }
        }
    }


    val intentionById: MutableLiveData<Intention> = MutableLiveData()
    private suspend fun getIntentionById(id: Int) {
        when (val result = getIntentionByIdUseCase(id)) {
            is Result.Success -> {
                intentionById.value = result.data?.toAppModel()
            }

            is Result.Error -> {
                Log.d("DaoRequest", "getIntentionById: ${result.errorMessage}")
            }
        }
    }


    val languageById: MutableLiveData<Language> = MutableLiveData()
    private suspend fun getLanguageById(languageId: Int) {
        when (val result = getLanguageByIdUseCase(languageId)) {
            is Result.Success -> {
                languageById.value = result.data?.toAppModel()
            }

            is Result.Error -> {
                Log.d("DaoTest", "getLeadById: ${result.errorMessage}")
            }
        }
    }

    val countryById: MutableLiveData<Country> = MutableLiveData()
    private suspend fun getCountryById(countryId: Int) {
        when (val result = getCountryByIdUseCase(countryId)) {
            is Result.Success -> {
                countryById.value = result.data?.toAppModel()
            }

            is Result.Error -> {
                Log.d("DaoTest", "getCountryById: ${result.errorMessage}")
            }
        }
    }


    val allIntentions: MutableLiveData<Resource<List<Intention>>> = MutableLiveData()
    fun getAllIntentions() {
        job = viewModelScope.launch {
            when (val result = getAllIntentionsUseCase()) {
                is Result.Success -> {
                    allIntentions.value = Resource.success(result.data?.map { it.toAppModel() })
                }

                is Result.Error -> {
                    allIntentions.value = Resource.error(result.errorMessage)
                    Log.d("DaoRequest", "getIntentions: ${result.errorMessage}")
                }
            }
        }
    }

}