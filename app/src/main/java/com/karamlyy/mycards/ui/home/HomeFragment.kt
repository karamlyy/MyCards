package com.karamlyy.mycards.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.karamlyy.mycards.R
import com.karamlyy.mycards.databinding.FragmentHomeBinding
import com.karamlyy.mycards.model.CardModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.appcompat.widget.SearchView

@AndroidEntryPoint
class HomeFragment : Fragment(), CardCLickListener, SearchView.OnQueryTextListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<HomeViewModel>()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.cardClickListener = this

        setHasOptionsMenu(true)


        binding.fragmentHomeFab.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_newAndEditFragment)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)

        val search = menu.findItem(R.id.action_search)
        val searchView = search.actionView as? SearchView

        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)
    }

    override fun onCardClick(id: Int) {
        val action = HomeFragmentDirections.actionHomeFragmentToNewAndEditFragment(id)
        findNavController().navigate(action)
    }

    override fun onCardChecked(cardModel: CardModel) {
        viewModel.updateCard(cardModel)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let {
            viewModel.searchCard(it)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        newText?.let {
            viewModel.searchCard(it)
        }

        return true
    }
}