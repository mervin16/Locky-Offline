package com.th3pl4gu3.locky_offline.ui.main.main.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.th3pl4gu3.locky_offline.repository.database.repositories.AccountRepository
import com.th3pl4gu3.locky_offline.repository.database.repositories.BankAccountRepository
import com.th3pl4gu3.locky_offline.repository.database.repositories.CardRepository
import com.th3pl4gu3.locky_offline.ui.main.utils.Constants.KEY_USER_ACCOUNT
import com.th3pl4gu3.locky_offline.ui.main.utils.LocalStorageManager
import com.th3pl4gu3.locky_offline.ui.main.utils.extensions.activeUser

class ProfileViewModel(application: Application) : AndroidViewModel(application) {

    private val _signUserOut = MutableLiveData(false)

    val signUserOut: LiveData<Boolean>
        get() = _signUserOut

    val accountSize =
        Transformations.map(
            AccountRepository.getInstance(getApplication()).size(activeUser.email)
        ) {
            it.toString()
        }

    val cardSize =
        Transformations.map(CardRepository.getInstance(getApplication()).size(activeUser.email)) {
            it.toString()
        }

    val bankAccountSize =
        Transformations.map(
            BankAccountRepository.getInstance(getApplication()).size(activeUser.email)
        ) {
            it.toString()
        }

    fun removeUserSession() {
        /*
        * Removes the user session
        */
        LocalStorageManager.withLogin(getApplication())
        LocalStorageManager.remove(KEY_USER_ACCOUNT)

        /* Set value to true to sign user out from firebase*/
        _signUserOut.value = true
    }
}