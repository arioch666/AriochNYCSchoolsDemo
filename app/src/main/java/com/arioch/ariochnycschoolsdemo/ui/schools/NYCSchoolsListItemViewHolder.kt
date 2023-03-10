package com.arioch.ariochnycschoolsdemo.ui.schools

import androidx.recyclerview.widget.RecyclerView
import com.arioch.ariochnycschoolsdemo.R
import com.arioch.ariochnycschoolsdemo.databinding.NycSchoolsListItemBinding
import com.arioch.ariochnycschoolsdemo.ui.data.NYCSchoolsListUiObj

/**
 * [RecyclerView.ViewHolder] for the recyclerView Adapter that displays schools.
 *
 * @param binding the binding for the view that will be used to display the data.
 *
 * @see RecyclerView.ViewHolder
 * @see NYCSchoolsRecyclerViewAdapter
 * @see NYCSchoolsListUiObj
 * @see NycSchoolsListItemBinding
 *
 * @author Arioch
 */
class NYCSchoolsListItemViewHolder(private val binding: NycSchoolsListItemBinding):
    RecyclerView.ViewHolder(binding.root) {

    /**
     * Binds the data to the view.
     *
     * @param school the data to be displayed.
     *
     * @see NYCSchoolsListUiObj
     * @see NycSchoolsListItemBinding
     *
     */
    fun bind(school: NYCSchoolsListUiObj) {
        binding.textViewSchoolName.text = school.schoolName

        binding.textViewBorough.text = school.borough ?: "N/A"

        binding.textViewCity.text = school.city ?: "N/A"

        binding.textViewNeighborhood.text = school.neighborhood ?: "N/A"

        binding.textViewZip.text = school.zip ?: "N/A"

        binding.textViewTotalStudents.text = binding
            .root
            .context
            .getString(R.string.total_number_of_students,
                school.totalStudents ?: "N/A")

        binding.textViewNumberOfSatTestTakers.text = binding
            .root
            .context
            .getString(R.string.number_of_sat_test_takers,
                school.numberOfSATTestTakers?: "N/A")

        binding.textViewSatCriticalReadingAvgScore.text = binding
            .root
            .context
            .getString(R.string.sat_critical_reading_avg_score,
                school.satCriticalReadingAvgScore?: "N/A")

        binding.textViewSatMathAvgScore.text = binding
            .root
            .context
            .getString(R.string.sat_math_avg_score,
                school.satMathAvgScore?: "N/A")

       binding.textViewSatWritingAvgScore.text = binding
            .root
            .context
            .getString(R.string.sat_writing_avg_score,
                school.satWritingAvgScore?: "N/A")

       binding.root.setOnClickListener {
           binding.constraintLayoutSchoolInfo.visibility =
               if (binding.constraintLayoutSchoolInfo.visibility == android.view.View.VISIBLE) {
                   android.view.View.GONE
               } else {
                   android.view.View.VISIBLE
               }
       }
    }
}