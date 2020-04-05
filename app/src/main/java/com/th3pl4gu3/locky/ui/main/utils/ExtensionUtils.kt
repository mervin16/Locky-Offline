package com.th3pl4gu3.locky.ui.main.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.Window
import android.widget.PopupMenu
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.th3pl4gu3.locky.R
import com.th3pl4gu3.locky.core.Card
import com.th3pl4gu3.locky.ui.main.utils.Constants.Companion.REGEX_CREDIT_CARD_AMEX
import com.th3pl4gu3.locky.ui.main.utils.Constants.Companion.REGEX_CREDIT_CARD_DINNERSCLUB
import com.th3pl4gu3.locky.ui.main.utils.Constants.Companion.REGEX_CREDIT_CARD_DISCOVER
import com.th3pl4gu3.locky.ui.main.utils.Constants.Companion.REGEX_CREDIT_CARD_JCB
import com.th3pl4gu3.locky.ui.main.utils.Constants.Companion.REGEX_CREDIT_CARD_MASTERCARD
import com.th3pl4gu3.locky.ui.main.utils.Constants.Companion.REGEX_CREDIT_CARD_VISA

//TODO: Need to test all functions properly in ExtensionUtils.kt

fun Context.toast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) = Toast.makeText(this, text, duration).show()

fun Window.activateLightStatusBar(view: View) {
    var flags: Int = view.systemUiVisibility
    flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    view.systemUiVisibility = flags
    this.statusBarColor = Color.WHITE
}

fun Window.activateDarkStatusBar() {
    this.statusBarColor = context.getColor(R.color.colorPrimary)
}

fun Long.getCardType(): Card.CardType{

    val number = this.toString().replace(",", "")

    return when {
        Regex(pattern = REGEX_CREDIT_CARD_VISA).containsMatchIn(number) -> {
            Card.CardType.VISA
        }
        Regex(pattern = REGEX_CREDIT_CARD_MASTERCARD).containsMatchIn(number)  -> {
            Card.CardType.MASTERCARD
        }
        Regex(pattern = REGEX_CREDIT_CARD_AMEX).containsMatchIn(number)  -> {
            Card.CardType.AMERICAN_EXPRESS
        }
        Regex(pattern = REGEX_CREDIT_CARD_DINNERSCLUB).containsMatchIn(number)  -> {
            Card.CardType.DINNERS_CLUB
        }
        Regex(pattern = REGEX_CREDIT_CARD_DISCOVER).containsMatchIn(number)  -> {
            Card.CardType.DISCOVER
        }
        Regex(pattern = REGEX_CREDIT_CARD_JCB).containsMatchIn(number)  -> {
            Card.CardType.JCB
        }
        else -> {
            Card.CardType.DEFAULT
        }
    }
}

fun Long.toCreditCardFormat(): String{
    val number = this.toString().replace(",", "").trim()
    val result = StringBuilder()

    number.indices.forEach { i ->
        if (i % 4 == 0 && i != 0) {
            result.append(" ")
        }
        result.append(number[i])
    }

    return result.toString()
}

inline fun View.snackbar(message: String, length: Int = Snackbar.LENGTH_INDEFINITE, f: Snackbar.() -> Unit) {
    val snack = Snackbar.make(this, message, length)
    snack.f()
    snack.show()
}

fun Snackbar.action(action: String, listener: (View) -> Unit) {
    setAction(action, listener)
}

fun Context.copyToClipboard(data: String)
        = (getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager).setPrimaryClip(ClipData.newPlainText("text", data))

fun Context.createPopUpMenu(view: View, menu: Int, listener: PopupMenu.OnMenuItemClickListener){
    val popup = PopupMenu(this, view)
    //inflating menu from xml resource
    popup.inflate(menu)
    //adding click listener
    popup.setOnMenuItemClickListener(listener)
    popup.show()
}