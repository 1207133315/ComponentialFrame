package com.liningkang.ndk_demo

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class NdkDemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ndk_demo)

        Bitmap.createBitmap(1111,111,Bitmap.Config.RGB_565 )
    }

}