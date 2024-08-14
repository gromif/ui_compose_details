package com.nevidimka655.compose_details.entities

import com.nevidimka655.ui.compose_core.wrappers.TextWrap

data class DetailsGroup(
    val name: TextWrap,
    val items: List<DetailsItem>
)
