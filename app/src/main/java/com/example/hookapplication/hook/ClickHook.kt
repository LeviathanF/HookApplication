package com.example.hookapplication.hook

import android.util.Log
import android.view.View
import android.widget.Toast

/**
 *@Author：ZC
 *@Time： 2019/2/26 16:15
 *@Description：
 **/
object ClickHook{

    const val tag = "onclickHook"
    
    /**
     * onClickListener绑定Hook
     */
    fun hookOnClickListener(view: View){
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

    internal class HookedOnClickListener(private val origin: View.OnClickListener): View.OnClickListener{

        override fun onClick(v: View?) {
            Toast.makeText(v?.context,"hook click", Toast.LENGTH_SHORT).show()
            Log.d(tag,"before click hook")
            origin.onClick(v)
            Log.d(tag,"after click hook")
        }

    }
}