
USE `mydb`;


SET REFERENTIAL_INTEGRITY FALSE;


INSERT INTO `electrical_plant` VALUES (1,'Υποσταθμός Δομοκού');




INSERT INTO `transformer` VALUES (1,' ΜΣ1',1),(2,' ΜΣ2',1);


INSERT INTO `breaker` VALUES (1,'P-210',1),(2,'P-220',1),(3,'P-230',1),(4,'P-390',2),(5,'P-380',2),(6,'P-370',2);


SET REFERENTIAL_INTEGRITY TRUE;

