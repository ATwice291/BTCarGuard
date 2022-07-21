package com.atwice291.btcarguard.ui.mycar

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.atwice291.btcarguard.R

class MyCarFragment : Fragment() {

    companion object {
        fun newInstance() = MyCarFragment()
    }

    private lateinit var viewModel: MyCarViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_car, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MyCarViewModel::class.java)
        // TODO: Use the ViewModel
    }

}