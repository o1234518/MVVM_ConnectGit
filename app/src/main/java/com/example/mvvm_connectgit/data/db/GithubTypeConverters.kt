package com.example.mvvm_connectgit.data.db

import androidx.room.TypeConverter
import androidx.room.util.StringUtil
import java.util.*

class GithubTypeConverters {
    companion object {
        @TypeConverter
        fun stringToIntList(data: String): List<Int> {
            if (data == null) {
                return Collections.emptyList()
            }
            return StringUtil.splitToIntList(data)!!
        }

        @TypeConverter
        fun intListToString(ints: List<Int>): String {
            return StringUtil.joinIntoString(ints)!!
        }
    }
}