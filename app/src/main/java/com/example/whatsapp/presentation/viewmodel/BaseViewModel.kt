package com.example.whatsapp.presentation.viewmodel


import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.Image
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.whatsapp.Model.Massage
import com.example.whatsapp.presentation.chat_box.ChatListModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.io.ByteArrayInputStream
import java.io.InputStream
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

class BaseViewModel : ViewModel() {

    fun searchUserByPhoneNumber(phoneNumber: String, callback: (ChatListModel?) -> Unit) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser == null) {
            Log.e("BaseViewModel", "Current user is null")
            callback(null)
            return
        }

        val databaseRef = FirebaseDatabase.getInstance().getReference("users")
        databaseRef.orderByChild("phoneNumber").equalTo(phoneNumber)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val user = snapshot.children.first().getValue(ChatListModel::class.java)
                        callback(user)
                    } else {
                        callback(null)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("BaseViewModel", "Error fetching User: ${error.message}")
                    callback(null)
                }
            })
    }

    fun getChatByUser(userId: String, callback: (List<ChatListModel>) -> Unit) {
        val chatRef = FirebaseDatabase.getInstance().getReference("users/$userId/chats")
        chatRef.orderByChild("userId").equalTo(userId)

        chatRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val chatList = mutableListOf<ChatListModel>()
                for (chatSnapshot in snapshot.children) {
                    val chat = chatSnapshot.getValue(ChatListModel::class.java)
                    if (chat != null) {
                        chatList.add(chat)
                    }
                }
                callback(chatList)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("BaseViewModel", "Error fetching chat: ${error.message}")
                callback(emptyList())
            }

        })


    }

    private val _chatList = MutableStateFlow<List<ChatListModel>>(emptyList())
    val chatList = _chatList.asStateFlow()

    init {
        loadChatData()
    }

    private fun loadChatData() {
        val currentUserId = FirebaseAuth.getInstance().currentUser?.uid
        if (currentUserId != null) {
            val chatRef = FirebaseDatabase.getInstance().getReference("chats")
            chatRef.orderByChild("userId").equalTo(currentUserId)

            chatRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val chatList = mutableListOf<ChatListModel>()
                    for (chidSnapShot in snapshot.children) {
                        val chat = chidSnapShot.getValue(ChatListModel::class.java)
                        if (chat != null) {
                            chatList.add(chat)
                        }
                    }
                    _chatList.value = chatList

                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("BaseViewModel", "Error fetching chat: ${error.message}")
                    _chatList.value = emptyList()
                }
            })
        }
    }

    fun addChat(newChat: ChatListModel) {
        val currentUserId = FirebaseAuth.getInstance().currentUser?.uid
        if (currentUserId != null) {
            val newChatRef = FirebaseDatabase.getInstance().getReference("chats").push()
            val chatWithUser = newChat.copy(currentUserId)

            newChatRef.setValue(chatWithUser).addOnSuccessListener {

                Log.d("BaseViewModel", "Chat added successfully")

            }.addOnFailureListener {
                Log.e("BaseViewModel", "Error adding chat: ${it.message}")


            }
        } else {
            Log.e("BaseViewModel", "Current user is null")
        }
    }

    private val databaseRef = FirebaseDatabase.getInstance().getReference("chats")
    fun sendMessage(senderPhoneNumber: String, receiverPhoneNumber: String, messageText: String) {
        val messageId = databaseRef.push().key ?: return
        val message = Massage(
            senderPhoneNumber = senderPhoneNumber,
            messageText = messageText,
            timeStamp = System.currentTimeMillis()
        )

        databaseRef.child("messages").child("senderPhoneNumber").child("receiverPhoneNumber")
            .child(messageId).setValue(message)


        databaseRef.child("messages").child("receiverPhoneNumber").child("senderPhoneNumber")
            .child(messageId).setValue(message)


    }

    fun getMessages(
        senderPhoneNumber: String,
        receiverPhoneNumber: String,
        onNewMassage: (Massage) -> Unit
    ) {
        val messageRef =
            databaseRef.child("messages").child(senderPhoneNumber).child(receiverPhoneNumber)
        messageRef.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val message = snapshot.getValue(Massage::class.java)
                if (message != null) {
                    onNewMassage(message)
                }
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })

    }

    fun fetchLastMessageForChat(
        senderPhoneNumber: String,
        receiverPhoneNumber: String,
        onLastMessageFetched: (String, String) -> Unit
    ) {
        val chatRef =
            FirebaseDatabase.getInstance().reference.child("messages").child(senderPhoneNumber)
                .child(receiverPhoneNumber)


        chatRef.orderByChild("timeStamp").limitToLast(1)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val lastMessage =
                            snapshot.children.firstOrNull()?.child("message")
                                ?.getValue(String::class.java)

                        val timeStamp =
                            snapshot.children.firstOrNull()?.child("timeStamp")
                                ?.getValue(String::class.java)

                        onLastMessageFetched(
                            lastMessage ?: "No message",
                            lastMessage ?: "--:--"
                        )


                    } else {
                        onLastMessageFetched("No message", "--:--")
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("BaseViewModel", "Error fetching last message: ${error.message}")
                    onLastMessageFetched("No message", "--:--")

                }


            })


    }

    fun loadChatList(
        currentUserPhoneNumber: String,
        onChatListLoaded: (List<ChatListModel>) -> Unit
    ) {
        val chatList = mutableListOf<ChatListModel>()
        val chatRef =
            FirebaseDatabase.getInstance().reference.child("chats").child(currentUserPhoneNumber)

        chatRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    snapshot.children.forEach { child ->
                        val phoneNumber = child.key ?: return@forEach
                        val name = child.child("name").getValue(String::class.java) ?: "Unknown"
                        val profileImage = child.child("profileImage").getValue(String::class.java)

                        fetchLastMessageForChat(
                            currentUserPhoneNumber,
                            phoneNumber
                        ) { lastMessage, time ->
                            chatList.add(
                                ChatListModel(
                                    name = name,
                                    phoneNumber = phoneNumber,
                                    profileImage = profileImage,
                                    message = lastMessage,
                                    time = time
                                )
                            )
                            if (chatList.size == snapshot.childrenCount.toInt()) {
                                onChatListLoaded(chatList)
                            }
                        }
                    }
                } else {
                    onChatListLoaded(emptyList())
                }
            }

            override fun onCancelled(error: DatabaseError) {
                onChatListLoaded(emptyList())
            }
        })
    }


    @OptIn(ExperimentalEncodingApi::class)
    private fun decodeBase64toBitmap(base64Image: String): Bitmap? {
        return try {
            val decodedBytes = Base64.decode(base64Image, android.util.Base64.DEFAULT)
            BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    @OptIn(ExperimentalEncodingApi::class)
    fun base64toBitmap(base64String: String): Bitmap? {
        return try {
            val decodedBytes = Base64.decode(base64String, android.util.Base64.DEFAULT)
            val inputStream: InputStream = ByteArrayInputStream(decodedBytes)
            BitmapFactory.decodeStream(inputStream)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}