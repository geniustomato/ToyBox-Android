package com.clooy.toybox.feature.dashboard.exhibit.ui

import android.content.res.Configuration
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.clooy.toybox.R
import com.clooy.toybox.feature.dashboard.exhibit.data.ExhibitItem
import com.clooy.toybox.feature.dashboard.exhibit.data.ExhibitName

@Composable
fun ExhibitList(
    data: List<ExhibitItem>,
    modifier: Modifier = Modifier,
    onExhibitItemClicked: (ExhibitItem) -> Unit,
) {
    LazyColumn(modifier = modifier) {
        items(data) {exhibit ->
            if (exhibit.isActive) {
                ExhibitItem(exhibitItem = exhibit, modifier = Modifier.padding(8.dp)) {
                    onExhibitItemClicked(exhibit)
                }
            }

            if (exhibit != data.last()) {
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun ExhibitItem(
    exhibitItem: ExhibitItem,
    modifier: Modifier = Modifier,
    onEnterExhibitClicked: () -> Unit,
) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .weight(1f)
            ) {
                Text(text = exhibitItem.exhibit.name, style = MaterialTheme.typography.headlineMedium)
                if (expanded) {
                    Spacer(modifier = Modifier.height(32.dp))
                    Text(text = exhibitItem.description, style = MaterialTheme.typography.bodyMedium)
                    Spacer(modifier = Modifier.height(32.dp))
                    Button(onClick = onEnterExhibitClicked) {
                        Text(text = stringResource(R.string.enter))
                    }
                }
            }
            IconButton(
                modifier = Modifier.align(alignment = Alignment.Top),
                onClick = {
                    expanded = !expanded
                }
            ) {
                Icon(
                    imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                    contentDescription = if (expanded) {
                        stringResource(R.string.show_less)
                    } else {
                        stringResource(R.string.show_more)
                    }
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    name = "Exhibit List",
)
@Preview(
    showBackground = true,
    name = "Exhibit List - Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun ExhibitsListPreview() {
    ExhibitList(
        data = listOf(
            ExhibitItem(
                exhibit = ExhibitName.ExhibitA,
                description = "Description",
                isActive = true,
            ),
            ExhibitItem(
                exhibit = ExhibitName.ExhibitB,
                description = "Description",
                isActive = true,
            ),
            ExhibitItem(
                exhibit = ExhibitName.ExhibitC,
                description = "Description",
                isActive = true,
            )
        ),
        onExhibitItemClicked = {} // Do nothing
    )
}


