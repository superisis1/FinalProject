package kr.insungjung.finalproject.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import kr.insungjung.finalproject.R;
import kr.insungjung.finalproject.databinding.FragmentUserInfoBinding;
import kr.insungjung.finalproject.utils.ConnectServer;
import kr.insungjung.finalproject.utils.ContextUtil;

public class UserInfoFragment extends Fragment {

    FragmentUserInfoBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_info, container, false);

        // ContextUtil(SharedPreferences) 에 저장된 토큰을 받아오기 (로그인 성공한 사람만 가능)
        String token = ContextUtil.getUserToken(getActivity());

        // getActivity().getin
        Log.d("사용자토큰값", token);

        // 받아온 토큰을 가지고 /v2/me_info API 호출, 사용자 데이터 표시
        ConnectServer.getRequestMeInfo(getActivity(), token, new ConnectServer.JsonResponseHandler() {
            @Override
            public void onResponse(JSONObject json) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            int code = json.getInt("code");

                            if (code == 200) {

                                /* 1. 데이터 parsing */

                                // 1) "data"
                                JSONObject data = json.getJSONObject("data");

                                // 1-1) "data-user"
                                JSONObject user = data.getJSONObject("user");
                                String profile_image = user.getString("profile_image");
                                String name = user.getString("name");
                                int balance = user.getInt("balance");

                                // 1-2) "data-user-bank_code"
                                JSONObject bank_code = user.getJSONObject("bank_code"); // bank_code 가 user 하위 카테고리이기 때문에
                                String logo = bank_code.getString("logo");
                                String bankName = bank_code.getString("name");
                                String billing_account = user.getString("billing_account");

                                /* 2. 뷰바인더에 set */

                                // 1-1) "data-user"
                                Glide.with(getActivity()).load(profile_image).into(binding.profileImg);
                                binding.userNameTxt.setText(name);
                                binding.userBalnceTxt.setText(String.format("%,d P", balance));

                                // 2-1) "data-user-bank"
                                Glide.with(getActivity()).load(logo).into(binding.bankLogoImg);
                                binding.bankNameTxt.setText(bankName);
                                binding.bankAccountTxt.setText(billing_account);
                            }
                            else {

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        });

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();


    }
}