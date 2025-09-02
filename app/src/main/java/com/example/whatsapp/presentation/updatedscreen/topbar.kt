package com.example.whatsapp.presentation.updatedscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.whatsapp.R

@Preview(showSystemUi = true)
@Composable
fun TopBar() {
    var isSearching by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }
    var showmenu by remember { mutableStateOf(false) }

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
                        text = "Updates",
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
                            contentDescription = null,
                            modifier = Modifier.size(15.dp)
                        )

                    }
                } else {
                    IconButton(onClick = { }) {
                        Icon(
                            painter = painterResource(R.drawable.camera),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    IconButton(onClick = { isSearching = true }) {
                        Icon(
                            painter = painterResource(R.drawable.search),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    IconButton(onClick = { showmenu = true }) {
                        Icon(
                            painter = painterResource(R.drawable.more),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )


                        DropdownMenu(expanded = showmenu, onDismissRequest = { showmenu = false }) {
                            DropdownMenuItem(
                                text = { Text(text = "Status Privacy") },
                                onClick = { showmenu = false })
                            DropdownMenuItem(
                                text = { Text(text = "Create Channel") },
                                onClick = { showmenu = false })
                            DropdownMenuItem(
                                text = { Text(text = "Settings") },
                                onClick = { showmenu = false })

                        }

                    }
                }
            }

            HorizontalDivider()
        }
    }
}


//package com.example.whatsapp.presentation.updatedscreen
//
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.Icon
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextField
//import androidx.compose.material3.TextFieldDefaults
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.example.whatsapp.R
//
//@Preview(showSystemUi = true)
//@Composable
//fun TopBar() {
//    var isSearching by remember { mutableStateOf(true) } // make true for testing
//    var searchText by remember { mutableStateOf("") }
//
//    Box(modifier = Modifier.fillMaxWidth()) {
//        Column {
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 8.dp, vertical = 6.dp),
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                if (isSearching) {
//                    TextField(
//                        value = searchText,
//                        onValueChange = { searchText = it },
//                        placeholder = { Text(text = "Search") },
//                        colors = TextFieldDefaults.colors(
//                            focusedContainerColor = Color.Transparent,
//                            unfocusedContainerColor = Color.Transparent,
//                            focusedIndicatorColor = Color.Transparent,
//                            unfocusedIndicatorColor = Color.Transparent
//                        ),
//                        modifier = Modifier
//                            .weight(1f)
//                            .padding(end = 8.dp),
//                        singleLine = true
//                    )
//                    Icon(
//                        painter = painterResource(R.drawable.cross),
//                        contentDescription = "Close Search",
//                        tint = Color.Black,
//                        modifier = Modifier
//                            .size(24.dp)
//                            .padding(start = 4.dp)
//                    )
//                } else {
//                    Text(
//                        text = "Updates",
//                        fontWeight = FontWeight.Bold,
//                        fontSize = 28.sp,
//                        color = Color.Black,
//                        modifier = Modifier.padding(start = 4.dp)
//                    )
//                }
//            }
//        }
//    }
//}
