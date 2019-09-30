package com.csw.android.thespeedreading.view

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.csw.android.thespeedreading.R
import com.csw.android.videofloatwindow.ui.base.IUICreator
import kotlinx.android.synthetic.main.view_number_keyboard.view.*

class NumberKeyboard : FrameLayout, IUICreator {
    var onKeyboardListener: OnKeyboardListener? = null

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        View.inflate(context, getContentViewID(), this)
        initView(this,null)
        initAdapter()
        initListener()
        initData()
    }

    override fun getContentViewID(): Int {
        return R.layout.view_number_keyboard
    }

    override fun initView(rootView: View, savedInstanceState: Bundle?) {
    }

    override fun initAdapter() {
    }

    override fun initListener() {
        v_display.setOnClickListener {
            onKeyboardListener?.onDisplayClick()
        }
        v_delete.setOnClickListener {
            onKeyboardListener?.onDeleteClick()
        }
        v_next.setOnClickListener {
            onKeyboardListener?.onNextClick()
        }
        v_number_0.setOnClickListener {
            onKeyboardListener?.onNumberClick(0)
        }
        v_number_1.setOnClickListener {
            onKeyboardListener?.onNumberClick(1)
        }
        v_number_2.setOnClickListener {
            onKeyboardListener?.onNumberClick(2)
        }
        v_number_3.setOnClickListener {
            onKeyboardListener?.onNumberClick(3)
        }
        v_number_4.setOnClickListener {
            onKeyboardListener?.onNumberClick(4)
        }
        v_number_5.setOnClickListener {
            onKeyboardListener?.onNumberClick(5)
        }
        v_number_6.setOnClickListener {
            onKeyboardListener?.onNumberClick(6)
        }
        v_number_7.setOnClickListener {
            onKeyboardListener?.onNumberClick(7)
        }
        v_number_8.setOnClickListener {
            onKeyboardListener?.onNumberClick(8)
        }
        v_number_9.setOnClickListener {
            onKeyboardListener?.onNumberClick(9)
        }
    }

    override fun initData() {
    }

    interface OnKeyboardListener {
        fun onDisplayClick()
        fun onDeleteClick()
        fun onNextClick()
        fun onNumberClick(num: Int)
    }

}