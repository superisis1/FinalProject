package kr.insungjung.finalproject.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class ContextUtil {

//    어떤 파일 (메모장 : ~~.txt)에 저장할지 경로를 담아두는 변수
    private static final String prefName = "LoginServerPracticePref";

//    이 메모에서 다루는 항목들의 리스트를 변수로 생성.
    private static final String USER_INPUT_ID = "USER_INPUT_ID";
    private static final String USER_INPUT_PW = "USER_INPUT_PW";
    private static final String USER_TOKEN = "USER_TOKEN";
    private static final String AUTO_LOGIN = "AUTO_LOGIN";

    /**
     * 아이디 저장
     * 실제로 각각의 항목을 저장/불러오는 기능
     * @param context
     * @param inputId
     */
    // setter : ID를 저장하는 id_setter
    public static void setUserInputId(Context context, String inputId) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE); // 메모장파일(TXT) 여는 작업
        pref.edit().putString(USER_INPUT_ID, inputId).apply(); // 실제로 데이터 저장하기
    }

    // getter : 저장된 ID가 있다면, 불러오기
    public static String getUserInputId(Context context) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE); // 메모장 파일을 열어주는 작업
        return pref.getString(USER_INPUT_ID, "");
    }

    /**
     * 비번 저장
     * 실제로 각각의 항목을 저장/불러오는 기능
     */
    // setter : ID를 저장하는 id_setter
    public static void setUserInputPw(Context context, String inputPw) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE); // 메모장파일(TXT) 여는 작업
        pref.edit().putString(USER_INPUT_PW, inputPw).apply(); // 실제로 데이터 저장하기
    }

    // getter : 저장된 ID가 있다면, 불러오기
    public static String getUserInputPw(Context context) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE); // 메모장 파일을 열어주는 작업
        return pref.getString(USER_INPUT_PW, "");
    }

    /**
     * 토큰 저장
     * @param context
     * @param token
     */
    public static void setUserToken(Context context, String token) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        pref.edit().putString(USER_TOKEN, token).apply();
    }

    public static String getUserToken(Context context) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        return pref.getString(USER_TOKEN, "");
    }

    /**
     * 자동 로그인 체크값
     * @param context
     * @param autoLogin
     */
    public static void setAutoLogin(Context context, boolean autoLogin) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        pref.edit().putBoolean(AUTO_LOGIN, autoLogin).apply();
    }

    public static Boolean getAutoLogin(Context context) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        return pref.getBoolean(AUTO_LOGIN, false);
    }

}









