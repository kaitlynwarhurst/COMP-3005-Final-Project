//Kaitlyn Warhurst

import java.sql.Date;
import java.sql.Time;
import java.sql.ResultSet;

import java.util.Scanner;

public class main {
    static public Scanner input = new Scanner(System.in);
    static private int currentUser = -1;

    static private UserType currentUserType = UserType.NOT_SIGNED_IN;
    static public enum UserType {ADMIN, TRAINER, MEMBER, NOT_SIGNED_IN};
    public static boolean signInUser(){
        //Clear buffer
        input.nextLine();
        System.out.println("Please enter in the needed information as requested:\n" +
                            "email: ");
        String email = input.nextLine();
        while (email.isEmpty()) {
            System.out.println("Email cannot be empty. Please enter your email:");
            email = input.nextLine();
        }
        currentUser = queries.getUserId(email);
        if(currentUser == -1){
            System.out.println("No User with this email");
            return false;
        }
        System.out.println("Password: ");
        String input_password = input.nextLine();
        String actual_password = queries.getPassword(currentUser);

        if(input_password.equals(actual_password)){
            if(queries.verifyMember(currentUser)){
                currentUserType = UserType.MEMBER;
                return true;
            }
            if(queries.verifyTrainer(currentUser)){
                currentUserType = UserType.TRAINER;
                return true;
            }
            if(queries.verifyAdmin(currentUser)){
                currentUserType = UserType.ADMIN;
                return true;
            }
        }
        System.out.println("Wrong password- please ensure you are not adding extra spaces");
        return false;
    }

    public static int registerNewUser(){
        //Clear buffer
        input.nextLine();
        System.out.println("Please enter in the needed information as requested:" +
                "email: ");
        String email = input.nextLine();
        int userId = queries.getUserId(email);
        if(userId != -1){
            System.out.println("There is already a user with this email");
            return -1;
        }
        System.out.println("First name: ");
        String first_name = input.nextLine();
        System.out.println("Last name: ");
        String last_name = input.nextLine();
        System.out.println("Phone number: ");
        String phone = input.nextLine();
        System.out.println("Please create a password: ");
        String password = input.nextLine();
        boolean success = queries.registerNewUser(first_name, last_name, email, phone, password);
        if(success){
            currentUser = queries.getUserId(email);
            return currentUser;
        }
        System.out.println("Failed to create new user");
        return -1;
    }

    public static void updateUserPassword() {
        input.nextLine();
        System.out.println("Please enter new password: ");
        String new_password = input.nextLine();
        while(new_password.isEmpty()) {
            System.out.println("Password can't be empty!");
            new_password = input.nextLine();
        }
        boolean success =  queries.updatePassword(currentUser, new_password);
        if(success) {
            System.out.println("Password updated!");
            return;
        }
        System.out.println("Password reset unsuccessful!");
    }
    public static void viewMemberSchedule(){
        input.nextLine();
        System.out.println("Your upcoming events are: ");
        System.out.println("Training Sessions: ");
        try {
            ResultSet upcomingSessions = queries.getMemberSessions(currentUser);
            while (upcomingSessions.next()) {
                int session_id = upcomingSessions.getInt("session_id");
                int trainer_id = upcomingSessions.getInt("trainer_id");
                String trainer_name = queries.getUserName(trainer_id);
                Date sessionDate = upcomingSessions.getDate("session_date");
                Time fromTime = upcomingSessions.getTime("session_start");
                Time toTime = upcomingSessions.getTime("session_end");
                System.out.println("Session ID: " + session_id + " with Trainer: " + trainer_name + "Trainer_id: "+ trainer_id + sessionDate + " from " + fromTime + " to " +toTime);
            }
        }
        catch(Exception e){
            System.out.println("Unknown error occurred "+ e);
            return;
        }
        System.out.println("Classes: ");
        try {
            ResultSet upcomingClasses = queries.getMemberClasses(currentUser);
            while (upcomingClasses.next()) {
                int attendance_id = upcomingClasses.getInt("attendance_id");
                int class_id = upcomingClasses.getInt("class_id");
                String class_description =  upcomingClasses.getString("class_description");
                int trainer_id = upcomingClasses.getInt("trainer_id");
                String trainer_name = queries.getUserName(trainer_id);
                Date sessionDate = upcomingClasses.getDate("class_date");
                Time fromTime = upcomingClasses.getTime("start_time");
                Time toTime = upcomingClasses.getTime("end_time");
                int difficulty = upcomingClasses.getInt("difficulty");
                float cost = upcomingClasses.getFloat("cost");
                System.out.println("Attendance ID: " + attendance_id + ", Class ID: " + class_id + ", Description: " + class_description + ", Trainer: " + trainer_name + ", Date: " + sessionDate + ", Time: " + fromTime + " - " + toTime + ", Difficulty: " + difficulty + ", Cost: " + cost);
            }
        }
        catch(Exception e){
            System.out.println("Unknown error occurred "+ e);
            return;
        }
    }

