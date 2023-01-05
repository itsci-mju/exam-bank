-- CREATE SCHEMA `itsci_exam_db` DEFAULT CHARACTER SET utf8mb4 ;
use itsci_exam_db;

INSERT INTO `itsci_exam_db`.`authorities` (`id`, `authority`, `description`) VALUES ('1', 'ROLE_ADMIN', 'ผู้ดูแลระบบ');
INSERT INTO `itsci_exam_db`.`authorities` (`id`, `authority`, `description`) VALUES ('2', 'ROLE_MEMBER', 'สมาชิก');
INSERT INTO `itsci_exam_db`.`authorities` (`id`, `authority`, `description`) VALUES ('3', 'ROLE_TEACHER', 'อาจารย์');


-- คำสั่งสำหรับแทรกข้อมูล login โดยมีรหัสผ่านเป็น '1234'
INSERT INTO `itsci_exam_db`.`logins` (`id`, `enabled`, `password`, `username`) VALUES ('1', '1', '{bcrypt}$2a$10$/GUlfBF1jG6Z7h2IiF6UGOCniw.HQeza8pWpW/x2eGWm6LL/rAlLO', 'infoitsci@mju.ac.th');

-- คำสั่งสำหรับแทรกข้อมูลผู้ใช้
INSERT INTO `itsci_exam_db`.`users` (`DTYPE`, `id`, `firstName`, `lastName`, `expiredDate`, `validFrom`, `login_id`) VALUES ('Member', '1', 'itsci', 'itsci', NOW(), DATE_ADD(NOW(), INTERVAL 1 YEAR), '1');

-- คำสั่งสำหรับแทรกข้อมูล user_authority
INSERT INTO `itsci_exam_db`.`user_authority` (`user_id`, `authority_id`) VALUES ('1', '1');
INSERT INTO `itsci_exam_db`.`user_authority` (`user_id`, `authority_id`) VALUES ('1', '2');
