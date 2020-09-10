package romansytnyk.spacex.ui.capsules.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import kotlinx.android.synthetic.main.item_capsules.view.*
import romansytnyk.spacex.R
import romansytnyk.spacex.data.db.entity.CapsuleEntity


/**
 * Created by Roman on 02.03.2018
 */
class CapsulesAdapter(private var capsules: List<CapsuleEntity>) : androidx.recyclerview.widget.RecyclerView.Adapter<CapsulesAdapter.RocketViewHolder>() {

    init {
        capsules = capsules.reversed()
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RocketViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_capsules, parent, false)
        return RocketViewHolder(v)
    }

    override fun onBindViewHolder(holder: RocketViewHolder, position: Int) {
        holder.fillWith(capsules[position])
    }

    override fun getItemCount(): Int = capsules.size

    inner class RocketViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SetTextI18n")
        fun fillWith(item: CapsuleEntity) {
            itemView.name.text = item.name
            itemView.crew.text = String.format(getString(R.string.capsule_crew), item.crewCapacity)

            // Orbit Duration
            itemView.orbitDurationYears.text = String.format(getString(R.string.capsule_orbit_duration),
                    item.orbitDurationYr?.toInt())

            // Material
            itemView.material.text = String.format(getString(R.string.capsule_material),
                    item.heatShield?.material,
                    item.heatShield?.sizeMeters,
                    item.heatShield?.tempDegrees?.toInt())

            // Sidewall Angle
            itemView.sidewallAngle.text = String.format(getString(R.string.capsule_sidewall_angle),
                    item.sidewallAngleDeg)

            // Playload
            itemView.launchPayloadMass.text = String.format(getString(R.string.capsule_launch_payload),
                    item.launchPayloadMass?.kg?.toInt(),
                    item.launchPayloadMass?.lb?.toInt(),
                    item.launchPayloadVol?.cubicMeters?.toInt())

            // Active
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

        private fun getString(@StringRes id: Int): String = itemView.context.getString(id)
    }
}