package com.example.safekiddo.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.safekiddo.model.PhoneDetails
import com.example.safekiddo.repository.remote.Api
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(private val api: Api) : ViewModel() {

    val compositeDisposable = CompositeDisposable()

    private val _samsungLiveData = MutableLiveData<PhoneDetails>()
    private val samsungLiveData: LiveData<PhoneDetails>
        get() = _samsungLiveData
    private val _xiaomiLiveData = MutableLiveData<PhoneDetails>()
    private val xiaomiLiveData: LiveData<PhoneDetails>
        get() = _xiaomiLiveData
    private val _huaweiLiveData = MutableLiveData<PhoneDetails>()
    private val huaweiLiveData: LiveData<PhoneDetails>
        get() = _huaweiLiveData


    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}