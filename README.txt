README:

queries.java - 
	Currently set to have default url, user and password to connect to the database- these values need to be changed if you are not using default values- can be done by just setting static variables at the top of the class

	(There is a change of way of how I create the queries half way through because I found an easier way to build the queries and did not want to redo the work I had already completed- https://www.geeksforgeeks.org/how-to-use-preparedstatement-in-java/)

	Includes all the queries that can be run in the application:

	1. registerNewUser: Inserts a new user into the Users table.
	2. getUserId: Retrieves the user ID based on the email from the Users table.
	3. registerMember: Inserts a new member into the Members table.
	4. registerTrainer: Inserts a new trainer into the Trainers table.
	5. registerAdmin: Inserts a new admin into the Admin table.
	6. updateMetrics: Inserts or updates metrics for a member in the MemberMetrics table.
	7. addExcersizeRoutine: Adds an exercise routine for a member in the ExcersizeRoutines table.
	8. addTrainerAvailability: Adds availability for a trainer in the TrainerAvailability table.
	9. getAllTrainersAvailability: Retrieves all trainers' availability within a specified time range from the TrainerAvailability table.
	10. getTrainerAvailability: Retrieves a specific trainer's availability within a specified time range from the TrainerAvailability table.
	11. removeAvailability: Removes a trainer's availability for a specific date and time range from the TrainerAvailability table.
	12. addTrainingSession: Adds a training session for a member with a specific trainer in the TrainingSessions table.
	13. removeTrainingSession: Removes a training session from the TrainingSessions table based on the session ID.
	14. getGoalsAndMetrics: Retrieves goals and metrics for a member from the MemberMetrics table.
	15. getExcersizeRoutines: Retrieves exercise routines for a member from the ExcersizeRoutines table.
	16. addAchievement: Adds an achievement for a member in the FitnessAchievement table.
	17. getAchievements: Retrieves achievements for a member from the FitnessAchievement table.
	18. addTrainerReview: Updates a trainer's rating and number of reviews in the Trainers table based on the trainer ID.
	19. updateUserInformation: Updates the User's personal information
	20. updatePassword: User can update their password
	21. viewMemberProfile: View the member's profile based on first and last name
	22. addRoomBooking: Adds a room booking and returns the booking_id
	23. changeMaintenanceStatus: to update the maintenance status if a machine is repaired/maintained or if a machine breaks down and  needs maintenance
	24. getNeedMaintenanceEquipment: Gets all the equipment that needs maintenance 
	25. addClass: Adds a class to the Classes table
	26. enrollInClass: signs a user up for a class
	27. dropOut: Allows users to drop out from a class
	28. generateBill: generates a bill for a member
	29. payBill: updates the relative bill to reflect amount paid
	30. getPassword: gets the relevant user's password
	

	
	