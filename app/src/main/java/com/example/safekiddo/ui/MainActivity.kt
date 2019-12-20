package com.example.safekiddo.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.safekiddo.R
import com.example.safekiddo.databinding.ActivityMainBinding
import com.example.safekiddo.di.ViewModelInjectionFactory
import com.example.safekiddo.ui.detailsActivity.DetailsActivity
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var mainActivityViewModel: MainActivityViewModel

    @Inject
    lateinit var viewModelInjectionFactory: ViewModelInjectionFactory<MainActivityViewModel>

    @Inject
    lateinit var adapter: PhonesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainActivityViewModel = ViewModelProviders.of(this, viewModelInjectionFactory)
            .get(MainActivityViewModel::class.java)
        mainActivityViewModel.getData()
        initRecyclerView()
        swipeToRefresh()
    }

    fun initRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.setDivider(R.drawable.recycler_view_divider)
        registerObservers()
        openNewActivity()

    }

    fun registerObservers() {
        mainActivityViewModel.phoneDataLiveData.observe(this, Observer { phoneDetails ->
            adapter.setPhonesList(phoneDetails)
            adapter.notifyDataSetChanged()
            swipe_refresh.isRefreshing = false
        })
        mainActivityViewModel.progressLiveData.observe(this, Observer {
            if (it) {
                binding.progressBar.visibility = View.VISIBLE
            } else binding.progressBar.visibility = View.GONE
        })
    }

    fun swipeToRefresh() {
        swipe_refresh.setOnRefreshListener {
            mainActivityViewModel.clearDatabse()
            mainActivityViewModel.getPhones(true)
        }
    }

    fun openNewActivity() {
        adapter.setOnClickListener { device ->
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("device", device)
            startActivity(intent)
        }
    }


}
