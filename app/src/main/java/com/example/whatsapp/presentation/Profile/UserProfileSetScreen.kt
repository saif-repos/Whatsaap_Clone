//package com.example.whatsapp.presentation.Profile
//
//import android.graphics.Bitmap
//
//import android.graphics.ImageDecoder
//import android.net.Uri
//import android.os.Build
//import androidx.compose.ui.graphics.Color
//import androidx.activity.compose.rememberLauncherForActivityResult
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.border
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.material3.Button
//import androidx.compose.material3.ButtonDefaults
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextField
//import androidx.compose.material3.TextFieldDefaults
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.asImageBitmap
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.colorResource
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.hilt.navigation.compose.hiltViewModel
//import androidx.navigation.NavHostController
//import coil.compose.rememberImagePainter
//import com.example.whatsapp.R
//import com.example.whatsapp.presentation.viewmodel.PhoneAuthViewModel
//import com.google.firebase.auth.FirebaseAuth
//import dagger.hilt.android.lifecycle.HiltViewModel
//
//@Composable
//fun UserProfileSetScreen(
//
//    phoneAuthViewModel: PhoneAuthViewModel = hiltViewModel(),
//    navHostController: NavHostController
//) {
//    var name by remember { mutableStateOf("") }
//    var status by remember { mutableStateOf("") }
//    var profileImageUri by remember { mutableStateOf<Uri?>(null) }
//    var bitmapImage by remember { mutableStateOf<Bitmap?>(null) }
//
//    val firebaseAuth = FirebaseAuth.getInstance()
//
//    val phoneNumber = firebaseAuth.currentUser?.phoneNumber ?: ""
//    val userId = firebaseAuth.currentUser?.uid
//
//    val context = LocalContext.current
//    val imagePickerLauncher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.GetContent(),
//        onResult = { uri: Uri? ->
//            profileImageUri = uri
//            uri?.let {
//                bitmapImage = if (Build.VERSION.SDK_INT < 28) {
//
//                    @Suppress("DEPRECATION")
//                    android.provider.MediaStore.Images.Media.getBitmap(context.contentResolver, it)
//                    null
//                } else {
//
//                    val source = ImageDecoder.createSource(context.contentResolver, it)
//                    ImageDecoder.decodeBitmap(source)
//                    null
//                }
//            }
//        }
//    )
//    Column(
//        modifier = Modifier
//            .padding(16.dp)
//            .fillMaxWidth(),
//        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
//    ) {
//        Box(
//            modifier = Modifier
//                .size(128.dp)
//                .clip(CircleShape)
//                .border(
//                    width = 2.dp,
//                    color = colorResource(id = R.color.dark_green), // ✅ Correct way
//                    shape = CircleShape
//                )
//                .clickable {
//                    imagePickerLauncher.launch("image/*")
//                }
//                .background(Color.White)
//        ) {
//            if (bitmapImage != null) {
//                // Your content inside Box
//                Image(
//                    bitmap = bitmapImage!!.asImageBitmap(),
//                    contentDescription = null,
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .clip(
//                            CircleShape
//                        ), contentScale = ContentScale.Crop
//                )
//            } else if (profileImageUri != null) {
//                Image(
//                    painter = rememberImagePainter(profileImageUri),
//                    contentDescription = null,
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .clip(CircleShape), contentScale = ContentScale.Crop
//                )
//            } else {
//                Image(
//                    painter = painterResource(id = R.drawable.profile_placeholder),
//                    contentDescription = null,
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .align(androidx.compose.ui.Alignment.Center)
//                )
//            }
//
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Text(text = "$phoneNumber", style = MaterialTheme.typography.bodyMedium)
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        TextField(
//            value = name,
//            onValueChange = { name = it },
//            label = { Text(text = "Name") },
//            modifier = Modifier.fillMaxWidth(),
//            colors = TextFieldDefaults.colors(
//                unfocusedContainerColor = Color.Transparent,
//                focusedContainerColor = Color.Transparent,
//                focusedIndicatorColor = colorResource(id = R.color.light_green),
//            )
//        )
//        Spacer(modifier = Modifier.height(16.dp))
//
//        TextField(
//            value = status,
//            onValueChange = { status = it },   // ✅ Correct
//            label = { Text(text = "Status") },
//            modifier = Modifier.fillMaxWidth(),
//            colors = TextFieldDefaults.colors(
//                unfocusedContainerColor = Color.Transparent,
//                focusedContainerColor = Color.Transparent,
//                focusedIndicatorColor = colorResource(id = R.color.light_green),
//            )
//        )
//
//        Spacer(modifier = Modifier.height(32.dp))
//
//        Button(onClick = {
//            phoneAuthViewModel.saveUserProfile(userId!!, name, status, bitmapImage)
//            navHostController.navigate("home_screen")
//        }, colors = ButtonDefaults.buttonColors(colorResource(R.color.light_green))) {
//            Text(text = "Save")
//
//
//        }
//
//    }
//}
//
//


package com.example.whatsapp.presentation.Profile

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.compose.ui.graphics.Color
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.whatsapp.R
import com.example.whatsapp.presentation.viewmodel.PhoneAuthViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun UserProfileSetScreen(
    phoneAuthViewModel: PhoneAuthViewModel = hiltViewModel(),
    navHostController: NavHostController
) {
    var name by remember { mutableStateOf("") }
    var status by remember { mutableStateOf("") }
    var profileImageUri by remember { mutableStateOf<Uri?>(null) }
    var bitmapImage by remember { mutableStateOf<Bitmap?>(null) }

    val firebaseAuth = FirebaseAuth.getInstance()
    val phoneNumber = firebaseAuth.currentUser?.phoneNumber ?: ""
    val userId = firebaseAuth.currentUser?.uid

    val context = LocalContext.current
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? ->
            profileImageUri = uri
            uri?.let {
                bitmapImage = if (Build.VERSION.SDK_INT < 28) {
                    @Suppress("DEPRECATION")
                    android.provider.MediaStore.Images.Media.getBitmap(context.contentResolver, it)
                } else {
                    val source = ImageDecoder.createSource(context.contentResolver, it)
                    ImageDecoder.decodeBitmap(source)
                }
            }
        }
    )

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(128.dp)
                .clip(CircleShape)
                .border(
                    width = 2.dp,
                    color = colorResource(id = R.color.dark_green),
                    shape = CircleShape
                )
                .clickable {
                    imagePickerLauncher.launch("image/*")
                }
                .background(Color.White)
        ) {
            if (bitmapImage != null) {
                Image(
                    bitmap = bitmapImage!!.asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            } else if (profileImageUri != null) {
                Image(
                    painter = rememberImagePainter(profileImageUri),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.profile_placeholder),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .align(androidx.compose.ui.Alignment.Center)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = phoneNumber, style = MaterialTheme.typography.bodyMedium)

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text(text = "Name") },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                focusedIndicatorColor = colorResource(id = R.color.light_green),
            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = status,
            onValueChange = { status = it },   // ✅ FIXED
            label = { Text(text = "Status") },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                focusedIndicatorColor = colorResource(id = R.color.light_green),
            )
        )
        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                if (userId != null) {
                    phoneAuthViewModel.saveUserProfile(userId, name, status, bitmapImage)
                    navHostController.navigate("home_screen")
                }
            },
            colors = ButtonDefaults.buttonColors(colorResource(R.color.light_green))
        ) {
            Text(text = "Save")
        }
    }
}