    public static void dropClass(){
        input.nextLine();
        System.out.println("Please enter the Class ID of the class you wish to cancel: ");
        int class_id = input.nextInt();
        System.out.println("Do you wish to cancel attendance for all future instances of this class? y/n");
        if(input.nextLine().trim().equalsIgnoreCase("y")){
            if(queries.dropOut(class_id, currentUser)){
                System.out.println("Dropped out class!");
            }
            else{
                System.out.println("Unable to remove you from future classes");
            }
            return;
        }
        System.out.println("Please enter the attendance id of the class instance you want to cancel: ");
        int attendance_id = input.nextInt();
        if(queries.cancelOneClass(attendance_id, currentUser)){
            System.out.println("Cancelled attendance for class "+ attendance_id);
            return;
        }
        System.out.println("Unable to cancel attendance for class "+ attendance_id + ". Please contact admin!");
        return;

    }

    public static void cancelTrainingSession(){
        input.nextLine();
        System.out.println("Please enter the ID of the trainer for the session you wish to cancel: ");
        int trainer_id = input.nextInt();
        System.out.println("Do you wish to cancel attendance for all future sessions with this trainer? y/n");
        if(input.nextLine().trim().equalsIgnoreCase("y")){
            if(queries.removeTrainingSession(trainer_id, currentUser)){
                System.out.println("Cancelled all training sessions with trainer " + trainer_id + "!");
            }
            else{
                System.out.println("Unable to cancel future sessions");
            }
            return;
        }
        System.out.println("Please enter the session id of the session you want to cancel: ");

        int session_id = input.nextInt();
        if(queries.removeTrainingSession(session_id)){
            System.out.println("Cancelled session "+ session_id);
            return;
        }
        System.out.println("Unable to cancel session  "+ session_id + ". Please contact admin or the trainer!");
        return;
    }

    public static boolean registerNewMember(){
        int userId = registerNewUser();
        if(userId == -1) return false;
        return queries.registerMember(userId);
    }

    public static void updateInfo(String first_name, String last_name, String email, String phone){
        //Clear buffer
        input.nextLine();
        System.out.println("Change first_name: " + first_name + "? y/n");
        if(input.nextLine().trim().equalsIgnoreCase("y")){
            System.out.println("First name: ");
            first_name = input.nextLine();
        }
        System.out.println("Change last_name: " + last_name + "? y/n");
        if(input.nextLine().trim().equalsIgnoreCase("y")){
            System.out.println("Last name: ");
            last_name = input.nextLine();
        }
        System.out.println("Change email: " + email + "? y/n");
        if(input.nextLine().trim().equalsIgnoreCase("y")){
            System.out.println("email: ");
            email = input.nextLine();
        }
        System.out.println("Change phone number: " + phone + "? y/n");
        if(input.nextLine().trim().equalsIgnoreCase("y")){
            System.out.println("Phone number: ");
            phone = input.nextLine();
        }
        queries.updateUserInformation(currentUser, first_name, last_name, email, phone);
    }

    public static void viewDashboard(){

        System.out.println("Dashboard:");

        float weight = (float)0.0, height = (float)0.0, goalWeight = (float)0.0;
        String goalDesc = null;
        Date checkin_date = null, last_update = null;
        try {
            ResultSet metrics = queries.getGoalsAndMetrics(currentUser);
            if (metrics.next()) {
                weight = metrics.getFloat("member_weight");
                height = metrics.getFloat("member_height");
                goalWeight = metrics.getFloat("goal_weight");
                goalDesc = metrics.getString("goal_description");
                checkin_date = metrics.getDate("goal_checkin_date");
                last_update = metrics.getDate("last_update");
            }
            else{
                System.out.println("Metrics and Goals are not set yet!");
                return;
            }
        }
        catch(Exception e){
            System.out.println("An unknown error occurred: " + e);
            return;
        }

        System.out.println("Health Metrics:");
        System.out.println("Weight: "+ weight + " Height: " + height);
        System.out.println("Goals:");
        System.out.println("Goal Weight: "+ goalWeight + "\nGoal Description: "+ goalDesc);
        System.out.println("Last checkin: "+ last_update +" Next Checkin Date: " + checkin_date);

    }

