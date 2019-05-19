package kr.insungjung.finalproject.datas;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Notice implements Serializable {

    public int id;
    public String date;
    public String title;
    public String content;


    // 생성자 아니고, 파싱해서 static 메소드 만들어야 한다.
    // JSON 을 가지고 Bank 클래스로 전환해주는 코드 작성
    public static Notice getNoticeFromJson(JSONObject noticeJson) {

        Notice noticeObject = new Notice();

        try {
            noticeObject.id = noticeJson.getInt("id");
            noticeObject.date = noticeJson.getString("created_at");
            noticeObject.title = noticeJson.getString("title");
            noticeObject.content = noticeJson.getString("content");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return noticeObject;
    }

}
