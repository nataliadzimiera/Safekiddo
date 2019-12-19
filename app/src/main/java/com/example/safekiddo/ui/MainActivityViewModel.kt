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
import java.util.*
import javax.inject.Inject

const val APIKEY = "ed51dfc18ec4facf752bfca2f2de8ce33092188e619cfc52"

class MainActivityViewModel @Inject constructor(
    private val api: Api,
    private val db: PhoneDatabase
) : ViewModel() {

    val compositeDisposable = CompositeDisposable()


    private val _devicesLiveData = MutableLiveData<List<PhoneDetails>>()
    val devicesLiveData: LiveData<List<PhoneDetails>>
        get() = _devicesLiveData

    private val _phoneDataLiveData = MutableLiveData<List<PhoneData>>()
    val phoneDataLiveData: LiveData<List<PhoneData>>
        get() = _phoneDataLiveData

    fun getData() {
        val listPhones = db.phoneDao().getAll()
        val size = listPhones.size
        db.phoneDao().getAll().size
        _phoneDataLiveData.value = db.phoneDao().getAll()

        if (size < 10) {
            getPhones()

        }
    }

    fun getPhones() {
        val devicesList = mutableListOf<PhoneDetails>()
        compositeDisposable.add(
            api.getPhonesList(APIKEY, "S", "Xiaomi")
                .flatMap {
                    devicesList.addAll(it)
                    return@flatMap api.getPhonesList(APIKEY, "S", "Huawei")
                }
                .flatMap {
                    devicesList.addAll(it)
                    return@flatMap api.getPhonesList(APIKEY, "S", "Samsung")
                }
                .doOnSubscribe {
                    //todo progressbar sie pojawia
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        devicesList.addAll(it)
                        _devicesLiveData.value = devicesList
                        val size = devicesList.size
                        for (n in 0..size) {
                            db.phoneDao().insert(
                                PhoneData(
                                    n,
                                    devicesLiveData.value!!.get(n).deviceName,
                                    devicesLiveData.value!!.get(n).brand,
                                    devicesLiveData.value?.get(n)?.dimensions,
                                    devicesLiveData.value?.get(n)?.weight,
                                    devicesLiveData.value?.get(n)?.size,
                                    devicesLiveData.value?.get(n)?.cardSlot,
                                    devicesLiveData.value?.get(n)?.loudspeaker,
                                    devicesLiveData.value?.get(n)?.wlan,
                                    devicesLiveData.value?.get(n)?.bluetooth,
                                    devicesLiveData.value?.get(n)?.gps,
                                    devicesLiveData.value?.get(n)?.radio,
                                    devicesLiveData.value?.get(n)?.usb,
                                    devicesLiveData.value?.get(n)?.messaging,
                                    devicesLiveData.value?.get(n)?.browser,
                                    devicesLiveData.value?.get(n)?.java,
                                    devicesLiveData.value?.get(n)?.colors

                                )
                            )

                        }
                    },
                    {
                        //todo progressbar znika
                        it.printStackTrace()
                    },
                    {
                        //todo progressbar znika
                    }
                )
        )
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}

