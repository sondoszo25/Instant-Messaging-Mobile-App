package com.example.mtkdem2_targel3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactsListAdapter extends RecyclerView.Adapter<ContactsListAdapter.ContactViewHolder> {

    class ContactViewHolder extends RecyclerView.ViewHolder{
        private final TextView tvName;
        private final TextView tvLastmsg;
        private final TextView tvTime;
        private final ImageView ivPic;

        private ContactViewHolder(View itemView) {
            super(itemView);
            tvLastmsg = itemView.findViewById(R.id.tvLastmsg);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvName = itemView.findViewById(R.id.tvName);
            ivPic = itemView.findViewById(R.id.ivPic);
        }
    }
    private final LayoutInflater mInflater;
    private List<Contacts> contacts;

    public ContactsListAdapter(Context context)
    {
        mInflater=LayoutInflater.from(context);
    }


    @Override
    public ContactViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View itemView =mInflater.inflate(R.layout.contact_layout,parent,false);
        return new ContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        if (contacts != null)
        {
            final Contacts current=contacts.get(position);
            holder.tvName.setText(current.getUser().getDisplayName());
            if(current.getLastMessage() != null)
            {
                holder.tvTime.setText(current.getLastMessage().getCreated());
                holder.tvLastmsg.setText(current.getLastMessage().getContent());
            }
            if(current.getUser().getProfilePic() == null || current.getUser().getProfilePic().equals("data:image/*;base64,"))
            {
                holder.ivPic.setImageResource(R.drawable.dimg);
            }
            else{

                String base64Image = current.getUser().getProfilePic() .substring(current.getUser().getProfilePic() .indexOf(",") + 1);
                byte[] decodedBytes = Base64.decode(base64Image, Base64.DEFAULT);
                Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
                holder.ivPic.setImageBitmap(decodedBitmap);
            }
        }
    }

    public void setContacts(List<Contacts> contacts) {
        this.contacts = contacts;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(contacts != null)
        {
            return contacts.size();
        }
        else{
            return 0;
        }
    }

    public List<Contacts> getContacts() {
        return contacts;
    }
}
