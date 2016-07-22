package es.alvaroweb.testkotlin2
/**
 * Copyright (C) 2016 Alvaro Bolanos Rodriguez
 */

import android.app.Activity
import android.util.Log
import android.widget.TextView
import java.util.concurrent.TimeUnit


/** Chronometer logic, it uses a Thread */
class ChronometerHandler(chronometerView: TextView, activity: Activity, savedTime: Int?) :Runnable {
    private val mView: TextView?
    private var mCurrentTime : Int = savedTime ?: 0
    private val mActivity: Activity?
    private var  mThread: Thread
    var isChronometerRunning: Boolean = false

    init {
        mActivity = activity
        mView = chronometerView
        mView.text = formatTime(mCurrentTime)
        mThread = Thread(this)
    }


    override fun run() {
        while(isChronometerRunning && mCurrentTime < Int.MAX_VALUE){
            Thread.sleep(1000)
            // isChronometerRunning is checked again so that its state was changed during sleep()
            if(isChronometerRunning){
                mCurrentTime++
                updateChronometer()
            }
        }
    }


    private fun updateChronometer() {
        Log.d("chronometer", "time:$mCurrentTime")
       mActivity?.runOnUiThread {
           refreshTime()
       }
    }

    private fun formatTime(currentTime: Int): CharSequence? {
        val t = currentTime.toLong()
        val sec = currentTime % 60
        val min = TimeUnit.SECONDS.toMinutes(t) % 60
        val hour = TimeUnit.SECONDS.toHours(t)
        return String.format("%02d:%02d:%02d",hour, min, sec)
    }

    fun toggle(){
        if(mThread.isAlive){
            isChronometerRunning = false
        }else{
            isChronometerRunning = true
            refreshTime()
            initThread()
            mThread.start()
        }

    }

    private fun initThread() {
        mThread = Thread(this)
    }

    fun stop() {
        isChronometerRunning = false
    }

    fun setTime(time: Int?){
        mCurrentTime = time ?: return
        refreshTime()
    }

    fun  getCurrrentTime(): Int {
        return mCurrentTime;
    }

    fun refreshTime() {
        mView?.text = formatTime(mCurrentTime)
    }

    fun add(secs: Int) {
        mCurrentTime += secs
        refreshTime()
    }
}