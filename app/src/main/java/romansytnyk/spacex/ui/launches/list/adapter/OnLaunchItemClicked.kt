package romansytnyk.spacex.ui.launches.list.adapter

import romansytnyk.spacex.data.api.model.Launch

/**
 * Created by Roman on 04.03.2018
 */
interface OnLaunchItemClicked {
    fun onLaunchItemClicked(launch: Launch)
}