package romansytnyk.spacex.ui.rockets.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import kotlinx.android.synthetic.main.item_rockets.view.*
import romansytnyk.spacex.R
import romansytnyk.spacex.data.db.entity.RocketEntity
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*


/**
 * Created by Roman on 02.03.2018
 */
class RocketsAdapter(private var rockets: List<RocketEntity>) : androidx.recyclerview.widget.RecyclerView.Adapter<RocketsAdapter.RocketViewHolder>() {

    init {
        rockets = rockets.reversed()
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RocketViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_rockets, parent, false)
        return RocketViewHolder(v)
    }

    override fun onBindViewHolder(holder: RocketViewHolder, position: Int) {
        holder.fillWith(rockets[position])
    }

    override fun getItemCount(): Int = rockets.size

    inner class RocketViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        fun fillWith(item: RocketEntity) {
            itemView.rocket.text = item.name
            itemView.description.text = item.description
            itemView.stages.text = String.format(getString(R.string.rocket_stages), item.stages)
            itemView.boosters.text = String.format(getString(R.string.rocket_boosters), item.boosters)
            itemView.firstFlight.text = String.format(getString(R.string.rocket_first_flight), item.firstFlight)
            itemView.successRate.text = String.format(getString(R.string.rocket_success_rate), item.successRatePct)

            // Params
            itemView.rocketHeight.text = String.format(
                    getString(R.string.rocket_height),
                    item.height?.meters,
                    item.height?.feet)
            itemView.rocketDiameter.text = String.format(
                    getString(R.string.rocket_diameter),
                    item.diameter?.meters,
                    item.diameter?.feet)
            itemView.rocketMass.text = String.format(
                    getString(R.string.rocket_mass),
                    item.mass?.kg?.toInt(),
                    item.mass?.lb?.toInt())
            itemView.costPerLaunch.text = String.format(
                    getString(R.string.rocket_cost_per_launch),
                    formatCost(item.costPerLaunch ?: 0))

            // First Stage
            itemView.firstStage.visibility = View.GONE
            item.firstStage?.let {
                itemView.firstStage.visibility = View.VISIBLE
                itemView.firstStage.text = String.format(getString(R.string.rocket_first_stage_info),
                        it.engines,
                        it.fuelAmountTons?.toInt(),
                        it.burnTimeSec?.toInt(),
                        if (it.reusable == true) getString(R.string.rocket_first_stage_reuseable) else "")
            }

            // Second Stage
            itemView.secondStage.visibility = View.GONE
            item.secondStage?.let {
                itemView.secondStage.visibility = View.VISIBLE
                itemView.secondStage.text = String.format(getString(R.string.rocket_second_stage_info),
                        it.engines,
                        it.burnTimeSec?.toInt())
            }

            // Status
            item.active?.let {
                if (it) {
                    itemView.status.text = String.format(
                            getString(R.string.status),
                            getString(R.string.active))
                } else {
                    itemView.status.text = String.format(
                            getString(R.string.status),
                            getString(R.string.inactive))
                }
            }
        }

        private fun formatCost(cost: Int): String {
            val formatter = NumberFormat.getInstance(Locale.US) as DecimalFormat
            val symbols = formatter.decimalFormatSymbols

            symbols.groupingSeparator = ' '
            formatter.decimalFormatSymbols = symbols
            return formatter.format(cost)
        }

        private fun getString(@StringRes id: Int): String = itemView.context.getString(id)
    }
}