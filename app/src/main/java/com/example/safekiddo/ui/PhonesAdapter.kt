package com.example.safekiddo.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.safekiddo.databinding.ItemPhoneBinding
import com.example.safekiddo.model.PhoneData
import javax.inject.Inject

class PhonesAdapter @Inject constructor() : RecyclerView.Adapter<PhonesAdapter.ViewHolder>() {


    private var phonesList: List<PhoneData> = emptyList()
    private var onClick: (PhoneData) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhonesAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemPhoneBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding, onClick)
    }

    override fun getItemCount(): Int {
        return phonesList.size
    }

    fun setPhonesList(phonesList: List<PhoneData>) {
        this.phonesList = phonesList
        notifyDataSetChanged()
    }

    fun setOnClickListener(onClick: (PhoneData) -> Unit) {
        this.onClick = onClick
    }

    override fun onBindViewHolder(holder: PhonesAdapter.ViewHolder, position: Int) {
        holder.bind(phonesList[position])
    }

    class ViewHolder(private val binding: ItemPhoneBinding, val onClick: (PhoneData) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        lateinit var phoneDetails: PhoneData
        fun bind(phoneDetails: PhoneData) {
            this.phoneDetails = phoneDetails
            binding.textPhoneBrand.text = phoneDetails.brand
            binding.textPhoneModel.text = phoneDetails.deviceName
            binding.root.setOnClickListener {
                onClick(phoneDetails)
            }
        }
    }

}