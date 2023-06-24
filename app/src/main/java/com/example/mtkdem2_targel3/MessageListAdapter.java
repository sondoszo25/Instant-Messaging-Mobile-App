package com.example.mtkdem2_targel3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MessageListAdapter extends RecyclerView.Adapter<MessageListAdapter.MessageViewHolder> {

    private String me;
    private final LayoutInflater mInflater;
    private List<Message> messages;

    public MessageListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    public void setMe(String me) {
        this.me = me;
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        if (viewType == 0) {
            itemView = mInflater.inflate(R.layout.messages_layoutr, parent, false);
        } else {
            itemView = mInflater.inflate(R.layout.messages_layout, parent, false);
        }
        return new MessageViewHolder(itemView, me);
    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position) {
        if (messages != null) {
            final Message current = messages.get(position);
            if (current.getSender().getUsername().equals(holder.me)) {
                holder.tvMsgR.setText(current.getContent());
                holder.tvTimeR.setText(current.getCreated());
            } else {
                holder.tvMsg.setText(current.getContent());
                holder.cloudBackground.setBackgroundResource(R.drawable.imgcloud23);
                holder.tvTime.setText(current.getCreated());
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        final Message current = messages.get(position);
        if (current.getSender().getUsername().equals(me)) {
            return 0;
        } else {
            return 1;
        }
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (messages != null) {
            return messages.size();
        } else {
            return 0;
        }
    }

    public List<Message> getMessages() {
        return messages;
    }

    static class MessageViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvMsg;
        private final TextView tvTime;
        private final TextView tvMsgR;
        private final TextView tvTimeR;
        private final LinearLayout cloudBackground;
        private final RelativeLayout allMsgIdLayout;
        private final String me;

        public MessageViewHolder(View itemView, String me) {
            super(itemView);
            tvMsg = itemView.findViewById(R.id.tvMsgMsg);
            tvTime = itemView.findViewById(R.id.tvTimeMsg);
            tvMsgR = itemView.findViewById(R.id.tvMsgMsgR);
            tvTimeR = itemView.findViewById(R.id.tvTimeMsgR);
            allMsgIdLayout = itemView.findViewById(R.id.allmsgid);
            cloudBackground= itemView.findViewById(R.id.cloudBackground);
            this.me = me;
        }
    }
}
