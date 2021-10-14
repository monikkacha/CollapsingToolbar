package com.builders.detailpageproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import com.builders.detailpageproject.databinding.ActivityMainBinding
import com.google.android.material.appbar.AppBarLayout

class MainActivity : AppCompatActivity() {

    private var TAG = "MainActivity"
    private lateinit var mainActivityBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActivityBinding.root)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
        setBackArrow()
        setListener();
    }

    private fun setListener() {
        mainActivityBinding.appbar.addOnOffsetChangedListener (object :
            AppBarStateChangeListener() {
            override fun onStateChanged(appBarLayout: AppBarLayout?, state: State?) {
                if (state == State.EXPANDED) {
                    mainActivityBinding.overlayCard.visibility = View.VISIBLE
                } else if (state == State.COLLAPSED) {
                    mainActivityBinding.overlayCard.visibility = View.GONE
                }
            }
        })
    }

    private fun setBackArrow() {
        mainActivityBinding.toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_material)
        mainActivityBinding.toolbar.setNavigationOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                onBackPressed()
            }
        })
    }
}