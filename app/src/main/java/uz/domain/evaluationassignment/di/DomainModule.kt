package uz.domain.evaluationassignment.di

import org.koin.dsl.module
import uz.domain.repository.CountryRepositoryImpl
import uz.domain.repository.ICountryRepository
import uz.domain.repository.IIntentionRepository
import uz.domain.repository.ILanguageRepository
import uz.domain.repository.ILeadRepository
import uz.domain.repository.IntentionRepositoryImpl
import uz.domain.repository.LanguageRepositoryImpl
import uz.domain.repository.LeadRepositoryImpl
import uz.domain.usecase.country.AddCountrysUseCase
import uz.domain.usecase.country.GetCountryByIdUseCase
import uz.domain.usecase.intention.AddIntentionsUseCase
import uz.domain.usecase.intention.GetIntentionByIdUseCase
import uz.domain.usecase.language.AddLanguageUseCase
import uz.domain.usecase.language.GetLanguageByIdUseCase
import uz.domain.usecase.lead.AddLeadUseCase
import uz.domain.usecase.lead.AddLeadsUseCase
import uz.domain.usecase.lead.GetLeadByIdUseCase
import uz.domain.usecase.lead.GetLeadsListUseCase

val domainModule = module {

    factory<GetLeadsListUseCase> {
        GetLeadsListUseCase(repository = get())
    }

    factory<GetLeadByIdUseCase> {
        GetLeadByIdUseCase(repository = get())
    }

    factory<AddLeadUseCase> {
        AddLeadUseCase(repository = get())
    }

    factory<AddLeadsUseCase> {
        AddLeadsUseCase(repository = get())
    }

    factory<ILeadRepository> {
        LeadRepositoryImpl(leadsDao = get())
    }

    //--------------------------------------------------------------------------------------

    factory<AddIntentionsUseCase> {
        AddIntentionsUseCase(repository = get())
    }

    factory<GetIntentionByIdUseCase> {
        GetIntentionByIdUseCase(repository = get())
    }

    factory<IIntentionRepository> {
        IntentionRepositoryImpl(dao = get())
    }

    //--------------------------------------------------------------------------------------

    factory<AddCountrysUseCase> {
        AddCountrysUseCase(repository = get())
    }

    factory<GetCountryByIdUseCase> {
        GetCountryByIdUseCase(repository = get())
    }

    factory<ICountryRepository> {
        CountryRepositoryImpl(dao = get())
    }

    //--------------------------------------------------------------------------------------

    factory<AddLanguageUseCase> {
        AddLanguageUseCase(repository = get())
    }

    factory<GetLanguageByIdUseCase> {
        GetLanguageByIdUseCase(repository = get())
    }

    factory<ILanguageRepository> {
        LanguageRepositoryImpl(dao = get())
    }
}