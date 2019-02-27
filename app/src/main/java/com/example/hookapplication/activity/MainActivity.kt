package com.example.hookapplication.activity

import android.content.Intent
import android.util.Log
import android.widget.Button
import com.example.hookapplication.R
import com.example.hookapplication.base.BaseActivity
import com.example.hookapplication.hook.ActivityHook
import com.example.hookapplication.hook.ClickHook

class MainActivity : BaseActivity() {
    private lateinit var btnClickHook:Button
    private lateinit var btnActivityHook:Button
    private val _tag = "hook"

    override fun getLayoutId() = R.layout.activity_main

    override fun initView() {
        btnClickHook = findViewById(R.id.btn_onclick_hook)
        btnActivityHook = findViewById(R.id.btn_activity_hook)
    }

    override fun init() {
        btnClickHook.setOnClickListener { Log.d(_tag,"click") }
        ClickHook.hookOnClickListener(btnClickHook)
        btnActivityHook.setOnClickListener {
            val intent = Intent(this@MainActivity,HookActivity::class.java)
            startActivity(intent)
        }
        ActivityHook.hookActivityLog()
    }

    override fun initToolsbar() {
    }
}
