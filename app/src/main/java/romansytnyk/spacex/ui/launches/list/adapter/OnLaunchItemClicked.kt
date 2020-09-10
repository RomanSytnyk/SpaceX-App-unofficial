package romansytnyk.spacex.ui.launches.list.adapter

import romansytnyk.spacex.data.db.entity.LaunchEntity

/**
 * Created by Roman on 04.03.2018
 */
interface OnLaunchItemClicked {
    fun onLaunchItemClicked(launch: LaunchEntity)
}