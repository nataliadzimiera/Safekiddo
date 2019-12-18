package com.example.safekiddo.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.safekiddo.databinding.ItemPhoneBinding
import com.example.safekiddo.model.PhoneDetails
import javax.inject.Inject

class PhonesAdapter @Inject constructor(): RecyclerView.Adapter<PhonesAdapter.ViewHolder>() {


    private var phonesList: List<PhoneDetails> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhonesAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemPhoneBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return phonesList.size
    }

    fun setPhonesList(phonesList: List<PhoneDetails>){
        this.phonesList = phonesList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: PhonesAdapter.ViewHolder, position: Int) {
        holder.bind(phonesList[position])
    }
    class ViewHolder(private val binding: ItemPhoneBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(phoneDetails: PhoneDetails){
            binding.textPhoneBrand.text = phoneDetails.brand
            binding.textPhoneModel.text = phoneDetails.deviceName
        }
    }

}