package com.vivek.stack.client.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vivek.stack.client.R;
import com.vivek.stack.client.model.LoggedInQuestionModel.Item;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Vivek on 17-04-2016.
 */
public class GuestUserQuestionAdapter extends RecyclerView.Adapter<GuestUserQuestionAdapter.GuestUserQuestionViewHolder> {


    private List<Item> itemList;
    private Item item;

    public GuestUserQuestionAdapter(List<Item> items) {
        this.itemList = items;
    }

    @Override
    public GuestUserQuestionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.guestuser_stack_recycler_view_item, parent, false);
        return new GuestUserQuestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GuestUserQuestionViewHolder holder, int position) {
        item = itemList.get(position);
        holder.title.setText(item.getTitle());
        holder.link.setText(item.getLink());
        holder.tag.setText(item.getTags().toString());
        holder.s.setText(String.valueOf(item.getScore()));
        holder.user.setText(item.getOwner().getDisplayName() + " :");

        int getCreationate = item.getCreationDate();
        Date date = new Date(Long.parseLong(String.valueOf(getCreationate)));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String convertedDate = simpleDateFormat.format(date);
        holder.date.setText(convertedDate);

        //         for(int i = 0 ; i < itemList.size() ; i++){
//             for(int j = 0 ; j < itemList.get(i).getTags().size();j++ ){
//                 holder.tag.setText(item.getTags().get(position));
//             }
//         }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class GuestUserQuestionViewHolder extends RecyclerView.ViewHolder {

        TextView title, link, tag, s, user, date;

        public GuestUserQuestionViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title_text);
            link = (TextView) itemView.findViewById(R.id.link_text);
            tag = (TextView) itemView.findViewById(R.id.tags_text);
            s = (TextView) itemView.findViewById(R.id.score);
            user = (TextView) itemView.findViewById(R.id.user_text);
            date = (TextView) itemView.findViewById(R.id.date_text);

        }
    }
}
