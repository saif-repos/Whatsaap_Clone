//package com.example.whatsapp.presentation.homescreen
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.example.whatsapp.R
//import com.example.whatsapp.presentation.chat_box.ChatListModel
//
//@Composable
//
//
//fun ChatDesign(chatListModel: ChatListModel) {
//    Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
//
//
//        Image(
//            painter = painterResource(chatListModel.image),
//            contentDescription = null,
//            modifier = Modifier
//                .size(60.dp)
//                .padding(4.dp)
//                .clip(CircleShape),
//            contentScale = ContentScale.Crop,
//            alignment = Alignment.TopCenter // shifts image downward
//        )
//
//        Spacer(modifier = Modifier.width(8.dp))
//
//        Column() {
//
//            Row(
//                horizontalArrangement = Arrangement.SpaceBetween,
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Text(
//                    text = chatListModel.name,
//                    fontWeight = FontWeight.Bold,
//                    fontSize = 18.sp
//                )
//                Text(
//                    text = chatListModel.time,
//                    fontWeight = FontWeight.Bold,
//                    fontSize = 18.sp,
//                    color = Color.Gray
//                )
//            }
//            Spacer(modifier = Modifier.width(8.dp))
//            Text(
//                text = chatListModel.message,
//                fontSize = 16.sp,
//                color = Color.Gray,
//                fontWeight = FontWeight.Bold
//            )
//        }
//
//
//    }
//}
