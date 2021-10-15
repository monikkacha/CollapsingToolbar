package com.builders.detailpageproject

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.builders.detailpageproject.adapters.ClubHobbieAdapter
import com.builders.detailpageproject.databinding.ActivityMainBinding
import com.builders.detailpageproject.model.ClubHobbies
import com.google.android.material.appbar.AppBarLayout
import android.content.res.ColorStateList
import com.builders.detailpageproject.adapters.EventAdapter
import com.builders.detailpageproject.model.DummyData

enum class Event {
    UPCOMING, PAST
}

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var TAG = "MainActivity"
    private lateinit var mainActivityBinding: ActivityMainBinding
    var def: ColorStateList? = null
    private lateinit var eventAdapter: EventAdapter
    private lateinit var upcomingEventsList: MutableList<DummyData>
    private lateinit var pastEventsList: MutableList<DummyData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActivityBinding.root)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        setBackArrow()
        setListener()
        setHobbies()
        setTabs()
    }

    private fun setTabs() {
        upcomingEventsList = mutableListOf()
        upcomingEventsList.add(
            DummyData(
                "",
                "1st may-sat-2:00 PM",
                "A Virtual Evening Of Smooth  Jazz"
            )
        )
        upcomingEventsList.add(DummyData("", "1st may-sat-2:00 PM", "London Music Day"))
        upcomingEventsList.add(
            DummyData(
                "",
                "1st may-sat-2:00 PM",
                "International Gala Music Festival"
            )
        )

        pastEventsList = mutableListOf()
        pastEventsList.add(
            DummyData(
                "",
                "1st may-sat-2:00 PM",
                "A Virtual Evening Of Smooth  Jazz"
            )
        )

        mainActivityBinding.item1.setOnClickListener(this);
        mainActivityBinding.item2.setOnClickListener(this);
        def = mainActivityBinding.item2.textColors

        // setting adapter
        eventAdapter = EventAdapter(this)
        mainActivityBinding.eventsRecyclerView.layoutManager = LinearLayoutManager(this)
        mainActivityBinding.eventsRecyclerView.setHasFixedSize(true)
        loadData(Event.UPCOMING)
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
            loadData(Event.UPCOMING)
        } else if (view?.getId() == R.id.item2) {
            mainActivityBinding.item1.setTextColor(def)
            mainActivityBinding.item2.setTextColor(R.color.purple_500)
            val size: Int = mainActivityBinding.item2.getWidth()
            mainActivityBinding.select.animate().x(size.toFloat()).setDuration(100)
            loadData(Event.PAST)
        }
    }

    fun loadData(event: Event) {
        var data = mutableListOf<DummyData>()
        when (event) {
            Event.UPCOMING -> {
                data = upcomingEventsList
            }
            Event.PAST -> {
                data = pastEventsList
            }
            else -> {
                data = upcomingEventsList
            }
        }
        if (data.isEmpty()) {
            mainActivityBinding.noDataLl.visibility = View.VISIBLE
            mainActivityBinding.eventsRecyclerView.visibility = View.GONE
        } else {
            eventAdapter.addData(data)
            mainActivityBinding.noDataLl.visibility = View.GONE
            mainActivityBinding.eventsRecyclerView.visibility = View.VISIBLE
        }
    }
}