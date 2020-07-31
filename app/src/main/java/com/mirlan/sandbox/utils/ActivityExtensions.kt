
package com.mirlan.sandbox.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

/** apply title to the toolbar simply. */
fun AppCompatActivity.simpleToolbarWithHome(toolbar: Toolbar, title_: String = "") {
  setSupportActionBar(toolbar)
  supportActionBar?.run {
    setDisplayHomeAsUpEnabled(true)
   // setHomeAsUpIndicator(R.drawable.ic_arrow_back)
    title = title_
  }
}
