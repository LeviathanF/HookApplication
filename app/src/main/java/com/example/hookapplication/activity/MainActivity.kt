package com.example.hookapplication.activity

import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.hookapplication.R
import com.example.hookapplication.base.BaseActivity

class MainActivity : BaseActivity() {
    private lateinit var btnClickHook:Button
    private val _tag = "hook"

    override fun getLayoutId() = R.layout.activity_main

    override fun initView() {
        btnClickHook = findViewById(R.id.btn_onclick_hook)
    }

    override fun init() {
        btnClickHook.setOnClickListener { Log.d(_tag,"click") }
        hookOnClickListener(btnClickHook)
    }

    override fun initToolsbar() {
    }

    /**
     * onClickListener绑定Hook
     */
    private fun hookOnClickListener(view:View){
//        得到 View 的 ListenerInfo 对象
        val getListenerInfo = View::class.java.getDeclaredMethod("getListenerInfo")
        getListenerInfo.isAccessible = true
        val listenerInfo = getListenerInfo.invoke(view)
//        得到 原始的 OnClickListener 对象
        val listenerClz =  Class.forName("android.view.View\$ListenerInfo")
        val mOnClickListener = listenerClz.getDeclaredField("mOnClickListener")
        mOnClickListener.isAccessible = true
        val originOnClickListener = mOnClickListener.get(listenerInfo) as View.OnClickListener
//        用自定义的 OnClickListener 替换原始的 OnClickListener
        val hookedOnClickListener = HookedOnClickListener(originOnClickListener)
        mOnClickListener.set(listenerInfo,hookedOnClickListener)
    }

    internal inner class HookedOnClickListener(val origin:View.OnClickListener):View.OnClickListener{

        override fun onClick(v: View?) {
            Toast.makeText(this@MainActivity,"hook click",Toast.LENGTH_SHORT).show()
            Log.d(_tag,"before click hook")
            origin.onClick(v)
            Log.d(_tag,"after click hook")
        }

    }
}
