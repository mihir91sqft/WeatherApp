package com.example.jetweatherforecast.screens.settings

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetweatherforecast.repository.WeatherDbRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private val repository: WeatherDbRepository): ViewModel() {
        private val _unitList = MutableStateFlow<List<Unit>>(emptyList())
        val unitList = _unitList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO){
            repository.getUnits().distinctUntilChanged()
                .collect{listOfUnits->
                    if(listOfUnits.isNullOrEmpty()){
                        Log.d("TAG", "Empty List: ")
                    }else{
                        _unitList.value = listOfUnits
                    }
                }
        }
    }

    }
