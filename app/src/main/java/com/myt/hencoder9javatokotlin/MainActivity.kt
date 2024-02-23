package com.myt.hencoder9javatokotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.myt.base.core.utils.CacheUtils
import com.myt.base.core.utils.Utils
import com.myt.hencoder9javatokotlin.entity.User
import com.myt.hencoder9javatokotlin.widget.CodeView
import com.myt.lesson.LessonActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var et_username: EditText
    private lateinit var et_password: EditText
    private lateinit var et_code: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        et_username = findViewById<EditText>(R.id.et_username).apply {
            setText(CacheUtils.get(usernameKey))
        }
        et_password = findViewById<EditText>(R.id.et_password).apply {
            setText(CacheUtils.get(passwordKey))
        }
        et_code = findViewById(R.id.et_code)

        findViewById<Button>(R.id.btn_login).apply {
            setOnClickListener(this@MainActivity)
        }
        findViewById<CodeView>(R.id.code_view).apply{
            setOnClickListener(this@MainActivity)
        }
    }

    override fun onClick(v: View?) {
        if(v is CodeView){
            v.updateCode()
        }else if(v is Button){
            login()
        }
    }

    private fun login(){
        val username = et_username.text.toString()
        val password = et_password.text.toString()
        val code = et_code.text.toString()

        val user = User(username, password, code)
        if(verify(user)){
            CacheUtils.save(usernameKey, username)
            CacheUtils.save(passwordKey, password)
            startActivity(Intent(this, LessonActivity::class.java))
        }
    }

    private fun verify(user: User): Boolean{
        user.username?.let {
            if(it.length < 4){
                Utils.toast("用户名不合法")
                return false
            }
        }
        user.password?.let {
            if(it.length < 4){
                Utils.toast("密码不合法")
                return false
            }
        }
        return true
    }

    companion object{
        private const val usernameKey = "username"
        private const val passwordKey = "password"
    }
}