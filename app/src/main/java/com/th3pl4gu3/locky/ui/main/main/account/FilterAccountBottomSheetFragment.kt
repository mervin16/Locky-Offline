package com.th3pl4gu3.locky.ui.main.main.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.th3pl4gu3.locky.databinding.FragmentBottomSheetAccountFilterBinding

class FilterAccountBottomSheetFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentBottomSheetAccountFilterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBottomSheetAccountFilterBinding.inflate(inflater, container, false)

        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}