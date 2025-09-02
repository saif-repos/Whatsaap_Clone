package com.example.whatsapp.presentation.homescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.whatsapp.Model.phoneAuthUser
import com.example.whatsapp.R
import com.example.whatsapp.presentation.bottomnavigation.BottomNavigation
import com.example.whatsapp.presentation.chat_box.ChatListBox
import com.example.whatsapp.presentation.chat_box.ChatListModel
import com.example.whatsapp.presentation.navigation.Routes
import com.example.whatsapp.presentation.viewmodel.BaseViewModel
import com.google.firebase.auth.FirebaseAuth

//@Composable
//fun HomeScreen(navHostController: NavHostController, homeBaseViewModel: BaseViewModel) {
//
//    val chatData by homeBaseViewModel.chatList.collectAsState()
//
//    val userId = FirebaseAuth.getInstance().currentUser?.uid
//    if (userId != null) {
//        LaunchedEffect(userId) {
//            homeBaseViewModel.getChatByUser(userId) { /* chats -> handle here if needed */ }
//        }
//    }
//
//    // states
//    var isSearching by remember { mutableStateOf(false) }
//    var searchText by remember { mutableStateOf("") }
//    var showMenu by remember { mutableStateOf(false) }
//
//    Scaffold(
//        floatingActionButton = {
//            FloatingActionButton(
//                onClick = { /* TODO: action */ },
//                containerColor = colorResource(id = R.color.light_green),
//                contentColor = Color.White,
//                modifier = Modifier.size(60.dp)
//            ) {
//                Icon(
//                    painter = painterResource(R.drawable.add_chat_icon),
//                    contentDescription = "Add",
//                    modifier = Modifier.size(28.dp),
//                    tint = Color.White
//                )
//            }
//        },
//        bottomBar = {
//            BottomNavigation()
//        }
//    ) { innerPadding ->
//        Column(
//            modifier = Modifier
//                .padding(innerPadding)
//                .background(Color.White)
//        ) {
//            Spacer(modifier = Modifier.height(10.dp))
//
//            Box(
//                modifier = Modifier.fillMaxWidth(),
//                contentAlignment = Alignment.Center
//            ) {
//                if (isSearching) {
//                    TextField(
//                        value = searchText,
//                        onValueChange = { searchText = it },
//                        placeholder = {
//                            Text(
//                                text = "Search",
//                                color = Color.Gray
//                            )
//                        },
//                        singleLine = true,
//                        modifier = Modifier
//                            .fillMaxWidth(0.8f)
//                            .align(Alignment.CenterStart)
//                            .padding(start = 12.dp),
//                        colors = TextFieldDefaults.colors(
//                            focusedIndicatorColor = Color.Transparent,
//                            unfocusedIndicatorColor = Color.Transparent,
//                            unfocusedContainerColor = Color.Transparent,
//                            focusedContainerColor = Color.Transparent
//                        )
//                    )
//                } else {
//                    Text(
//                        text = "Chats",
//                        color = colorResource(id = R.color.light_green), // sirf ek hi color use hoga
//                        fontSize = 28.sp,
//                        modifier = Modifier
//                            .align(Alignment.CenterStart)
//                            .padding(start = 12.dp),
//                        fontWeight = FontWeight.Bold
//                    )
//
//                    Row(modifier = Modifier.align(Alignment.CenterEnd)) {
//                        IconButton(onClick = {}, modifier = Modifier.padding(end = 10.dp)) {
//                            Icon(
//                                painter = painterResource(id = R.drawable.camera),
//                                contentDescription = null,
//                                modifier = Modifier.size(24.dp)
//                            )
//                        }
//                        if (isSearching) {
//                            IconButton(onClick = {
//                                isSearching = false
//                                searchText = ""
//                            }, modifier = Modifier.padding(end = 10.dp)) {
//                                Icon(
//                                    painter = painterResource(id = R.drawable.cross),
//                                    contentDescription = null,
//                                    modifier = Modifier.size(24.dp)
//                                )
//                            }
//                        } else {
//                            IconButton(onClick = {
//                                isSearching = true
//                            }, modifier = Modifier.padding(end = 10.dp)) {
//                                Icon(
//                                    painter = painterResource(id = R.drawable.search),
//                                    contentDescription = null,
//                                    modifier = Modifier.size(24.dp)
//                                )
//                            }
//                        }
//
//                        IconButton(onClick = {
//                            showMenu = !showMenu
//                        }) {
//                            Icon(
//                                painter = painterResource(id = R.drawable.more),
//                                contentDescription = null,
//                                modifier = Modifier.size(24.dp)
//                            )
//
//                            DropdownMenu(expanded = showMenu, onDismissRequest = {
//                                showMenu = false
//                            }, modifier = Modifier.background(color = Color.White)) {
//
//                                DropdownMenuItem(
//                                    text = { Text("New group") },
//                                    onClick = {
//                                        showMenu = false
//                                    }
//                                )
//                                DropdownMenuItem(
//                                    text = { Text("New broadcast") },
//                                    onClick = {
//                                        showMenu = false
//
//                                    }
//                                )
//                                DropdownMenuItem(
//                                    text = { Text("Linked devices") },
//                                    onClick = {
//                                        showMenu = false
//                                    }
//                                )
//
//                                DropdownMenuItem(
//                                    text = { Text("Starred Messages") },
//                                    onClick = {
//                                        showMenu = false
//                                    }
//                                )
//                                DropdownMenuItem(
//                                    text = { Text("Settings") },
//                                    onClick = {
//                                        showMenu = false
//                                        navHostController.navigate("settings_screen")
//                                    }
//                                )
//
//                            }
//                        }
//
//
//                    }
//
//                }
//
//            }
//            Spacer(modifier = Modifier.height(10.dp))
//
//            HorizontalDivider()
//
//            Spacer(modifier = Modifier.height(12.dp))
//
//            LazyColumn {
//                items(chatData) { chat ->
//                    ChatListBox(
//                        chatListModel = chat,
//                        onClick = {
//                            navHostController.navigate(
//                                Routes.ChatScreen.createRoutes(
//                                    phoneNumber = chat.phoneNumber ?: "ok"
//                                )
//                            )
//                        }
//                    )
//                }
//            }
//
//
//        }
//    }
//}
@Composable
fun HomeScreen(navHostController: NavHostController, homeBaseViewModel: BaseViewModel) {

    val chatData by homeBaseViewModel.chatList.collectAsState()

    val userId = FirebaseAuth.getInstance().currentUser?.uid
    if (userId != null) {
        LaunchedEffect(userId) {
            homeBaseViewModel.getChatByUser(userId) { /* chats -> handle here if needed */ }
        }
    }

    // states
    var isSearching by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }
    var showMenu by remember { mutableStateOf(false) }
    var showPopup by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showPopup = true },
                containerColor = colorResource(id = R.color.light_green),
                contentColor = Color.White,
                modifier = Modifier.size(60.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.add_chat_icon),
                    contentDescription = "Add",
                    modifier = Modifier.size(28.dp),
                    tint = Color.White
                )
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
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .background(Color.White)
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                if (isSearching) {
                    TextField(
                        value = searchText,
                        onValueChange = { searchText = it },
                        placeholder = {
                            Text(
                                text = "Search",
                                color = Color.Gray
                            )
                        },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .align(Alignment.CenterStart)
                            .padding(start = 12.dp),
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent
                        )
                    )
                } else {
                    Text(
                        text = "Chats",
                        color = colorResource(id = R.color.light_green),
                        fontSize = 28.sp,
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .padding(start = 12.dp),
                        fontWeight = FontWeight.Bold
                    )

                    Row(modifier = Modifier.align(Alignment.CenterEnd)) {
                        IconButton(onClick = {}, modifier = Modifier.padding(end = 10.dp)) {
                            Icon(
                                painter = painterResource(id = R.drawable.camera),
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        if (isSearching) {
                            IconButton(onClick = {
                                isSearching = false
                                searchText = ""
                            }, modifier = Modifier.padding(end = 10.dp)) {
                                Icon(
                                    painter = painterResource(id = R.drawable.cross),
                                    contentDescription = null,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        } else {
                            IconButton(onClick = {
                                isSearching = true
                            }, modifier = Modifier.padding(end = 10.dp)) {
                                Icon(
                                    painter = painterResource(id = R.drawable.search),
                                    contentDescription = null,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }

                        IconButton(onClick = {
                            showMenu = !showMenu
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.more),
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )

                            DropdownMenu(
                                expanded = showMenu,
                                onDismissRequest = { showMenu = false },
                                modifier = Modifier.background(color = Color.White)
                            ) {
                                DropdownMenuItem(
                                    text = { Text("New group") },
                                    onClick = { showMenu = false }
                                )
                                DropdownMenuItem(
                                    text = { Text("New broadcast") },
                                    onClick = { showMenu = false }
                                )
                                DropdownMenuItem(
                                    text = { Text("Linked devices") },
                                    onClick = { showMenu = false }
                                )
                                DropdownMenuItem(
                                    text = { Text("Starred Messages") },
                                    onClick = { showMenu = false }
                                )
                                DropdownMenuItem(
                                    text = { Text("Settings") },
                                    onClick = {
                                        showMenu = false
                                        navHostController.navigate(Routes.SettingsScreen.route)
                                    }
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(12.dp))

            if (showPopup) {
                AddUserPopup(
                    onDismiss = { showPopup = false }, onUserAdded =
                    { newUser ->
                        homeBaseViewModel.addChat(newUser)
                    }, baseViewModel = homeBaseViewModel
                )
            }

            LazyColumn {
                items(chatData) { chat ->
                    ChatListBox(
                        chatListModel = chat,
                        homeBaseViewModel = homeBaseViewModel, // ✅ must pass this
                        onClick = {
                            navHostController.navigate(
                                Routes.ChatScreen.createRoute(chat.phoneNumber ?: "")
                            )
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun AddUserPopup(
    onDismiss: () -> Unit,

    onUserAdded: (ChatListModel) -> Unit,
    baseViewModel: BaseViewModel
) {
    var phoneNumber by remember { mutableStateOf("") }
    var isSearching by remember { mutableStateOf(false) }
    var userFound by remember { mutableStateOf<ChatListModel?>(null) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        TextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text("Enter Phone Number") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent
            )
        )


        Button(
            onClick = {
                isSearching = true
                baseViewModel.searchUserByPhoneNumber(phoneNumber) { foundUser ->
                    isSearching = false

                    if (foundUser != null) {
                        userFound = foundUser
                    } else {
                        userFound = null
                    }
                }

            },
            modifier = Modifier.weight(1f),
            colors = ButtonDefaults.buttonColors(colorResource(R.color.light_green))
        ) {
            Text(text = "Search")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onDismiss,

            modifier = Modifier.weight(1f),
            colors = ButtonDefaults.buttonColors(colorResource(R.color.light_green))
        ) {
            Text(text = "Cancel")
        }


        if (isSearching) {
            Text(text = "Searching...", color = Color.Gray)


        }
        userFound?.let {
            Column {
                Text(text = "User Found ${it.name}")

                Button(onClick = {
                    onUserAdded(it)
                    onDismiss()
                }, colors = ButtonDefaults.buttonColors(colorResource(R.color.light_green))) {

                }
                Text(text = "Add to chat")
            }
        } ?: run {
            if (!isSearching) {
                Text(text = "No user found", color = Color.Gray)
            }
        }
    }


}
