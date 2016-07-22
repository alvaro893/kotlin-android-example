package es.alvaroweb.testkotlin2

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageButton
import kotlinx.android.synthetic.main.activity_other.*

class OtherActivity : AppCompatActivity() {

    private val SAVED_TIME: String = "saved-time"
    private var mChronometer: ChronometerHandler? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other)

        val savedTime = (savedInstanceState?.getInt(SAVED_TIME))

        mChronometer = ChronometerHandler(chronometer_view, this, savedTime)

        play_button.setOnClickListener { view ->
            mChronometer?.toggle()
            togglePlayButon(view as ImageButton)
        }

        reload_button.setOnClickListener { view -> mChronometer?.setTime(0) }

        plus_one_minute_button.setOnClickListener { view -> mChronometer?.add(60) }

        plus_one_hour_button.setOnClickListener { view -> mChronometer?.add(3600) }
    }

    private fun togglePlayButon(imageButton: ImageButton) {
        if(mChronometer?.isChronometerRunning!!) {
            imageButton.setImageDrawable(getDrawable(R.drawable.ic_pause_black_24dp))
        }else{
            imageButton.setImageDrawable(getDrawable(R.drawable.ic_play_arrow_black_24dp))
        }
    }

    override fun onPause() {
        super.onPause()
        mChronometer?.stop()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        val currentTime = mChronometer?.getCurrrentTime() ?: 0
        outState?.putInt(SAVED_TIME, currentTime)
    }
}


