package com.counterappmvvmandhilt

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log


class MyService: Service()  {


    override fun onCreate() {
        super.onCreate()

        Log.d("MyService", "Service got created")
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val count  = intent?.getIntExtra(Constants.COUNT,0)
       Log.d(Constants.COUNT,"$count")
        stopSelf()
        return START_STICKY
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d("MyService", "Service stopped")
        // stopping the process


    }

}