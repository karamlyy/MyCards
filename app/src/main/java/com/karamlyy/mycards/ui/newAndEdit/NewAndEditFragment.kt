package com.karamlyy.mycards.ui.newAndEdit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.karamlyy.mycards.databinding.FragmentNewAndEditBinding
import com.karamlyy.mycards.model.Type
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewAndEditFragment : Fragment() {

    private var _binding: FragmentNewAndEditBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<NewAndEditViewModel>()

    private val args by navArgs<NewAndEditFragmentArgs>()
    private var currentTypeIndex = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewAndEditBinding.inflate(inflater, container, false)

        if(args.cardId == -1) {
            binding.fragmentNewAndEditDeleteButton.isVisible = false
            (activity as AppCompatActivity?)!!.supportActionBar!!.title = "New Card"
        } else {
            (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Edit Card"
            viewModel.getCard(args.cardId)
        }
        initializeView()

        viewModel.cardModel.observe(viewLifecycleOwner) {
            binding.fragmentNewAndEditTitleEditText.setText(it.title)
            binding.fragmentNewAndEditNumberEditText.setText(it.number)
            binding.fragmentNewAndEditExpiredMonthEditText.setText(it.expiredMonth)
            binding.fragmentNewAndEditExpiredYearEditText.setText(it.expiredYear)
            binding.fragmentNewAndEditCvvEditText.setText(it.cvv)
            binding.fragmentNewAndEditHolderEditText.setText(it.holder)
            binding.fragmentNewAndEditAutoComplete.setText(it.type?.name, false)
            binding.fragmentNewAndEditCheckbox.isChecked = it.isFavorite == true



            currentTypeIndex = when(it.type) {
                Type.MASTER -> 0
                Type.VISA -> 1
                else -> 2
            }
        }


        return binding.root
    }

    private fun initializeView() {
        binding.fragmentNewAndEditSaveButton.setOnClickListener {
            handleSaveButton()
        }

        binding.fragmentNewAndEditAutoComplete.setAdapter(
            ArrayAdapter(
                requireContext(),
                android.R.layout.simple_list_item_1,
                arrayOf(Type.MASTER.name, Type.VISA.name)
            )
        )

        binding.fragmentNewAndEditAutoComplete.setOnItemClickListener { _, _, index, _ ->
            currentTypeIndex = index
        }

        binding.fragmentNewAndEditDeleteButton.setOnClickListener {
            viewModel.deleteCard()
            findNavController().popBackStack()
            Toast.makeText(requireContext(), "Successfully Deleted", Toast.LENGTH_LONG).show()

        }
    }

    private fun handleSaveButton() {
        val title = binding.fragmentNewAndEditTitleEditText.text.toString()
        val number = binding.fragmentNewAndEditNumberEditText.text.toString()
        val expiredMonth = binding.fragmentNewAndEditExpiredMonthEditText.text.toString()
        val expiredYear = binding.fragmentNewAndEditExpiredYearEditText.text.toString()
        val cvv = binding.fragmentNewAndEditCvvEditText.text.toString()
        val holder = binding.fragmentNewAndEditHolderEditText.text.toString()



        if (title.isEmpty() || number.isEmpty() || expiredMonth.isEmpty() || expiredYear.isEmpty() || cvv.isEmpty() || holder.isEmpty()) {
            Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_LONG).show()
            return
        }

        val type = when(currentTypeIndex) {
            0 -> Type.MASTER
            1 -> Type.VISA
            else -> Type.NONE
        }

        if (args.cardId == -1) {
            viewModel.insertCard(title, number,  expiredMonth, expiredYear, cvv, holder, type, binding.fragmentNewAndEditCheckbox.isChecked)
        } else {
            viewModel.updateCard(title, number,  expiredMonth, expiredYear, cvv, holder, type, binding.fragmentNewAndEditCheckbox.isChecked)
        }




        Toast.makeText(requireContext(), "Successfully Saved", Toast.LENGTH_LONG).show()
        findNavController().popBackStack()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}