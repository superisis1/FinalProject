package kr.insungjung.finalproject;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.widget.TextView;

import kr.insungjung.finalproject.databinding.ActivityNoticeDetailBinding;
import kr.insungjung.finalproject.datas.Notice;

public class NoticeDetailActivity extends BaseActivity {

    ActivityNoticeDetailBinding act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

    }

    @Override
    public void setValues() {

       TextView contentTxt = act.noticeContentTxt;
       TextView titleTxt = act.noticeTitleTxt;
       TextView dateTxt = act.noticeDateTxt;

        Notice notice = (Notice) getIntent().getSerializableExtra("공지사항정보");

        titleTxt.setText(notice.title);
        dateTxt.setText(notice.date);
        contentTxt.setText(notice.content);

    }

    @Override
    public void bindViews() {

        act = DataBindingUtil.setContentView(this, R.layout.activity_notice_detail);
    }
}
