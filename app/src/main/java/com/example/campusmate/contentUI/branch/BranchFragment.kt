package com.example.campusmate.contentUI.branch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.campusmate.databinding.BranchFragmentBinding

class BranchFragment : Fragment() {
    private lateinit var binding: BranchFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = BranchFragmentBinding.inflate(inflater, container, false)
        val items : List<Item> = Loader().getDataSet()
        binding.branchRecyclerView.adapter = Adapter(requireContext(), items)
        binding.branchRecyclerView.setHasFixedSize(true)
        return binding.root
    }
}