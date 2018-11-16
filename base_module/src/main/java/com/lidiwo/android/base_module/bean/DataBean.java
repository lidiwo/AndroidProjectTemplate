package com.lidiwo.android.base_module.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * *****************************************************
 *
 * @author：lidi
 * @date：2018/10/11 17:07
 * @Company：智能程序员
 * @Description： *****************************************************
 */
@Entity
public class DataBean {
    @Id
    private Long id;
    @Unique
    private String name;
    @Generated(hash = 1644002801)
    public DataBean(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    @Generated(hash = 908697775)
    public DataBean() {
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
