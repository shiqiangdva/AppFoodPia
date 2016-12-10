package com.kevin.appfoodpie.values;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by dllo on 16/12/10.
 */
@Entity
public class DataMore {
    @Id
    private Long id;
    private String img;
    private String name;
    private String qk;
    private String db;
    private String zf;
    private String ts;
    private String ss;
    @Generated(hash = 750814078)
    public DataMore(Long id, String img, String name, String qk, String db,
            String zf, String ts, String ss) {
        this.id = id;
        this.img = img;
        this.name = name;
        this.qk = qk;
        this.db = db;
        this.zf = zf;
        this.ts = ts;
        this.ss = ss;
    }
    @Generated(hash = 1244705956)
    public DataMore() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getImg() {
        return this.img;
    }
    public void setImg(String img) {
        this.img = img;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getQk() {
        return this.qk;
    }
    public void setQk(String qk) {
        this.qk = qk;
    }
    public String getDb() {
        return this.db;
    }
    public void setDb(String db) {
        this.db = db;
    }
    public String getZf() {
        return this.zf;
    }
    public void setZf(String zf) {
        this.zf = zf;
    }
    public String getTs() {
        return this.ts;
    }
    public void setTs(String ts) {
        this.ts = ts;
    }
    public String getSs() {
        return this.ss;
    }
    public void setSs(String ss) {
        this.ss = ss;
    }
    

}
