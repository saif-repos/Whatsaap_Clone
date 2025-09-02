package com.example.whatsapp.presentation.welcomescreen

import android.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.whatsapp.R


@Composable

fun WelcomeScreen(navHostController: NavHostController) {


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Image(
            painter = painterResource(id = R.drawable.whatsapp_sticker),
            contentDescription = null,
            modifier = Modifier.size(300.dp)
        )

        Text(text = "Welcome to WhatsApp", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(20.dp))

        Row {

            Text(
                text = "Terms of Service",
                fontSize = 16.sp,
                color = androidx.compose.ui.graphics.Color.Gray
            )

            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = "Privacy Policy",
                fontSize = 16.sp,
                color = colorResource(id = R.color.light_green)
            )
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Agree and continue !",
                fontSize = 16.sp,
                color = androidx.compose.ui.graphics.Color.Gray
            )


        }

        Spacer(modifier = Modifier.height(20.dp))


        Button(
            onClick = {navHostController.navigate("user_registration") },
            modifier = Modifier.size(200.dp, 43.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.dark_green),
                contentColor = androidx.compose.ui.graphics.Color.White
            )
        ) {

            Text(text = "AGREE AND CONTINUE")

        }
    }


}