package com.mirlan.sandbox.utils

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.*
import android.view.animation.TranslateAnimation
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.text.HtmlCompat
import androidx.lifecycle.MutableLiveData
import com.google.android.material.appbar.AppBarLayout
import com.mirlan.sandbox.data.vo.Resource
import kz.atf24.bank.custom.clicklistener.SafeClickListener

const val DIVIDE_SCREEN_BY_HORIZONTAL_TO_THREE = 3F

@SuppressWarnings("Move to another class")
inline fun <T : Any> ifLet(vararg elements: T?, closure: (List<T>) -> Unit) {
    if (elements.all { it != null }) {
        closure(elements.filterNotNull())
    }
}

fun View.setVisibility(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun showGroupViews(vararg view: View) {
    view.forEach {
        it.show()
    }
}

fun hideGroupViews(vararg view: View) {
    view.forEach {
        it.hide()
    }
}

fun setTextGroup(vararg textViews: TextView, text: String) {
    textViews.forEach {
        it.text = text
    }
}

fun View.setHeight(height: Int) {
    layoutParams.height = height
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun View.showKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
}

/**
 * Show keyboard after hide dialog
 */
fun View.showKeyboardWithTimeOut() {
    Handler().postDelayed({
        showKeyboard()
    }, 200)
}

/**
 * Альтернатива к этой строчке кода: app:layout_behavior="@string/appbar_scrolling_view_behavior"
 * Тоесть программна откл/вкл appbar_scrolling_view_behavior
 */

fun View.setScrollingBehavior(isScrollBehavior: Boolean) {
    val params = layoutParams as CoordinatorLayout.LayoutParams

    if (isScrollBehavior) {
        params.behavior = AppBarLayout.ScrollingViewBehavior()
    } else {
        params.behavior = null
        requestLayout()
    }

}

fun View.setSafeOnClickListener(onSafeClick: (View) -> Unit) {
    val safeClickListener = SafeClickListener {
        onSafeClick(it)
    }
    setOnClickListener(safeClickListener)
}

fun View.animateView() {
    this.animate()
        .translationX(0f)
        .translationY(0f)
        .duration = 300
}

fun View.slideUp() {
    val animate = TranslateAnimation(
        0f,
        0f,
        this.height.toFloat(),
        0f
    )
    animate.duration = 300
    animate.fillAfter = true
    this.startAnimation(animate)
}


fun View.invisibleAndDisabled() {
    visibility = View.INVISIBLE
    isEnabled = false
}

fun View.visibleAndEnabled() {
    visibility = View.VISIBLE
    isEnabled = true
}

fun View.disable() {
    isEnabled = false
}

fun View.enable() {
    isEnabled = true
}

/**
 * MutableLiveData
 */
fun <T> MutableLiveData<Resource<T>>.setSuccess(data: T) =
    postValue(Resource.success(data))

fun <T> MutableLiveData<Resource<T>>.setLoading() =
    postValue(Resource.loading())

fun <T> MutableLiveData<Resource<T>>.setError(e: Throwable) =
    postValue(Resource.error(e.message, e))

fun <T> MutableLiveData<T>.call() = postValue(null)

fun View.notClickable() {
    this.isClickable = false
}

fun View.clickable() {
    this.isClickable = true
}

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View =
    LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)

fun TextView.setUrl(url: String) {
    if (!url.isBlank()) {
        this.apply {
            text = Html.fromHtml(url)
            movementMethod = LinkMovementMethod.getInstance()
        }
    }
}
fun TextView.link(title: String, url: String?) {
    if (url != null && url.isNotBlank()) {
        text = HtmlCompat.fromHtml("<a href=\"$url\">$title</a>", HtmlCompat.FROM_HTML_MODE_LEGACY)
        visibility = View.VISIBLE
        movementMethod = LinkMovementMethod.getInstance()
    }
}
fun TextView.show(content: String?) {
    if (content != null && content.isNotBlank()) {
        text = content
        visibility = View.VISIBLE
    }
}