package com.coopersimpson.pokedex.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp

@Composable
fun PokeTile() {
    Text(
        text = "POKEMON",
        style = TextStyle(fontSize = 32.sp),
        color = Color.Red,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}
