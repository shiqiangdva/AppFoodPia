package com.kevin.appfoodpie.values;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by dllo on 16/12/5.
 */
@Entity
public class Food {
    @Id
    private Long id;
    private String name;
    @Generated(hash = 750019843)
    public Food(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    @Generated(hash = 866324199)
    public Food() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
