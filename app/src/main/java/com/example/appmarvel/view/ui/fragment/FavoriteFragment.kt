package com.example.appmarvel.view.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appmarvel.R
import com.example.appmarvel.data.remote.service.DataClass
import com.example.appmarvel.databinding.FragmentFavoriteBinding
import com.example.appmarvel.util.base.BaseFragment
import com.example.appmarvel.view.ui.adapter.AdapterClass
import com.example.appmarvel.view.ui.viewModel.MainViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding, MainViewModel>() {

    override val viewModel: MainViewModel by viewModel()
    private val adapterClass by lazy { AdapterClass() }
    var item: ArrayList<DataClass> = arrayListOf()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFavoriteBinding =
        FragmentFavoriteBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureFavoriteClickedAdapter()
        viewModel.getAllFromListFavorite()
        observerGetAllList()
        configureRecyclerView()
        setAlert()
        binding.ivBackFavorite.setOnClickListener {
            Navigation.findNavController(requireView())
                .navigate(R.id.action_favoriteFragmentNav_to_homeFragmentNav)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        adapterClass.dataList = arrayListOf()
    }

    private fun observerGetAllList() = lifecycleScope.launch {
        viewModel.listGetFavorite.collect { list ->
            list.forEach {
                item.add(
                    it
                )
                setAlert()
                adapterClass.dataList = item
            }
        }
    }

    private fun configureRecyclerView() = with(binding) {
        rvCharactersFavorite.apply {
            adapter = adapterClass
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun configureFavoriteClickedAdapter() = with(binding) {
        adapterClass.setOnFavoriteClicked { character ->
            if (character.dataFavorite) {
                viewModel.addOnListOfFavorite(character)
            } else {
                viewModel.deleteFromListOfFavorite(character)
            }
        }
    }
    fun setAlert(){
        if(item.isNotEmpty()){
            binding.tvFavoriteListAlert.visibility = GONE
        }else{
            binding.tvFavoriteListAlert.visibility = VISIBLE
        }
    }
}