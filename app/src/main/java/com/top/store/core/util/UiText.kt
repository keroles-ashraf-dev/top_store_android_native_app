package com.top.store.core.util

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

sealed class UiText {
    data class DynamicStr(val value: String) : UiText()
    class ResourceStr(@StringRes val id: Int, val args: Array<Any> = emptyArray()) : UiText()

    @Composable
    fun asString(): String {
        return when (this) {
            is DynamicStr -> value
            is ResourceStr -> stringResource(id = id, args)
        }
    }
}