package com.ashoka.capstonedicoding.ui.setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ashoka.capstonedicoding.R
import com.ashoka.capstonedicoding.databinding.FragmentDetailMoviesBinding
import com.ashoka.capstonedicoding.databinding.FragmentSettingBinding


class SettingFragment : Fragment(R.layout.fragment_setting) {

    private val binding by viewBinding(FragmentSettingBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}