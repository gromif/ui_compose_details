package com.nevidimka655.compose_details.model

import com.nevidimka655.ui.compose_core.wrappers.TextWrap

data class DetailsGroup(
    val title: TextWrap,
    val detailsItemList: List<DetailsItem>,
)