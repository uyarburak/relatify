package com.okapi.relatives.model;

import java.util.Objects;

/**
 * Created by burak on 2/24/2018.
 */
public class Person {

    String gender;
    String name;
    String surname;
    String fatherName;
    String motherName;
    String birthPlaceAndDate;
    String address;
    String serieNo;
    String maritalStatus;
    String liveStatus;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getBirthPlaceAndDate() {
        return birthPlaceAndDate;
    }

    public void setBirthPlaceAndDate(String birthPlaceAndDate) {
        this.birthPlaceAndDate = birthPlaceAndDate;
    }

    public String getSerieNo() {
        return serieNo;
    }

    public void setSerieNo(String serieNo) {
        this.serieNo = serieNo;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getLiveStatus() {
        return liveStatus;
    }

    public void setLiveStatus(String liveStatus) {
        this.liveStatus = liveStatus;
    }

    @Override
    public int hashCode() {
        // maritalStatus and liveStatus are not include cause they can change by time.
        return Objects.hash(gender, name, surname, fatherName, motherName, birthPlaceAndDate, address, serieNo);
    }
}
