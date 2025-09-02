package com.example.whatsapp.presentation.updatedscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.whatsapp.R
import com.example.whatsapp.presentation.bottomnavigation.BottomNavigation
import com.example.whatsapp.presentation.navigation.Routes

@Composable

fun UpdateScreen(navHostController: NavHostController) {
    val scrollState = rememberScrollState()
    val samplestatus = listOf(
        statusData(image = R.drawable.mabrorcrop, name = "Mabroor", time = "10:00 AM"),
        statusData(image = R.drawable.saif4, name = "Saif", time = "11:00 AM"),
        statusData(image = R.drawable.rahman, name = "Rahman", time = "1:00 PM"),

        )
    val samplechannel = listOf(
        channels(image = R.drawable.meta, name = "Meta", description = "Meta description"),
        channels(
            image = R.drawable.whatsapp_icon,
            name = "WhatsApp",
            description = "WhatsApp description"
        ),
        channels(image = R.drawable.img, name = "Food lover", description = "Discover new recipes")
    )


    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    // Your action here
                }, containerColor = colorResource(R.color.light_green),
                modifier = Modifier.size(65.dp), contentColor = Color.White
            ) {
                // Add an icon or text inside the FAB
                Icon(
                    painter = painterResource(id = R.drawable.camera),
                    contentDescription = "Chat",
                    modifier = Modifier.size(28.dp) // Icon size chota kar diya (adjust as needed)

                )
            }
        }, bottomBar = {
            BottomNavigation(navHostController, selectedItem = 0, onClick = { index ->
                when (index) {
                    0 -> {
                        navHostController.navigate(Routes.HomeScreen.route)
                    }

                    1 -> {
                        navHostController.navigate(Routes.UpdateScreen.route)
                    }

                    2 -> {
                        navHostController.navigate(Routes.CommunitiesScreen.route)
                    }

                    3 -> {
                        navHostController.navigate(Routes.CallScreen.route)
                    }
                }
            })
        },
        topBar = {
            TopBar()
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            Text(
                text = "Status",
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
            )
            MyStatus()

            samplestatus.forEach {
                StatusItem(statusData = it)

            }
            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider(color = Color.LightGray, thickness = 1.dp)
            Text(
                text = "Channels",
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))

            Column(modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)) {
                Text(
                    text = "Stay updated on topics that matters to you. Find channels to follow below ",
                    color = Color.Black,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "Find channels to follow",
                    color = Color.Black,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                )

            }
            Spacer(modifier = Modifier.height(2.dp))


            samplechannel.forEach {
                ChannelItemDesign(channels = it)

            }

        }
    }


}