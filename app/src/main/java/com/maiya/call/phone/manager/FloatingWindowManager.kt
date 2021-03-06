package com.maiya.call.phone.manager

import android.content.Context
import com.maiya.call.phone.impl.IPhoneCallListenerImpl
import com.maiya.call.phone.utils.CacheUtils
import com.maiya.call.phone.view.FloatingWindow

/**
 * 悬浮窗管理类
 *
 * Author : ymc
 * Date   : 2020/5/11  20:19
 */
class FloatingWindowManager private constructor() {
    var videoLink: String? = null
    var context: Context? = null
    var mp4Url = "http://mp4.vjshi.com/2013-05-28/2013052815051372.mp4"
    companion object {
        val instance: FloatingWindowManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            FloatingWindowManager()
        }
    }

    private var fw: FloatingWindow? = null

    fun initManager(context: Context) {
        this.context = context
    }

    fun show(number: String?, isCallIn: Boolean) {
        if(context != null){
            videoLink = CacheUtils.getString(CacheUtils.SP_FILE_KEY, mp4Url)
            fw = FloatingWindow(
                    context,
                    videoLink,
                    IPhoneCallListenerImpl()
            )
            fw?.show(number, isCallIn)
        }
    }

    fun dismiss() {
        fw?.dismiss()
    }
}