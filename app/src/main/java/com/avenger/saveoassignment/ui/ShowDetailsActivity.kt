package com.avenger.saveoassignment.ui

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.avenger.saveoassignment.R
import com.avenger.saveoassignment.application.MyApplication
import com.avenger.saveoassignment.components.GenreAdapter
import com.avenger.saveoassignment.datamodels.Show
import com.avenger.saveoassignment.repository.SampleRepository
import com.avenger.saveoassignment.showmodel.Image
import com.avenger.saveoassignment.showmodel.ShowModel
import com.avenger.saveoassignment.util.StringUtils
import com.avenger.saveoassignment.viewmodels.SampleListViewModel
import com.avenger.saveoassignment.viewmodels.SampleListViewModelFactory
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_show_details.*

class ShowDetailsActivity : AppCompatActivity() {

    private lateinit var appClass: MyApplication
    private lateinit var repository: SampleRepository
    private lateinit var sampleViewModel: SampleListViewModel
    private lateinit var selectedShowModel: ShowModel
    private lateinit var mTvShowName: TextView
    private lateinit var mTvTopTitle: TextView
    private lateinit var mTvDateTimeInfo: TextView
    private lateinit var mRvGenres: RecyclerView
    private lateinit var mRbRating: RatingBar
    private lateinit var mTvRating: TextView
    private lateinit var mTvSummary: TextView
    private lateinit var mIvSelectedImage: ImageView

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_details)
        initViewModel()
        initViews()
        selectedShowModel = sampleViewModel.getShowModelObj()
        setData()
    }

    private fun initViews() {
        mRvGenres = findViewById(R.id.rvGenres)
        mRbRating = findViewById(R.id.rbRating)
        mTvDateTimeInfo = findViewById(R.id.tvDateTimeInfo)
        mTvShowName = findViewById(R.id.tvShowName)
        mIvSelectedImage = findViewById(R.id.ivSelectedImage)
        mTvRating = findViewById(R.id.tvRating)
        mTvSummary = findViewById(R.id.tvSynopsis)
        mTvTopTitle = findViewById(R.id.tvTopTitle)
        cvBookNow.setOnClickListener {

        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setData() {

        try {
            mTvDateTimeInfo.text = StringUtils.getFormatedText(
                selectedShowModel.premiered!!,
                selectedShowModel.averageRuntime!!
            )
            Glide.with(this).load(selectedShowModel.image?.original).into(mIvSelectedImage)
            mTvShowName.text = selectedShowModel.name
            val rating = (selectedShowModel.rating?.average!! / 2).toFloat()
            mRbRating.rating = rating
            mTvRating.text = String.format("%.1f", rating)

            if (selectedShowModel.summary != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    mTvSummary.text = Html.fromHtml(
                        selectedShowModel.summary,
                        Html.FROM_HTML_MODE_COMPACT
                    )
                } else {
                    mTvSummary.text = Html.fromHtml(selectedShowModel.summary);
                }
            } else
                mTvSummary.text = "There is nothing much to share"

        } catch (e: Exception) {
            Log.d("TAG", "setData: $e")
        }

        setRecyclerView()
    }

    private fun setRecyclerView() {
        mRvGenres.layoutManager = GridLayoutManager(this, 3)
        try {
            val l = ArrayList<String>()

            for (i in 0 until selectedShowModel.genres?.size!!)
                l.add(selectedShowModel.genres!![i]!!)
            mRvGenres.adapter = GenreAdapter(l)
            mTvTopTitle.text = l[0]
        } catch (e: Exception) {
        }


    }

    private fun initViewModel() {
        appClass = application as MyApplication
        repository = appClass.repository
        val viewModelFactory = SampleListViewModelFactory(repository)
        sampleViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(SampleListViewModel::class.java)
    }

}