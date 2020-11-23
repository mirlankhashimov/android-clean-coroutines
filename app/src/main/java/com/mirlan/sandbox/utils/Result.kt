package com.mirlan.sandbox.utils

import android.content.Context
import android.text.Layout
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.view.children
import com.mirlan.sandbox.R

class Result @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    fun setResult(result: Result<Any>) {
        children.forEach(View::hide)
        val id = getId(result)
        findViewById<View?>(id)?.show()?.run { return }
        val newView = createView(result)
        newView.id = id
        val match = ViewGroup.LayoutParams.MATCH_PARENT
        val params = LayoutParams(match, match)
        addView(newView, params)
    }
    private fun getId(result: Result<Any>): Int {
        when (result) {
            is Result.Success -> children.first().id
            is Result.Error -> R.id.error
            is Result.Loading -> R.id.loading
            is Result.Empty -> R.id.empty }
    }
    private fun createView(result: Result<Any>): View = when (result) {
        is Result.Success -> View(context)
        is Result.Error -> inflate(R.layout.layout_error)
        is Result.Loading -> Loading(context)//custom  view
        is Result.Empty -> Empty(context)}//custonm view }

    override fun onFinishInflate() {
        super.onFinishInflate()
        setResult(if (isInEditMode) Result.Success(Unit) else Result.Loading)
    }
}