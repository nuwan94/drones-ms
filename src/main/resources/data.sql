---------------------- Drone State [Meta Data] ----------------------
insert into drone_state (name) values ('IDLE');
insert into drone_state (name) values ('LOADING');
insert into drone_state (name) values ('LOADED');
insert into drone_state (name) values ('DELIVERING');
insert into drone_state (name) values ('DELIVERED');
insert into drone_state (name) values ('RETURNING');

---------------------- Drone Models [Meta Data] ----------------------
insert into drone_model (name) values ('Lightweight');
insert into drone_model (name) values ('Middleweight');
insert into drone_model (name) values ('Cruiserweight');
insert into drone_model (name) values ('Heavyweight');

---------------------- Drone ----------------------
insert into drone (battery, serial_number, weight, model_id, state_id) values (100.0, 'D101010', 0.0, 1, 1);
insert into drone (battery, serial_number, weight, model_id, state_id) values (100.0, 'D101011', 0.0, 2, 2);
insert into drone (battery, serial_number, weight, model_id, state_id) values (100.0, 'D101012', 0.0, 3, 3);
insert into drone (battery, serial_number, weight, model_id, state_id) values (100.0, 'D101013', 0.0, 4, 4);
insert into drone (battery, serial_number, weight, model_id, state_id) values (100.0, 'D101014', 0.0, 1, 5);
insert into drone (battery, serial_number, weight, model_id, state_id) values (100.0, 'D101015', 0.0, 2, 6);
insert into drone (battery, serial_number, weight, model_id, state_id) values (100.0, 'D101016', 0.0, 3, 1);
insert into drone (battery, serial_number, weight, model_id, state_id) values (100.0, 'D101017', 0.0, 4, 2);
insert into drone (battery, serial_number, weight, model_id, state_id) values (100.0, 'D101018', 0.0, 1, 3);
insert into drone (battery, serial_number, weight, model_id, state_id) values (100.0, 'D101019', 0.0, 2, 4);

---------------------- Medication ----------------------
insert into medication (name, weight, code, image) values ('Atorvastatin', 100.0, 'ZYOOZK', 'atorvastatin.jpeg');
insert into medication (name, weight, code, image) values ('Levothyroxine', 75.0, 'CHMAYO', 'levothyroxine.jpeg');
insert into medication (name, weight, code, image) values ('Metformin', 25.0, 'ZHVJKU', 'metformin.jpeg');
insert into medication (name, weight, code, image) values ('Lisinopril', 200.0, 'LCFUYH', 'lisinopril.jpeg');
insert into medication (name, weight, code, image) values ('Amlodipine', 110.0, 'GTKVLP', 'amlodipine.jpeg');
insert into medication (name, weight, code, image) values ('Metoprolol', 85.0, 'DWCLCO', 'metoprolol.jpeg');
insert into medication (name, weight, code, image) values ('Albuterol', 90.0, 'RFBTNI', 'albuterol.jpeg');
insert into medication (name, weight, code, image) values ('Omeprazole', 80.0, 'LVVTQW', 'omeprazole.jpeg');
insert into medication (name, weight, code, image) values ('Losartan', 50.0, 'HFMSYI', 'losartan.jpeg');
insert into medication (name, weight, code, image) values ('Gabapentin', 100.0, 'KKEAUX', 'gabapentin.jpeg');