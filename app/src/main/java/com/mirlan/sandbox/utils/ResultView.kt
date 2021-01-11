package com.mirlan.sandbox.utils

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.view.children
import com.mirlan.sandbox.R
import com.mirlan.sandbox.utils.custom_network.EmptyView
import com.mirlan.sandbox.utils.custom_network.LoadingView

class ResultView @JvmOverloads constructor(
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

    private fun getId(result: Result<Any>) =
        when (result) {
            is Result.Success -> children.first().id
            is Result.Error -> R.id.error
            is Result.Loading -> R.id.loading
            is Result.Empty -> R.id.empty
        }

    private fun createView(result: Result<Any>): View = when (result) {
        is Result.Success -> View(context)
        is Result.Error -> inflate(R.layout.layout_error)
        is Result.Loading -> LoadingView(context)
        is Result.Empty -> EmptyView(context)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        setResult(if (isInEditMode) Result.Success(Unit) else Result.Loading)
    }
}