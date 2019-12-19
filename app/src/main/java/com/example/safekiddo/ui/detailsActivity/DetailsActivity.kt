package com.example.safekiddo.ui.detailsActivity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.safekiddo.R
import com.example.safekiddo.databinding.ActivityDetailsBinding
import com.example.safekiddo.model.PhoneData
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity

class DetailsActivity : DaggerAppCompatActivity() {


    lateinit var binding: ActivityDetailsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details)
        val device: PhoneData? = intent.getParcelableExtra("device")
        showDetails(device)
    }

    @SuppressLint("SetTextI18n")
    fun showDetails(device: PhoneData?) {
        binding.textPhoneBrand.text = "${device?.brand}"
        binding.textModel.text = "${device?.deviceName}"
        binding.textDetails.text = "colors: ${device?.colors}\n" +
                "weight: ${device?.weight}\n" +
                "dimensions: ${device?.dimensions}\n" +
                "size: ${device?.size}\n" +
                "bluetooth: ${device?.bluetooth}\n" +
                "wlan: ${device?.wlan}\n" +
                "usb: ${device?.usb}\n" +
                "radio: ${device?.radio}\n" +
                "messaging: ${device?.messaging}\n" +
                "loudspeaker: ${device?.loudspeaker}\n" +
                "java: ${device?.java}\n" +
                "gps: ${device?.gps}\n" +
                "card slot: ${device?.cardSlot}"
    }
}
