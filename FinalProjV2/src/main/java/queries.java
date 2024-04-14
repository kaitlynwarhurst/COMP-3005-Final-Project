import java.sql.*;
import java.sql.DriverManager;
public class queries {

    //    static variables used by all the functions to establish a connection with the database of ProstgreSql
    //    All of below must be updated for specific database - currently its the defaults
    static String url = "jdbc:postgresql://localhost:5432/FitnessClubDatabase";
    static String user = "postgres";
    static String password = "3rag0n";

    public static boolean registerNewUser(String first_name, String last_name, String email, String phone, String userPword){

        try {
//            Establish connection
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            String addUser = "INSERT INTO Users (user_id, first_name, lastname, email, phone, pword) VALUES ('"+ first_name +"','" + last_name +"','" + email + "','" + phone +"');";
            statement.executeUpdate(addUser);
            statement.close();
            connection.close();
        }

        catch(Exception e){
            System.out.println(e);
            return false;
        }
        return true;
    }

    public static int getUserId(String email){
        try{
            //            Establish connection
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            Statement statement = connection.createStatement();
            //The SQL query that will be run
            String getUserIDQuery = "SELECT user_id FROM Users WHERE email =  '" +email + "'" ;
            //Executing query, collecting results and returning the relevant id
            ResultSet rs = statement.executeQuery(getUserIDQuery);
            int id = -1;
            while(rs.next()){
                id = rs.getInt("user_id");
            }

            return id;

        }
        catch(Exception e){
            System.out.println(e);
        }
        return 0;
    }

    public static String getPassword(int user_id){
        try{
            //            Establish connection
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            Statement statement = connection.createStatement();
            //The SQL query that will be run
            String getPword = "SELECT pword FROM Users WHERE user_id =  " +user_id  + ";";       //Executing query, collecting results and returning the relevant id
            ResultSet rs = statement.executeQuery(getPword);
            String password = null;
            while(rs.next()){
                password = rs.getString("pword");
            }

            return password;

        }
        catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    public static boolean verifyMember(int user_id){
        try{
            //            Establish connection
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);


            //The SQL query that will be run
            String verifyMember = "SELECT COUNT(*) AS count FROM Members WHERE user_id = ?;";       //Executing query, collecting results and returning the relevant id
            PreparedStatement statement = connection.prepareStatement(verifyMember);
            statement.setInt(1,user_id);
            ResultSet rs = statement.executeQuery();
            int count = 0;
            while(rs.next()){
                count = rs.getInt("count");
            }

            if(count>0) {
                return true;
            }

        }
        catch(Exception e){
            System.out.println(e);
        }
        return false;
    }

