package romansytnyk.spacex.ui.launches.details

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import kotlinx.android.synthetic.main.activity_launch_details.*
import romansytnyk.spacex.R
import romansytnyk.spacex.data.db.entity.LaunchEntity
import romansytnyk.spacex.ui.base.BaseActivity
import romansytnyk.spacex.util.formatLaunchDateToNativeTimezone
import romansytnyk.spacex.util.formatLaunchDateToUTC
import romansytnyk.spacex.util.formatLaunchDateToUserTimezone

class LaunchDetailsActivity : BaseActivity() {
    private lateinit var launchData: LaunchEntity
    private var youTubePlayer: YouTubePlayer? = null

    companion object {
        private const val EXTRA_LAUNCH = "launch"

        fun start(context: Context?, launch: LaunchEntity) {
            val intent = Intent(context, LaunchDetailsActivity::class.java)
            intent.putExtra(EXTRA_LAUNCH, launch)
            context?.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch_details)
        launchData = intent.getParcelableExtra(EXTRA_LAUNCH) as LaunchEntity

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = "${launchData.flightNumber}. ${launchData.rocket?.rocketName} (${launchData.rocket?.rocketType})"
        initViews()
    }

    private fun initViews() {
        initBaseInformation()
        initCores()
        initPayloads()
        initLinks()
        initVideoPlayer()
    }

    @SuppressLint("SetTextI18n")
    private fun initBaseInformation() {
        val flightNumber = launchData.flightNumber
        val missionName = launchData.missionName
        var missionId = launchData.missionId ?: ""
        if (missionId.isNotBlank()) {
            missionId = "($missionId)"
        }

        info.text = String.format(getString(R.string.launch_flight_num), "#$flightNumber \n") +
                String.format(getString(R.string.launch_mission), "$missionName $missionId")


        startPlace.text = launchData.launchSite?.siteNameLong
        time.text =
                (launchData.launchDateLocal ?: "").formatLaunchDateToNativeTimezone() + "\n" +
                (launchData.launchDateUtc ?: "").formatLaunchDateToUTC() + "\n" +
                (launchData.launchDateUtc ?: "").formatLaunchDateToUserTimezone()

        // Details
        launchData.details?.let {
            description.visibility = View.VISIBLE
            description.text = it
        }

        // Status
        launchData.launchSuccess?.let {
            launchResult.visibility = View.VISIBLE
            if (it) {
                launchResult.text = getText(R.string.launch_success)
                launchResult.setTextColor(
                        ContextCompat.getColor(this, R.color.colorSuccess))
            } else {
                launchResult.text = getText(R.string.launch_fail)
                launchResult.setTextColor(
                        ContextCompat.getColor(this, R.color.colorFail))
            }
        }
    }

    private fun initLinks() {
        launchData.links?.missionPatch?.let {
            if (it.isNotEmpty()) {
                image.visibility = View.VISIBLE
                Glide.with(this)
                        .load(it)
                        .into(image)
            }
        }

        launchData.links?.articleLink?.let {
            articleLink.visibility = View.VISIBLE
            articleLink.setOnClickListener {
                openLink(launchData.links?.articleLink)
            }
        }

        launchData.links?.redditLink?.let {
            redditLink.visibility = View.VISIBLE
            redditLink.setOnClickListener {
                openLink(launchData.links?.redditLink)
            }
        }
    }

    private fun initVideoPlayer() {
        val link = launchData.links?.youTubeId ?: ""
        if (link.isNotEmpty()) {
            lifecycle.addObserver(youtubePlayerView)
            youtubePlayerView.enableBackgroundPlayback(false)
            youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    super.onReady(youTubePlayer)
                    this@LaunchDetailsActivity.youTubePlayer = youTubePlayer

                    youTubePlayer.cueVideo(link, 0f)
                }
            })
        } else {
            youtubePlayerView.visibility = View.GONE
        }
    }

    private fun initCores() {
        val coreList = launchData.rocket?.firstStage?.cores
        coreList?.let {
            firstStage.visibility = View.VISIBLE
            cores.visibility = View.VISIBLE

            var str = ""
            for (core in coreList) {
                core?.coreSerial?.let {
                    str += String.format(getString(R.string.launch_core_info),
                            core.coreSerial,
                            core.flight,
                            if (core.reused == true) getString(R.string.launch_reused_core) else getString(R.string.launch_new_core))


                    str += String.format(getString(R.string.launch_core_info),
                            core.coreSerial,
                            core.flight,
                            if (core.reused == true) getString(R.string.launch_reused_core) else getString(R.string.launch_new_core))
                }
            }
            cores.text = str
                    .replace("null", getString(R.string.launch_unknown))
                    .trim()
        }

        if (cores.text.isEmpty()) {
            firstStage.visibility = View.GONE
            cores.visibility = View.GONE
        }
    }

    private fun initPayloads() {
        val payloadList = launchData.rocket?.secondStage?.payloads
        payloadList?.let {
            secondStage.visibility = View.VISIBLE
            payload.visibility = View.VISIBLE

            var str = ""
            for (load in payloadList) {
                load?.payloadId?.let {
                    // Make customers list
                    var customers = ""
                    load.customers?.let {
                        for ((index, value) in load.customers.withIndex()) {
                            customers += value
                            if (index != load.customers.size - 1) customers += ", "
                        }
                    }

                    str += String.format(getString(R.string.launch_payload_info),
                            load.payloadId,
                            load.payloadType,
                            load.payloadMassKg?.toInt(),
                            load.orbit,
                            customers)
                }
            }
            payload.text = str
                    .replace("null", getString(R.string.launch_unknown))
                    .trim()
        }

        if (payload.text.isEmpty()) {
            secondStage.visibility = View.GONE
            payload.visibility = View.GONE
        }
    }

    override fun onPause() {
        youTubePlayer?.pause()
        super.onPause()
    }

    override fun onStop() {
        youTubePlayer?.pause()
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        youtubePlayerView.release()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun openLink(url: String?) {
        val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(webIntent)
    }
}
