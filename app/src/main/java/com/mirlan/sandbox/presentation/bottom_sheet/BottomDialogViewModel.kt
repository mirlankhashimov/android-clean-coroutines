package com.mirlan.sandbox.presentation.bottom_sheet

import com.mirlan.sandbox.core.BaseViewModel

class BottomDialogViewModel : BaseViewModel() {
    fun nav() = router.navigateTo(screens.bottomFullDialog())
}