    public static boolean verifyAdmin(int user_id){
        try{
            //            Establish connection
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);


            //The SQL query that will be run
            String verifyAdmin = "SELECT COUNT(*) AS count FROM Admin WHERE user_id = ?;";       //Executing query, collecting results and returning the relevant id
            PreparedStatement statement = connection.prepareStatement(verifyAdmin);
            statement.setInt(1,user_id);
            ResultSet rs = statement.executeQuery();
            int count = 0;
            while(rs.next()){
                count = rs.getInt("count");
            }

            if(count>0) return true;

        }
        catch(Exception e){
            System.out.println(e);
        }
        return false;
    }

    public static boolean verifyTrainer(int user_id){
        try{
            //            Establish connection
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);


            //The SQL query that will be run
            String verifyTrainer = "SELECT COUNT(*) AS count FROM Trainers WHERE user_id = ?;";       //Executing query, collecting results and returning the relevant id
            PreparedStatement statement = connection.prepareStatement(verifyTrainer);
            statement.setInt(1,user_id);
            ResultSet rs = statement.executeQuery();
            int count = 0;
            while(rs.next()){
                count = rs.getInt("count");
            }

            if(count>0) return true;

        }
        catch(Exception e){
            System.out.println(e);
        }
        return false;
    }

    public static boolean registerMember(int user_id){
        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            String addMember = "INSERT INTO Members(user_id) VALUES ("+ user_id +");";
            statement.executeUpdate(addMember);
            statement.close();
            connection.close();
        }
        catch (Exception e){
            System.out.println(e);
            return false;
        }
        return true;
    }

    public static boolean registerTrainer(int user_id, float hourly_rate){
        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            String addTrainer = "INSERT INTO Trainers(user_id, hourly_rate) VALUES ("+ user_id +"," + hourly_rate+ ");";
            statement.executeUpdate(addTrainer);
            statement.close();
            connection.close();
        }
        catch (Exception e){
            System.out.println(e);
            return false;
        }
        return true;
    }

    public static boolean registerAdmin(int user_id){
        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            String addAdmin = "INSERT INTO Admin(user_id) VALUES ("+ user_id + ";";
            statement.executeUpdate(addAdmin);
            statement.close();
            connection.close();
        }
        catch (Exception e){
            System.out.println(e);
            return false;
        }
        return true;
    }

    public static boolean updateMetrics(int user_id, float member_height, float member_weight, String goal_description, float goal_weight, String goal_checkin_date){
        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            String updateMetrics = "INSERT INTO MemberMetrics(user_id, member_height,member_weight, goal_description, goal_weight, goal_checkin_date) VALUES ("+ user_id + ","+ member_height+ "," + member_weight + ",'" + goal_description + "',"+ goal_weight + ",'" + goal_checkin_date + "');";
            statement.executeUpdate(updateMetrics);
            statement.close();
            connection.close();
        }
        catch (Exception e){
            System.out.println(e);
            return false;
        }
        return true;
    }

    public static boolean addExcersizeRoutine(int user_id, int workout_id, String date){
        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            String addRoutine = "INSERT INTO ExcersizeRoutines(member_id, workout_id, date_completed) VALUES ("+ user_id + ","+ workout_id + ",'" + date + "');";
            statement.executeUpdate(addRoutine);
            statement.close();
            connection.close();
        }
        catch (Exception e){
            System.out.println(e);
            return false;
        }
        return true;
    }

    public static boolean addTrainerAvailability(int trainer_id, String available_date, String start_time, String end_time){
        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            String addAvailability = "INSERT INTO TrainerAvailability(trainer_id, available_date, startTime, endTime) VALUES ("+ trainer_id + ",'"+ available_date + "','" + start_time + "','" + end_time + "';";
            statement.executeUpdate(addAvailability);
            statement.close();
            connection.close();
        }
        catch (Exception e){
            System.out.println(e);
            return false;
        }
        return true;
    }

    public static ResultSet getAllTrainersAvailability(){

        try{
            //            Establish connection
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            Statement statement = connection.createStatement();
            //The SQL query that will be run
            String getAllAvailable = "SELECT * FROM TrainerAvailability INNER JOIN Trainers ON Trainers.user_id  = TrainerAvailability.trainer_id WHERE available_date > CURRENT_DATE;" ;
            //Executing query, collecting results and returning the relevant id
            ResultSet rs = statement.executeQuery(getAllAvailable);


            return rs;
        }
        catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    public static ResultSet getTrainerAvailability(int trainer_id, String after_date, String after_time, String before_time){

        try{
            //            Establish connection
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            Statement statement = connection.createStatement();
            //The SQL query that will be run
            String getAvailability = "SELECT * FROM TrainerAvailability WHERE trainer_id = " + trainer_id +" AND available_date > '" + after_date + "'AND session_start > '" + after_time + "'AND session_end < '" + before_time + "';" ;
            //Executing query, collecting results and returning the relevant id
            ResultSet rs = statement.executeQuery(getAvailability);


            return rs;
        }
        catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    public static ResultSet removeAvailability(int availability_id){
        ResultSet rs = null;
        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            // First, select the information of the deleted session
            String selectAvailability = "SELECT * FROM TrainerAvailability WHERE availability_id = ?";
            PreparedStatement selectStatement = connection.prepareStatement(selectAvailability);
            selectStatement.setInt(1, availability_id);
            rs = selectStatement.executeQuery();

            // Then, delete the session
            String removeAvailability = "DELETE FROM TrainerAvailability WHERE availability_id = ?";
            PreparedStatement removeStatement = connection.prepareStatement(removeAvailability);
            removeStatement.setInt(1, availability_id);
            removeStatement.executeUpdate();

        }
        catch (Exception e){
            System.out.println(e);
        }
        return rs;
    }


    public static boolean addTrainingSession(int member_id, int trainer_id, Date date, Time start, Time end){
        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            // Create a prepared statement
            String insertTrainingSession = "INSERT INTO TrainingSessions(member_id, trainer_id, session_date, session_start, session_end) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(insertTrainingSession);

            // Set values for the parameters
            statement.setInt(1, member_id);
            statement.setInt(2, trainer_id);
            statement.setDate(3, date);
            statement.setTime(4, start);
            statement.setTime(5, end);

            // Execute the prepared statement
            statement.executeUpdate();
            statement.close();
            connection.close();
        }
        catch (Exception e){
            System.out.println(e);
            return false;
        }
        return true;
    }


    public static boolean removeTrainingSession(int session_id){
        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            String removeSession = "DELETE FROM TrainingSessions WHERE session_id = " + session_id + ";";
            statement.executeUpdate(removeSession);
            statement.close();
            connection.close();
        }
        catch (Exception e){
            System.out.println(e);
            return false;
        }
        return true;
    }

    public static boolean removeTrainingSession(int trainer_id, int member_id){
        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            String removeSession = "DELETE FROM TrainingSessions WHERE trainer_id = ? AND member_id = ? ;";
            PreparedStatement statement = connection.prepareStatement(removeSession);
            statement.setInt(1, trainer_id);
            statement.setInt(2, member_id);
            statement.executeUpdate(removeSession);
            statement.close();
            connection.close();
        }
        catch (Exception e){
            System.out.println(e);
            return false;
        }
        return true;
    }

    public static ResultSet getGoalsAndMetrics(int member_id){
        try{
            //            Establish connection
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);


            //The SQL query that will be run
            String getGoalsandMetrics = "SELECT * FROM MemberMetrics WHERE user_id = ? ORDER BY metric_id DESC ;" ;
            PreparedStatement statement = connection.prepareStatement(getGoalsandMetrics);
            statement.setInt(1, member_id);
            ResultSet rs = statement.executeQuery();

            return rs;

            //Executing query, collecting results and returning the relevant id

        }
        catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    public static ResultSet getExcersizeRoutines(int member_id){
        try{
            //            Establish connection
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            //The SQL query that will be run
            String getExcersizeRoutines = "SELECT * FROM ExcersizeRoutines WHERE member_id = ?;" ;
            //Executing query, collecting results and returning the relevant id
            PreparedStatement statement = connection.prepareStatement(getExcersizeRoutines);
            statement.setInt(1, member_id);
            ResultSet rs = statement.executeQuery();

            return rs;
        }
        catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    public static boolean addAchievement(int member_id, String achievementType, String achievementDescription){
        try{
            //            Establish connection
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            //The SQL query that will be run
            String addAchievement = "INSERT INTO FitnessAchievement(member_id, achievement_type, achievement_description) VALUES (?,?,?);" ;
            //Executing query, collecting results and returning the relevant id
            PreparedStatement statement = connection.prepareStatement(addAchievement);
            statement.setInt(1, member_id);
            statement.setString(2, achievementType);
            statement.setString(3, achievementDescription);
            statement.executeUpdate();
            statement.close();
            connection.close();
        }
        catch(Exception e){
            System.out.println(e);
            return false;
        }
        return true;
    }

    public static ResultSet getAchievements(int member_id){
        try{
            //            Establish connection
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            //The SQL query that will be run
            String getAchievements = "SELECT * FROM FitnessAchievement WHERE member_id = ?;" ;
            //Executing query, collecting results and returning the relevant id
            PreparedStatement statement = connection.prepareStatement(getAchievements);
            statement.setInt(1, member_id);
            ResultSet rs = statement.executeQuery();

            return rs;
        }
        catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    public static boolean addTrainerReview(int trainer_id, int rating){
        try{
            //            Establish connection
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            //The SQL query that will be run
            String updateRating = "UPDATE trainers SET rating = (rating + ?), num_reviews = (num_reviews + 1) WHERE user_id = ?";
            PreparedStatement statement = connection.prepareStatement(updateRating);
            statement.setInt(1, rating);
            statement.setInt(2, trainer_id);
            statement.executeUpdate();
            statement.close();
            connection.close();
        }
        catch(Exception e){
            System.out.println(e);
            return false;
        }
        return true;
    }

    public static boolean updateUserInformation(int user_id, String first_name, String last_name, String email, String phone){
        try{
            //            Establish connection
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            //The SQL query that will be run
            String updateUser = "UPDATE Users SET first_name = ?, last_name = ?, email = ?, phone = ? WHERE user_id = ?";            //Executing query, collecting results and returning the relevant id

            PreparedStatement statement = connection.prepareStatement(updateUser);
            statement.setString(1, first_name);
            statement.setString(2, last_name);
            statement.setString(3, email);
            statement.setString(4, phone);
            statement.setInt(5, user_id);
            statement.executeUpdate();
            statement.close();
            connection.close();
        }
        catch(Exception e){
            System.out.println(e);
            return false;
        }
        return true;
    }

    public static boolean updatePassword(String email, String new_password){
        try{
            //            Establish connection
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            //The SQL query that will be run
            String updateUser = "UPDATE Users SET pword = ? WHERE email = ?";            //Executing query, collecting results and returning the relevant id

            PreparedStatement statement = connection.prepareStatement(updateUser);
            statement.setString(1, new_password);
            statement.setString(2, email);

            statement.executeUpdate();
            statement.close();
            connection.close();
        }
        catch(Exception e){
            System.out.println(e);
            return false;
        }
        return true;
    }

    public static boolean updatePassword(int user_id, String new_password){
        try{
            //            Establish connection
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            //The SQL query that will be run
            String updateUser = "UPDATE Users SET pword = ? WHERE user_id = ?";            //Executing query, collecting results and returning the relevant id

            PreparedStatement statement = connection.prepareStatement(updateUser);
            statement.setString(1, new_password);
            statement.setInt(2, user_id);

            statement.executeUpdate();
            statement.close();
            connection.close();
        }
        catch(Exception e){
            System.out.println(e);
            return false;
        }
        return true;
    }

    public static ResultSet viewMemberProfile(String first_name, String last_name){
        try{
            //            Establish connection
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            //The SQL query that will be run
            String getMembers = "SELECT * FROM Members INNER JOIN Users ON Members.user_id = Users.user_id WHERE first_name = ? AND last_name = ?;" ;
            //Executing query, collecting results and returning the relevant id
            PreparedStatement statement = connection.prepareStatement(getMembers);
            statement.setString(1, first_name);
            statement.setString(2, last_name);
            ResultSet rs = statement.executeQuery();

            return rs;
        }
        catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static ResultSet viewMemberProfile(int userId){
        try{
            //            Establish connection
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            //The SQL query that will be run
            String getMembers = "SELECT * FROM Members INNER JOIN Users ON Members.user_id = Users.user_id WHERE Users.user_id = ?;" ;
            //Executing query, collecting results and returning the relevant id
            PreparedStatement statement = connection.prepareStatement(getMembers);
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();

            return rs;
        }
        catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static int addRoomBooking(int room_id, Date booking_date, Time start, Time end){
        try{
            //            Establish connection
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            //The SQL query that will be run
            String addBooking = "INSERT INTO RoomBookings(room_id, booking_date, booking_time_start, booking_end_time ) VALUES (?,?,?, ?);" ;
            //Executing query, collecting results and returning the relevant id
            PreparedStatement statement = connection.prepareStatement(addBooking);
            statement.setInt(1, room_id);
            statement.setDate(2, booking_date);
            statement.setTime(3, start);
            statement.setTime(4, end);
            int changedRows = statement.executeUpdate();
            if( changedRows > 0){
                ResultSet rs = statement.getGeneratedKeys();
                if(rs.next()){
                    int booking_id = rs.getInt(1);
                    statement.close();
                    connection.close();
                    return booking_id;
                }
            }


        }
        catch(Exception e){
            System.out.println(e);
            return -1;
        }
        return -1;
    }

    public static boolean changeMaintenanceStatus(int equipment_id, boolean maintenanceRequired){
        try{
            //            Establish connection
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            String changeMaintenance;
            //The SQL query that will be run
            if(maintenanceRequired){
                changeMaintenance = "UPDATE Equipment SET needs_maintenance = ?, next_maintenance_date = CURRENT_DATE WHERE equipment_id = ?";            //Executing query, collecting results and returning the relevant id
            } else{
                changeMaintenance = "UPDATE Equipment SET needs_maintenance = ?, last_maintenance_date = CURRENT_DATE, next_maintenance_date = CURRENT_DATE + INTERVAL '6 months' WHERE equipment_id = ?";
            }

            PreparedStatement statement = connection.prepareStatement(changeMaintenance);
            statement.setBoolean(1, maintenanceRequired);
            statement.setInt(2, equipment_id);
            statement.close();
            connection.close();
            statement.executeUpdate();
        }
        catch(Exception e){
            System.out.println(e);
            return false;
        }
        return true;
    }

    public static ResultSet getNeedMaintenanceEquipment(){
        try{
            //            Establish connection
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            //The SQL query that will be run
            String needsMaintenance = "SELECT * FROM Equipment WHERE needs_maintenance = true OR next_maintenance_date > CURRENT_DATE;" ;
            PreparedStatement statement = connection.prepareStatement(needsMaintenance);
            //Executing query, collecting results and returning the relevant id

            ResultSet rs = statement.executeQuery();

            return rs;
        }
        catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static boolean addClass(int trainer_id, String class_description, int category, int room_id, Date date, Time start_time, Time end_time, float cost, int difficulty){
        try{
            //            Establish connection
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            //The SQL query that will be run
            String addClass = "INSERT INTO Classes(trainer_id, class_description, category, room_id, class_date, start_time, end_time, cost, difficulty) VALUES (?,?,?,?,?,?,?,?,?,?);" ;
            //Executing query, collecting results and returning the relevant id
            PreparedStatement statement = connection.prepareStatement(addClass);
            statement.setInt(1, trainer_id);
            statement.setString(2, class_description);
            statement.setInt(3, category);
            statement.setInt(4, room_id);
            statement.setDate(5, date);
            statement.setTime(6, start_time);
            statement.setTime(7, end_time);
            statement.setFloat(8,cost);
            statement.setInt(9, difficulty);
            statement.executeUpdate();
            statement.close();
            connection.close();
        }
        catch(Exception e){
            System.out.println(e);
            return false;
        }
        return true;
    }

    public static boolean enrollInClass(int class_id, int member_id){
        try{
            //            Establish connection
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            //The SQL query that will be run
            String addClass = "INSERT INTO ClassAttendance(class_id, member_id) VALUES (?,?);" ;
            //Executing query, collecting results and returning the relevant id
            PreparedStatement statement = connection.prepareStatement(addClass);
            statement.setInt(1, class_id);
            statement.setInt(2, member_id);

            statement.executeUpdate();
            statement.close();
            connection.close();
        }
        catch(Exception e){
            System.out.println(e);
            return false;
        }
        return true;
    }

    //Drop out removes the member from attending all future instances of the classes
    public static boolean dropOut(int class_id, int member_id) {
        try {
            // Establish connection
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            String dropOut = "DELETE FROM ClassAttendance WHERE class_id = ? AND member_id = ? AND class_id IN (SELECT class_id FROM Classes WHERE class_date > CURRENT_DATE)";

            // Executing query
            PreparedStatement statement = connection.prepareStatement(dropOut);
            statement.setInt(1, class_id);
            statement.setInt(2, member_id);

            statement.executeUpdate();
            statement.close();
            connection.close();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }


    public static boolean cancelOneClass(int attendance_id, int member_id){
        try{
            //            Establish connection
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            String dropOut = "DELETE FROM ClassAttendance WHERE attendance_id = ? AND member_id = ?";            //Executing query, collecting results and returning the relevant id

            PreparedStatement statement = connection.prepareStatement(dropOut);
            statement.setInt(1, attendance_id);
            statement.setInt(2, member_id);

            statement.executeUpdate();
            statement.close();
            connection.close();
        }
        catch(Exception e){
            System.out.println(e);
            return false;
        }
        return true;
    }

    public static int generateBill(int member_id, float bill_amount, String bill_description){
        try{
            //            Establish connection
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            //The SQL query that will be run
            String addClass = "INSERT INTO Bills (member_id, bill_amount, bill_description) VALUES (?,?,?);" ;
            //Executing query, collecting results and returning the relevant id
            PreparedStatement statement = connection.prepareStatement(addClass);
            statement.setInt(1, member_id);
            statement.setFloat(2, bill_amount);
            statement.setString(3, bill_description);

            statement.executeUpdate();

            int changedRows = statement.executeUpdate();
            if( changedRows > 0){
                ResultSet rs = statement.getGeneratedKeys();
                if(rs.next()){
                    int bill_id = rs.getInt(1);
                    statement.close();
                    connection.close();
                    return bill_id;
                }
            }

        }
        catch(Exception e){
            System.out.println(e);
            return -1;
        }
        return -1;
    }

    public static boolean payBill(int bill_id, float amount, String payment_method){
        try {
            // Establish connection
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            // The SQL query to update the bill payment
            String payBill = "UPDATE Bills SET amount_paid = (amount_paid + ?), payment_method = ?, date_paid = CURRENT_DATE WHERE bill_id = ?";

            // Create PreparedStatement
            PreparedStatement statement = connection.prepareStatement(payBill);
            statement.setFloat(1, amount);
            statement.setString(2, payment_method);
            statement.setInt(3, bill_id);

            // Execute the update
            statement.executeUpdate();

            // Close the statement and connection
            statement.close();
            connection.close();

            return true;
        } catch(Exception e) {
            // Handle any exceptions
            e.printStackTrace();
            return false;
        }
    }


    public static ResultSet getMemberSessions(int user_id){
        try{
            //            Establish connection
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            //The SQL query that will be run
            String getMemberClasses = "SELECT * FROM TrainingSessions WHERE member_id = ? AND session_date >= CURRENT_DATE;" ;
            PreparedStatement statement = connection.prepareStatement(getMemberClasses);
            statement.setInt(1, user_id);
            ResultSet rs = statement.executeQuery();



            return rs;
        }
        catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static ResultSet getMemberClasses(int user_id){
        try{
            //            Establish connection
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            //The SQL query that will be run
            String getMemberClasses = "SELECT * FROM ClassAttendance INNER JOIN Classes ON Classes.class_id = ClassAttendance.class_id WHERE ClassAttendance.member_id = ? AND Classes.class_date >= CURRENT_DATE;" ;
            PreparedStatement statement = connection.prepareStatement(getMemberClasses);
            statement.setInt(1, user_id);
            ResultSet rs = statement.executeQuery();

            return rs;
        }
        catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static String getUserName(int user_id){
        try{
            //            Establish connection
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            //The SQL query that will be run
            String getUserName = "SELECT first_name, last_name FROM Users WHERE user_id = ?;" ;
            PreparedStatement statement = connection.prepareStatement(getUserName);
            statement.setInt(1, user_id);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
               String name = rs.getString("first_name") + rs.getString("last_name");

               return name;
            }

        }
        catch(Exception e) {
            System.out.println(e);
            return null;
        }
        return null;
    }

    public static ResultSet viewUpcomingClasses(){
        try{
            //            Establish connection
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            //The SQL query that will be run
            String getMemberClasses = "SELECT * FROM Classes WHERE class_date >= CURRENT_DATE;" ;
            PreparedStatement statement = connection.prepareStatement(getMemberClasses);

            ResultSet rs = statement.executeQuery();

            return rs;
        }
        catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static float getTrainerHourly(int trainer_id) {
        float hourlyRate = 0.0f;
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = connection.prepareStatement("SELECT hourly_rate FROM Trainers WHERE user_id = ?");
            statement.setInt(1, trainer_id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                hourlyRate = resultSet.getFloat("hourly_rate");
            }

        } catch (Exception e) {
            System.out.println("Error: "+ e);
        }
        return hourlyRate;
    }


    public static float getClassCost(int class_id) {
        float classCost = 0.0f;
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = connection.prepareStatement("SELECT cost FROM Classes WHERE class_id = ?");
            statement.setInt(1, class_id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                classCost = resultSet.getFloat("cost");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return classCost;
    }

    public static ResultSet getMemberBills(int member_id){
        try {
            // Establish connection
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            // The SQL query to retrieve bills for a specific member
            String getMemberBillsQuery = "SELECT * FROM bills WHERE member_id = ?";
            PreparedStatement statement = connection.prepareStatement(getMemberBillsQuery);

            // Set the member id parameter in the query
            statement.setInt(1, member_id);

            // Execute the query
            ResultSet rs = statement.executeQuery();

            return rs;
        } catch (Exception e) {
            // Handle any exceptions
            e.printStackTrace();
            return null;
        }
    }





}
