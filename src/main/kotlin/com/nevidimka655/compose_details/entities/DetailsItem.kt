package com.nevidimka655.compose_details.entities

import com.nevidimka655.ui.compose_core.wrappers.IconWrap
import com.nevidimka655.ui.compose_core.wrappers.TextWrap

data class DetailsItem(
    val icon: IconWrap,
    val title: TextWrap,
    val summary: TextWrap
)