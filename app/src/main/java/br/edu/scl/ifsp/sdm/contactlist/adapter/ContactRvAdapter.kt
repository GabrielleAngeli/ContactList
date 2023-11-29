package br.edu.scl.ifsp.sdm.contactlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import br.edu.scl.ifsp.sdm.contactlist.R
import br.edu.scl.ifsp.sdm.contactlist.databinding.TileContactBinding
import br.edu.scl.ifsp.sdm.contactlist.model.Contact
import br.edu.scl.ifsp.sdm.contactlist.view.OnContactClickListener

class ContactRvAdapter(private val contactList: MutableList<Contact>,
                       private val onContactClickListener: OnContactClickListener
): RecyclerView.Adapter<ContactRvAdapter.ContactViewHolder>() {
    inner class ContactViewHolder(tileContactBinding: TileContactBinding):
        RecyclerView.ViewHolder(tileContactBinding.root){
        val nameTv: TextView = tileContactBinding.nameTv
        val emailTv: TextView = tileContactBinding.emailTv
        val phoneTv: TextView = tileContactBinding.phoneTv

        init {
            tileContactBinding.root.apply {
                setOnCreateContextMenuListener{ menu, _,_ ->
                    (onContactClickListener as AppCompatActivity).menuInflater.inflate(
                        R.menu.context_menu_main,
                        menu
                    )
                    menu.findItem(R.id.removeContactMi).setOnMenuItemClickListener {
                        onContactClickListener.onRemoveContactMenuItemClick(adapterPosition)
                        true
                    }
                    menu.findItem(R.id.editContactMi).setOnMenuItemClickListener {
                        onContactClickListener.onEditContactMenuItemClick(adapterPosition)
                        true
                    }
                }

                setOnClickListener {
                    onContactClickListener.onContactClick(adapterPosition)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TileContactBinding.inflate(LayoutInflater.from(parent.context),parent, false).run {
            ContactViewHolder(this)
    }

    override fun getItemCount() = contactList.size

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
       contactList[position].also { contact ->
           with(holder){
               nameTv.text = contact.name
               emailTv.text = contact.email
               phoneTv.text = contact.phone
           }
       }
    }
}