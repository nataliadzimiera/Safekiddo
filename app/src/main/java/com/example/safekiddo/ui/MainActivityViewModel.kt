package com.example.safekiddo.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.safekiddo.model.PhoneData
import com.example.safekiddo.model.PhoneDetails
import com.example.safekiddo.repository.local.PhoneDatabase
import com.example.safekiddo.repository.remote.Api
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val api: Api,
    private val db: PhoneDatabase
) : ViewModel() {

    val compositeDisposable = CompositeDisposable()

    private val _phoneDataLiveData = MutableLiveData<List<PhoneData>>()
    val phoneDataLiveData: LiveData<List<PhoneData>>
        get() = _phoneDataLiveData

    val progressLiveData = MutableLiveData<Boolean>()

    fun getData() {
        val listPhones = db.phoneDao().getAll()
        val size = listPhones.size
        db.phoneDao().getAll().size
        _phoneDataLiveData.value = listPhones
        if (size == 0) {
            getPhones(false)
        }
    }

    fun clearDatabse() {
        db.phoneDao().deleteAll()
    }

    fun getPhones(isRefresh: Boolean) {
        var devicesList = mutableListOf<PhoneDetails>()
        if (!isRefresh) {
            progressLiveData.value = true
        }
        compositeDisposable.add(
            // The API doesn't work when I try to send 3 brands in one query
            api.getPhonesList(APIKEY, "S", "Xiaomi")
                .flatMap {
                    devicesList.addAll(it)
                    return@flatMap api.getPhonesList(APIKEY, "S", "Huawei")
                }
                .flatMap {
                    devicesList.addAll(it)
                    return@flatMap api.getPhonesList(APIKEY, "S", "Samsung")
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        devicesList.addAll(it)
                        devicesList.shuffle()
                        devicesList = devicesList.subList(0, 10)
                        val phoneDataList = mutableListOf<PhoneData>()
                        val size = devicesList.size
                        for (n in 0 until size) {
                            val phoneData = PhoneData(
                                n,
                                devicesList[n].deviceName,
                                devicesList[n].brand,
                                devicesList[n].dimensions,
                                devicesList[n].weight,
                                devicesList[n].size,
                                devicesList[n].cardSlot,
                                devicesList[n].loudspeaker,
                                devicesList[n].wlan,
                                devicesList[n].bluetooth,
                                devicesList[n].gps,
                                devicesList[n].radio,
                                devicesList[n].usb,
                                devicesList[n].messaging,
                                devicesList[n].browser,
                                devicesList[n].java,
                                devicesList[n].colors
                            )
                            phoneDataList.add(
                                phoneData
                            )
                            db.phoneDao().insert(phoneData)
                        }
                        _phoneDataLiveData.postValue(phoneDataList)
                    },
                    {
                        progressLiveData.postValue(false)
                    },
                    {
                        progressLiveData.postValue(false)
                    }
                )
        )
    }

    companion object {
        const val APIKEY = "ed51dfc18ec4facf752bfca2f2de8ce33092188e619cfc52"

    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}

