package com.nevidimka655.compose_details

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nevidimka655.compose_details.model.DetailsGroup
import com.nevidimka655.compose_details.shared.DetailsList
import com.nevidimka655.compose_details.shared.FakeData
import com.nevidimka655.compose_details.shared.GroupList
import com.nevidimka655.compose_details.shared.Header
import com.nevidimka655.compose_details.shared.groups
import com.nevidimka655.ui.compose_core.TwoPanelLayoutInnerScroll

@Composable
fun DetailsScreen(
    groups: SnapshotStateList<DetailsGroup>,
    headerImage: @Composable BoxScope.() -> Unit,
    title: String,
    showGroups: Boolean = true,
) {
    var selectedGroup by remember { mutableIntStateOf(0) }
    TwoPanelLayoutInnerScroll(
        padding = 0.dp,
        left = {
            Header(
                headerImage = headerImage,
                title = title
            )
            if (showGroups) GroupList(
                groups = groups,
                selectedGroup = selectedGroup,
                onGroupSelected = { selectedGroup = it }
            )
        }, right = {
            DetailsList(items = groups[selectedGroup].detailsItemList)
        })
}

@Preview(showBackground = true)
@Composable
private fun DetailsScreenPreview(
    groups: SnapshotStateList<DetailsGroup> = mutableDetailsStateList().also {
        FakeData.groups(it)
    },
    headerImage: @Composable BoxScope.() -> Unit = {},
    title: String = "Title",
    showGroups: Boolean = true,
) = DetailsScreen(
    groups = groups,
    headerImage = headerImage,
    title = title,
    showGroups = showGroups
)