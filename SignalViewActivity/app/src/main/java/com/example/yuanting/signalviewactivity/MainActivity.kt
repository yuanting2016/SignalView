package com.example.yuanting.signalviewactivity

import android.app.Activity
import android.os.Bundle
import com.example.yuanting.signalviewactivity.view.SignalView
import com.example.yuanting.signalviewactivity.view.SignalView1

/**
 * Created by yuanting on 2018/7/3.
 */
class MainActivity: Activity() {

    private lateinit var  signalView1: SignalView1
    private lateinit var  signalView2:SignalView1
    private lateinit var  signalView3:SignalView
    private lateinit var  signalView4:SignalView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        signalView1 = findViewById(R.id.signalView1) as SignalView1
        signalView2 = findViewById(R.id.signalView2) as SignalView1
        signalView3 = findViewById(R.id.signalView3) as SignalView
        signalView4 = findViewById(R.id.signalView4) as SignalView
        signalView1.setSignalValue(2)

        signalView2.setSignalValue(1)
        signalView3.setSignalValue(2)
        signalView4.setSignalValue(1)
        signalView3.setOnClickListener {
            signalView3.setSignalColor(R.color.colorAccent)
        }

    }

}