package com.example.hookapplication.activity

import android.widget.TextView
import com.example.hookapplication.R
import com.example.hookapplication.base.BaseActivity

/**
 *@Author：ZC
 *@Time： 2019/2/26 17:47
 *@Description：
 **/
class TargetActivity:BaseActivity(){
    private lateinit var tvShow:TextView

    override fun getLayoutId() = R.layout.activity_hook

    override fun initView() {
        tvShow = findViewById(R.id.tv_show)
    }

    override fun init() {
        tvShow.text = "TargetActivity"
    }

    override fun initToolsbar() {
        title = "TargetActivity"
    }

}