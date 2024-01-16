package uz.domain.evaluationassignment.ui.leads

import android.graphics.Color
import androidx.lifecycle.ViewModel
import uz.domain.evaluationassignment.R
import uz.domain.evaluationassignment.models.Lead
import uz.domain.evaluationassignment.models.Status

class LeadsViewModel : ViewModel() {

    fun getLeads(): List<Lead> {
        return listOf(
            Lead(
                id = 1,
                fullName = "Jane Cooper",
                flag = R.drawable.af,
                status = Status(
                    id = 1,
                    name = "New",
                    textColor = Color.parseColor("#276EF1"),
                    backgroundColor = Color.parseColor("#EEF3FE")
                ),
                avatar = R.drawable.jane_cooper
            ),
            Lead(
                id = 2,
                fullName = "Albert Flores",
                flag = R.drawable.ae,
                status = Status(
                    id = 2, name = "Unsuccessful",
                    textColor = Color.parseColor("#545454"),
                    backgroundColor = Color.parseColor("#F6F6F6")
                ),
                avatar = R.drawable.albert_flores
            ),
            Lead(
                id = 3,
                fullName = "Jerome Bell",
                flag = R.drawable.af,
                status = Status(
                    id = 3, name = "Junk",
                    textColor = Color.parseColor("#D44333"),
                    backgroundColor = Color.parseColor("#FDF0EF")
                ),
                avatar = R.drawable.jerome_bell
            ),
            Lead(
                id = 4,
                fullName = "Guy Hawkins",
                flag = R.drawable.uz,
                status = Status(
                    id = 4, name = "Customer",
                    textColor = Color.parseColor("#3AA76D"),
                    backgroundColor = Color.parseColor("#F0F9F4")
                ),
                avatar = R.drawable.guy_awkins
            ),
            Lead(
                id = 5,
                fullName = "Annette Black",
                flag = R.drawable.ua,
                status = Status(
                    id = 5, name = "No Answer",
                    textColor = Color.parseColor("#545454"),
                    backgroundColor = Color.parseColor("#F6F6F6")
                ),
                avatar = R.drawable.annette_black
            ),
            Lead(
                id = 6,
                fullName = "Courtney Henry",
                flag = R.drawable.us,
                status = Status(
                    id = 6, name = "Option Sent",
                    textColor = Color.parseColor("#ED6E33"),
                    backgroundColor = Color.parseColor("#FEF3EF")
                ),
                avatar = R.drawable.courtney_henry
            ),
            Lead(
                id = 7,
                fullName = "Kristin Watson",
                flag = R.drawable.es,
                status = Status(
                    id = 3, name = "Junk",
                    textColor = Color.parseColor("#D44333"),
                    backgroundColor = Color.parseColor("#FDF0EF")
                ),
                avatar = R.drawable.kristin_watson
            ),
            Lead(
                id = 8,
                fullName = "Savannah Nguyen",
                flag = R.drawable.it,
                status = Status(
                    id = 4, name = "Customer",
                    textColor = Color.parseColor("#3AA76D"),
                    backgroundColor = Color.parseColor("#F0F9F4")
                ),
                avatar = R.drawable.savannah_nguyen
            ),
            Lead(
                id = 9,
                fullName = "Cameron Williamson",
                flag = R.drawable.kz,
                status = Status(
                    id = 6, name = "Option Sent",
                    textColor = Color.parseColor("#ED6E33"),
                    backgroundColor = Color.parseColor("#FEF3EF")
                ),
                avatar = R.drawable.cameron_williamson
            )
        )
    }


}