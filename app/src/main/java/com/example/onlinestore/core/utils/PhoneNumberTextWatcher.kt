package com.example.onlinestore.core.utils

import android.annotation.SuppressLint
import android.text.Editable
import android.text.Spannable
import android.text.TextWatcher
import android.view.KeyEvent
import android.widget.EditText

class PhoneNumberTextWatcher(private var editText: EditText) : TextWatcher {

    private var isPlusSevenAdded = false

    @SuppressLint("SetTextI18n")
    override fun onTextChanged(userInput: CharSequence?, start: Int, before: Int, count: Int) {
        if (userInput?.isEmpty() == true) isPlusSevenAdded = false
        if (!isPlusSevenAdded) {
            if (userInput?.isNotEmpty() == true && userInput[0] != '7') {
                isPlusSevenAdded = true
                editText.setText("+7$userInput")
                editText.setSelection(editText.text?.length ?: 0)
                return
            } else if (userInput?.isNotEmpty() == true && userInput[0] == '7') {
                isPlusSevenAdded = true
                editText.setText("+7")
                editText.setSelection(editText.text?.length ?: 0)
            }
        }
    }

    override fun afterTextChanged(editable: Editable?) {
        editable?.let { formatPhoneNumber(it) }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    private fun formatPhoneNumber(editable: Editable) {
        val paddingPx = 14
        val textLength = editable.length
        val spans = editable.getSpans(
            0, editable.length,
            PaddingRightSpan::class.java
        )
        for (i in spans.indices) {
            editable.removeSpan(spans[i])
        }
        for (i in 0 until textLength) {
            /** "X XXX XXX XX XX" */
            if (i in arrayOf(1, 4, 7, 9)) {
                val marginSPan = PaddingRightSpan(paddingPx)
                editable.setSpan(marginSPan, i, i + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
        }
    }

    init {
        editText.setOnKeyListener { _, keyCode, _ ->
            if (keyCode == KeyEvent.KEYCODE_DEL) {
                editText.text?.let {
                    if (it.length < 2) {
                        it.clear()
                    }
                }
            }
            false
        }
    }
}