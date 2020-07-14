package com.th3pl4gu3.locky_offline.ui.main.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.th3pl4gu3.locky_offline.databinding.CustomViewRecyclerviewCredentialsFieldBinding
import com.th3pl4gu3.locky_offline.ui.main.utils.Constants

class CredentialsViewAdapter(
    private val copyClickListener: CopyClickListener,
    private val viewClickListener: ViewClickListener
) : ListAdapter<CredentialsField, CredentialsViewAdapter.ViewHolder>(
    CardDiffCallback()
) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(copyClickListener, viewClickListener, getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(
            parent
        )
    }

    class ViewHolder private constructor(val binding: CustomViewRecyclerviewCredentialsFieldBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            copyClickListener: CopyClickListener,
            viewClickListener: ViewClickListener,
            credentialsField: CredentialsField
        ) {
            binding.credentialsField = credentialsField
            binding.copyClickListener = copyClickListener
            binding.viewClickListener = viewClickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CustomViewRecyclerviewCredentialsFieldBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                )
                return ViewHolder(
                    binding
                )
            }
        }
    }
}

class CardDiffCallback: DiffUtil.ItemCallback<CredentialsField>() {

    override fun areItemsTheSame(oldItem: CredentialsField, newItem: CredentialsField): Boolean {
        return oldItem.subtitle == newItem.subtitle
    }


    override fun areContentsTheSame(oldItem: CredentialsField, newItem: CredentialsField): Boolean {
        return oldItem == newItem
    }

}

class CopyClickListener(val clickListener: (actualData: String) -> Unit) {
    fun onClick(actualData: String) = clickListener(actualData)
}

class ViewClickListener(val clickListener: (actualData: String) -> Unit) {
    fun onClick(actualData: String) = clickListener(actualData)
}

data class CredentialsField(
    var subtitle: String = Constants.VALUE_EMPTY,
    var data: String = Constants.VALUE_EMPTY,
    var isCopyable: Boolean = false,
    var isViewable: Boolean = false
)