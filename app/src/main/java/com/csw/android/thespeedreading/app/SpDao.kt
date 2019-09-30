package com.csw.android.thespeedreading.app

import com.csw.android.thespeedreading.util.SpUtil
import java.util.*

class SpDao {

    class Catch {
        companion object {
            private val catchSp = SpUtil.getSpUtil("Catch")

            fun saveDisplayTime(displayTime: Long) {
                catchSp.putLong("displayTime", displayTime)
            }

            fun getDisplayTime(): Long {
                return catchSp.getLong("displayTime", 500)
            }

            fun saveLevel(level: Int) {
                catchSp.putInt("level", level)
            }

            fun getLevel(): Int {
                return catchSp.getInt("level", 4)
            }

            fun saveChangeLevelMinTimesLimit(changeLevelMinTimesLimit: Int) {
                catchSp.putInt("changeLevelMinTimesLimit", changeLevelMinTimesLimit)
            }

            fun getChangeLevelMinTimesLimit(): Int {
                return catchSp.getInt("changeLevelMinTimesLimit", 10)
            }

            fun saveUpgradeScore(upgradeScore: Double) {
                catchSp.putFloat("upgradeScore", upgradeScore.toFloat())
            }

            fun getUpgradeScore(): Double {
                return catchSp.getFloat("upgradeScore", 0.8f).toDouble()
            }

            fun saveDowngradeScore(downgradeScore: Double) {
                catchSp.putFloat("downgradeScore", downgradeScore.toFloat())
            }

            fun getDowngradeScore(): Double {
                return catchSp.getFloat("downgradeScore", 0.1f).toDouble()
            }

            fun saveTotalTimes(totalTimes: Int) {
                catchSp.putInt("totalTimes", totalTimes)
            }

            fun getTotalTimes(): Int {
                return catchSp.getInt("totalTimes", 0)
            }

            fun savePassTimes(passTimes: Int) {
                catchSp.putInt("passTimes", passTimes)
            }

            fun getPassTimes(): Int {
                return catchSp.getInt("passTimes", 0)
            }

            fun saveInputNumbers(inputNumbers: LinkedList<Int>) {
                val sb = StringBuilder()
                for (number in inputNumbers) {
                    sb.append(number)
                }
                catchSp.putString("inputNumbers", sb.toString())
            }

            fun getInputNumbers(): LinkedList<Int> {
                val numbers = catchSp.getString("inputNumbers")
                return if (numbers == null) {
                    LinkedList<Int>()
                } else {
                    val result = LinkedList<Int>()
                    try {
                        var intNumbers = Integer.parseInt(numbers)
                        var num = 0
                        while (intNumbers > 0) {
                            num = intNumbers % 10
                            intNumbers = intNumbers / 10
                            result.offerFirst(num)
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                    result
                }
            }

            fun saveCurrNumbers(currNumbers: LinkedList<Int>) {
                val sb = StringBuilder()
                for (number in currNumbers) {
                    sb.append(number)
                }
                catchSp.putString("currNumbers", sb.toString())
            }

            fun getCurrNumbers(): LinkedList<Int> {
                val numbers = catchSp.getString("currNumbers")
                return if (numbers == null) {
                    LinkedList<Int>()
                } else {
                    val result = LinkedList<Int>()
                    try {
                        var intNumbers = Integer.parseInt(numbers)
                        var num = 0
                        while (intNumbers > 0) {
                            num = intNumbers % 10
                            intNumbers = intNumbers / 10
                            result.offerFirst(num)
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                    result
                }
            }

        }

    }

}