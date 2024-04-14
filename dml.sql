--Dummy values- not integral to usage, but for basic testing
INSERT INTO Users (user_id, first_name, last_name, email, phone, pword) VALUES
(1,'Steven', 'Strange', 'stevenstrange@abc.com', '1231231234', 'doctorstrange'),
(2,'Clint', 'Barton', 'clintbarton@abc.com', '1233214321', 'hawkeye'),
(3,'Thor', 'Odinson', 'thorodinson@abc.com', '1231234321', 'jane'),
(4,'Tony', 'Stark', 'tonystark@abc.com', '1232312341', 'ironman'),
(5,'Steve', 'Rogers', 'steverogers@abc.com', '1232223344', 'captainamerica'),
(6,'Bucky', 'Barnes', 'buckybarnes@abc.com', '1234432211', 'wintersoldier'),
(7,'Nick', 'Fury', 'nickfury@abc.com', '1234321234', 'shield'),
(8,'Peter', 'Parker', 'peterparker@abc.com', '12333224444', 'spiderman'),
(9,'Peter', 'Quill', 'peterquill@abc.com', '12333442211', 'starlord'),
(10,'Rocket', 'Raccoon', 'rocketraccoon@abc.com', '1231112222', 'groot');

--Dummy values- not integral to usage, but for basic testing
INSERT INTO Members (user_id, join_date) VALUES
(1, '2023-01-01'),
(2, '2023-02-01'),
(3, '2024-01-01'),
(4, '2023-06-25'),
(5, '2023-02-28');

--Dummy values- not integral to usage, but for basic testing
INSERT INTO MemberMetrics (user_id, member_height, member_weight, goal_description, goal_weight, goal_checkin_date, last_update) VALUES
(1, 1.89, 220, 'LOSE WEIGHT', 190, '2024-05-01','2024-01-01'),
(2, 1.91, 180, 'GAIN WEIGHT', 200, '2024-12-01', '2024-01-01'),
(3, 1.98, 220, 'MAINTAIN WEIGHT', 220, '2024-12-02', '2023-12-02'),
(4, 1.85, 175, 'INCREASE STRENGTH', 180, '2024-02-01', '2024-01-01'),
(5, 1.62, 95, 'INCREASE MUSCLE MASS', 220, '2024-04-10', '2024-04-11');

--Dummy values- not integral to usage, but for basic testing, but need at least one trainer
INSERT INTO Trainers (user_id, hourly_rate) VALUES
(6, 50.00),
(8, 42.00),
(9, 30.00);

-- Need to have at least one admin for functionality of gym
INSERT INTO Admin (user_id) VALUES
(7), (10);

--Integral to functionality
INSERT INTO Rooms (room_description) VALUES
('MAIN GYM'),
('COMBATIVES ROOM'),
('YOGA ROOM'),
('STUDIO'),
('STUDIO');

INSERT INTO Equipment (machine_type) VALUES
('TREADMILL'),
('TREADMILL'),
('TREADMILL'),
('STATIONARY BIKE'),
('SQUAT RACK'),
('ROWING MACHINE'),
('LEG PRESS');

-- Dummy data for TrainingSessions with random dates within a range of one month
INSERT INTO TrainingSessions (member_id, trainer_id, session_date, session_start, session_end) VALUES
(1, 6, CURRENT_DATE - INTERVAL '5 days', '10:00:00', '11:00:00'),
(2, 6, CURRENT_DATE - INTERVAL '10 days', '11:00:00', '12:00:00'),
(3, 6, CURRENT_DATE - INTERVAL '3 days', '14:00:00', '15:00:00'),
(4, 6, CURRENT_DATE - INTERVAL '8 days', '15:00:00', '16:00:00'),
(5, 6, CURRENT_DATE - INTERVAL '2 days', '09:00:00', '10:00:00');

-- Dummy data for WorkoutTypes
INSERT INTO WorkoutTypes (workout_description) VALUES
('Cardio'),
('Strength Training'),
('Yoga'),
('Pilates');

-- Dummy data for ExcersizeRoutines
INSERT INTO ExcersizeRoutines (member_id, workout_id) VALUES
(1, 2),
(2, 3),
(3, 1),
(4, 2),
(5, 4);

-- Dummy data for TrainerAvailability with random dates within a range of one month
INSERT INTO TrainerAvailability (trainer_id, available_date, startTime, endTime) VALUES
(6, CURRENT_DATE + INTERVAL '3 days', '09:00:00', '17:00:00'),
(8, CURRENT_DATE + INTERVAL '7 days', '08:00:00', '16:00:00'),
(9, CURRENT_DATE + INTERVAL '2 days', '10:00:00', '18:00:00'),
(6, CURRENT_DATE - INTERVAL '3 days', '09:00:00', '17:00:00'),
(8, CURRENT_DATE - INTERVAL '7 days', '08:00:00', '16:00:00'),
(9, CURRENT_DATE - INTERVAL '2 days', '10:00:00', '18:00:00');

