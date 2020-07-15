package com.th3pl4gu3.locky_offline.ui.main.add.device

import android.app.Application
import androidx.databinding.Bindable
import com.th3pl4gu3.locky_offline.BR
import com.th3pl4gu3.locky_offline.R
import com.th3pl4gu3.locky_offline.ui.main.utils.ObservableViewModel
import com.th3pl4gu3.locky_offline.ui.main.utils.extensions.resources

class LogoCustomizationBottomSheetViewModel(application: Application) :
    ObservableViewModel(application) {

    private var _accent: String = ""
    private var _icon: String = ""
    private val _deviceIcons = setOf(
        R.drawable.ic_account,
        R.drawable.ic_credit_card,
        R.drawable.ic_bank,
        R.drawable.ic_device
    )

    /* Observations */
    var accent: String
        @Bindable get() {
            return _accent
        }
        set(value) {
            _accent = value
            notifyPropertyChanged(BR.accent)
        }

    var icon: String
        @Bindable get() {
            return _icon
        }
        set(value) {
            _icon = value
            notifyPropertyChanged(BR.icon)
        }

    /*
    * Accessible Functions
    */
    internal fun getAvailableHexColors() =
        (resources.getStringArray(R.array.hex_accent_logo)).asList()

    internal fun getAvailableIcons() = ArrayList<String>().apply {
        _deviceIcons.forEach { resId ->
            add(resources.getResourceEntryName(resId))
            sortBy { it }
        }
    }
}