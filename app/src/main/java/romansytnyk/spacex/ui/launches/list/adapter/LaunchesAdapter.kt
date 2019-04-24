package romansytnyk.spacex.ui.launches.list.adapter

import android.annotation.SuppressLint
import android.support.annotation.StringRes
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_launches.view.*
import romansytnyk.spacex.R
import romansytnyk.spacex.data.api.model.Launch
import romansytnyk.spacex.util.formatLaunchDateToNativeTimezone


/**
 * Created by Roman on 18.02.2018
 */
class LaunchesAdapter(private var futureLaunches: List<Launch>,
                      private var pastLaunches: List<Launch>,
                      private var listener: OnLaunchClicked) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val allLaunches: List<Launch>

    companion object {
        private const val TYPE_TITLE_FUTURE = 0
        private const val TYPE_TITLE_PAST = 1
        private const val TYPE_LAUNCH_INFO = 3
    }

    init {
        futureLaunches = futureLaunches.reversed()
        pastLaunches = pastLaunches.reversed()
        allLaunches = futureLaunches + pastLaunches
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_TITLE_PAST -> {
                val v = LayoutInflater.from(parent.context).inflate(R.layout.item_launches_title, parent, false)
                TitleViewHolder(v)
            }
            TYPE_TITLE_FUTURE -> {
                val v = LayoutInflater.from(parent.context).inflate(R.layout.item_launches_title, parent, false)
                TitleViewHolder(v)
            }
            else -> {
                val v = LayoutInflater.from(parent.context).inflate(R.layout.item_launches, parent, false)
                LaunchViewHolder(v)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(position) {
            0 -> {
                if (futureLaunches.isEmpty()) {
                    TYPE_TITLE_PAST
                } else {
                    TYPE_TITLE_FUTURE
                }
            }
            futureLaunches.size + 1 -> {
                if (futureLaunches.isEmpty()) {
                    TYPE_LAUNCH_INFO
                } else {
                    TYPE_TITLE_PAST
                }
            }

            else -> TYPE_LAUNCH_INFO
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            TYPE_TITLE_FUTURE -> (holder as TitleViewHolder).fillWith(R.string.future_launches)
            TYPE_TITLE_PAST -> (holder as TitleViewHolder).fillWith(R.string.past_launches)
            TYPE_LAUNCH_INFO -> {
                var offset = 1
                if (futureLaunches.isNotEmpty() && position > futureLaunches.size) offset += 1
                (holder as LaunchViewHolder).fillWith(allLaunches[position - offset])
            }
        }
    }

    override fun getItemCount(): Int {
        var size = allLaunches.size + 1
        if (futureLaunches.isNotEmpty()) size += 1
        return size
    }

    inner class LaunchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SetTextI18n")
        fun fillWith(item: Launch) {
            itemView.rocket.text = "${item.flightNumber}. ${item.rocket?.rocketName} (${item.rocket?.rocketType})"
            itemView.time.text = (item.launchDateLocal ?: "").formatLaunchDateToNativeTimezone()

            itemView.startPlace.visibility = View.GONE
            item.launchSite?.siteNameLong?.let {
                itemView.startPlace.text = it
                itemView.startPlace.visibility = View.VISIBLE
            }

            itemView.description.visibility = View.GONE
            item.details?.let {
                itemView.description.text = it
                itemView.description.visibility = View.VISIBLE
            }

            itemView.image.visibility = View.GONE
            item.links?.missionPatch?.let {
                if (it.isNotEmpty()) {
                    Glide.with(itemView.context)
                            .load(it)
                            .into(itemView.image)
                    itemView.image.visibility = View.VISIBLE
                }
            }

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

    inner class TitleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun fillWith(@StringRes id: Int) {
            val title: TextView = itemView.findViewById(R.id.title)
            title.setText(id)
        }
    }
}