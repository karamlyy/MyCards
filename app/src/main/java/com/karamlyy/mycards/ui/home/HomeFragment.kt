package com.karamlyy.mycards.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.karamlyy.mycards.R
import com.karamlyy.mycards.databinding.FragmentHomeBinding
import com.karamlyy.mycards.model.CardModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), CardCLickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<HomeViewModel>()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        binding.viewModel = viewModel
        binding.cardClickListener = this

        viewModel.cardList.observe(viewLifecycleOwner) {
            println(it)
        }
        binding.fragmentHomeFab.setOnClickListener {
            viewModel.insertCard()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding
    }

    override fun onCardClick(id: Int) {

    }

    override fun onCardChecked(cardModel: CardModel) {

    }


}