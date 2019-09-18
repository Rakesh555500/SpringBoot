insert into USER_INFO (ID, PASSWORD, USERNAME) values(1,'$2a$10$lS3EJGg2htbeVH8a1aBgXupykXYsJD3Lk3qzfcs8rVW6FbZICh99e','rakesh.gowda@gmail.com')

insert into CATEGORY (ID, AVAILABLE_CONTENT, NAME, PRICE) values(1,10,'Dutch Films',4.0)
insert into CATEGORY (ID, AVAILABLE_CONTENT, NAME, PRICE) values(2,20,'Dutch Series',6.0)
insert into CATEGORY (ID, AVAILABLE_CONTENT, NAME, PRICE) values(3,30,'International Films',8.0)

insert into SUBSCRIBE_CATEGORY (ID, REMAINING_CONTENT, START_DATE,CATEGORY_ID,USER_ID,SHARED_WITH) values(1000, 6, TO_DATE('17/05/2019', 'DD/MM/YYYY'), 3, 1, 0)