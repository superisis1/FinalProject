package kr.insungjung.finalproject.fragments;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import kr.insungjung.finalproject.NoticeDetailActivity;
import kr.insungjung.finalproject.R;
import kr.insungjung.finalproject.adapters.NoticeListAdapter;
import kr.insungjung.finalproject.databinding.FragmentNoticeListBinding;
import kr.insungjung.finalproject.datas.Notice;
import kr.insungjung.finalproject.utils.ConnectServer;
import kr.insungjung.finalproject.utils.ContextUtil;

public class NoticeListFragment extends Fragment {

    FragmentNoticeListBinding binding;

    List<Notice> noticeList = new ArrayList<>();
    NoticeListAdapter noticeAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_notice_list, container, false);

        noticeAdapter = new NoticeListAdapter(getActivity(), noticeList);
        binding.noticeListView.setAdapter(noticeAdapter);

        /* 아이템 클릭 이벤트 */
        binding.noticeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Notice clickedNoticeData = noticeList.get(position);

                Intent intent = new Intent(getActivity(), NoticeDetailActivity.class);
                intent.putExtra("공지사항정보", clickedNoticeData);
                startActivity(intent);
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    public void onResume() {
        super.onResume();

        String token = ContextUtil.getUserToken(getActivity());
        Log.d("사용자토큰값", token);

        ConnectServer.getRequestInfoNotice(getActivity(), token, new ConnectServer.JsonResponseHandler() {
            @Override
            public void onResponse(JSONObject json) {

                try {
                    int code = json.getInt("code");

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (code == 200) {

                                try {
                                    JSONObject data = json.getJSONObject("data"); // 서버에 저장된 키값으로 불러온다.
                                    JSONArray notices = data.getJSONArray("announcements");

                                    noticeList.clear();

                                    for (int i = 0; i < notices.length(); i++) {
                                        JSONObject notice = notices.getJSONObject(i);

                                        Notice noticeObj = Notice.getNoticeFromJson(notice); // bank 데이터로부터 세부정보를 불러와 저장하는 부분
                                        noticeList.add(noticeObj);
                                        noticeAdapter.notifyDataSetChanged();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                try {
                                    String message = json.getString("message");
                                    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}








