package com.company.shop.entity;

public class Counselor {
    private Integer counselorId;

    private String counselorName;

    private String tel;

    private Integer storeId;

    private Integer except;

    private String speciality;

    private String intro;

    private String headImg;
    
    private Store store;

    /**
     * @return the store
     */
    public Store getStore() {
      return store;
    }

    /**
     * @param store the store to set
     */
    public void setStore(Store store) {
      this.store = store;
    }

    public Integer getCounselorId() {
        return counselorId;
    }

    public void setCounselorId(Integer counselorId) {
        this.counselorId = counselorId;
    }

    public String getCounselorName() {
        return counselorName;
    }

    public void setCounselorName(String counselorName) {
        this.counselorName = counselorName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getExcept() {
        return except;
    }

    public void setExcept(Integer except) {
        this.except = except;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }
}