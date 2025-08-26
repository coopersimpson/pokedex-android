package com.coopersimpson.pokedex.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp

@Composable
fun PokeTile(name: String) {
    // TODO: use a material3 card
    Text(
        text = name,
        style = TextStyle(fontSize = 32.sp),
        color = Color.Red,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}
