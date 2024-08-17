package io.github.kabos.repository

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import io.github.kabos.bus.core.model.BusRouteName
import io.github.kabos.bus.core.model.StationName
import io.github.kabos.bus.core.model.Tsu0506
import io.github.kabos.bus.core.model.Tsu07
import io.github.kabos.bus.core.model.Tsu08
import io.github.kabos.bus.core.model.WeekTimetable
import io.github.kabos.data.takinoiTsu07
import io.github.kabos.data.takinoiTsu08
import io.github.kabos.data.tsudanumaTsu0506
import io.github.kabos.data.tsudanumaTsu07
import io.github.kabos.data.tsudanumaTsu08

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
                    tsudanumaTsu0506,
                    tsudanumaTsu07,
                    tsudanumaTsu08,
                )
            )

            else -> Err(IllegalArgumentException("定義のない駅です"))
        }
    }

    override fun getTimetableUrl(busRouteName: BusRouteName): Result<String, Exception> {
        return when (busRouteName.departureStationName) {
            StationName.takinoi -> {
                when (busRouteName.name) {
                    Tsu07 -> Ok("https://www.navitime.co.jp/diagram/bus/00139553/00032187/0/")
                    Tsu08 -> Ok("https://www.navitime.co.jp/diagram/bus/00139553/00032180/0/")
                    else -> Err(IllegalArgumentException("定義のない駅です"))
                }
            }

            StationName.tsudanuma -> {
                when (busRouteName.name) {
                    Tsu0506 -> Ok("https://www.navitime.co.jp/diagram/bus/00043845/00032183/1/")
                    Tsu07 -> Ok("https://www.navitime.co.jp/diagram/bus/00043845/00032187/1/")
                    Tsu08 -> Ok("https://www.navitime.co.jp/diagram/bus/00043845/00032180/1/")
                    else -> Err(IllegalArgumentException("定義のない駅です"))
                }
            }

            else -> Err(IllegalArgumentException("定義のない駅です"))
        }
    }
}
