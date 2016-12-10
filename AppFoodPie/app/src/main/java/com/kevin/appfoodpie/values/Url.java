package com.kevin.appfoodpie.values;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by dllo on 16/12/8.
 */
@Entity
public class Url {
    @Id
    private Long id;
    private String url;
    @Generated(hash = 384714742)
    public Url(Long id, String url) {
        this.id = id;
        this.url = url;
    }
    @Generated(hash = 257303629)
    public Url() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    

}
