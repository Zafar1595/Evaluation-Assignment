package uz.domain.evaluationassignment.ui

import android.graphics.Color
import android.net.Uri
import android.util.Log
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uz.domain.Result
import uz.domain.evaluationassignment.R
import uz.domain.evaluationassignment.models.Country
import uz.domain.evaluationassignment.models.Language
import uz.domain.evaluationassignment.models.Lead
import uz.domain.evaluationassignment.models.Intention
import uz.domain.evaluationassignment.models.mapper.toEntity
import uz.domain.evaluationassignment.ui.base.BaseViewModel
import uz.domain.usecase.country.AddCountrysUseCase
import uz.domain.usecase.intention.AddIntentionsUseCase
import uz.domain.usecase.intention.GetIntentionByIdUseCase
import uz.domain.usecase.language.AddLanguageUseCase
import uz.domain.usecase.lead.AddLeadUseCase
import uz.domain.usecase.lead.AddLeadsUseCase

class StartingDataViewModel(
    private val addLeadsUseCase: AddLeadsUseCase,
    private val addIntentionsUseCase: AddIntentionsUseCase,
    private val addCountrysUseCase: AddCountrysUseCase,
    private val addLanguageUseCase: AddLanguageUseCase
) : BaseViewModel() {

    fun insertStartingData() {
        job = viewModelScope.launch {
            addIntentionsUseCase(getIntentions().map { it.toEntity() })
            addCountrysUseCase(getCountry().map { it.toEntity() })
            addLanguageUseCase(getLanguage().map { it.toEntity() })
            addLeadsUseCase(getLeads().map { it.toEntity() })
        }
    }

    private fun getLeads(): List<Lead> {

        return listOf(
            Lead(
                id = 234234,
                firstName = "Jane",
                lastName = "Cooper",
                leadIntentionId = 1,
                adSource = getURLForResource(R.drawable.jane_cooper) ?: "",
                countryId = 1,
                languageId = 2,
                phoneNumber = "",
            ),
            Lead(
                id = 234235,
                firstName = "Albert",
                lastName = "Flores",
                leadIntentionId = 2,
                adSource = getURLForResource(R.drawable.albert_flores) ?: "",
                countryId = 2,
                languageId = 3,
                phoneNumber = "",
            ),
            Lead(
                id = 234233,
                firstName = "Jerome",
                lastName = "Bell",
                leadIntentionId = 3,
                adSource = getURLForResource(R.drawable.jerome_bell) ?: "",
                countryId = 3,
                languageId = 4,
                phoneNumber = "",
            ),
            Lead(
                id = 234234,
                firstName = "Guy",
                lastName = "Hawkins",
                leadIntentionId = 4,
                adSource = getURLForResource(R.drawable.guy_awkins) ?: "",
                countryId = 3,
                languageId = 5,
                phoneNumber = "",
            ),
            Lead(
                id = 234235,
                firstName = "Annette",
                lastName = "Black",
                leadIntentionId = 11,
                adSource = getURLForResource(R.drawable.annette_black) ?: "",
                countryId = 5,
                languageId = 6,
                phoneNumber = "",
            ),
            Lead(
                id = 234236,
                firstName = "Courtney",
                lastName = "Henry",
                leadIntentionId = 6,
                adSource = getURLForResource(R.drawable.courtney_henry) ?: "",
                countryId = 6,
                languageId = 7,
                phoneNumber = "",
            ),
            Lead(
                id = 234237,
                firstName = "Kristin",
                lastName = "Watson",
                leadIntentionId = 3,
                adSource = getURLForResource(R.drawable.kristin_watson) ?: "",
                countryId = 7,
                languageId = 8,
                phoneNumber = "",
            ),
            Lead(
                id = 234238,
                firstName = "Savannah",
                lastName = "Nguyen",
                leadIntentionId = 4,
                adSource = getURLForResource(R.drawable.savannah_nguyen) ?: "",
                countryId = 8,
                languageId = 9,
                phoneNumber = "",
            ),
            Lead(
                id = 234239,
                firstName = "Cameron",
                lastName = "Williamson",
                leadIntentionId = 6,
                adSource = getURLForResource(R.drawable.cameron_williamson) ?: "",
                countryId = 8,
                languageId = 2,
                phoneNumber = "",
            )
        )
    }

    private fun getIntentions(): List<Intention> {

        return listOf(
            Intention(
                id = 1,
                name = "New",
                textColor = Color.parseColor("#276EF1"),
                backgroundColor = Color.parseColor("#EEF3FE"),
                statusCount = 3,
                statusColor = Color.parseColor("#42A5F5"),
                statusPercent = 35
            ),
            Intention(
                id = 11, name = "No answer",
                textColor = Color.parseColor("#545454"),
                backgroundColor = Color.parseColor("#F6F6F6"),
                statusCount = 5,
                statusColor = Color.parseColor("#545454"),
                statusPercent = 54
            ),
            Intention(
                id = 2, name = "Unsuccessful",
                textColor = Color.parseColor("#545454"),
                backgroundColor = Color.parseColor("#F6F6F6"),
                statusCount = 5,
                statusColor = Color.parseColor("#BDBDBD"),
                statusPercent = 54
            ),
            Intention(
                id = 3, name = "Junk",
                textColor = Color.parseColor("#D44333"),
                backgroundColor = Color.parseColor("#FDF0EF"),
                statusCount = 2,
                statusColor = Color.parseColor("#EF5350"),
                statusPercent = 22
            ),
            Intention(
                id = 12, name = "Warm",
                textColor = Color.parseColor("#D4E157"),
                backgroundColor = Color.parseColor("#D4E157"),
                statusCount = 8,
                statusColor = Color.parseColor("#D4E157"),
                statusPercent = 80
            ),
            Intention(
                id = 13, name = "Hot",
                textColor = Color.parseColor("#AB47BC"),
                backgroundColor = Color.parseColor("#AB47BC"),
                statusCount = 8,
                statusColor = Color.parseColor("#AB47BC"),
                statusPercent = 80
            ),
            Intention(
                id = 4, name = "Customer",
                textColor = Color.parseColor("#3AA76D"),
                backgroundColor = Color.parseColor("#F0F9F4"),
                statusCount = 8,
                statusColor = Color.parseColor("#9CCC65"),
                statusPercent = 80
            ),
            Intention(
                id = 6, name = "Option Sent",
                textColor = Color.parseColor("#ED6E33"),
                backgroundColor = Color.parseColor("#FEF3EF"),
                statusCount = 4,
                statusColor = Color.parseColor("#FFB74D"),
                statusPercent = 35
            )
        )
    }

    private fun getCountry(): List<Country> {
        return listOf(
            Country(
                id = 1,
                name = "Afghanistan",
                code = "AF",
                flag = getURLForResource(R.drawable.af) ?: ""
            ),
            Country(
                id = 2,
                name = "United Arab Emirates",
                code = "AE",
                flag = getURLForResource(R.drawable.ae) ?: ""
            ),
            Country(
                id = 3,
                name = "Uzbekistan",
                code = "UZ",
                flag = getURLForResource(R.drawable.uz) ?: ""
            ),
            Country(
                id = 4,
                name = "United States",
                code = "US",
                flag = getURLForResource(R.drawable.us) ?: ""
            ),
            Country(
                id = 5,
                name = "Ukraine",
                code = "UA",
                flag = getURLForResource(R.drawable.ua) ?: ""
            ),
            Country(
                id = 6,
                name = "Italy",
                code = "IT",
                flag = getURLForResource(R.drawable.it) ?: ""
            ),
            Country(
                id = 7,
                name = "Spain",
                code = "ES",
                flag = getURLForResource(R.drawable.es) ?: ""
            ),
            Country(
                id = 8,
                name = "Kazakhstan",
                code = "KZ",
                flag = getURLForResource(R.drawable.kz) ?: ""
            )
        )
    }

    private fun getLanguage(): List<Language> {
         return listOf(
            Language(
                id = 1,
                name = "English"
            ),
            Language(
                id = 2,
                name = "Afghan"
            ),
            Language(
                id = 3,
                name = "Uzbek"
            ),
            Language(
                id = 4,
                name = "Arabic"
            ),
            Language(
                id = 5,
                name = "Armenian"
            ),
            Language(
                id = 6,
                name = "Spanish"
            ),
            Language(
                id = 7,
                name = "Esperanto"
            ),
            Language(
                id = 7,
                name = "Ukrainian"
            ),
            Language(
                id = 7,
                name = "Kazakh"
            )
        )
    }

    private fun getURLForResource(resourceId: Int): String? {
        return Uri.parse(
            "android.resource://" + R::class.java.getPackage().name + "/" + resourceId
        ).toString()
    }
}