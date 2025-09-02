package com.example.whatsapp.presentation.callscreen

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.whatsapp.R

@Composable
@Preview(showSystemUi = true)
fun FavroitSection() {
    val favroitcontacts = listOf(
        favroitcontact(R.drawable.mabrorcrop, "Mabroor"),
        favroitcontact(R.drawable.rahman, "Rahman"),
        favroitcontact(R.drawable.saif3, "Saif"),
    )
    Column(modifier = Modifier.padding(12.dp)) {
        Text(
            text = "Favorites",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .horizontalScroll(rememberScrollState())
        ) {
            favroitcontacts.forEach { favroitcontact ->
                FavroitItem(favroitcontact = favroitcontact)
            }
        }

    }
}

data class favroitcontact
    (
    val image: Int,
    val name: String
)
