package com.example.dashboard.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ScoreLogicScreen() {
    Box(modifier = Modifier.fillMaxSize().background(color = Color.LightGray)) {
        Text(text = "ScoreLogic", modifier = Modifier.align(Alignment.Center))
    }
}