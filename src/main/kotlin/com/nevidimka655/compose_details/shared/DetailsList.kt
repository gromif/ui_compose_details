package com.nevidimka655.compose_details.shared

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import com.nevidimka655.compose_details.model.DetailsItem
import com.nevidimka655.ui.compose_core.ColoredIconSecondary
import com.nevidimka655.ui.compose_core.TwoLineListItem

@Composable
internal fun DetailsList(items: List<DetailsItem>) {
    val context = LocalContext.current
    items.forEach {
        DetailsListItem(
            icon = it.icon,
            title = it.title.resolve(context),
            summary = it.summary.resolve(context)
        )
    }
}

@Composable
private fun DetailsListItem(
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