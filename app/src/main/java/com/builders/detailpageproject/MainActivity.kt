package com.builders.detailpageproject

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.builders.detailpageproject.adapters.ClubHobbieAdapter
import com.builders.detailpageproject.databinding.ActivityMainBinding
import com.builders.detailpageproject.model.ClubHobbies
import com.google.android.material.appbar.AppBarLayout
import android.content.res.ColorStateList
import android.graphics.Color


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var TAG = "MainActivity"
    private lateinit var mainActivityBinding: ActivityMainBinding
    var def: ColorStateList? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActivityBinding.root)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
        setBackArrow()
        setListener()
        setHobbies()
        setTabs()
    }

    private fun setTabs() {
        mainActivityBinding.item1.setOnClickListener(this);
        mainActivityBinding.item2.setOnClickListener(this);
        def = mainActivityBinding.item2.textColors
    }

    private fun setHobbies() {
        val dataList = mutableListOf<ClubHobbies>()
        dataList.add(ClubHobbies(R.drawable.ic_ball, "Social"))
        dataList.add(ClubHobbies(R.drawable.ic_music, "Music"))
        dataList.add(ClubHobbies(R.drawable.ic_folk, "Club"))
        dataList.add(ClubHobbies(R.drawable.ic_ball, "Sports"))
        dataList.add(ClubHobbies(R.drawable.ic_music, "Dance"))
        dataList.add(ClubHobbies(R.drawable.ic_folk, "Hotel"))

        val adapter = ClubHobbieAdapter()
        adapter.addItems(this, dataList)
        mainActivityBinding.hobbiesRecyclerRv.setHasFixedSize(true)
        mainActivityBinding.hobbiesRecyclerRv.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        mainActivityBinding.hobbiesRecyclerRv.adapter = adapter

    }

    private fun setListener() {
        mainActivityBinding.appbar.addOnOffsetChangedListener(object :
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

    @SuppressLint("ResourceAsColor")
    override fun onClick(view: View?) {
        if (view?.getId() == R.id.item1) {
            mainActivityBinding.select.animate().x(0F).setDuration(100)
            mainActivityBinding.item1.setTextColor(R.color.purple_500)
            mainActivityBinding.item2.setTextColor(def)
        } else if (view?.getId() == R.id.item2) {
            mainActivityBinding.item1.setTextColor(def)
            mainActivityBinding.item2.setTextColor(R.color.purple_500)
            val size: Int = mainActivityBinding.item2.getWidth()
            mainActivityBinding.select.animate().x(size.toFloat()).setDuration(100)
        }
    }
}