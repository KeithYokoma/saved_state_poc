package com.github.keithyokoma.poc.fatbundle.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    HomeScreen(
        state = viewModel.states.collectAsState(),
        onClickAddButton = { viewModel.addItem() }
    )
}

@Composable
fun HomeScreen(
    state: State<HomeViewState>,
    onClickAddButton: () -> Unit = {},
) {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (columnRef, addButtonRef) = createRefs()

        LazyColumn(
            modifier = Modifier.constrainAs(columnRef) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)

                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            }
        ) {
            items(state.value.items) { item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        modifier = Modifier.size(48.dp),
                        painter = rememberAsyncImagePainter(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(item.imageUrl)
                                .allowRgb565(true)
                                .crossfade(true)
                                .diskCachePolicy(CachePolicy.ENABLED)
                                .build(),
                        ),
                        contentDescription = null,
                    )

                    Spacer(modifier = Modifier.size(16.dp))

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        text = "${item.id}: ${item.name} - ${item.age}"
                    )
                }
            }
        }

        FloatingActionButton(
            modifier = Modifier.constrainAs(addButtonRef) {
                bottom.linkTo(parent.bottom, 16.dp)
                end.linkTo(parent.end, 16.dp)
            },
            onClick = onClickAddButton,
        ) {
            Icon(
                painter = rememberVectorPainter(image = Icons.Default.Add),
                contentDescription = null,
            )
        }
    }
}