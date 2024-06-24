package com.example.appmarvel.view.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appmarvel.R
import com.example.appmarvel.data.remote.service.DataClass
import com.example.appmarvel.databinding.FragmentHomeBinding
import com.example.appmarvel.util.base.BaseFragment
import com.example.appmarvel.util.base.ResourceState
import com.example.appmarvel.view.ui.adapter.AdapterClass
import com.example.appmarvel.view.ui.dialog.DetailsDialog
import com.example.appmarvel.view.ui.viewModel.MainViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, MainViewModel>() {

    override val viewModel: MainViewModel by viewModel()
    private val adapterClass by lazy { AdapterClass() }
    private var valueSearch: String = ""
    var isFavorite = false
    var item: ArrayList<DataClass> = arrayListOf()
    var itemInitialList: ArrayList<DataClass> = arrayListOf()
    var itemFavorite: ArrayList<DataClass> = arrayListOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observerGetAllList()
        configureRecyclerView()
        configureClickAdapter()
        configureSearch(valueSearch)
        observerSearchList()
        observerCompleteList()
        configureFavoriteClickedAdapter()

        binding.btnFab.setOnClickListener {
            Navigation.findNavController(requireView())
                .navigate(R.id.action_homeFragment_to_favoriteFragment)
        }
        binding.btnRefreshList.setOnClickListener {
            Navigation.findNavController(requireView())
                .navigate(R.id.action_homeFragmentNav_self)
        }
        binding.btnFabDelete.setOnClickListener {
            viewModel.deleteAll()
        }
    }

    private fun observerGetAllList() {
        lifecycleScope.launch {
            viewModel.listGetFavorite.collect { list ->
                list.forEach {
                    itemFavorite.add(
                        it
                    )
                }
            }
        }
    }

    private fun observerSearchList() = lifecycleScope.launch {
        viewModel.search.collect { resource ->
            when (resource) {
                is ResourceState.Success -> {
                    item.clear()
                    resource.data?.let { values ->
                        binding.pBListCharacter.visibility = GONE
                        values.forEach {
                            item.add(
                                DataClass(
                                    id = it.id,
                                    dataUrl = it.thumbnail.path,
                                    extension = it.thumbnail.extension,
                                    dataTitle = it.name,
                                    dataFavorite = isFavorite,
                                    dataDescription = it.description
                                )
                            )
                        }

                        adapterClass.dataList = item
                        configureRecyclerView()
                    }
                }

                is ResourceState.Error -> {
                    binding.pBListCharacter.visibility = GONE
                    resource.message?.let { message ->
                        Toast(context).setText(message)
                    }
                }

                is ResourceState.Load -> {
                    binding.pBListCharacter.visibility = VISIBLE
                }

                else -> {}
            }
        }
    }

    private fun observerCompleteList() = lifecycleScope.launch {
        viewModel.list.collect { resource ->
            when (resource) {
                is ResourceState.Success -> {
                    resource.data?.let { values ->
                        binding.pBListCharacter.visibility = GONE
                        values.forEach {
                            item.add(
                                DataClass(
                                    id = it.id,
                                    dataUrl = it.thumbnail.path,
                                    extension = it.thumbnail.extension,
                                    dataTitle = it.name,
                                    dataFavorite = isFavorite,
                                    dataDescription = it.description
                                )
                            )
                        }
                        itemInitialList = item
                        adapterClass.dataList = item
                    }
                }

                is ResourceState.Error -> {
                    binding.pBListCharacter.visibility = GONE
                    resource.message?.let { message ->
                        Toast.makeText(requireContext(), "$message.", Toast.LENGTH_LONG).show()
                    }
                }

                is ResourceState.Load -> {
                    binding.pBListCharacter.visibility = VISIBLE
                }

                else -> {}
            }
        }
    }


    private fun configureRecyclerView() = with(binding) {
        rvCharacters.apply {
            adapter = adapterClass
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun configureClickAdapter() = with(binding) {
        adapterClass.setOnItemClickListener { character ->
            DetailsDialog(character).show(
                childFragmentManager,
                DetailsDialog.TAG
            )
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

    private fun configureSearch(inputText: String) = with(binding) {
        titleSearchCharacter.setText(inputText)

        titleSearchCharacter.setOnEditorActionListener { _, actionId, _ ->
            val clicked = actionId == EditorInfo.IME_ACTION_GO

            if (clicked) {
                searchCharacter()
                valueSearch = titleSearchCharacter.text?.trim().toString()
                true
            } else {
                false
            }
        }

        titleSearchCharacter.setOnKeyListener { _, code, event ->
            val clickedEnter =
                event.action == KeyEvent.ACTION_DOWN && code == KeyEvent.KEYCODE_ENTER

            if (clickedEnter) {
                searchCharacter()
                valueSearch = titleSearchCharacter.text?.trim().toString()
                true
            } else {
                false
            }
        }
    }

    private fun searchCharacter() = with(binding) {
        titleSearchCharacter.text.toString().trim().let {
            viewModel.getCharacterSearch(it)
        }
    }


    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding =
        FragmentHomeBinding.inflate(inflater, container, false)
}