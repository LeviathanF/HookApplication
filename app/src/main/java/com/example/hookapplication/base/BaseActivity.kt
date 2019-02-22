package com.example.hookapplication.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 *@Author：ZC
 *@Time： 2018/12/3 16:09
 *@Description：封装的基础Activity
 **/
abstract class BaseActivity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initView()
        init()
        initToolsbar()
        initRv()
    }

    abstract fun getLayoutId():Int

    abstract fun initView()

    abstract fun init()

    abstract fun initToolsbar()

    open fun initRv(){}
}