## **_CALORIES MANAGER_**
<img src="https://i0.wp.com/tunedinparents.com/wp-content/uploads/2017/04/Tuned-In-Parents-Top-5-Ways-to-Boost-Your-Metabolism-for-Healthy-Weight-Loss-BMR.jpg" alt="Drawing" width="500" height="150" />

### **_WHAT IS IT?_**
####  This is a **site** which provides a possibility to **write down** some information about your meals and to **manage** a number of calories which you eat per a day. If a number of calories, consumed by you for some day, are more than a maximum number of calories per a day, specified in your profile, all your meals get a **red color** for this day. It clearly allows to control a number of calories which you eat per a day and can be useful for you if you train somewhere (gym, swimming, etc.). The application keeps all information about your meals and allows to filter it by **date** and **time** and also to find it by a **field**, specified by you.
####  For managing your meals all **CRUD** operations (Create, Read, Update, Delete) are available for you.


### **EXTRA OPPORTUNITIES**
#### **LANGUAGE**
The application has the next language modes:
- **Russian**
- **English**

You can choose suitable for you.

#### **PROFILE SETTINGS**
You can change your:
- **Name**
- **Email**
- **Calories per a day**
- **Password**


#### **ROLES**
The application has the next types of roles:
- **User**
- **Admin**


#### **ADMIN INFO**
If you are an **ADMIN**, in addition to the above, you can:
- **Read a list of all users**
- **Add user (name, email, password)**
- **Edit user (name, email)**
- **Delete user (besides ROLE_ADMIN)**
- **Disable/enable user (ban/unban)**


### **VALIDATION**
###### All information which you can enter on the site is validated and therefore read carefully the tooltips when you have some error.


### **HOW TO START**
1. Go to **http://calories-manager-java.herokuapp.com/**
1. If you haven't an account, press **Create account**
1. If you already have an account, enter your credentials and press **Login**
1. If you don't want to create your own account, you can test by using the next accounts:
    - **user@yandex.ru &nbsp; 12345**  (USER)
    - **admin@gmail.com  12345**  (ADMIN)

### **EXTRA INFORMATION**

#### **REST API**
The application provides a possibility to get data by using **REST API** (using **JSON**).<br/>
Firstly you need to pass a **basic authentication** and then you can use it by the next urls:
###### For **USER** and **ADMIN**
- http://calories-manager-java.herokuapp.com/rest/profile
- http://calories-manager-java.herokuapp.com/rest/profile/meals

###### For **ADMIN**
- http://calories-manager-java.herokuapp.com/rest/admin/users

#### **TESTING**
Every layer of the application is covered by **Spring/JUnit4** tests.


### **TECHNOLOGY STACK**
<img src="https://user-images.githubusercontent.com/13649199/27433714-8294e6fe-575e-11e7-9c41-7f6e16c5ebe5.jpg" alt="Drawing" width="750" height="300" />



### **ABOUT DEVELOPING**
##### The project was developed in conjunction with <a style="color:blue" href="http://gkislin.ru">Grigory Kislin</a> as **Java Enterprise Internship.**