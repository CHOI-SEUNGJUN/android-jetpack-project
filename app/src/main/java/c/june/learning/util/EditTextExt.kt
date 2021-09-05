package c.june.learning.util

import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.EditText

fun EditText.setOnKeyDownListener(onKeyboardDown: () -> Unit) {
    this.setOnKeyListener { _, keyCode, event ->
        if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
            onKeyboardDown()
            true
        } else {
            false
        }
    }
}

fun EditText.setOnEditorGoActionListener(onActionGo: () -> Unit) {
    this.setOnEditorActionListener { _, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_GO) {
            onActionGo()
            true
        } else {
            false
        }
    }
}

fun EditText.setOnEditCompleteListener(onAction: () -> Unit) {
    this.setOnKeyDownListener { onAction() }
    this.setOnEditorGoActionListener { onAction() }
}