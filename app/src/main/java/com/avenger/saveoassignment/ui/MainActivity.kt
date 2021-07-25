package com.avenger.saveoassignment.ui

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Pair
import android.view.View
import android.widget.ImageView
import android.widget.ScrollView
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.avenger.saveoassignment.R
import com.avenger.saveoassignment.application.MyApplication
import com.avenger.saveoassignment.components.ItemListenerInterface
import com.avenger.saveoassignment.components.ShowListAdapter
import com.avenger.saveoassignment.components.SliderAdapter
import com.avenger.saveoassignment.datamodels.SampleResponse
import com.avenger.saveoassignment.repository.SampleRepository
import com.avenger.saveoassignment.showmodel.ShowModel
import com.avenger.saveoassignment.viewmodels.SampleListViewModel
import com.avenger.saveoassignment.viewmodels.SampleListViewModelFactory
import kotlin.math.abs

class MainActivity : AppCompatActivity(), ItemListenerInterface {

    private var carouselList: ArrayList<SampleResponse> = ArrayList()
    private var showList: ArrayList<ShowModel> = ArrayList()
    private var showListAdapter = ShowListAdapter(showList, this)
    private lateinit var viewPager2: ViewPager2
    private lateinit var appClass: MyApplication
    private lateinit var repository: SampleRepository
    private lateinit var sampleViewModel: SampleListViewModel
    private lateinit var mRvList: RecyclerView
    private lateinit var mSvMainActivity: ScrollView

    private var pageValue: Int = 1;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        initViewModel()
        getResponseAndPost()
    }

    private fun getResponseAndPost() {
        sampleViewModel.getAllResponse("game of").observe(this, {
            if (it != null && it.isNotEmpty()) {
                carouselList.clear()
                carouselList.addAll(it)
                setViewPagerForScrollCarousel()
            }
        })
        sampleViewModel.getByPage(pageValue).observe(this, {
            if (it != null && it.isNotEmpty()) {
                showList.clear()
                Log.d("TAG", "getResponseAndPost: $it")
                showList.addAll(it)
                setRecyclerView()
            }
        })
    }

    private fun setRecyclerView() {
        mRvList.layoutManager = GridLayoutManager(this, 3)
        showListAdapter = ShowListAdapter(showList, this)
        mRvList.adapter = showListAdapter
        showListAdapter.notifyDataSetChanged()

    }

    private fun initViewModel() {
        appClass = application as MyApplication
        repository = appClass.repository
        val viewModelFactory = SampleListViewModelFactory(repository)
        sampleViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(SampleListViewModel::class.java)
    }

    private fun initViews() {
        viewPager2 = findViewById(R.id.viewPager2)
        mRvList = findViewById(R.id.rvListMainActivity)
    }

    private fun setViewPagerForScrollCarousel() {
        val adapter = SliderAdapter(carouselList, viewPager2)
        viewPager2.adapter = adapter
        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.offscreenPageLimit = 3
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(40))
        compositePageTransformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.15f
        }
        viewPager2.setPageTransformer(compositePageTransformer)
        adapter.notifyDataSetChanged()
    }

    override fun onItemClick(showModel: ShowModel, imageView: ImageView) {
        sampleViewModel.setShowModelObj(showModel)
        val intent = Intent(this, ShowDetailsActivity::class.java)
        val options =
            ActivityOptions.makeSceneTransitionAnimation(this, Pair(imageView, "imageTransition"))
        startActivity(intent, options.toBundle())
    }
}