package com.csw.android.thespeedreading.ui.main

import com.csw.android.thespeedreading.R
import com.csw.android.thespeedreading.ui.catch.CatchFragment
import com.csw.android.videofloatwindow.ui.base.BaseActivity
import com.csw.android.videofloatwindow.ui.base.CommonActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun getContentViewID(): Int {
        return R.layout.activity_main
    }

    override fun initListener() {
        super.initListener()
        v_catch.setOnClickListener {
            CommonActivity.openActivity(this, CatchFragment::class.java, null)
        }
    }

}