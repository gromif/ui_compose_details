package com.nevidimka655.compose_details

import android.os.Bundle
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.nevidimka655.compose_details.entities.DetailsGroup
import com.nevidimka655.compose_details.entities.DetailsItem
import com.nevidimka655.ui.compose_core.wrappers.IconWrap
import com.nevidimka655.ui.compose_core.wrappers.TextWrap

class DetailsManager {
    val list = mutableStateListOf<DetailsGroup>()
    var title by mutableStateOf("")
    var selectedGroup by mutableStateOf<DetailsGroup?>(null)

    fun addGroup(
        name: TextWrap,
        itemsBuilder: MutableList<DetailsItem>.() -> Unit,
    ) {
        val group = DetailsGroup(
            name = name,
            items = mutableListOf<DetailsItem>().apply(itemsBuilder).toList()
        )
        list.add(group)
    }

    fun clear() {
        list.clear()
        selectedGroup = null
    }

}

fun MutableList<DetailsItem>.addItem(
    icon: IconWrap,
    title: TextWrap,
    summary: TextWrap
) = add(DetailsItem(icon = icon, title = title, summary = summary))