CREATE TABLE Users(
    user_id     SERIAL          PRIMARY KEY,
    first_name  VARCHAR(255)	NOT NULL,
	last_name	VARCHAR(255)	NOT NULL,
	email		VARCHAR(255)	UNIQUE NOT NULL,
    phone		VARCHAR(15),
    pword       VARCHAR(50)     NOT NULL
);

CREATE TABLE Members (
	user_id     int     PRIMARY KEY     REFERENCES Users(user_id),
	join_date	DATE	DEFAULT CURRENT_DATE	
);

CREATE TABLE Trainers (
    user_id     int     PRIMARY KEY     REFERENCES Users(user_id),
    hourly_rate FLOAT   DEFAULT 17.50,
    rating          int     DEFAULT 0,
    num_reviews     int     DEFAULT 0
);

CREATE TABLE Admin (
    user_id     int     PRIMARY KEY     REFERENCES Users(user_id)
);

CREATE TABLE MemberMetrics (
    metric_id           SERIAL  PRIMARY KEY,     
    user_id             int     REFERENCES Members(user_id),
    member_height       FLOAT,
    member_weight       FLOAT,
    goal_description    TEXT,
    goal_weight         FLOAT,
    goal_checkin_date   DATE    DEFAULT CURRENT_DATE + INTERVAL '1 month',
    last_update         DATE    DEFAULT CURRENT_DATE
);

create TABLE TrainingSessions (
    session_id      SERIAL  PRIMARY KEY,
    member_id       int     REFERENCES Members(user_id),
    trainer_id      int     REFERENCES Trainers(user_id),
    session_date    DATE    DEFAULT CURRENT_DATE + INTERVAL '1 day',
    session_start   TIME,
    session_end     TIME
);

CREATE TABLE WorkoutTypes (
    workout_id          SERIAL  PRIMARY KEY,
    workout_description VARCHAR(50)
);

CREATE TABLE ExcersizeRoutines (
    routine_id      SERIAL  PRIMARY KEY,
    member_id       int     REFERENCES Members(user_id),
    workout_id      int     REFERENCES WorkoutTypes(workout_id),
    date_completed  DATE    DEFAULT CURRENT_DATE
);

CREATE TABLE TrainerAvailability (
    availability_id SERIAL  PRIMARY KEY,
    trainer_id      int     REFERENCES Trainers(user_id),
    available_date  DATE,
    startTime       TIME,
    endTime         TIME
);

CREATE TABLE Rooms (
    room_id             SERIAL  PRIMARY KEY,
    room_description    TEXT,
    cost                FLOAT   DEFAULT 100.0
);

CREATE TABLE Classes (
    class_id            SERIAL  PRIMARY KEY,
    trainer_id          int     REFERENCES Trainers(user_id),
    class_description   TEXT,
    category            int     REFERENCES WorkoutTypes(workout_id),
    room_id             int     REFERENCES Rooms(room_id),
    class_date          DATE, 
    start_time          TIME,
    end_time            TIME,
    cost                FLOAT   DEFAULT 0,
    difficulty          int, -- 0 easiest 5 hardest
    rating              int     DEFAULT 0,
    num_reviews         int     DEFAULT 0

);


CREATE TABLE RoomBookings (
    booking_id      SERIAL  PRIMARY KEY,
    room_id         int     REFERENCES Rooms(room_id),
    booking_date    DATE,
    booking_time_start   TIME,
    booking_end_time    TIME
);

CREATE TABLE ClassAttendance (
    attendance_id       SERIAL  PRIMARY KEY,
    class_id            int     REFERENCES Classes(class_id),
    member_id           int     REFERENCES Members(user_id)
);

CREATE TABLE Bills (
    bill_id             SERIAL  PRIMARY KEY,
    member_id           int     REFERENCES Members(user_id),
    bill_amount         FLOAT   DEFAULT 0,
    amount_paid         FLOAT   DEFAULT 0,
    payment_method      VARCHAR(10),
    bill_description    TEXT,
    bill_date           DATE    DEFAULT CURRENT_DATE,
    date_paid           DATE
);

CREATE TABLE Equipment (
    equipment_id            SERIAL      PRIMARY KEY,
    needs_maintenance       BOOLEAN     DEFAULT false,
    last_maintenance_date   DATE        DEFAULT CURRENT_DATE, 
    next_maintenance_date   DATE        DEFAULT CURRENT_DATE + INTERVAL '6 months',
    machine_type            VARCHAR(256)
);

CREATE TABLE FitnessAchievement (
    acievement_id           SERIAL PRIMARY KEY,
    member_id               int REFERENCES Members(user_id),
    achievement_type        VARCHAR(50),
    achievement_description TEXT,
    date_achieved           DATE    DEFAULT CURRENT_DATE
);

