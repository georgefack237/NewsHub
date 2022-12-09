package com.health13.newshub.presentation.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.health13.newshub.databinding.FragmentHomeBinding
import com.health13.newshub.presentation.DetailsActivity
import com.health13.newshub.presentation.NewsAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    lateinit var homeViewModel: HomeViewModel
    lateinit var newsAdapter: NewsAdapter
    lateinit var breakingNews: RecyclerView

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

         breakingNews = _binding!!.breakingNewsList

        newsAdapter = NewsAdapter()
        breakingNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }


        newsAdapter.setOnItemClickListener {


            val intent = Intent(context, DetailsActivity::class.java)
            context?.startActivity(intent)
        }


        populate()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun populate(){

        homeViewModel.breakingNews.observe(viewLifecycleOwner){
            newsAdapter.differ.submitList(it)

        }

    }




}