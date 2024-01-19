package uz.domain.evaluationassignment.di


import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import uz.domain.evaluationassignment.ui.StartingDataViewModel
import uz.domain.evaluationassignment.ui.screens.leads.LeadsViewModel
import uz.domain.evaluationassignment.ui.screens.leads.detail.LeadDetailViewModel

val appModule = module {

    viewModel<LeadsViewModel> {
        LeadsViewModel(
            getLeadsListUseCase = get(),
            addLeadUseCase = get(),
            getAllIntentionsUseCase = get(),
            getAllCountrysUseCase = get(),
        )
    }

    viewModel<StartingDataViewModel> {
        StartingDataViewModel(
            addLeadsUseCase = get(),
            addIntentionsUseCase = get(),
            addCountrysUseCase = get(),
            addLanguageUseCase = get()
        )
    }

    viewModel<LeadDetailViewModel> {
        LeadDetailViewModel(
            getLeadByIdUseCase = get(),
            getIntentionByIdUseCase = get(),
            getLanguageByIdUseCase = get(),
            getCountryByIdUseCase = get(),
            getAllIntentionsUseCase = get()
        )
    }


}