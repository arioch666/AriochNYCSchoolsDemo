package com.arioch.ariochnycschoolsdemo.ui.schools

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arioch.ariochnycschoolsdemo.databinding.NycSchoolsListItemBinding
import com.arioch.ariochnycschoolsdemo.ui.data.NYCSchoolsListUiObj

/**
 * Adapter for the recyclerView that will display the list of NYC Schools.
 *
 * @param nycSchoolsList the list of NYC Schools to display.
 *
 * @see RecyclerView.Adapter
 * @see NYCSchoolsListUiObj
 * @see NycSchoolsListItemBinding
 *
 * @author Arioch
 */
class NYCSchoolsRecyclerViewAdapter(private var nycSchoolsList: List<NYCSchoolsListUiObj>):
    RecyclerView.Adapter<NYCSchoolsListItemViewHolder>() {
    private lateinit var binding: NycSchoolsListItemBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NYCSchoolsListItemViewHolder {
        binding = NycSchoolsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NYCSchoolsListItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NYCSchoolsListItemViewHolder, position: Int) {
        holder.bind(nycSchoolsList[position])
    }

    override fun getItemCount() = nycSchoolsList.size

    /**
     * replaces all values in the existing list with the values from the new list.
     */
    fun submitList(newList: List<NYCSchoolsListUiObj>) {
        nycSchoolsList = newList
        notifyItemRangeChanged(0, itemCount)
    }
}