package com.example.whatsapp.presentation.callscreen

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

fun CallScreen(navHostController: NavHostController) {
    val sampleCalls = listOf(
        Call(R.drawable.mabrorcrop, "Mabroor", " .10:00 PM", true),
        Call(R.drawable.rahman, "Rahman", " .10:00 AM", false),
        Call(R.drawable.saif3, "Saif", " .11:00 PM", true)
    )

    var isSearching by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }
    var showMenu by remember { mutableStateOf(false) }

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
                    painter = painterResource(id = R.drawable.add_call),
                    contentDescription = "Chat",
                    modifier = Modifier.size(28.dp) // Icon size chota kar diya (adjust as needed)

                )
            }
        },
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
                                text = "Call",
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
            BottomNavigation(navHostController, selectedItem = 0, onClick = {index ->
                when(index){
                    0 -> {navHostController.navigate(Routes.HomeScreen.route)}
                    1 -> {navHostController.navigate(Routes.UpdateScreen.route)}
                    2 -> {navHostController.navigate(Routes.CommunitiesScreen.route)}
                    3 -> {navHostController.navigate(Routes.CallScreen.route)}
                }
            })
        }
    ) { innerPadding ->
        // Your screen content
        Column(modifier = Modifier.padding(innerPadding)) {
            FavroitSection()

            Button(
                onClick = { /* Handle call button click */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp) // light green
            ) {
                Text(
                    text = "Start a new Call",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )


            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Recent Calls",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

            LazyColumn {
                items(sampleCalls) { call ->
                    CallItemDesign(call)
                }
            }

        }

    }
}



