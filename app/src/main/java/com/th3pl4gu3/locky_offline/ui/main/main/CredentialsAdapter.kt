package com.th3pl4gu3.locky_offline.ui.main.main

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.th3pl4gu3.locky_offline.core.credentials.Credentials

class CredentialsAdapter(
    private val credentialListener: CredentialListener,
    private val isSimplified: Boolean
) : ListAdapter<Credentials, CredentialsViewHolder>(
    diffCallback
) {
    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Credentials>() {
            override fun areItemsTheSame(oldItem: Credentials, newItem: Credentials): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Credentials, newItem: Credentials): Boolean {
                return oldItem.equals(newItem)
            }
        }
    }

    override fun onBindViewHolder(holder: CredentialsViewHolder, position: Int) {
        holder.bind(
            credentialListener,
            getItem(position),
            isSimplified
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CredentialsViewHolder {
        return CredentialsViewHolder.from(
            parent
        )
    }
}