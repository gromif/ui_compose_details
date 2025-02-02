package com.nevidimka655.compose_details

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.graphics.vector.ImageVector
import com.nevidimka655.compose_details.model.DetailsGroup
import com.nevidimka655.compose_details.model.DetailsItem
import com.nevidimka655.ui.compose_core.wrappers.TextWrap

fun mutableDetailsStateList() = mutableStateListOf<DetailsGroup>()

inline fun SnapshotStateList<DetailsGroup>.addGroup(
    name: TextWrap,
    groupBuilder: MutableList<DetailsItem>.() -> Unit
) {
    val list = mutableListOf<DetailsItem>().apply { groupBuilder(this) }.toList()
    add(DetailsGroup(name, list))
}

fun MutableList<DetailsItem>.addItem(
    icon: ImageVector,
    title: TextWrap,
    summary: TextWrap
) = add(DetailsItem(icon = icon, title = title, summary = summary))