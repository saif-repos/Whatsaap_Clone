package com.example.whatsapp.presentation.callscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.whatsapp.R

@Composable
fun CallItemDesign(call: Call) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(call.image),
            contentDescription = "Profile picture",
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = call.name,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_call_missed_24),
                    contentDescription = "Call status",
                    modifier = Modifier.size(16.dp),
                    tint = if (call.ismissed) androidx.compose.ui.graphics.Color.Red
                    else androidx.compose.ui.graphics.Color.Green
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = if (call.ismissed) "Missed call" else "Outgoing call",
                    fontSize = 14.sp,
                    color = if (call.ismissed) androidx.compose.ui.graphics.Color.Red
                    else androidx.compose.ui.graphics.Color.Green
                )

                // You might want to add call time/duration here
                Text(
                    text = call.time,
                    fontSize = 14.sp,
                    color = androidx.compose.ui.graphics.Color.Gray
                )
            }
        }

        // You might want to add a call type icon here (voice/video)
        Icon(
            painter = painterResource(id = R.drawable.telephone), // Replace with your icon
            contentDescription = "Call type",
            modifier = Modifier.size(24.dp)
        )
    }
}


