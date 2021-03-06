package com.maiya.call.phone.manager

import com.maiya.call.phone.bean.PhoneMsg
import com.maiya.call.phone.mode.Callback
import com.maiya.call.phone.mode.PhoneModel

/**
 * 电话管理类
 *
 * Author : ymc
 * Date   : 2020/5/25  18:33
 * Class  : PhoneNumberManager
 */

object PhoneNumberManager {
    private var phoneMsg: PhoneMsg? = null

    /**
     * 根据电话号码 获取电话归属地和 运行商信息
     */
    @JvmStatic
    @JvmOverloads
    fun getStageTaskList(number: String, listener: OnPhoneListener? = null) {
        PhoneModel.searchMsgByPhoneNumber(number, object : Callback<PhoneMsg> {
            override fun onSuccess(response: PhoneMsg?) {
                if (null == response) {
                    return
                }
                phoneMsg = response
                listener?.onSuccess(phoneMsg)
            }

            override fun onFailed(code: Int, message: String?) {
                listener?.onFailed(code, message)
            }

        })
    }


    data class OnPhoneListenerWrapper(val listener: OnPhoneListener)

    interface OnPhoneListener {

        fun onSuccess(obj: PhoneMsg?)

        fun onFailed(code: Int?, errorMsg: String?)

    }

}