//package com.example.whatsapp.presentation.userregistrationscreen
//
//import android.app.Activity
//import android.util.Log
//import android.widget.Toast
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.heightIn
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.ArrowDropDown
//import androidx.compose.material3.Button
//import androidx.compose.material3.ButtonDefaults
//import androidx.compose.material3.CircularProgressIndicator
//import androidx.compose.material3.DropdownMenu
//import androidx.compose.material3.DropdownMenuItem
//import androidx.compose.material3.HorizontalDivider
//import androidx.compose.material3.Icon
//import androidx.compose.material3.LocalTextStyle
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextButton
//import androidx.compose.material3.TextField
//import androidx.compose.material3.TextFieldDefaults
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.res.colorResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.example.whatsapp.R
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.hilt.navigation.compose.hiltViewModel
//import androidx.navigation.NavHostController
//import com.example.whatsapp.presentation.navigation.Routes
//import com.example.whatsapp.presentation.viewmodel.AuthState
//import com.example.whatsapp.presentation.viewmodel.PhoneAuthViewModel
//import kotlin.math.exp
//
//@Composable
//fun UserRegistrationScreen(
//    navController: NavHostController,
//    phoneAuthViewModel: PhoneAuthViewModel = hiltViewModel()
//) {
//
//    val authState by phoneAuthViewModel.authState.collectAsState()
//    val context = LocalContext.current
//    val activity = LocalContext.current as Activity
//
//
//    var otp by remember { mutableStateOf("") }
//    var verificationId by remember { mutableStateOf<String?>(null) }
//
//    var expanded by remember { mutableStateOf(false) }
//    var selectedCountry by remember { mutableStateOf("Pakistan") }
//    var countryCode by remember {
//        mutableStateOf("+92")
//    }
//    var phonenumber by remember {
//        mutableStateOf("")
//    }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(color = Color.White)
//            .padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text(
//            text = "Enter your phone number",
//            fontSize = 20.sp,
//            color = colorResource(R.color.dark_green),
//            fontWeight = FontWeight.Bold
//        )
//
//        Spacer(modifier = Modifier.height(24.dp))
//
//        Row {
//            Text(
//                text = "Whatsapp will need to validate your phone number",
//                fontSize = 16.sp
//            )
//            Spacer(modifier = Modifier.width(4.dp))
//        }
//
//        Text(
//            text = "What's my number",
//            fontSize = 16.sp,
//            color = colorResource(id = R.color.dark_green)
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        TextButton(
//            onClick = { expanded = true },
//            modifier = Modifier
//                .height(50.dp)
//                .fillMaxWidth()
//        ) {
//            Box(modifier = Modifier.width(230.dp)) {
//                Text(
//                    text = selectedCountry,
//                    modifier = Modifier.align(Alignment.Center),
//                    fontSize = 16.sp,
//                    color = colorResource(R.color.black)
//                )
//                Icon(
//                    imageVector = Icons.Default.ArrowDropDown,
//                    contentDescription = null,
//                    modifier = Modifier.align(Alignment.CenterEnd),
//                    tint = colorResource(R.color.dark_green)
//                )
//            }
//        }
//
//        HorizontalDivider(
//            modifier = Modifier.padding(horizontal = 66.dp),
//            thickness = 2.dp,
//            color = colorResource(R.color.light_green)
//        )
//        DropdownMenu(
//            expanded = expanded,
//            onDismissRequest = { expanded = false },
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            listOf("Pakistan", "China", "Iran", "Iraq", "Canada").forEach { country ->
//                DropdownMenuItem(text = { Text(text = country) }, onClick = {
//                    selectedCountry = country
//                    expanded = false
//                })
//
//            }
//
//        }
//
//        when (authState) {
//            is AuthState.Idle, is AuthState.Loading, is AuthState.CodeSent -> {
//                if (authState is AuthState.CodeSent) {
//                    verificationId = (authState as AuthState.CodeSent).verificationId
//
//                }
//                if (verificationId == null) {
//                    Spacer(modifier = Modifier.height(16.dp))
//                    Row(
//                        verticalAlignment = Alignment.CenterVertically,
//                        modifier = Modifier.fillMaxWidth()
//                    ) {
//                        TextField(
//                            value = countryCode,
//                            onValueChange = { countryCode = it },
//                            modifier = Modifier.width(70.dp),
//                            singleLine = true,
//                            colors = TextFieldDefaults.colors(
//                                unfocusedContainerColor = Color.Transparent,
//                                focusedContainerColor = Color.Transparent,
//                                focusedIndicatorColor = colorResource(R.color.light_green),
//                                unfocusedIndicatorColor = colorResource(R.color.dark_green)
//
//                            )
//                        )
//                        Spacer(modifier = Modifier.width(8.dp))
//                        TextField(
//                            value = phonenumber,
//                            onValueChange = { phonenumber = it },
//                            placeholder = { Text(text = "Phone Number") },
//                            singleLine = true,
//                            colors = TextFieldDefaults.colors(
//                                unfocusedContainerColor = Color.Transparent,
//                                focusedContainerColor = Color.Transparent,
//                                focusedIndicatorColor = Color.Transparent,
//                            )
//                        )
//
//
//                    }
//                    Spacer(modifier = Modifier.height(16.dp))
//                    Button(
//                        onClick = {
//                            if (phonenumber.isNotEmpty()) {
//                                val fullPhoneNumber = countryCode + phonenumber
//                                phoneAuthViewModel.sendVerificationCode(fullPhoneNumber, activity)
//                            } else {
//                                Toast.makeText(
//                                    context,
//                                    "Please enter your phone number",
//                                    Toast.LENGTH_SHORT
//                                ).show()
//                            }
//                        }, shape = RoundedCornerShape(6.dp), colors = ButtonDefaults.buttonColors(
//                            colorResource(R.color.dark_green)
//                        )
//                    ) {
//                        Text(text = "Send OTP")
//                    }
//                    if (authState is AuthState.Loading) {
//                        Spacer(modifier = Modifier.height(16.dp))
//                        CircularProgressIndicator()
//                    }
//
//                } else {
//                    // OTP input UI
//                    Spacer(modifier = Modifier.height(16.dp))
//
//                    Text(
//                        "Enter OTP:",
//                        fontSize = 18.sp,
//                        fontWeight = FontWeight.Bold,
//                        color = colorResource(R.color.dark_green)
//                    )
//                    Spacer(modifier = Modifier.height(16.dp))
//                    TextField(
//                        value = otp,
//                        onValueChange = { otp = it },
//                        placeholder = { Text(text = "Enter OTP") },
//                        singleLine = true,
//                        modifier = Modifier.fillMaxWidth(),
//                        colors = TextFieldDefaults.colors(
//                            unfocusedContainerColor = Color.Transparent,
//                            focusedContainerColor = Color.Transparent,
//                            focusedIndicatorColor = colorResource(R.color.light_green),
//                            unfocusedIndicatorColor = colorResource(R.color.dark_green)
//
//                        )
//                    )
//                    Spacer(modifier = Modifier.height(32.dp))
//                    Button(
//                        onClick = {
//                            if (otp.isNotEmpty() && verificationId != null) {
//                                phoneAuthViewModel.verifyCode(otp, context)
//                            } else {
//                                Toast.makeText(
//                                    context,
//                                    "Please enter the OTP",
//                                    Toast.LENGTH_SHORT
//                                ).show() // Added missing .show() method
//                            }
//                        },
//                        shape = RoundedCornerShape(6.dp),
//                        colors = ButtonDefaults.buttonColors(colorResource(id = R.color.dark_green))
//                    ) {
//                        Text(text = "Verify OTP")
//                    }
//
//                    if (authState is AuthState.Loading) {
//                        Spacer(modifier = Modifier.height(16.dp))
//                        CircularProgressIndicator()
//                    }
//                }
//            }
//
//            is AuthState.Success -> {
//                Log.d("PhoneAuth", "Login Successfully")
//                phoneAuthViewModel.resetAuthState()
//                navController.navigate(Routes.UserProfileScreen.route) {
//                    popUpTo(Routes.UserRegistrationScreen.route) {
//                        inclusive = true
//                    }
//                }
//            }
//
//            is AuthState.Error -> {
//                Toast.makeText(context, (authState as AuthState.Error).message, Toast.LENGTH_SHORT)
//
//
//            }
//        }
//    }
//}
package com.example.whatsapp.presentation.userregistrationscreen

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.whatsapp.R
import com.example.whatsapp.presentation.navigation.Routes
import com.example.whatsapp.presentation.viewmodel.AuthState
import com.example.whatsapp.presentation.viewmodel.PhoneAuthViewModel
import androidx.compose.foundation.text.KeyboardOptions

@Composable
fun UserRegistrationScreen(
    navController: NavHostController,
    phoneAuthViewModel: PhoneAuthViewModel = hiltViewModel()
) {

    val authState by phoneAuthViewModel.authState.collectAsState()
    val context = LocalContext.current
    val activity = LocalContext.current as Activity

    var otp by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var selectedCountry by remember { mutableStateOf("Pakistan") }
    var countryCode by remember { mutableStateOf("+92") }
    var phonenumber by remember { mutableStateOf("") }

    // Get verificationId from authState
    val verificationId = when (authState) {
        is AuthState.CodeSent -> (authState as AuthState.CodeSent).verificationId
        else -> null
    }

    // Debug logging
    LaunchedEffect(authState) {
        Log.d("AuthDebug", "AuthState changed to: $authState")
        Log.d("AuthDebug", "VerificationId: $verificationId")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Enter your phone number",
            fontSize = 20.sp,
            color = colorResource(R.color.dark_green),
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Whatsapp will need to validate your phone number",
            fontSize = 16.sp
        )

        Text(
            text = "What's my number",
            fontSize = 16.sp,
            color = colorResource(id = R.color.dark_green)
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(
            onClick = { expanded = true },
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
        ) {
            Box(modifier = Modifier.width(230.dp)) {
                Text(
                    text = selectedCountry,
                    modifier = Modifier.align(Alignment.Center),
                    fontSize = 16.sp,
                    color = colorResource(R.color.black)
                )
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.CenterEnd),
                    tint = colorResource(R.color.dark_green)
                )
            }
        }

        HorizontalDivider(
            modifier = Modifier.padding(horizontal = 66.dp),
            thickness = 2.dp,
            color = colorResource(R.color.light_green)
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            listOf("Pakistan", "China", "Iran", "Iraq", "Canada").forEach { country ->
                DropdownMenuItem(
                    text = { Text(text = country) },
                    onClick = {
                        selectedCountry = country
                        expanded = false
                    }
                )
            }
        }

        when (authState) {
            is AuthState.Idle, is AuthState.Loading, is AuthState.CodeSent -> {
                if (verificationId == null) {
                    // PHONE NUMBER INPUT UI
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        TextField(
                            value = countryCode,
                            onValueChange = { countryCode = it },
                            modifier = Modifier.width(70.dp),
                            singleLine = true,
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = Color.Transparent,
                                focusedContainerColor = Color.Transparent,
                                focusedIndicatorColor = colorResource(R.color.light_green),
                                unfocusedIndicatorColor = colorResource(R.color.dark_green)
                            )
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        TextField(
                            value = phonenumber,
                            onValueChange = { phonenumber = it },
                            placeholder = { Text(text = "Phone Number") },
                            singleLine = true,
                            modifier = Modifier.weight(1f),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = Color.Transparent,
                                focusedContainerColor = Color.Transparent,
                                focusedIndicatorColor = colorResource(R.color.light_green),
                                unfocusedIndicatorColor = colorResource(R.color.dark_green)
                            )
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            if (phonenumber.isNotEmpty()) {
                                val fullPhoneNumber = countryCode + phonenumber
                                phoneAuthViewModel.sendVerificationCode(fullPhoneNumber, activity)
                            } else {
                                Toast.makeText(
                                    context,
                                    "Please enter your phone number",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        },
                        shape = RoundedCornerShape(6.dp),
                        colors = ButtonDefaults.buttonColors(colorResource(R.color.dark_green))
                    ) {
                        Text(text = "Send OTP")
                    }
                    if (authState is AuthState.Loading) {
                        Spacer(modifier = Modifier.height(16.dp))
                        CircularProgressIndicator()
                    }

                } else {
                    // OTP INPUT UI
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        "Enter OTP sent to $countryCode$phonenumber",
                        fontSize = 16.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Text(
                        "Enter OTP:",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(R.color.dark_green)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    TextField(
                        value = otp,
                        onValueChange = {
                            if (it.length <= 6 && it.all { char -> char.isDigit() }) {
                                otp = it
                            }
                        },
                        placeholder = { Text(text = "Enter 6-digit OTP") },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 32.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent,
                            focusedIndicatorColor = colorResource(R.color.light_green),
                            unfocusedIndicatorColor = colorResource(R.color.dark_green)
                        )
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    Button(
                        onClick = {
                            if (otp.isNotEmpty() && verificationId != null) {
                                phoneAuthViewModel.verifyCode(otp, context)
                            } else {
                                Toast.makeText(
                                    context,
                                    "Please enter the OTP",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        },
                        shape = RoundedCornerShape(6.dp),
                        colors = ButtonDefaults.buttonColors(colorResource(id = R.color.dark_green)),
                        modifier = Modifier.fillMaxWidth(0.8f)
                    ) {
                        Text(text = "Verify OTP", fontSize = 16.sp)
                    }

                    if (authState is AuthState.Loading) {
                        Spacer(modifier = Modifier.height(16.dp))
                        CircularProgressIndicator()
                    }
                }
            }

            is AuthState.Success -> {
                Log.d("PhoneAuth", "Login Successfully")
                phoneAuthViewModel.resetAuthState()
                navController.navigate(Routes.UserProfileSetScreen.route) {
                    popUpTo(Routes.UserRegistrationScreen.route) {
                        inclusive = true
                    }
                }
            }

            is AuthState.Error -> {
                Toast.makeText(context, (authState as AuthState.Error).message, Toast.LENGTH_SHORT)
                    .show()

                Button(
                    onClick = {
                        phoneAuthViewModel.resetAuthState()
                    },
                    shape = RoundedCornerShape(6.dp),
                    colors = ButtonDefaults.buttonColors(colorResource(id = R.color.dark_green))
                ) {
                    Text(text = "Try Again")
                }
            }
        }
    }
}