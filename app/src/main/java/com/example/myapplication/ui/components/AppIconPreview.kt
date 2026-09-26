package com.example.myapplication.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R

@Composable
fun AppIconDisplay(modifier: Modifier = Modifier, shape: Shape = RoundedCornerShape(22.dp)) {
    Box(
        modifier = modifier
            .size(120.dp)
            .clip(shape)
    ) {
        // Background
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
        // Foreground
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "MyMerito App Icon",
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0B132B)
@Composable
fun AppIconPreview() {
    Column(
        modifier = Modifier
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "MyMerito App Icon",
            color = Color.White,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Academic Icon with Graduation Cap, Letter M, Navy Blue, Cyan Teal & Gold",
            color = Color(0xFF88A0C0),
            fontSize = 12.sp,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                AppIconDisplay(shape = RoundedCornerShape(26.dp))
                Spacer(modifier = Modifier.height(8.dp))
                Text("Squircle (120dp)", color = Color.White, fontSize = 12.sp)
            }

            Spacer(modifier = Modifier.width(24.dp))

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                AppIconDisplay(shape = CircleShape)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Circle (120dp)", color = Color.White, fontSize = 12.sp)
            }
        }
    }
}
