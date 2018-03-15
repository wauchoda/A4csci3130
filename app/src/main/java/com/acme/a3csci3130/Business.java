package com.acme.a3csci3130;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wauch on 2018-03-15.
 */

/**
 * A business object that stores business related information.
 */
public class Business implements Serializable {
    public String bid;
    public String businessNumber;
    public String name;
    public String primaryBusiness;
    public String address;
    public String province;

    /**
     * Default constructor for building list.
     */
    public Business(){

    }

    public Business(String bid, String businessNumber, String name, String primaryBusiness, String address, String province){
        this.bid = bid;
        this.businessNumber = businessNumber;
        this.name = name;
        this.primaryBusiness = primaryBusiness;
        this.address = address;
        this.province = province;

    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("bid", bid);
        result.put("businessNumber", businessNumber);
        result.put("name", name);
        result.put("primaryBusiness", primaryBusiness);
        result.put("address", address);
        result.put("province", province);
        return result;
    }

}
