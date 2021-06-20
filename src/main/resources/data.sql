-- user=sensor passwort=sensor
insert into users(username,password,enabled)
	values('sensor','$2a$10$CJ/mObOBP9k7jJzxHsifs.hH.cUD.zuof7al4DJak4LiFrvOguWjq',true);
insert into authorities(username,authority) 
	values('sensor','ROLE_SENSOR');