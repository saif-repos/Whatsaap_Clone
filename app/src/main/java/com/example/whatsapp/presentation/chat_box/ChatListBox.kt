package com.example.whatsapp.presentation.chat_box

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.whatsapp.R
import com.example.whatsapp.presentation.viewmodel.BaseViewModel


//@Composable
//fun ChatListBox(
//    chatListModel: ChatListModel,
//    onClick: () -> Unit,
//    homeBaseViewModel: BaseViewModel
//) {
//    Row(
//        modifier = Modifier
//            .padding(8.dp)
//            .fillMaxWidth()
//            .clickable { onClick() }, // 👈 make row clickable
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        val profileImage = chatListModel.profileImage
//
//        val bitmapImage = remember {
//            profileImage?.let { homeBaseViewModel.base64toBitmap(it) }
//        }
//
//        Image(
//            painter = if (bitmapImage != null) {
//                rememberImagePainter(bitmapImage)
//            } else {
//                painterResource(id = R.drawable.profile_placeholder)
//            },
//            contentDescription = null,
//            modifier = Modifier
//                .size(60.dp)
//                .padding(4.dp)
//                .background(color = Color.Gray)
//                .clip(CircleShape),
//            contentScale = ContentScale.Crop,
//            alignment = Alignment.TopCenter
//        )
//
//        Spacer(modifier = Modifier.width(8.dp))
//
//        Column {
//            Row(
//                horizontalArrangement = Arrangement.SpaceBetween,
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Text(
//                    text = chatListModel.name ?: "Unknown",
//                    fontWeight = FontWeight.Bold,
//                    fontSize = 18.sp
//                )
//                Text(
//                    text = chatListModel.time ?: "--:--",
//                    fontWeight = FontWeight.Bold,
//                    fontSize = 18.sp,
//                    color = Color.Gray
//                )
//            }
//            Spacer(modifier = Modifier.width(8.dp))
//            Text(
//                text = chatListModel.message ?: "message",
//                fontSize = 16.sp,
//                color = Color.Gray,
//                fontWeight = FontWeight.Bold
//            )
//        }
//    }
//}

@Composable
fun ChatListBox(
    chatListModel: ChatListModel,
    homeBaseViewModel: BaseViewModel,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        val profileImage = chatListModel.profileImage

        val bitmapImage = remember {
            profileImage?.let { homeBaseViewModel.base64toBitmap(it) }
        }

        Image(
            painter = if (bitmapImage != null) {
                rememberImagePainter(bitmapImage)
            } else {
                painterResource(id = R.drawable.profile_placeholder)
            },
            contentDescription = null,
            modifier = Modifier
                .size(60.dp)
                .padding(4.dp)
                .background(color = Color.Gray)
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
            alignment = Alignment.TopCenter
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = chatListModel.name ?: "Unknown",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Text(
                    text = chatListModel.time ?: "--:--",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.Gray
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = chatListModel.message ?: "message",
                fontSize = 16.sp,
                color = Color.Gray,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

