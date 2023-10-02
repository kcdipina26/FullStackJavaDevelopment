package com.petelevator;

import java.util.ArrayList;
import java.util.List;


    public class Customer extends Person {
        private String phoneNumber;
        private List<Pet> pets;

        public Customer(String firstName, String lastName, String phoneNumber) {
            super(firstName, lastName);
            this.phoneNumber = phoneNumber;
            this.pets = new ArrayList<>();
        }

        public Customer(String firstName, String lastName) {
            this(firstName, lastName, "");
        }

        @Override
        public String getFullName() {
            return null;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public List<Pet> getPets() {
            return pets;
        }

        public void setPets(List<Pet> pets) {
            this.pets = pets;
        }
    }



