package com.csw.android.thespeedreading.ui.catch

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.View
import com.csw.android.thespeedreading.R
import com.csw.android.thespeedreading.app.SpDao
import com.csw.android.thespeedreading.view.NumberKeyboard
import com.csw.android.videofloatwindow.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_catch.*

class CatchFragment : BaseFragment() {
    private var displayTime = SpDao.Catch.getDisplayTime()//数字显示时间
    private var level = SpDao.Catch.getLevel()//等级
    private var changeLevelMinTimesLimit = SpDao.Catch.getChangeLevelMinTimesLimit()//改变等级的最小次数限制
    private var upgradeScore = SpDao.Catch.getUpgradeScore()//升级的正确率
    private var downgradeScore = SpDao.Catch.getDowngradeScore()//降级的正确率
    private var totalTimes = SpDao.Catch.getTotalTimes()//总次数
    private var passTimes = SpDao.Catch.getPassTimes()//通过次数
    private val inputNumbers = SpDao.Catch.getInputNumbers()//输入的数字
    private val currNumbers = SpDao.Catch.getCurrNumbers()//当前显示的数字

    override fun getContentViewID(): Int {
        return R.layout.fragment_catch
    }

    override fun initListener() {
        super.initListener()
        numberKeyBoard.onKeyboardListener = object : NumberKeyboard.OnKeyboardListener {
            override fun onDisplayClick() {
                displayCurrNumber()
            }

            override fun onDeleteClick() {
                inputNumbers.pollLast()
                updateInputNumber()
            }

            override fun onNextClick() {
                if (inputIsTrue()) {
                    passTimes++
                }
                //清空输入
                inputNumbers.clear()
                //等级修正
                checkLevel()
                //生成新号码
                generateNumber()
                updateCurrNumber()
                delayDisplayCurrNumber()
            }

            override fun onNumberClick(num: Int) {
                inputNumbers.offerLast(num)
                updateInputNumber()
                if (inputIsTrue()) {
                    onNextClick()
                }
            }
        }
    }

    private fun inputIsTrue(): Boolean {
        val inputNumbersIterator = inputNumbers.iterator()
        val currNumbersIterator = currNumbers.iterator()
        var inputIsTrue = true
        //按数字顺序逐字校对，若发现输入字数不够，或数字不对，则表明输入不正确
        while (currNumbersIterator.hasNext()) {
            if (!inputNumbersIterator.hasNext() || inputNumbersIterator.next() != currNumbersIterator.next()) {
                inputIsTrue = false
                break
            }
        }
        //到这里若输入正确答案，迭代器应该已经没有下个元素，若有，则说明用户输入的数字数量不对
        return inputIsTrue && !inputNumbersIterator.hasNext()
    }

    override fun initData() {
        super.initData()
        if (currNumbers.isEmpty()) {
            generateNumber()
        }
        updateCurrNumber()
        displayCurrNumber()
        if (!inputNumbers.isEmpty()) {
            updateInputNumber()
        }
    }

    /**
     * 检查当前难度等级是否满足需求
     */
    private fun checkLevel() {
        if (totalTimes >= changeLevelMinTimesLimit) {
            val score = passTimes.toDouble() / totalTimes
            if (score >= upgradeScore) {
                level++
                totalTimes = 0
                passTimes = 0
            } else if (score < downgradeScore) {
                level--
                totalTimes = 0
                passTimes = 0
            }
        }
    }

    /**
     * 根据当前等级生成新号码
     */
    private fun generateNumber() {
        totalTimes++
        currNumbers.clear()
        for (i in 0 until level) {
            currNumbers.offerLast((Math.random() * 10).toInt())
        }
    }

    /**
     * 更新用户输入的数字
     */
    private fun updateInputNumber() {
        val spannableStringBuilder = SpannableStringBuilder()
        val inputNumbersIterator = inputNumbers.iterator()
        val currNumbersIterator = currNumbers.iterator()
        //按数字顺序逐字校对，若发现输入字数不够，或数字不对，则表明输入不正确
        var input: Int
        while (inputNumbersIterator.hasNext()) {
            input = inputNumbersIterator.next()
            spannableStringBuilder.append(input.toString())
            if (currNumbersIterator.hasNext()) {
                if (input == currNumbersIterator.next()) {
                    spannableStringBuilder.setSpan(ForegroundColorSpan(Color.GREEN), spannableStringBuilder.length - 1, spannableStringBuilder.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                } else {
                    spannableStringBuilder.setSpan(ForegroundColorSpan(Color.RED), spannableStringBuilder.length - 1, spannableStringBuilder.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                }
            } else {
                spannableStringBuilder.setSpan(ForegroundColorSpan(Color.BLUE), spannableStringBuilder.length - 1, spannableStringBuilder.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
        }
        tv_input.text = spannableStringBuilder
    }

    /**
     * 更新当前数字
     */
    private fun updateCurrNumber() {
        val sb = StringBuilder()
        val currNumbersIterator = currNumbers.iterator()
        for (i in currNumbersIterator) {
            sb.append(i)
        }
        tv_number.text = sb
    }

    /**
     * 显示号码
     */
    private fun displayCurrNumber() {
        tv_number.visibility = View.VISIBLE
        tv_number.postDelayed(
                { tv_number.visibility = View.GONE },
                displayTime
        )
    }


    private fun delayDisplayCurrNumber() {
        tv_number.postDelayed(
                {
                    updateInputNumber()
                    displayCurrNumber()
                },
                1000
        )
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        saveData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        saveData()
    }

    private fun saveData() {
        SpDao.Catch.saveDisplayTime(displayTime)
        SpDao.Catch.saveLevel(level)
        SpDao.Catch.saveChangeLevelMinTimesLimit(changeLevelMinTimesLimit)
        SpDao.Catch.saveUpgradeScore(upgradeScore)
        SpDao.Catch.saveDowngradeScore(downgradeScore)
        SpDao.Catch.saveTotalTimes(totalTimes)
        SpDao.Catch.savePassTimes(passTimes)
        SpDao.Catch.saveInputNumbers(inputNumbers)
        SpDao.Catch.saveCurrNumbers(currNumbers)
    }
}