-- Dummy data for Classes with random dates within a range of one month
INSERT INTO Classes (trainer_id, class_description, category, room_id, class_date, start_time, end_time, cost, difficulty) VALUES
(6, 'Morning Cardio Blast', 1, 1, CURRENT_DATE - INTERVAL '5 days', '07:00:00', '08:00:00', 15.00, 3),
(6, 'Strength and Conditioning', 2, 2, CURRENT_DATE - INTERVAL '10 days', '12:00:00', '13:00:00', 20.00, 4),
(6, 'Power Yoga', 3, 3, CURRENT_DATE - INTERVAL '3 days', '16:00:00', '17:00:00', 18.00, 2),
(8, 'Beginner Pilates', 4, 4, CURRENT_DATE - INTERVAL '8 days', '09:00:00', '10:00:00', 15.00, 1),
(9, 'HIIT Training', 2, 1, CURRENT_DATE + INTERVAL '2 days', '18:00:00', '19:00:00', 25.00, 5),
(6, 'Morning Cardio Blast', 1, 1, CURRENT_DATE + INTERVAL '5 days', '07:00:00', '08:00:00', 15.00, 3),
(6, 'Strength and Conditioning', 2, 2, CURRENT_DATE + INTERVAL '10 days', '12:00:00', '13:00:00', 20.00, 4),
(6, 'Power Yoga', 3, 3, CURRENT_DATE + INTERVAL '3 days', '16:00:00', '17:00:00', 18.00, 2),
(8, 'Beginner Pilates', 4, 4, CURRENT_DATE + INTERVAL '8 days', '09:00:00', '10:00:00', 15.00, 1),
(9, 'HIIT Training', 2, 1, CURRENT_DATE + INTERVAL '2 days', '18:00:00', '19:00:00', 25.00, 5);

-- Dummy data for RoomBookings with random dates within a range of one month
INSERT INTO RoomBookings (room_id, booking_date, booking_time_start, booking_end_time) VALUES
(1, CURRENT_DATE - INTERVAL '5 days', '07:00:00', '08:00:00'),
(2, CURRENT_DATE - INTERVAL '10 days', '12:00:00', '13:00:00'),
(3, CURRENT_DATE - INTERVAL '3 days', '16:00:00', '17:00:00'),
(4, CURRENT_DATE - INTERVAL '8 days', '09:00:00', '10:00:00'),
(1, CURRENT_DATE - INTERVAL '2 days', '18:00:00', '19:00:00');

-- Dummy data for Bills with random dates within a range of one month
INSERT INTO Bills (member_id, bill_amount, amount_paid, payment_method, bill_description, bill_date, date_paid) VALUES
(1, 50.00, 50.00, 'Credit', 'Monthly Membership Fee', CURRENT_DATE - INTERVAL '5 days', CURRENT_DATE - INTERVAL '3 days'),
(2, 60.00, 60.00, 'Credit', 'Monthly Membership Fee', CURRENT_DATE - INTERVAL '10 days', CURRENT_DATE - INTERVAL '5 days'),
(3, 70.00, 70.00, 'Credit', 'Monthly Membership Fee', CURRENT_DATE + INTERVAL '3 days', CURRENT_DATE - INTERVAL '2 days'),
(4, 80.00, 80.00, 'Credit', 'Monthly Membership Fee', CURRENT_DATE + INTERVAL '8 days', CURRENT_DATE - INTERVAL '4 days'),
(5, 90.00, 90.00, 'Credit', 'Monthly Membership Fee', CURRENT_DATE + INTERVAL '2 days', CURRENT_DATE - INTERVAL '1 day');

-- Dummy data for FitnessAchievement with random dates within a range of one month
INSERT INTO FitnessAchievement (member_id, achievement_type, achievement_description, date_achieved) VALUES
(1, 'Weight Loss', 'Lost 10 lbs in a month!', CURRENT_DATE - INTERVAL '10 days'),
(2, 'Muscle Gain', 'Gained 5 lbs of muscle mass!', CURRENT_DATE - INTERVAL '5 days'),
(3, 'Fitness Milestone', 'Achieved personal best in deadlift!', CURRENT_DATE - INTERVAL '8 days'),
(4, 'Endurance Improvement', 'Ran 10k without stopping!', CURRENT_DATE - INTERVAL '3 days'),
(5, 'Flexibility Improvement', 'Mastered the splits!', CURRENT_DATE - INTERVAL '7 days');


-- Dummy data for ClassAttendance
INSERT INTO ClassAttendance (class_id, member_id) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 1),
(7, 2),
(8, 3),
(9, 4),
(10, 5);