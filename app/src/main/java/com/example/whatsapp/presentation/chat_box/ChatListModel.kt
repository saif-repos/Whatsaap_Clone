package com.example.whatsapp.presentation.chat_box

data class ChatListModel(
    val name: String? = null,
    val phoneNumber: String? = null,
    val userId: String? = null,
    val time: String? = null,
    val message: String? = null,
    val profileImage: String? = null // Base64 string from Firebase
) {
    constructor() : this(null, null, null, null, null, null)
}

