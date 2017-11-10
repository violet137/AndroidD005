package com.greenacademy.ga_finalprojecthm.model;

import java.io.Serializable;

/**
 * Created by GIT on 11/4/2017.
 */

public class QuestionSupport implements Serializable{
    int Id;
    String NoiDungCauHoi;
    String TraLoi;
    String Html;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNoiDungCauHoi() {
        return NoiDungCauHoi;
    }

    public void setNoiDungCauHoi(String noiDungCauHoi) {
        NoiDungCauHoi = noiDungCauHoi;
    }

    public String getTraLoi() {
        return TraLoi;
    }

    public void setTraLoi(String traLoi) {
        TraLoi = traLoi;
    }

    public String getHtml() {
        return Html;
    }

    public void setHtml(String html) {
        Html = html;
    }
}
