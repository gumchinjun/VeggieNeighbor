package com.example.veggieneighbors

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.veggieneighbors.databinding.ActivityLogInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class LogInActivity : AppCompatActivity() {

    lateinit var binding: ActivityLogInBinding
    lateinit var mAuth: FirebaseAuth
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var mDbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mAuth = Firebase.auth

        sharedPreferences = getSharedPreferences("loginPrefs", Context.MODE_PRIVATE)
        mDbRef = Firebase.database.reference

        // Check if user is already logged in using SharedPreferences
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)
        if (isLoggedIn) {
            val intent = Intent(this@LogInActivity, NaviActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.loginBtn.setOnClickListener {

            val email = binding.emailEdit.text.toString()
            val password = binding.passwordEdit.text.toString()

            Log.d("email","$email")

            login(email, password)
        }


        binding.signUpBtn.setOnClickListener {
            val intent: Intent = Intent(this@LogInActivity, SignUpActivity::class.java)

            startActivity(intent)
        }
    }


    private fun login(email: String, password: String) {

        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = mAuth.currentUser
                    user?.let {
                        val userId = it.uid

                        Log.d("ITM","current signed in user : ${user.uid}, ${user.displayName}")

                        saveLoginStatus()
                        getUserInfo(userId)

                        val intent = Intent(this@LogInActivity, NaviActivity::class.java)
                        startActivity(intent)
                        Toast.makeText(this, "Welcome back!", Toast.LENGTH_SHORT).show()
                        if (!binding.checkBox4.isChecked){
                            Log.d("ITM", "checkbox is not checked")
                            val editor = sharedPreferences.edit()
                            editor.putBoolean("isLoggedIn", false)
                            editor.apply()
                            val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", true)
                            Log.d("ITM", "isLoggedIn value: $isLoggedIn")
                        }
                        finish()
                    }

                } else {
                    // 로그인 실패 시 메시지 표시
                    Toast.makeText(this, "Failed to log in", Toast.LENGTH_SHORT).show()
                    Log.d("login", "에러: ${task.exception}")
                }
            }
    }

    private fun saveLoginStatus() {
        // Save login status in SharedPreferences
        val editor = sharedPreferences.edit()
        editor.putBoolean("isLoggedIn", true)
        editor.apply()
    }

    private fun getUserInfo(uId:String){
        val editor = sharedPreferences.edit()
        var userInfo = User("","","")
        mDbRef.child("user").child(uId)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        // dataSnapshot에서 데이터를 읽어옴
                        val name = snapshot.child("name").getValue(String::class.java)
                        Log.d("ITM","mDbRef username : $name")

                        editor.putString("currentUsername", name)
                        editor.putString("currentUserId",uId)
                        editor.apply()


                    } else {
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    // 데이터 읽기 실패 시 처리
                    println("Failed to read user data: ${error.message}")
                }
            })
    }


}