//package com.example.whatsapp.presentation.viewmodel
//
//import android.app.Activity
//import android.content.Context
//import android.graphics.Bitmap
//import android.util.Base64
//import android.util.Log
//import androidx.lifecycle.ViewModel
//import com.example.whatsapp.Model.phoneAuthUser
//import com.google.firebase.FirebaseException
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.auth.PhoneAuthCredential
//import com.google.firebase.auth.PhoneAuthOptions
//import com.google.firebase.auth.PhoneAuthProvider
//import com.google.firebase.database.FirebaseDatabase
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.asStateFlow
//import java.io.ByteArrayOutputStream
//import java.util.concurrent.TimeUnit
//import javax.inject.Inject
//
//@HiltViewModel
//class PhoneAuthViewModel @Inject constructor(
//    private val firebase: FirebaseAuth,
//    private val database: FirebaseDatabase
//) : ViewModel() {
//    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
//    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
//    var authState = _authState.asStateFlow()
//    private val userRef = database.reference.child("users")
//
//    fun sendVerificationCode(phoneNumber: String, activity: Activity) {
//        _authState.value = AuthState.Loading
//        val options = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//
//            override fun onCodeSent(id: String, token: PhoneAuthProvider.ForceResendingToken) {
//                super.onCodeSent(id, token)
//                Log.d("PhoneAuth", "onCodeSend triggered.verification ID: $id")
//                _authState.value = AuthState.CodeSent(verificationId = id)
//
//            }
//
//
//            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
//                signInWithCredential(credential, context = activity)
//
//            }
//
//            override fun onVerificationFailed(exeption: FirebaseException) {
//                Log.e("PhoneAuth", "onVerificationFailed: ${exeption.message}")
//                _authState.value = AuthState.Error(exeption.message ?: "Verification failed")
//
//            }
//
//        }
//
//
//        val phoneAuthOptions = PhoneAuthOptions.newBuilder(firebase)
//            .setPhoneNumber(phoneNumber)
//            .setTimeout(60L, TimeUnit.SECONDS)
//            .setActivity(activity)
//            .setCallbacks(options)
//            .build()
//
//        PhoneAuthProvider.verifyPhoneNumber(phoneAuthOptions)
//
//    }
//
//    private fun signInWithCredential(credential: PhoneAuthCredential, context: Context) {
//        _authState.value = AuthState.Loading
//        firebase.signInWithCredential(credential).addOnCompleteListener { task ->
//            if (task.isSuccessful) {
//                val user = firebase.currentUser
//
//                val phoneAuthUser = phoneAuthUser(
//                    userId = user?.uid ?: "",
//                    phoneNumber = user?.phoneNumber ?: ""
//                )
//                markUserAsSignedIn(context)
//                _authState.value = AuthState.Success(phoneAuthUser)
//
//                fetchUserProfile(user?.uid ?: "")
//            } else {
//                _authState.value = AuthState.Error(task.exception?.message ?: "Sign in failed")
//
//            }
//        }
//    }
//
//    private fun markUserAsSignedIn(context: Context) {
//        val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
//        sharedPreferences.edit().putBoolean("isSignedIn", true).apply()
//    }
//
//    private fun fetchUserProfile(userId: String) {
//        userRef.child(userId).get().addOnSuccessListener { snapshot ->
//            if (snapshot.exists()) {
//                val userProfile = snapshot.getValue(phoneAuthUser::class.java)
//                if (userProfile != null) {
//                    _authState.value = AuthState.Success(userProfile)
//                }
//
//
//            }
//        }.addOnFailureListener {
//            _authState.value = AuthState.Error(it.message ?: "Failed to fetch user profile")
//        }
//    }
//
//    fun verifyCode(otp: String, context: Context) {
//        val currentAuthState = authState.value
//        if (currentAuthState !is AuthState.CodeSent || currentAuthState.verificationId.isEmpty()) {
//            Log.d("PhoneAuth", "Attempting to verify OTP without a valid verification ID")
//            _authState.value = AuthState.Error("Invalid verification ID")
//            return
//        }
//
//        val credential = PhoneAuthProvider.getCredential(currentAuthState.verificationId, otp)
//        signInWithCredential(credential, context as Activity)
//    }
//
//    fun saveUserProfile(userid: String, name: String, status: String, profileImage: Bitmap?) {
//
//        val database = FirebaseDatabase.getInstance()
//        val encodedImage = profileImage?.let {
//            convertBitmapToBase64(it)
//        }
//        val userProfile = phoneAuthUser(
//            userId = userid,
//            name = name,
//            status = status,
//            phoneNumber = FirebaseAuth.getInstance().currentUser?.phoneNumber ?: "",
//            profileImage = encodedImage
//        )
//        database.reference.child("users").child(userid).setValue(userProfile)
//    }
//
//    private fun convertBitmapToBase64(bitmap: Bitmap): String {
//        val byteArrayOutputStream = ByteArrayOutputStream()
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
//        val byteArray = byteArrayOutputStream.toByteArray()
//        return Base64.encodeToString(byteArray, Base64.DEFAULT)
//    }
//
//    fun resetAuthState() {
//        _authState.value = AuthState.Idle
//
//    }
//
//    private fun signOut(activity: Activity) {
//        firebaseAuth.signOut()
//        val sharedPreferences = activity.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
//        sharedPreferences.edit().putBoolean("isSignedIn", false).apply()
//        _authState.value = AuthState.Idle
//    }
//
//
//}
//
//
//sealed class AuthState {
//    object Idle : AuthState()
//    object Loading : AuthState()
//    data class CodeSent(val verificationId: String) : AuthState()
//    data class Success(val user: phoneAuthUser) : AuthState()
//    data class Error(val message: String) : AuthState()
//}

