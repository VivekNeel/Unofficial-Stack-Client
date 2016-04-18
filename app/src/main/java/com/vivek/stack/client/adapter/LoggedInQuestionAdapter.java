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
import java.util.TimeZone;

/**
 * Created by Vivek on 17-04-2016.
 */
public class LoggedInQuestionAdapter extends RecyclerView.Adapter<LoggedInQuestionAdapter.LoggedInQuestionViewHolder> {


    private List<Item> itemList;
    private Item item;

    public LoggedInQuestionAdapter(List<Item> items) {
        this.itemList = items;
    }

    @Override
    public LoggedInQuestionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.loggedin_userquestion_listitem, parent, false);
        return new LoggedInQuestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LoggedInQuestionViewHolder holder, int position) {
        item = itemList.get(position);
        holder.title.setText(item.getTitle());
        holder.link.setText(item.getLink());
        holder.tag.setText(item.getTags().toString());
        holder.s.setText(String.valueOf(item.getScore()));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(Long.parseLong(String.valueOf(item.getLastActivityDate())));
        String getCreationDate = simpleDateFormat.format(date);
        TimeZone timeZone = TimeZone.getTimeZone("IST");
        simpleDateFormat.setTimeZone(timeZone);
        holder.date.setText(getCreationDate);

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

    public class LoggedInQuestionViewHolder extends RecyclerView.ViewHolder {

        TextView title, link, tag, s, date;

        public LoggedInQuestionViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title_text);
            link = (TextView) itemView.findViewById(R.id.link_text);
            tag = (TextView) itemView.findViewById(R.id.tags_text);
            s = (TextView) itemView.findViewById(R.id.score);
            date = (TextView) itemView.findViewById(R.id.date_text);
        }
    }
}
