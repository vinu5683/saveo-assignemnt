package com.avenger.saveoassignment.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.lang.Exception
import java.time.Month

class StringUtils {
    companion object {
        @RequiresApi(Build.VERSION_CODES.O)
        fun getFormatedText(date: String, runTime: Int): String {
            var rt = ""
            var rd = ""
            try {
                rt =
                    if (runTime < 60)
                        "$runTime min"
                    else
                        "${runTime / 60}h ${runTime % 60}min"

                val x = date.split("-")
                rd = "$rt | ${
                    x[2] + " " + Month.of(x[1].toInt()).toString().subSequence(0, 3) + " " + x[0]
                }"
            } catch (e: Exception) {

            }
            return rd
        }
    }

}