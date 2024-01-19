package uz.domain.evaluationassignment.di


import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import uz.domain.evaluationassignment.ui.StartingDataViewModel
import uz.domain.evaluationassignment.ui.screens.leads.LeadsViewModel

val appModule = module {

    viewModel<LeadsViewModel> {
        LeadsViewModel(
            getLeadsListUseCase = get(),
            getLeadByIdUseCase = get(),
            addLeadUseCase = get()
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

}