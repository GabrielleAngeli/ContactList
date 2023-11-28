package br.edu.scl.ifsp.sdm.contactlist.model

import android.os.Parcelable
import br.edu.scl.ifsp.sdm.contactlist.model.Constant.INVALID_CONTACT
import kotlinx.parcelize.Parcelize

@Parcelize
data class Contact(
    var id: Int? = INVALID_CONTACT, //reflexão e introspecção orientação a objeto
    var name: String = "",
    var address: String = "",
    var phone: String = "",
    var email: String = ""
) : Parcelable
