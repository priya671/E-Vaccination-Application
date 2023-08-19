package com.example.e_vaccinationsystem;

public class CenterRVModal{
    public String centerName;
    public String centerAddress;
    public String centerFromTime;
    public String centerToTime;
    public String fee_type;
    public Integer ageLimit;
    public String vaccineName;
    public Integer availableCapacity;

    public CenterRVModal(String centerName,String centerAddress,String centerFromTime,String centerToTime,
                         String fee_type,Integer ageLimit,String vaccineName,Integer availableCapacity){
        this.centerName = centerName;
        this.centerAddress = centerAddress;
        this.centerFromTime = centerFromTime;
        this.centerToTime = centerToTime;
        this.fee_type = fee_type;
        this.ageLimit = ageLimit;
        this.vaccineName = vaccineName;
        this.availableCapacity = availableCapacity;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public String getCenterAddress() {
        return centerAddress;
    }

    public void setCenterAddress(String centerAddress) {
        this.centerAddress = centerAddress;
    }

    public String getCenterFromTime() {
        return centerFromTime;
    }

    public void setCenterFromTime(String centerFromTime) {
        this.centerFromTime = centerFromTime;
    }

    public String getCenterToTime() {
        return centerToTime;
    }

    public void setCenterToTime(String centerToTime) {
        this.centerToTime = centerToTime;
    }

    public String getFee_type() {
        return fee_type;
    }

    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    public Integer getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(Integer ageLimit) {
        this.ageLimit = ageLimit;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public Integer getAvailableCapacity() {
        return availableCapacity;
    }

    public void setAvailableCapacity(Integer availableCapacity) {
        this.availableCapacity = availableCapacity;
    }

}