    static public void updateMetricsAndGoals(){
        //Clear buffer
        input.nextLine();
        float weight = (float)0.0, height = (float)0.0, goalWeight = (float)0.0;
        String checkin_date = null, goalDesc = null;
        Date  last_update = null;
        System.out.println("Please provide the needed details as prompted\n");
        System.out.println("Current weight: ");
        weight = input.nextFloat();
        System.out.println("Current height: ");
        height = input.nextFloat();
        System.out.println("Goal weight: ");
        goalWeight = input.nextFloat();
        input.nextLine();
        System.out.println("Goal description (please type only one line): ");
        goalDesc = input.nextLine();
        System.out.println("Next checkin date: ");
        checkin_date = input.nextLine();

        queries.updateMetrics(currentUser, height, weight, goalDesc, goalWeight,checkin_date);
    }

    static public void addClass(){
        input.nextLine();
        System.out.println("Would you like to view upcoming classes? y/n");
        if(input.nextLine().trim().equalsIgnoreCase("y")){
            try {
                ResultSet classes = queries.viewUpcomingClasses();
                while (classes.next()) {
                    int class_id = classes.getInt("class_id");
                    int trainer_id = classes.getInt("trainer_id");
                    String class_desc = classes.getString("class_description");
                    Date class_date = classes.getDate("class_date");
                    Time start_time = classes.getTime("start_time");
                    Time end_time = classes.getTime("end_time");
                    float cost = classes.getFloat("cost");
                    int difficulty = classes.getInt("difficulty");

                    System.out.println("class_id: " + class_id + ", trainer_id: " + trainer_id + ", class_description: " + class_desc +
                            ", class_date: " + class_date + ", start_time: " + start_time + ", end_time: " + end_time +
                            ", cost: " + cost + ", difficulty: " + difficulty);
                }
            }
            catch (Exception e){
                System.out.println("An unknown error occurred!");
                return;
            }

        }
        System.out.println("Please enter the ID of the class you would like to enroll in (Please enter -1 if you no longer which to add a class)");
        int desired_class = input.nextInt();
        if(desired_class == -1) return;
        queries.enrollInClass(desired_class, currentUser);
        float cost = queries.getClassCost(desired_class);
        queries.generateBill(currentUser, cost, "Class cost for class  "+ desired_class);


    }

    static public void addTrainingSession(){
        input.nextLine();
        System.out.println("Would you like to view all available trainers? y/n");
        if(input.nextLine().trim().equalsIgnoreCase("y")){
            try {
                ResultSet classes = queries.getAllTrainersAvailability();
                while (classes.next()) {
                    int availability_id = classes.getInt("availability_id");
                    int trainer_id = classes.getInt("trainer_id");
                    Date class_date = classes.getDate("available_date");
                    Time start_time = classes.getTime("startTime");
                    Time end_time = classes.getTime("endTime");


                    System.out.println("availability_id: " + availability_id + ", trainer_id: " + trainer_id + ", available_date: " + class_date +
                            ", start_time: " + start_time + ", end_time: " + end_time);


                }

                System.out.println("Please enter the availability id of the session you would like to book (-1 if you no longer wish to book): ");
                int option = input.nextInt();
                if(option== -1) return;
                ResultSet rs = queries.removeAvailability(option);
                if (rs.next()){
                    int trainer_id = rs.getInt("trainer_id");
                    Date date = rs.getDate("available_date");
                    Time start = rs.getTime("startTime");
                    Time end = rs.getTime("endTime");
                    queries.addTrainingSession(currentUser, trainer_id,date, start, end);
                    float cost = queries.getTrainerHourly(trainer_id);
                    queries.generateBill(currentUser, cost, "Training Session with trainer "+ trainer_id);
                    return;
                }

            }
            catch (Exception e){
                System.out.println("An unknown error occurred!");
                return;
            }

        }

    }

