package com.app.skilledlabour.helpers;

import com.app.skilledlabour.models.Booking;
import com.app.skilledlabour.models.Customer;
import com.app.skilledlabour.models.Labour;
import com.app.skilledlabour.models.Query;

import java.util.ArrayList;
import java.util.List;

public class common_helper {
    public static String collection_customers = "customers";
    public static String collection_labours = "labours";
    public static String collection_skills = "skills";

    public static boolean getAdminLogin(String email, String password){
        return email.equals("admin@gmail.com") && password.equals("admin123");
    }

    public static Labour getLabourById(String id){
        //read list based on id
        Labour labour = new Labour("1",
                "employee 1",
                "emp@gmail.com",
                "123",
                25,
                "electrician, plumber",
                "10:00-18:00",
                "4.5",
                true
        );
        return labour;
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
        list.add(new Customer("2",
                "cust2@gmail.com",
                "Customer 2",
                "+10924655545",
                "1, fblock Merine",
                true
        ));
        list.add(new Customer("3",
                "cust3@gmail.com",
                "Customer 3",
                "+1075475623126",
                "1, fblock Merine",
                true
        ));
        return list;
    }

    public static Customer getCustomer(String id) {
        List<Customer> list = getCustomersList();
        Customer customer = new Customer();
        for (int i = 0; i < list.size(); i++) {
                if(list.get(i).getId().equals(id))
                    customer = list.get(i);
        }
        return customer;
    }

    public static List<Booking> getAllBookingsData(){
        List<Booking> list = new ArrayList<>();
        list.add(new Booking(1,
                1,
                "Customer1",
                1,
        "employee 1",
                "Ac repair",
                "01-03-2023 10:30",
                "Booked"
                ));
        list.add(new Booking(2,
                2,
                "Customer1",
                2,
        "employee 2",
                "Water leakage problem",
                "02-03-2023 12:30",
                "Canceled"
                ));
        list.add(new Booking(3,
                3,
                "Customer3",
                2,
                "employee 3",
                "Electric works",
                "03-03-2023 12:30",
                "Booked"
        ));
        list.add(new Booking(4,
                3,
                "Customer3",
                2,
                "employee 4",
                "Hardware problem",
                "05-03-2023 12:30",
                "Booked"
        ));
        return list;
    }

    public static Booking getBookingData(int id){
        List<Booking> list = getAllBookingsData();
        Booking booking = list.get(id-1);
        return booking;
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

    public static List<Query> getLabourQueries(int lab_id){
        List<Query> list = getAllQueries();
        List<Query> my_list = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getEmp_id() == lab_id) my_list.add(list.get(i));
        }
        return my_list;
    }

    public static Query getQueryDetails(int q_id){
        List<Query> list = getAllQueries();
        Query query = new Query();
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getId() == q_id) query = list.get(i);
        }
        return query;
    }

}
