package com.example.android.kotlinmultiplatform.views.input

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.example.android.kotlinmultiplatform.R
import com.jetbrains.handson.mpp.mobile.model.InputRow
import kotlinx.android.synthetic.main.input_view.view.*
import org.koin.core.KoinComponent
import org.koin.core.inject

class PocInputView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), KoinComponent{

    private val viewModel by inject<PocInputViewModel>()

    init {
        inflate(context, R.layout.input_view, this)
    }

    fun setRow(row: InputRow) {
        viewModel.setRow(row)
        setView(row)
    }

    private fun setView(row: InputRow) {
        setHint(row.data.hint)
    }

    private fun setHint(hint : CharSequence) {
        edtInput.hint = hint
    }
}