package com.example.whatsapp.presentation.viewmodel

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.util.Base64
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.whatsapp.Model.phoneAuthUser
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.database.FirebaseDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.io.ByteArrayOutputStream
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class PhoneAuthViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth, // Use injected instance only
    private val database: FirebaseDatabase
) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState = _authState.asStateFlow()
    private val userRef = database.reference.child("users")

    fun sendVerificationCode(phoneNumber: String, activity: Activity) {
        _authState.value = AuthState.Loading
        val options = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onCodeSent(id: String, token: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(id, token)
                Log.d("PhoneAuth", "onCodeSent triggered. Verification ID: $id")
                _authState.value = AuthState.CodeSent(verificationId = id)
            }

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                signInWithCredential(credential, activity)
            }

            override fun onVerificationFailed(exception: FirebaseException) { // Fixed typo
                Log.e("PhoneAuth", "onVerificationFailed: ${exception.message}")
                _authState.value = AuthState.Error(exception.message ?: "Verification failed")
            }
        }

        val phoneAuthOptions = PhoneAuthOptions.newBuilder(firebaseAuth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(options)
            .build()

        PhoneAuthProvider.verifyPhoneNumber(phoneAuthOptions)
    }

    private fun signInWithCredential(credential: PhoneAuthCredential, context: Context) {
        _authState.value = AuthState.Loading
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = firebaseAuth.currentUser
                val phoneAuthUser = phoneAuthUser(
                    userId = user?.uid ?: "",
                    phoneNumber = user?.phoneNumber ?: ""
                )
                markUserAsSignedIn(context)
                _authState.value = AuthState.Success(phoneAuthUser)
                fetchUserProfile(user?.uid ?: "")
            } else {
                _authState.value = AuthState.Error(task.exception?.message ?: "Sign in failed")
            }
        }
    }

    fun verifyCode(otp: String, context: Context) {
        val currentAuthState = _authState.value
        if (currentAuthState !is AuthState.CodeSent || currentAuthState.verificationId.isEmpty()) {
            Log.e("PhoneAuth", "Attempting to verify OTP without a valid verification ID")
            _authState.value =
                AuthState.Error("Invalid verification ID. Please request a new code.")
            return
        }

        try {
            val credential = PhoneAuthProvider.getCredential(currentAuthState.verificationId, otp)
            // Pass context instead of casting to Activity
            signInWithCredential(credential, context)
        } catch (e: Exception) {
            Log.e("PhoneAuth", "Error creating credential: ${e.message}")
            _authState.value = AuthState.Error("Invalid OTP format")
        }
    }

    private fun markUserAsSignedIn(context: Context) {
        val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().putBoolean("isSignedIn", true).apply()
    }

    private fun fetchUserProfile(userId: String) {
        userRef.child(userId).get().addOnSuccessListener { snapshot ->
            if (snapshot.exists()) {
                val userProfile = snapshot.getValue(phoneAuthUser::class.java)
                if (userProfile != null) {
                    _authState.value = AuthState.Success(userProfile)
                } else {
                    _authState.value = AuthState.Error("User profile data is corrupted")
                }
            } else {
                // User doesn't exist in database yet, this is normal for new users
                Log.d("PhoneAuth", "User profile doesn't exist yet for userId: $userId")
            }
        }.addOnFailureListener {
            Log.e("PhoneAuth", "Failed to fetch user profile: ${it.message}")
            _authState.value = AuthState.Error(it.message ?: "Failed to fetch user profile")
        }
    }

    fun saveUserProfile(userId: String, name: String, status: String, profileImage: Bitmap?) {
        _authState.value = AuthState.Loading

        val encodedImage = profileImage?.let { compressAndEncodeImage(it) }

        val userProfile = phoneAuthUser(
            userId = userId,
            name = name,
            status = status,
            phoneNumber = firebaseAuth.currentUser?.phoneNumber ?: "",
            profileImage = encodedImage
        )

        userRef.child(userId).setValue(userProfile)
            .addOnSuccessListener {
                _authState.value = AuthState.Success(userProfile)
                Log.d("PhoneAuth", "User profile saved successfully")
            }
            .addOnFailureListener { exception ->
                Log.e("PhoneAuth", "Failed to save user profile: ${exception.message}")
                _authState.value = AuthState.Error(exception.message ?: "Failed to save profile")
            }
    }

    private fun compressAndEncodeImage(bitmap: Bitmap): String {
        // Compress image to reduce size before encoding
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(
            Bitmap.CompressFormat.JPEG,
            70,
            byteArrayOutputStream
        ) // Reduced quality for smaller size
        val byteArray = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(byteArray, Base64.DEFAULT)
    }

    fun resetAuthState() {
        _authState.value = AuthState.Idle
    }

    fun signOut(context: Context) {
        firebaseAuth.signOut()
        val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().putBoolean("isSignedIn", false).apply()
        _authState.value = AuthState.Idle
        Log.d("PhoneAuth", "User signed out successfully")
    }
}

sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    data class CodeSent(val verificationId: String) : AuthState()
    data class Success(val user: phoneAuthUser) : AuthState()
    data class Error(val message: String) : AuthState()
}