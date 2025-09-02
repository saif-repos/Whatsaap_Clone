package com.example.whatsapp.presentation.bottomnavigation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.whatsapp.R

@Composable
fun BottomNavigation(
    navHostController: NavHostController,
    onClick: (index: Int) -> Unit,
    selectedItem: Int
) {
    val items = listOf(
        NavigationItem("Chats", R.drawable.chat_icon, R.drawable.outline_chat_24),
        NavigationItem("Updates", R.drawable.update_icon, R.drawable.update_icon),
        NavigationItem("Communities", R.drawable.baseline_groups_24, R.drawable.outline_groups_24),
        NavigationItem("Calls", R.drawable.telephone, R.drawable.outline_phone_24)
    )

    NavigationBar(
        containerColor = Color.White,
        modifier = Modifier.height(60.dp)
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItem == index,
                onClick = { onClick(index) },
                icon = {
                    Icon(
                        painter = painterResource(
                            if (selectedItem == index) item.selectedIcon else item.unSelectedIcon
                        ),
                        contentDescription = item.name,
                        modifier = Modifier.size(24.dp) // ✅ icon size fixed at 24dp
                    )
                },
                label = {
                    Text(
                        text = item.name,
                        color = if (index == selectedItem) Color.Black else Color.DarkGray,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        }
    }
}

data class NavigationItem(
    val name: String,
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unSelectedIcon: Int
)
