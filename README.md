# JFS6WDE_CAPSTONE_BUS-RESERVATION

Data Distribution Inside This Project

1.pom.xml -> This file holds all the dependency of this project. 2.src/main/java -> This folder holds the main java programs.

--------Inside src/main/java there are 8 important packages.------

1. Base Package -> Contain Main SpringBoot run application class.

2. Controller package -> Contain 4 controllers to hadnle endpoints.

Auth Controller - for Login and Registration Authentication endpoints.

BusController - for Bus entity endpoints.

BookingConrtoller - for Booking endpoints.

RouteController - for Route nevigation endpoints;

3. Dto package -> Contain all Dto files

UserDto - for User entity

BookingDto - for BookingHistory entity

4. Entity Package - Contains all entity files.

Bus , BookingHistory, Role, User

5. Exception Package -> Contains neccessary user defined Exceptions.

AdminException, ResourceNotFoundExceptions

ErrorDetails -  to give time and message of exception.
GlobalExceptionHandler - to handle user defined exception.

6. Respository package -> Contains all the Repository classes.

BookingRepository, BusRepository, RoleRepository. UserRepository

7. Service Package -> Contains all Bussiness logic for this application.

8. Configuration Package -> This package maintain the security Congiguration of this application.


----------------Steps To Run The Application-------------------

 Step 1. First setup you database, I am using Local Database, so i have setup my configuration according to my local database.

 To Setup Database -> Go to application.properties inside src/main/resources package and implement the dependecies of the database you want to use.

 Step 2. Go to base pacakge and Run the main Application.

 Step 3. User and Admin Registration ->

 For Admin Registration - you have to register as a admin and to register as admin use this below email and password :-

 Admin email -> admin@admin.com
 Admin password -> admin

 you cannot make your own admin account to make your own admin account you have to change the logic setting of CustomUserServices inside services package.

 Permition of pages for Admin ->login, register, users , buslist, addBus, updatebus

 For User Login -> you can register as user with any email and password you want.

 Step 4 After registering as an Admin you need to add buses for user. To add buses go to add bus page and fill the add bus form. You can update and delete the buses you added after adding.

 Step 5 Now login as a User to enjoy bus application services like.

 Book Now :-  You can book the Added bus by clicking on Book Now button and after you click Book Now button you have to fill the payment form and after a successfull payment it will nevigate you to the booking history.

 Show Route :- Show Route page will provide you the information of buses of the specific route of your choosen.

 BookingHistory :- It contains the booking history of the logged in user.

Permition of pages for non-admin user ->login, register, index, bookinghsitory, addbooking, showRoute.
