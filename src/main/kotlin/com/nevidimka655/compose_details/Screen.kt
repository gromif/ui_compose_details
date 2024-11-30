package com.nevidimka655.compose_details

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.nevidimka655.compose_details.entities.DetailsGroup
import com.nevidimka655.compose_details.entities.DetailsItem
import com.nevidimka655.ui.compose_core.ColoredIconSecondary
import com.nevidimka655.ui.compose_core.TwoLineListItem
import com.nevidimka655.ui.compose_core.TwoPanelLayoutInnerScroll
import com.nevidimka655.ui.compose_core.chips.Chips
import com.nevidimka655.ui.compose_core.chips.Filter
import com.nevidimka655.ui.compose_core.ext.LocalWindowHeight
import com.nevidimka655.ui.compose_core.ext.isCompact
import com.nevidimka655.ui.compose_core.ext.isMedium
import com.nevidimka655.ui.compose_core.theme.spaces

@Preview(showBackground = true)
@Composable
fun Details.Screen(
    detailsManager: DetailsManager = DetailsManager(),
    headerImage: @Composable BoxScope.() -> Unit = {},
    showGroups: Boolean = true
) {
    val context = LocalContext.current
    val list = detailsManager.list
    if (list.isEmpty()) return
    val selectedGroup = detailsManager.selectedGroup
    TwoPanelLayoutInnerScroll(
        padding = 0.dp,
        left = {
            Header(
                headerImage = headerImage,
                title = detailsManager.title
            )
            if (showGroups) Groups(
                context = context,
                list = list,
                selectedGroup = selectedGroup ?: list[0],
                onGroupSelected = {
                    detailsManager.selectedGroup = if (it == list[0]) null else it
                }
            )
        }, right = {
            ListGroupItems(context = context, items = (selectedGroup ?: list[0]).items)
        })
}

@Composable
private fun ListGroupItems(context: Context, items: List<DetailsItem>) = items.forEach {
    Item(
        icon = it.icon.imageVector,
        title = it.title.resolve(context),
        summary = it.summary.resolve(context)
    )
}

@Composable
private fun Item(
    icon: ImageVector?,
    title: String,
    summary: String
) {
    TwoLineListItem(
        leadingContent = icon?.let {
            @Composable { ColoredIconSecondary(imageVector = icon) }
        },
        titleText = title,
        summaryText = summary
    )
}

@Composable
private fun Header(
    headerImage: @Composable BoxScope.() -> Unit = {},
    title: String = "TEST_TITLE_JDFJFKJFKDJFJF#$(994039409"
) {
    val height = LocalWindowHeight.current
    val adaptiveImageWidth = remember {
        when {
            height.isCompact -> 180.dp
            height.isMedium -> 280.dp
            else -> 320.dp
        }
    }

    @Composable
    fun content() {
        Surface(modifier = Modifier.width(adaptiveImageWidth), shape = MaterialTheme.shapes.large) {
            Box(modifier = Modifier.aspectRatio(1f), contentAlignment = Alignment.Center) {
                headerImage()
            }
        }
    }

    if (height.isCompact) Row(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surfaceVariant)
            .padding(MaterialTheme.spaces.spaceMedium),
    ) {
        content()
        HeaderTitle(title = title, padding = 10.dp)
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.surfaceVariant)
                .padding(MaterialTheme.spaces.spaceMedium),
            contentAlignment = Alignment.Center
        ) {
            content()
        }
        HeaderTitle(title = title)
    }
}

@Composable
private fun HeaderTitle(title: String, padding: Dp = 30.dp) = Text(
    modifier = Modifier.padding(padding),
    text = title,
    style = MaterialTheme.typography.titleMedium
)

@Composable
private fun Groups(
    context: Context,
    list: SnapshotStateList<DetailsGroup>,
    selectedGroup: DetailsGroup,
    onGroupSelected: (DetailsGroup) -> Unit
) = Row(
    modifier = Modifier
        .horizontalScroll(rememberScrollState())
        .padding(horizontal = MaterialTheme.spaces.spaceMedium),
    horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spaces.spaceMedium)
) {
    list.forEach {
        Chips.Filter(
            selected = it == selectedGroup,
            onClick = { onGroupSelected(it) },
            label = it.name.resolve(context)
        )
    }
}