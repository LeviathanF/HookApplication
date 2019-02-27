package com.example.hookapplication.hook

import android.util.Log
import com.example.hookapplication.handler.AmsInvocationHandler
import java.lang.reflect.Field
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

/**
 *@Author：ZC
 *@Time： 2019/2/26 16:29
 *@Description：hookActivity 跳转
 **/
object ActivityHook{

    fun hookActivityLog(){
        val version = android.os.Build.VERSION.SDK_INT
        when(version){
            android.os.Build.VERSION_CODES.P -> hookAMS28()
            else -> return
        }
    }

    private fun hookAMS28(){
        val amnClass = Class.forName("android.app.ActivityManager")
        val defaultFiled = amnClass.getDeclaredField("IActivityManagerSingleton")
        defaultFiled.isAccessible = true
        val defaultValue = defaultFiled.get(null)

        val singletonClass = Class.forName("android.util.Singleton")
        val mInstance = singletonClass.getDeclaredField("mInstance")
        mInstance.isAccessible = true
        val iActivityManagerObj = mInstance.get(defaultFiled)

        val iActivityManager = Class.forName("android.app.IActivityManager")
        val handler = AmsInvocationHandler(iActivityManagerObj)
        val proxy = Proxy.newProxyInstance(Thread.currentThread().contextClassLoader, arrayOf(iActivityManager),handler)
        mInstance.set(defaultFiled,proxy)
    }
}