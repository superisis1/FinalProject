package kr.insungjung.finalproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import kr.insungjung.finalproject.R;
import kr.insungjung.finalproject.datas.Notice;

public class NoticeListAdapter extends ArrayAdapter<Notice> {

    Context mContext;
    List<Notice> mList;
    LayoutInflater inf;

    public NoticeListAdapter(Context context, List<Notice> list) {
        super(context, R.layout.notice_list_item, list);

        mContext = context;
        mList = list;
        inf = LayoutInflater.from(mContext);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        if (row == null) {
            row = inf.inflate(R.layout.notice_list_item, null);
        }

        Notice noticeData = mList.get(position);

        TextView noticeTitleTxt = row.findViewById(R.id.noticeTitleTxt);
        TextView noticeDateTxt = row.findViewById(R.id.noticeDateTxt);

        noticeTitleTxt.setText(noticeData.title);
        noticeDateTxt.setText(noticeData.date);

        return row;
    }
}