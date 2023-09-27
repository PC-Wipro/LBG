package com.lbg.project.view.activity

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.lbg.project.databinding.ActivityMainBinding
import com.lbg.project.models.CatsDataModel
import com.lbg.project.utils.Constants
import com.lbg.project.view.adapters.CatsListAdapter
import com.lbg.project.viewModel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), CatsListAdapter.CatDetailCallBack {
    private lateinit var binding: ActivityMainBinding
    private lateinit var catsListAdapter: CatsListAdapter
    private val viewModel: MainViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpViews()
        setObservers()
        viewModel.getCatsData()
    }

    private fun setUpViews() {
        with(binding) {
            rvCats.apply {
                catsListAdapter = CatsListAdapter(this@MainActivity, this@MainActivity)
                val lManager = GridLayoutManager(this@MainActivity, 2)
                adapter = catsListAdapter
                layoutManager = lManager
            }
        }
    }


    private fun setObservers() {
        with(viewModel) {
            catsData.observe(this@MainActivity) {
                catsListAdapter.submitList(it)
            }

            isLoading.observe(this@MainActivity) {
                if (it) {
                    binding.mainProgressBar.visibility = View.VISIBLE
                } else {
                    binding.mainProgressBar.visibility = View.GONE
                }
            }


            error.observe(this@MainActivity) {
                it.let { Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT).show() }
            }
        }


    }

    override fun callForCatDetails(cats: CatsDataModel) {
        startActivity(Intent(this, FullImageActivity::class.java).apply {
            putExtra(
                Constants.URL,
                cats.url
            )
        }, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
    }

}