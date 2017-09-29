## **_<p align="center">CALORIES MANAGER</p>_**
<p align="center"><img src="https://i0.wp.com/tunedinparents.com/wp-content/uploads/2017/04/Tuned-In-Parents-Top-5-Ways-to-Boost-Your-Metabolism-for-Healthy-Weight-Loss-BMR.jpg" alt="Drawing" width="500" height="150" /></p>

### **_WHAT IS IT?_**
<font size="5">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;This is a **_site_** which provides a possibility to **_write down_** some information about your meals and to **_manage_** a number of calories which you eat per a day. If a number of calories, consumed by you for some day, are more than a <u>maximum</u> number of calories per a day, specified in your profile, all your meals get a <font color="red">**_red color_**</font> for this day. It clearly allows to control a number of calories which you eat per a day and can be useful for you if you train somewhere (gym, swimming, etc.). <br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;The application keeps all information about your meals and allows to filter it by <u>**_date_**</u> and <u>**_time_**</u> and also to find it by a <u>**_field_**</u>, specified by you. For managing your meals all **_CRUD_** operations (Create, Read, Update, Delete) are available for you.</font>

-------------

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

-------------

### **VALIDATION**
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;All information which you can enter on the site <u>_is validated_</u> and therefore read carefully the tooltips when you have some error.

-------------

### **HOW TO START**
1. Go to **http://calories-manager-java.herokuapp.com/**
1. If you haven't an account, press **Create account**
1. If you already have an account, enter your credentials and press **Login**
1. If you don't want to create your own account, you can test by using the next accounts:
    - **user@yandex.ru &nbsp;&nbsp; password**  (USER)
    - **admin@gmail.com &nbsp;&nbsp;&nbsp;&nbsp; admin**  (ADMIN)

-------------

### **EXTRA INFORMATION**

#### **REST API**
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;The application provides a possibility to get data by using **REST API** (using **JSON**).<br/>
Firstly you need to pass a **basic authentication** and then you can use it by the next urls:
###### For **USER** and **ADMIN**
- http://calories-manager-java.herokuapp.com/rest/profile
- http://calories-manager-java.herokuapp.com/rest/profile/meals

###### For **ADMIN**
- http://calories-manager-java.herokuapp.com/rest/admin/users

#### **TESTING**
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Every layer of the application is covered by **Spring/JUnit4** tests.

-------------

### **TECHNOLOGY STACK**
<img src="https://user-images.githubusercontent.com/13649199/27433714-8294e6fe-575e-11e7-9c41-7f6e16c5ebe5.jpg" alt="Drawing" width="750" height="300" />

-------------

### **ABOUT DEVELOPING**
##### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;The project was developed in conjunction with <a style="color:blue" href="http://gkislin.ru">Grigory Kislin</a> as **<span style="font-size:18pt">Java Enterprise Internship.</span>**
