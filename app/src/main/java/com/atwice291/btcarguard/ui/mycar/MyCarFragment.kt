package com.atwice291.btcarguard.ui.mycar

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Switch
import androidx.cardview.widget.CardView
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

        val view:View = inflater.inflate(R.layout.fragment_my_car, container, false)

        val cardAutoActions: CardView = view.findViewById(R.id.cardAutoActions)
        val cardManulaActions: CardView = view.findViewById(R.id.cardManualActions)

        val swAutoBlock: Switch = view.findViewById(R.id.swAutoBlock)

        val sbAutoBlockLevel :SeekBar = view.findViewById(R.id.sbAutoBlockLevel)

        val sharedPref = activity?.getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE)

        swAutoBlock.isChecked = sharedPref?.getBoolean(getString(R.string.swAutoBlockIsChecked), false) ?: false
        if(swAutoBlock.isChecked) {
            cardAutoActions.visibility = View.VISIBLE
            cardManulaActions.visibility = View.GONE
        } else {
            cardAutoActions.visibility = View.GONE
            cardManulaActions.visibility = View.VISIBLE
        }
        sbAutoBlockLevel.progress = sharedPref?.getInt(getString(R.string.sbAutoBlockLevel), 5) ?: 5

        swAutoBlock.setOnCheckedChangeListener{_, isChecked ->
            if(isChecked) {
                cardAutoActions.visibility = View.VISIBLE
                cardManulaActions.visibility = View.GONE
            } else {
                cardAutoActions.visibility = View.GONE
                cardManulaActions.visibility = View.VISIBLE
            }
            if(sharedPref != null) {
                val editor = sharedPref.edit()
                editor.putBoolean(getString(R.string.swAutoBlockIsChecked), isChecked)
                editor.apply()
            }
        }

        sbAutoBlockLevel.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if(sharedPref != null) {
                    val editor = sharedPref.edit()
                    editor.putInt(getString(R.string.sbAutoBlockLevel), progress)
                    editor.apply()
                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {    }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {     }
        })

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MyCarViewModel::class.java)
        // TODO: Use the ViewModel
    }

}