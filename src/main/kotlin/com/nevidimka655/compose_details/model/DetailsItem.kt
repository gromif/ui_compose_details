package com.nevidimka655.compose_details.model

import androidx.compose.ui.graphics.vector.ImageVector
import com.nevidimka655.ui.compose_core.wrappers.TextWrap

data class DetailsItem(
    val icon: ImageVector,
    val title: TextWrap,
    val summary: TextWrap
)