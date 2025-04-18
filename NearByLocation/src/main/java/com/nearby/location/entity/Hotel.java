//package com.nearby.location.entity;
//
//public class Hotel {
//    private String name;
//    private String address;
//    private String city;
//
//    public Hotel() {
//        super();
//    }
//
//    public Hotel(String name, String address, String city) {
//        super();
//        this.name = name;
//        this.address = address;
//        this.city = city;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public String getCity() {
//        return city;
//    }
//
//    public void setCity(String city) {
//        this.city = city;
//    }
//
//    @Override
//    public String toString() {
//        return "Hotel [name=" + name + ", address=" + address + ", city=" + city + "]";
//    }
//}

package com.nearby.location.entity;

public class Hotel {
    private String name;
    private String address;
    private String city;
    private String state;

    public Hotel() {
        super();
    }

    public Hotel(String name, String address, String city, String state) {
        super();
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Hotel [name=" + name + ", address=" + address + ", city=" + city + ", state=" + state + "]";
    }
}