    public static void viewBills(){
        try {
            // Iterate through the ResultSet and print each bill
            ResultSet bills = queries.getMemberBills(currentUser);
            while (bills.next()) {
                int billId = bills.getInt("bill_id");
                float billAmount = bills.getFloat("bill_amount");
                float amountPaid = bills.getFloat("amount_paid");
                String paymentMethod = bills.getString("payment_method");
                String billDescription = bills.getString("bill_description");
                Date billDate = bills.getDate("bill_date");
                Date datePaid = bills.getDate("date_paid");

                // Print the bill details
                System.out.println("Bill ID: " + billId);
                System.out.println("Amount: " + billAmount);
                System.out.println("Amount Paid: " + amountPaid);
                System.out.println("Payment Method: " + paymentMethod);
                System.out.println("Description: " + billDescription);
                System.out.println("Bill Date: " + billDate);
                System.out.println("Date Paid: " + datePaid);
                System.out.println("---------------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void payBill(){
        input.nextLine();
        System.out.println("Please input the bill id you wish to pay: ");
        int bill_id = input.nextInt();
        System.out.println("Please enter the amount you wish to pay: ");
        float amount = input.nextFloat();
        System.out.println("Please enter your visa card number: ");
        input.nextLine();
        queries.payBill(bill_id, amount, "VISA");
    }

    public static void main(String[] args){


        String first_name = null, last_name= null, email = null, phone = null;
        System.out.println("Welcome to the FitnessClub! \n");
        enum menu {LOGIN_MENU, MEMBER_OPTIONS, TRAINER_OPTIONS, ADMIN_OPTIONS, MEMBER_SCHEDULE, MEMBER_BILLING, TRAINER_SCHEDULE};
        menu currentMenu = menu.LOGIN_MENU;
        while(true){

            switch (currentMenu){
                case LOGIN_MENU:
                    System.out.println( "1. Sign in\n" +
                                        "2. Register\n" +
                                        "0. Quit\n" +
                                        "Please Note registering is only for new members- admin and trainers must sign in with provided credentials");
                    int option = input.nextInt();

                    switch(option){
                        case 0:
                            System.out.println("Quitting");
                            return;

                        case 1:
                            if(signInUser()) System.out.println("User signed in as user " + currentUser + " as a " + currentUserType);
                            switch (currentUserType) {
                                case ADMIN -> currentMenu = menu.ADMIN_OPTIONS;
                                case TRAINER -> currentMenu = menu.TRAINER_OPTIONS;
                                case MEMBER -> currentMenu = menu.MEMBER_OPTIONS;
                            }
                            break;

                        case 2:
                            if(registerNewMember()){
                                currentMenu = menu.MEMBER_OPTIONS;
                                break;
                            }
                            break;
                    }
                    break;

                case MEMBER_OPTIONS:
                    try {
                        ResultSet member_info = queries.viewMemberProfile(currentUser);
                        if (member_info.next()) {
                            first_name = member_info.getString("first_name");
                            last_name = member_info.getString("last_name");
                            email = member_info.getString("email");
                            phone = member_info.getString("phone");
                        }
                    }
                    catch(Exception e){
                        System.out.println("An unknown error occurred: " + e);
                        return;
                    }
                    System.out.println("Welcome! " + first_name);
                    System.out.println("MemberInfo: \n" +
                                        "Name: " + first_name + " " + last_name + " " +
                                        "Phone number: " + phone +
                                        " Email: " + email);
                    System.out.println("0. Quit\n" +
                                        "1. Update Profile\n" +
                                        "2. View Dashboard\n" +
                                        "3. Update Metrics and Goals\n"+
                                        "4. Manage Schedule\n" +
                                        "5. Update password\n" +
                                        "6. Log Out\n" +
                                        "7. Manage bills");
                    option = 0;
                    option = input.nextInt();
                    switch(option){
                        case 0:
                            return;
                        case 1:
                            updateInfo(first_name, last_name, email, phone);
                            break;
                        case 2:
                            viewDashboard();
                            break;
                        case 3:
                            updateMetricsAndGoals();
                            break;
                        case 4:
                            currentMenu = menu.MEMBER_SCHEDULE;
                            break;
                        case 5:
                            updateUserPassword();
                            break;
                        case 6:
                            currentUser = -1;
                            currentUserType = null;
                            currentMenu = menu.LOGIN_MENU;
                            break;
                        case 7:
                            currentMenu = menu.MEMBER_BILLING;
                            break;
                    }
                    break;
                case MEMBER_SCHEDULE:

                    viewMemberSchedule();

                    System.out.println("Please select an option:\n" +
                                        "1. Cancel a class\n" +
                                        "2. Cancel a training Session\n" +
                                        "3. Add a class\n" +
                                        "4. Add a training session\n" +
                                        "5. Go back\n" +
                                        "0. Quit");

                    option = 0;
                    option = input.nextInt();

                    switch(option){
                        case 1:
                            dropClass();
                            break;
                        case 2:
                            cancelTrainingSession();
                            break;
                        case 3:
                            addClass();
                            break;
                        case 4:
                            addTrainingSession();
                            break;
                        case 5:
                            currentMenu = menu.MEMBER_OPTIONS;
                            break;
                        case 0:
                            return;
                    }
                    break;
                case MEMBER_BILLING:
                    System.out.println("IF you would like a refund please speak to admin");
                    System.out.println("Please select an option:" +
                                        "0. Quit\n" +
                                        "1. Go back \n" +
                                        "2. ViewBills \n" +
                                        "3. Pay bill");
                    int billing_opt = input.nextInt();
                    switch(billing_opt){
                        case 0:
                            return;
                        case 1:
                            currentMenu = menu.MEMBER_OPTIONS;
                            break;
                        case 2:
                            viewBills();
                            break;
                        case 3:
                            payBill();
                            break;
                    }


            }
        }

//


        //input.close();
    }


}
