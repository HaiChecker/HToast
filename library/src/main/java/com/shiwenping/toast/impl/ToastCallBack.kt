package com.shiwenping.toast.impl

interface ToastCallBack {
    fun getToastDialog(): ToastDialogListener
    fun getToastView(): ToastViewListener
}