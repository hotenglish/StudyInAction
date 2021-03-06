--CREATE TABLE s (
--    s#      CHAR(4),
--    sname   CHAR(8),
--    age     SMALLINT,
--    sex     CHAR(1),
--    PRIMARY KEY (s#)
--);

--CREATE TABLE c (
--    c#        CHAR(4),
--    cname     CHAR(10) NOT NULL,
--    teacher   CHAR(8),
--    PRIMARY KEY (c#)
--);
--
--CREATE TABLE sc (
--    s#      CHAR(4),
--    c#      CHAR(4),
--    grade   SMALLINT,
--    PRIMARY KEY (s#,c#),
--    FOREIGN KEY (s#) REFERENCES S ( s# ),
--    FOREIGN KEY (c#) REFERENCES C ( c# ),
--    CHECK ( ( grade IS NULL )
--            OR ( grade BETWEEN 0 AND 100 ) )
--);

--TABLE S DATA
--INSERT INTO S(S#,SNAME,AGE,SEX) VALUES('S1','WANG',20,'M');
--INSERT INTO S(S#,SNAME,AGE,SEX) VALUES('S4','WU',19,'M');
--INSERT INTO S(S#,SNAME,AGE,SEX) VALUES('S2','LIU',21,'F');
--INSERT INTO S(S#,SNAME,AGE,SEX) VALUES('S3','CHEN',22,'M');
--INSERT INTO S(S#,SNAME,AGE,SEX) VALUES('S8','DONG',18,'F');

--TABLE C DATA
--INSERT INTO C(C#,CNAME,TEACHER) VALUES('C2','MATHS','MA');
--INSERT INTO C(C#,CNAME,TEACHER) VALUES('C4','PHYSICS','SHI');
--INSERT INTO C(C#,CNAME,TEACHER) VALUES('C3','CHEMISTRY','ZHOU');
--INSERT INTO C(C#,CNAME,TEACHER) VALUES('C1','DATABASE','LI');

--TABLE SC DATA
--INSERT INTO SC(S#,C#,GRADE) VALUES('S1','C1', 80);
--INSERT INTO SC(S#,C#,GRADE) VALUES('S3','C1', 90);
--INSERT INTO SC(S#,C#,GRADE) VALUES('S1','C2', 70);
--INSERT INTO SC(S#,C#,GRADE) VALUES('S3','C2', 85);
--INSERT INTO SC(S#,C#,GRADE) VALUES('S3','C3', 95);
--INSERT INTO SC(S#,C#,GRADE) VALUES('S4','C4', 70);
--INSERT INTO SC(S#,C#,GRADE) VALUES('S8','C3', 90);

--COMMIT;

--ALTER TABLE S ADD ADDRESS VARCHAR2(30);

--ALTER TABLE S DROP COLUMN ADDRESS;

--3.3.1
SELECT  COUNT(S.S#),AVG(AGE) FROM S WHERE sex='M';
SELECT  COUNT(DISTINCT S#) FROM SC;

--3.3.2
SELECT AGE,COUNT(DISTINCT S.S#) FROM S,SC WHERE SC.S#=S.S# GROUP BY AGE;
SELECT AGE,COUNT(S#) FROM S WHERE S.SEX='M' GROUP BY AGE HAVING COUNT(*)>1 ORDER BY 2,AGE DESC;

--3.3.3
SELECT DISTINCT C# FROM S,SC WHERE S.S#=SC.S# AND S.SEX='M';
SELECT S#,SNAME,1999-AGE FROM S;
SELECT SNAME FROM S WHERE AGE>=18 AND AGE<=20;
SELECT SNAME FROM S WHERE AGE BETWEEN 18 AND 20;
SELECT SNAME AS STUDENT_NAME,1999-AGE AS BIRTH_YEAR FROM S;
SELECT SNAME FROM S WHERE SNAME LIKE 'D%';
SELECT SNAME FROM S WHERE AGE IS NULL;

SELECT DISTINCT X.S# FROM SC X WHERE ('C2','C4') NOT IN (SELECT C# FROM SC Y WHERE Y.S#=X.S#);
SELECT S#,SNAME FROM S WHERE S#=SOME(SELECT S# FROM SC WHERE C#='C2');
SELECT DISTINCT S# FROM SC WHERE GRADE > SOME(SELECT GRADE FROM SC WHERE SC.S#='S4');
SELECT SNAME,AGE FROM S WHERE S# <> ALL(SELECT S# FROM SC WHERE C#='C2');
SELECT S# FROM SC GROUP BY S# HAVING AVG(GRADE)>=ALL(SELECT S#,AVG(GRADE) FROM SC GROUP BY S#);
--SELECT X.TEACHER FROM C X WHERE UNIQUE(SELECT Y.TEACHER FROM C Y WHERE Y.TEACHER=X.TEACHER);
SELECT S#,AVG(GRADE) FROM SC GROUP BY S# HAVING AVG(GRADE)>80;
--SELECT S#,AVG_GRADE FROM (SELECT S#,AVG(GRADE) FROM SC GROUP BY S#) AS RESULT(S#,AVG_GRADE) WHERE AVG_GRADE>80;
SELECT S.S#,SNAME FROM S,SC WHERE S.S#=SC.S# AND C#='C2';
SELECT S#,SNAME FROM (S NATURAL INNER JOIN SC) WHERE C#='C2';

--3.4
INSERT INTO SC(S#,C#,GRADE) VALUES('S4','C1',90);

INSERT INTO SC(S#,C#) VALUES('S4','C2');

--INSERT INTO SC (TABLE('S4','C1',85),('S3','C5',90),('S2','C4',70));

DROP TABLE S_GRADE;

CREATE TABLE S_GRADE (
    S#      CHAR(4),
    AVG_GRADE   SMALLINT
);

INSERT INTO S_GRADE(S#,AVG_GRADE) 
SELECT S#,AVG(GRADE) FROM SC WHERE S# IN (SELECT S# FROM S WHERE SEX='M') GROUP BY S# HAVING AVG(GRADE)>80;

DELETE FROM SC WHERE C# IN (SELECT C# FROM C WHERE CNAME='MATHS');

DELETE FROM SC WHERE C#='C1' AND GRADE<(SELECT AVG(GRADE) FROM SC WHERE C#='C1');

--3.4.3
UPDATE C SET TEACHER='WU' WHERE C#='C5';
UPDATE SC SET GRADE=GRADE*1.1 WHERE S# IN (SELECT S# FROM S WHERE SEX='F');
UPDATE SC SET GRADE=GRADE*1.05 WHERE C#='C4' AND GRADE<(SELECT AVG(GRADE) FROM SC WHERE C#='C4');

--3.4.4
CREATE VIEW STUDENT_GRADE AS SELECT S.S#,S.SNAME,C.CNAME,SC.GRADE FROM S,SC,C WHERE S.S#=SC.S# AND SC.C#=c.c#;
INSERT INTO STUDENT_GRADE VALUES('S24','WANG','MATHS',80);

CREATE VIEW S_GRADE1(S#,C_NUM,AVG_GRADE) AS SELECT S#,COUNT(C#),AVG(GRADE) FROM SC WHERE GRADE IS NOT NULL GROUP BY S#;
INSERT INTO S_GRADE1(S#,C_NUM,AVG_GRADE) VALUES('S9','C3',80);

CREATE VIEW S_MALE AS SELECT S#,SNAME,AGE FROM S WHERE SEX='M';
INSERT INTO S_MALE VALUES('S28','WU',18);
COMMIT;

--3.2
SELECT SNAME FROM S WHERE SEX='F' AND
EXISTS (SELECT * FROM SC,C WHERE SC.S#=S.S# AND SC.C#=C.C# AND C.TEACHER='LIU');

SELECT SNAME FROM S WHERE SEX='F' AND
EXISTS (SELECT 1 FROM C WHERE C.TEACHER='LIU' AND EXISTS (SELECT 1 FROM SC WHERE sc.c#=C.C# AND SC.S#=S.S#));

SELECT SNAME FROM S WHERE SEX='F' AND 
EXISTS (SELECT * FROM SC WHERE S.S#=SC.S# AND EXISTS (SELECT * FROM C WHERE SC.C#=C.C# AND TEACHER='LIU'));

--4)
select c# from c where exists 
(select * from s where sname='WANG' and not exists (select * from sc where sc.s#=s.s# and sc.c#=c.c#));

--6)
select c#,cname from c where not exists (select * from s where not exists (select * from sc where sc.s#=s.s# and sc.c#=c.c#));

--7)
select s# from s  where not exists 
(select * from c where c.teacher='LIU' and not exists (select * from sc Y where Y.s#=s.s# and Y.c#=C.c#));

select distinct s# from sc X where not exists 
(select * from c where c.teacher='LIU' and not exists (select * from sc Y where Y.s#=X.s# and Y.c#=C.c#));


--3.7
select s#,sname,sex from s where not exists 
(select distinct c# from sc x where not exists (select * from sc y where s.s#=Y.s# and x.c#=y.c# and y.grade>=80));

select s#,sname,sex from s where not exists (SELECT * FROM SC WHERE SC.S#=S.S# AND sc.grade<80);
