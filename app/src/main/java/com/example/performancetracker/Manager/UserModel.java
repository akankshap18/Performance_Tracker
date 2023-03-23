package com.example.performancetracker.Manager;

import java.util.Comparator;

public class UserModel {

    String name, phone, email;
//    int date;

    public UserModel(){}

    public UserModel(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
//        this.date = date;
    }

    public static Comparator<UserModel> modelAtoZComparator = new Comparator<UserModel>() {
        @Override
        public int compare(UserModel o1, UserModel o2) {
            return o1.getName().compareTo(o2.getName());
        }
    };

    public static Comparator<UserModel> modelZtoAComparator = new Comparator<UserModel>() {
        @Override
        public int compare(UserModel o1, UserModel o2) {
            return o2.getName().compareTo(o1.getName());
        }
    };

//    public static Comparator<UserModel> modelAscComparator = new Comparator<UserModel>() {
//        @Override
//        public int compare(UserModel o1, UserModel o2) {
//            return o1.getDate()- o2.getDate();
//        }
//    };
//
//    public static Comparator<UserModel> modelDescComparator = new Comparator<UserModel>() {
//        @Override
//        public int compare(UserModel o1, UserModel o2) {
//            return o1.getName().compareTo(o2.getName());
//        }
//    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public int getDate() {
//        return date;
//    }
//
//    public void setDate(int date) {
//        this.date = date;
//    }
}