package io.github.kabos.repository

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import io.github.kabos.StationName
import io.github.kabos.WeekTimetable
import io.github.kabos.data.takinoiTsu07
import io.github.kabos.data.takinoiTsu08
import io.github.kabos.data.tsudanumaTsu07

class DefaultTimetableRepository : TimetableRepository {
    override fun getTimetableForEachRoute(stationName: StationName): Result<List<WeekTimetable>, Exception> {
        return when (stationName) {
            StationName.takinoi -> Ok(
                listOf(
                    takinoiTsu07,
                    takinoiTsu08,
                )
            )

            StationName.tsudanuma -> Ok(
                listOf(
                    tsudanumaTsu07,
                )
            )

            else -> Err(IllegalArgumentException("定義のない駅です"))
        }
    }

    override fun getTimetableUrl(stationName: StationName): Result<String, Exception> {
        return when (stationName) {
            StationName.takinoi -> return Ok("https://www.navitime.co.jp/diagram/bus/00139553/00032180/0/")
            StationName.tsudanuma -> return Ok("https://www.navitime.co.jp/diagram/bus/00043845/00032180/1/")
            else -> Err(IllegalArgumentException("定義のない駅です"))
        }
    }
}
