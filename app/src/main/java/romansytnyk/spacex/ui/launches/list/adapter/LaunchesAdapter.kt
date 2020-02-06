package romansytnyk.spacex.ui.launches.list.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_launches.view.*
import romansytnyk.spacex.R
import romansytnyk.spacex.data.api.model.Launch
import romansytnyk.spacex.data.db.entity.LaunchEntity
import romansytnyk.spacex.util.formatLaunchDateToNativeTimezone


/**
 * Created by Roman on 18.02.2018
 */
class LaunchesAdapter(private var launches: List<LaunchEntity>,
                      private var listener: OnLaunchItemClicked) : RecyclerView.Adapter<LaunchesAdapter.LaunchViewHolder>() {

    init {
        launches = launches.reversed()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_launches, parent, false)
        return LaunchViewHolder(v)
    }


    override fun onBindViewHolder(holder: LaunchViewHolder, position: Int) {
        holder.fillWith(launches[position])
    }

    override fun getItemCount(): Int {
        return launches.size
    }

    inner class LaunchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SetTextI18n")
        fun fillWith(item: LaunchEntity) {
            itemView.rocket.text = "${item.flightNumber}. ${item.rocket?.rocketName} (${item.rocket?.rocketType})"
            itemView.time.text = (item.launchDateLocal ?: "").formatLaunchDateToNativeTimezone()

            // Launch Place
            itemView.startPlace.visibility = View.GONE
            item.launchSite?.siteNameLong?.let {
                itemView.startPlace.text = it
                itemView.startPlace.visibility = View.VISIBLE
            }

            // Details
            itemView.description.visibility = View.GONE
            item.details?.let {
                itemView.description.text = it
                itemView.description.visibility = View.VISIBLE
            }

            // Image
            itemView.image.visibility = View.GONE
            item.links?.missionPatch?.let {
                if (it.isNotEmpty()) {
                    Glide.with(itemView.context)
                            .load(it)
                            .into(itemView.image)
                    itemView.image.visibility = View.VISIBLE
                }
            }

            // Result
            itemView.launchResult.visibility = View.GONE
            item.launchSuccess?.let {
                itemView.launchResult.visibility = View.VISIBLE
                if (it) {
                    itemView.launchResult.text = itemView.context.getText(R.string.launch_success)
                    itemView.launchResult.setTextColor(
                            ContextCompat.getColor(itemView.context, R.color.colorSuccess))
                } else {
                    itemView.launchResult.text = itemView.context.getText(R.string.launch_fail)
                    itemView.launchResult.setTextColor(
                            ContextCompat.getColor(itemView.context, R.color.colorFail))
                }
            }

            itemView.setOnClickListener {
                listener.onLaunchItemClicked(item)
            }
        }
    }
}