package c.june.learning.adapters

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.databinding.BindingAdapter

@SuppressLint("SetTextI18n")
@BindingAdapter("app:setLanguageText")
fun setLanguageText(textView: TextView, language: String?) {
    if (!language.isNullOrEmpty()) {
        textView.text = "Language: $language"
    }
}