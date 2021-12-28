package com.example.myapplication.ui.news

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.data.repositories.NewsRepository
import com.example.myapplication.data.services.RetrofitService
import com.example.myapplication.databinding.NewsFragmentBinding
import kotlinx.coroutines.flow.collectLatest

class NewsFragment : Fragment() {

    private var _binding: NewsFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: NewsViewModel

    private val adapter = NewsAdapter()

    companion object {
        fun newInstance() = NewsFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = NewsFragmentBinding.inflate(inflater, container, false)
        binding.recyclerView.adapter = adapter
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val retrofitService = RetrofitService.getInstance()
        val repository = NewsRepository(retrofitService)

        viewModel = ViewModelProvider(this, NewsViewModelFactory(repository))[NewsViewModel::class.java]

        viewModel.apply {
            articles.observe(viewLifecycleOwner, {
                adapter.setArticles(it)
            })
            errorMessage.observe(viewLifecycleOwner, {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            })
            loading.observe(viewLifecycleOwner, {
                if (it) {
                    binding.progressDialog.visibility = View.VISIBLE
                } else {
                    binding.progressDialog.visibility = View.GONE
                }
            })
            getArticles()
//            viewLifecycleOwner.lifecycleScope.launchWhenCreated {
//                articlesFlow.collectLatest { adapter.submitData(it) }
//            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}