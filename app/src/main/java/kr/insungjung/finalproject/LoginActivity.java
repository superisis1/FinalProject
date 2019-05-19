package kr.insungjung.finalproject;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import kr.insungjung.finalproject.databinding.ActivityLoginBinding;
import kr.insungjung.finalproject.utils.ConnectServer;
import kr.insungjung.finalproject.utils.ContextUtil;

public class LoginActivity extends BaseActivity {

    ActivityLoginBinding act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

        TextView inputId = act.loginIdEdt;
        TextView inputPw = act.loginPwEdt;

        if (ContextUtil.getAutoLogin(mContext) == true) {
            act.autoLoginCheckBox.setChecked(true);
            inputId.setText(ContextUtil.getUserInputId(mContext));
            inputPw.setText(ContextUtil.getUserInputPw(mContext));
        } else {
            act.autoLoginCheckBox.setChecked(false);
        }

        act.signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String inputId = act.loginIdEdt.getText().toString();
                String inputPw = act.loginPwEdt.getText().toString();

                ContextUtil.setUserInputId(mContext, inputId);
                ContextUtil.setUserInputPw(mContext, inputPw);

                ConnectServer.postRequestSignIn(mContext, inputId, inputPw, new ConnectServer.JsonResponseHandler() {
                    @Override
                    public void onResponse(JSONObject json) {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    int code = json.getInt("code");
                                    Log.d("로그인시 받아온 코드", Integer.toString(code));

                                    if (code == 200) {
                                        JSONObject data = json.getJSONObject("data");
                                        String token = data.getString("token");
                                        ContextUtil.setUserToken(mContext, token);

                                        if (act.autoLoginCheckBox.isChecked()) {  // 자동로그인을 하려고 한다.
                                            ContextUtil.setAutoLogin(mContext, true);
                                        } else if (act.autoLoginCheckBox.isChecked() == false) {
                                            ContextUtil.setAutoLogin(mContext, false);
                                        }

                                        Intent intent = new Intent(mContext, MainActivity.class);
                                        intent.putExtra("userToken", token);
                                        startActivity(intent);
                                        finish();

                                    } else {
                                        AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
                                        alert.setTitle("로그인 실패 알림");
                                        alert.setMessage(json.getString("message"));
                                        alert.setPositiveButton("확인", null);
                                        alert.show();
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });

                    }
                });

            }
        });
    }

    @Override
    public void setValues() {

    }

    @Override
    public void bindViews() {
        act = DataBindingUtil.setContentView(this, R.layout.activity_login);
    }
}
