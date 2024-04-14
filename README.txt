README:

UNFORTUNATELY RAN OUT OF TIME TO COMPLETE_ SO ONLY MEMBER UI IMPLEMENTATION IS DONE.
QUERIES IS COMPLETE AND HAS ALL THE QUERIES THAT WOULD BE NEEDED. 

queries.java - 
	Currently set to have default url, user and password to connect to the database- these values need to be changed if you are not using default values- can be done by just setting static variables at the top of the class

	(There is a change of way of how I create the queries half way through because I found an easier way to build the queries and did not want to redo the work I had already completed- https://www.geeksforgeeks.org/how-to-use-preparedstatement-in-java/)

	Includes all the queries that can be run in the application:

	registerNewUser: Inserts a new user into the Users table.
		1. getUserId: Retrieves the user ID based on the email from the Users table.
		2. getPassword: Retrieves the password based on the user ID from the Users table.
		3. verifyMember: Checks if a user is a member by counting records in the Members table.
		4. verifyAdmin: Checks if a user is an admin by counting records in the Admin table.
		5. verifyTrainer: Checks if a user is a trainer by counting records in the Trainers table.
		6. registerMember: Inserts a new member into the Members table.
		7. registerTrainer: Inserts a new trainer into the Trainers table.
		8. registerAdmin: Inserts a new admin into the Admin table.
		9. updateMetrics: Inserts member metrics into the MemberMetrics table.
		10. addExcersizeRoutine: Inserts an exercise routine into the ExcersizeRoutines table.
		11. addTrainerAvailability: Inserts trainer availability into the TrainerAvailability table.
		12. getAllTrainersAvailability: Retrieves all trainers' availability based on date and time from the TrainerAvailability table.
		13. getTrainerAvailability: Retrieves a specific trainer's availability based on date and time from the TrainerAvailability table.
		14. removeAvailability: Removes a trainer's availability from the TrainerAvailability table.
		15. addTrainingSession: Inserts a training session into the TrainingSessions table.
		16. removeTrainingSession (two versions): Removes a training session from the TrainingSessions table, either by session ID or trainer ID and member ID.
		17. getGoalsAndMetrics: Retrieves member goals and metrics from the MemberMetrics table.
		18. getExcersizeRoutines: Retrieves exercise routines for a member from the ExcersizeRoutines table.
		19. addAchievement: Inserts a fitness achievement for a member into the FitnessAchievement table.
		20. getAchievements: Retrieves member achievements from the FitnessAchievement table.
		21. addTrainerReview: Updates a trainer's rating and number of reviews in the Trainers table.
		22. updateUserInformation: Updates user information in the Users table.
		23. updatePassword: Updates a user's password in the Users table using the user's email
		24. updatePassword: Updates a user's password in the Users table based on user ID.
		25. viewMemberProfile (two versions):
		26. Retrieves member profile information based on first name and last name from the Members and Users tables.
		27. Retrieves member profile information based on user ID from the Members and Users tables.
		28. addRoomBooking: Inserts a new room booking into the RoomBookings table.
		29. changeMaintenanceStatus: Updates equipment maintenance status in the Equipment table.
		30. getNeedMaintenanceEquipment: Retrieves equipment needing maintenance from the Equipment table.
		31. addClass: Inserts a new class into the Classes table.
		32. enrollInClass: Inserts a member into a class in the ClassAttendance table.
		33. dropOut: Removes a member from attending future instances of a class in the ClassAttendance table.
		34. cancelOneClass: Removes a member from a specific class instance in the ClassAttendance table.
		35. generateBill: Inserts a new bill into the Bills table.
		36. payBill: Updates bill payment information in the Bills table.
		37. getMemberSessions: Retrieves training sessions for a member from the TrainingSessions table.
		38. getMemberClasses: Retrieves classes enrolled by a member from the ClassAttendance and Classes tables.
		39. getUserName: Retrieves the first name and last name of a user based on user ID from the Users table.
		40. viewUpcomingClasses: Views all upcoming classes (classes after current date) from Classes table.
		
	

	
	
