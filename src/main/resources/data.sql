
insert into theatre (theatre_id, theatre_name, city_id, movie_id) values
(1, 'Elante', 1,1000),
(2, 'Centra' , 1,1001);

insert into showdtl (theatre_id, show_date_time, total_seats, available_seats) values
(1, '2018-12-26 11:30:00.0000',  10, 10 ),
(1, '2018-12-26 16:30:00.0000',  10, 10 ),
(2, '2018-12-26 11:30:00.0000',  10, 10 ),
(2, '2018-12-26 16:30:00.0000', 10, 10 );

insert into seat (show_id, seat_name, status,price) values 
(1, 'a1',0,100.00),
(1, 'a2',0,100.00),
(1, 'a3',0,100.00),
(1, 'a4',0,100.00),
(1, 'a5',0,100.00),
(1, 'a6',0,100.00),
(1, 'a7',0,100.00),
(1, 'a8',0,100.00),
(1, 'a9',0,100.00),
(1, 'a10',0,100.00),
(2, 'a1',0,100.00),
(2, 'a2',0,100.00),
(2, 'a3',0,100.00),
(2, 'a4',0,100.00),
(2, 'a5',0,100.00),
(2, 'a6',0,100.00),
(2, 'a7',0,100.00),
(2, 'a8',0,100.00),
(2, 'a9',0,100.00),
(2, 'a10',0,100.00),
(3, 'b1',0,100.00),
(3, 'b2',0,100.00),
(3, 'b3',0,100.00),
(3, 'b4',0,100.00),
(3, 'b5',0,100.00),
(3, 'b6',0,100.00),
(3, 'b7',0,100.00),
(3, 'b8',0,100.00),
(3, 'b9',0,100.00),
(3, 'b10',0,100.00),
(4, 'b1',0,100.00),
(4, 'b2',0,100.00),
(4, 'b3',0,100.00),
(4, 'b4',0,100.00),
(4, 'b5',0,100.00),
(4, 'b6',0,100.00),
(4, 'b7',0,100.00),
(4, 'b8',0,100.00),
(4, 'b9',0,100.00),
(4, 'b10',0,100.00);