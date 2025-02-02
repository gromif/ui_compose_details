package com.nevidimka655.compose_details.shared

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Details
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.nevidimka655.compose_details.addGroup
import com.nevidimka655.compose_details.addItem
import com.nevidimka655.compose_details.model.DetailsGroup
import com.nevidimka655.ui.compose_core.wrappers.TextWrap

internal object FakeData

internal fun FakeData.groups(
    stateList: SnapshotStateList<DetailsGroup>
) = repeat(7) { group ->
    stateList.addGroup(name = TextWrap.Text("Group$group")) {
        repeat(5) {
            addItem(
                icon = Icons.Default.Details,
                title = TextWrap.Text("Title #$it [Group #$group]"),
                summary = TextWrap.Text("summery #$it")
            )
        }
    }
}