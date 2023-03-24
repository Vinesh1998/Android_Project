package com.app.skilledlabour.helpers;

import com.app.skilledlabour.models.Booking;
import com.app.skilledlabour.models.Customer;
import com.app.skilledlabour.models.Labour;
import com.app.skilledlabour.models.Query;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class common_helper {
    public static String collection_customers = "customers";
    public static String collection_labours = "labours";
    public static String collection_skills = "skills";

    public static boolean getAdminLogin(String email, String password){
        return email.equals("admin@gmail.com") && password.equals("admin123");
    }

    public static List<Labour> getLabourById(String id){
        //read list based on id
        List<Labour> list = new ArrayList<>();
        list.add(new Labour("1",
                "employee 1",
                "emp@gmail.com",
                "123",
                25,
                "electrician, plumber",
                "10:00-18:00",
                "4.5",
                true
        ));
        return list;
    }

    public static List<Customer> getCustomersList(){
        List<Customer> list = new ArrayList<>();
        list.add(new Customer("1",
                "cust1@gmail.com",
                "Customer 1",
                "+1091234567890",
                "1, fblock Merine",
                true
                ));
        return list;
    }

    public static List<Booking> getAllBookingsData(){
        List<Booking> list = new ArrayList<>();
        list.add(new Booking(1,
                1,
                "Customer1",
                1,
        "employee 1",
                32,
                "01-03-2023 10:30",
                "Booked"
                ));
        list.add(new Booking(2,
                2,
                "Customer1",
                2,
        "employee 2",
                35,
                "02-03-2023 12:30",
                "Canceled"
                ));
        return list;
    }

    public static List<Query> getAllQueries(){
        List<Query> list = new ArrayList<>();
        list.add(new Query(1,
                1,
                "Customer 1",
                1,
                "employee 1",
                "complaint on service!",
                "04-01-2023 14:30",
                true
        ));
        return list;
    }
}
