package com.mirlan.sandbox.presentation.bottom_sheet

import com.mirlan.sandbox.core.BaseViewModel

class FullScreenDialogViewModel : BaseViewModel() {
    fun back() = router.backTo(screens.bottomDialog())
}