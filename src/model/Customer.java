package model;

    public class Customer {

        private int customerId;
        private String name;
        private String gender;
        private int age;
        private String phone;
        private String email;
        private String address;

        public Customer() {
        }

        public Customer(int customerId, String name, String gender, int age,
                        String phone, String email, String address) {
            this.customerId = customerId;
            this.name = name;
            this.gender = gender;
            this.age = age;
            this.phone = phone;
            this.email = email;
            this.address = address;
        }

        public int getCustomerId() {
            return customerId;
        }

        public void setCustomerId(int customerId) {
            this.customerId = customerId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        @Override
        public String toString() {
            return "Customer{" +
                    "customerId=" + customerId +
                    ", name='" + name + '\'' +
                    ", phone='" + phone + '\'' +
                    '}';
        }
    }

