package com.example.whatsapp.presentation.communitiesscreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
fun CommunityScreen(navHostController: NavHostController) {
    var isSearching by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }
    var showMenu by remember { mutableStateOf(false) }
    val sampleCommunities = listOf(
        Communities(R.drawable.img, "Community 1", "10 members"),
        Communities(R.drawable.img, "Community 2", "20 members"),
        Communities(R.drawable.img, "Community 3", "15 members")
    )

    Scaffold(
        topBar = {
            Box(modifier = Modifier.fillMaxWidth()) {
                Column {
                    Row {
                        if (isSearching) {
                            TextField(
                                value = searchText,
                                onValueChange = { searchText = it },
                                placeholder = { Text(text = "Search") },
                                colors = TextFieldDefaults.colors(
                                    focusedContainerColor = Color.Transparent,
                                    unfocusedContainerColor = Color.Transparent,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent
                                ),
                                modifier = Modifier.padding(start = 8.dp),
                                singleLine = true
                            )
                        } else {
                            Text(
                                text = "Communities",
                                fontWeight = FontWeight.Bold,
                                fontSize = 28.sp,
                                color = Color.Black,
                                modifier = Modifier.padding(start = 4.dp, top = 6.dp)
                            )
                        }

                        Spacer(modifier = Modifier.weight(1f))

                        if (isSearching) {
                            IconButton(onClick = {
                                isSearching = false
                                searchText = ""
                            }) {
                                Icon(
                                    painter = painterResource(R.drawable.cross),
                                    contentDescription = "Close search",
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        } else {
                            IconButton(onClick = { isSearching = true }) {
                                Icon(
                                    painter = painterResource(R.drawable.search),
                                    contentDescription = "Search",
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                            IconButton(onClick = { showMenu = true }) {
                                Icon(
                                    painter = painterResource(R.drawable.more),
                                    contentDescription = "More options",
                                    modifier = Modifier.size(24.dp)
                                )
                            }

                            // ✅ DropdownMenu must be outside IconButton
                            DropdownMenu(
                                expanded = showMenu,
                                onDismissRequest = { showMenu = false }
                            ) {

                                DropdownMenuItem(
                                    text = { Text("Settings") },
                                    onClick = { showMenu = false }
                                )
                            }
                        }
                    }
                    HorizontalDivider()
                }
            }
        },
        bottomBar = {
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
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            // Your screen body content goes here

            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(colorResource(R.color.light_green)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = "Start a Community", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Your Communities",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.Black,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

            LazyColumn {
                items(sampleCommunities) { community ->
                    CommunityItemScreen(communities = community)
                }
            }


        }
    }
}

