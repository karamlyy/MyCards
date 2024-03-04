package com.karamlyy.mycards.ui.newAndEdit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.karamlyy.mycards.databinding.FragmentNewAndEditBinding
import com.karamlyy.mycards.model.Type
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewAndEditFragment : Fragment() {

    private var _binding: FragmentNewAndEditBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<NewAndEditViewModel>()

    private var currentTypeIndex = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewAndEditBinding.inflate(inflater, container, false)


        binding.fragmentNewAndEditSaveButton.setOnClickListener {
            insertCard()
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
        return binding.root
    }

    private fun insertCard() {
        val title = binding.fragmentNewAndEditTitleEditText.text.toString()
        val number = binding.fragmentNewAndEditNumberEditText.text.toString()
        val expiredDate = binding.fragmentNewAndEditExpiredDateEditText.text.toString()
        val cvv = binding.fragmentNewAndEditCvvEditText.text.toString()
        val holder = binding.fragmentNewAndEditHolderEditText.text.toString()

        val type = when(currentTypeIndex) {
            0 -> Type.MASTER
            1 -> Type.VISA
            else -> Type.NONE
        }

        viewModel.insertCard(title, number, expiredDate, cvv, holder, type, binding.fragmentNewAndEditCheckbox.isChecked)

        Toast.makeText(requireContext(), "Successfully Saved", Toast.LENGTH_LONG).show()
        findNavController().popBackStack()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}