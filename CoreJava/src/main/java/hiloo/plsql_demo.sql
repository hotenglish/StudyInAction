=====变量部分=====
DECLARE
   v_date date :='01-JAN-07';
BEGIN
   DBMS_OUTPUT.PUT_LINE(to_char(v_date,'yyyy mm dd hh24:mi:ss'));
END;

DECLARE
  v_boolean BOOLEAN  := (1=2);
BEGIN
   IF NOT v_boolean THEN
      DBMS_OUTPUT.PUT_LINE('false');
   END IF;
END;

DECLARE
  v_num NUMBER;
  v_boolean BOOLEAN := v_num is null ;
BEGIN
   IF NOT v_boolean THEN
      DBMS_OUTPUT.PUT_LINE('false');
   END IF;
END;


=======DML/SELECT INTO语句===============
BEGIN
   INSERT INTO test SELECT * FROM test;
   IF SQL%FOUND THEN
      DBMS_OUTPUT.PUT_LINE(SQL%ROWCOUNT);
   END IF;
   IF SQL%NOTFOUND THEN
      DBMS_OUTPUT.PUT_LINE('no rows');
   END IF;
   COMMIT;
END;

BEGIN
   DELETE FROM test WHERE c1=3;
   IF SQL%FOUND THEN
      DBMS_OUTPUT.PUT_LINE(SQL%ROWCOUNT);
   END IF;
   IF SQL%NOTFOUND THEN
      DBMS_OUTPUT.PUT_LINE('no rows');
   END IF;
   COMMIT;
END;
SQL%FOUND SQL%NOTFOUND SQL%ISOPEN SQL%ROWCOUNT


变量的作用域
declare
  v_num number(5,2);
begin
  v_num :=123.45;
      dbms_output.put_line(v_num);
    declare
      v_ch varchar2(10);
    begin
      v_num :=321.45;
      v_ch :='Hello';
      dbms_output.put_line(v_num||'   '||v_ch);
    end;
      dbms_output.put_line(v_num);
      --dbms_output.put_line(v_ch);
end;

<<outer>>
declare
  v_num number(5,2);
begin
  v_num :=123.45;
      dbms_output.put_line(v_num);
    declare
      v_ch varchar2(10);
      v_num number(3) :=100;
    begin
      v_num :=321.45;
      v_ch :='Hello';
      outer.v_num := 234.56;
      dbms_output.put_line(v_num||'   '||v_ch);
      dbms_output.put_line(outer.v_num);
    end;
      dbms_output.put_line(v_num);
      --dbms_output.put_line(v_ch);
end;

DECLARE
  v_num NUMBER;
BEGIN
  SELECT c1 INTO v_num FROM test WHERE c1=100;
  IF SQL%FOUND THEN
     DBMS_OUTPUT.PUT_LINE(SQL%ROWCOUNT);
  END IF;
/* EXCEPTION
  WHEN NO_DATA_FOUND THEN
    IF SQL%NOTFOUND THEN
       DBMS_OUTPUT.PUT_LINE(SQL%ROWCOUNT);
    END IF;
  WHEN TOO_MANY_ROWS THEN
    IF SQL%FOUND THEN
       DBMS_OUTPUT.PUT_LINE('too many rows');
       DBMS_OUTPUT.PUT_LINE(SQL%ROWCOUNT);
    END IF;
*/
END;

================IF语句==================
declare
v_c1 char(10) :='abc   ';
begin
 dbms_output.put_line('length(v_c1)='||length(v_c1));
 if v_c1 = 'abc' then
   dbms_output.put_line ('='||length(v_c1));
 else
   dbms_output.put_line ('<>');
 end if;
end;
/
=10

declare
v_c1 varchar2(10) :='abc   ';
begin
 dbms_output.put_line('length(v_c1)='||length(v_c1));
 if v_c1 = 'abc' then
   dbms_output.put_line ('='||length(v_c1));
 else
   dbms_output.put_line ('<>');
 end if;
end;
<>

结论：被比较的字符是'abc'，无论v_c1 char(10) :='abc'或者v_c1 varchar2(10) :='abc'，比较都相同，长度一个为10，一个为3,被比较的字符是'abc ',char(10)相等，varchar2(10)的不等。

declare
v_v1 number;
v_v2 number;
v_v3 boolean;
begin
v_v1 := 3;
if v_v1 < v_v2 then
  dbms_output.put_line('>');
else
  dbms_output.put_line('<');
end if;
end;
/
<
结论：无论是>/</=,只要比较的串中有空值，一律为false。


=======================循环======================
DECLARE
  v_emp		s_emp%ROWTYPE;
  v_cnt		BINARY_INTEGER := 1;
BEGIN
  LOOP
    SELECT *
      INTO v_emp
      FROM s_emp
     WHERE id = v_cnt;
    
    DBMS_OUTPUT.PUT_LINE(v_emp.id||'  '||v_emp.first_name);
    v_cnt := v_cnt + 1;

    EXIT WHEN v_cnt > 5;
  END LOOP;

  DBMS_OUTPUT.PUT_LINE('=============');
  v_cnt := 1;
  
  WHILE v_cnt <=5 LOOP
    SELECT *
      INTO v_emp
      FROM s_emp
     WHERE id = v_cnt;
    
    DBMS_OUTPUT.PUT_LINE(v_emp.id||'  '||v_emp.first_name);
    v_cnt := v_cnt + 1;
  END LOOP;

  DBMS_OUTPUT.PUT_LINE('=============');

  FOR v_counter IN REVERSE 1..5 LOOP
    SELECT *
      INTO v_emp
      FROM s_emp
     WHERE id = v_counter;
    
    DBMS_OUTPUT.PUT_LINE(v_emp.id||'  '||v_emp.first_name);
  END LOOP;
END;

==================利用循环计算1到100的和======================
declare
v_sum BINARY_INTEGER:=0;
begin
for i in 1..100 loop
v_sum:=v_sum+i;
end loop;
DBMS_OUTPUT.PUT_LINE(v_sum);
end;

==========================复合类型===========================
记录类型
declare
  v_rec1   students%rowtype;
  v_rec2   students%rowtype;
begin
  select * into v_rec1 from students;
  v_rec2 := v_rec1;
  dbms_output.put_line(v_rec2.id||v_rec2.first_name||v_rec2.major);
end;
/

declare
type t_rec is record(
 student_id number,
 firstname varchar2(10),
 lastname  varchar2(10),
 major      varchar2(20),
 currentcredits   number(1));
  v_rec1   students%rowtype;
  v_rec2   t_rec;
begin
  select * into v_rec1 from students;
  v_rec2 := v_rec1;
  dbms_output.put_line(v_rec2.student_id||v_rec2.firstname||v_rec2.major);
end;
/


==========异常============
申明部分出现异常：
BEGIN
  DECLARE
    v_num number(3) := 'abc';
  BEGIN
    DBMS_OUTPUT.PUT_LINE(v_num);
  EXCEPTION
    WHEN VALUE_ERROR THEN
      v_num := 100;
    WHEN OTHERS THEN
      v_num :=100;
  END;
EXCEPTION
    WHEN VALUE_ERROR THEN
      DBMS_OUTPUT.PUT_LINE('value error');
END;

执行部分出现异常：
DECLARE
  e1 EXCEPTION;
BEGIN
  raise e1;
EXCEPTION
  WHEN e1 THEN
    DBMS_OUTPUT.PUT_LINE('Trap');
END;

DECLARE
  e1 EXCEPTION;
  e2 EXCEPTION;
BEGIN
  BEGIN
    raise e2;
  EXCEPTION
    WHEN e1 THEN
      DBMS_OUTPUT.PUT_LINE('Trap');
  END;
EXCEPTION
  WHEN e2 THEN
    DBMS_OUTPUT.PUT_LINE('Outer Trap');
END;

DECLARE
  e1 EXCEPTION;
  e2 EXCEPTION;
  e3 EXCEPTION;
BEGIN
  BEGIN
    raise e3;
  EXCEPTION
    WHEN e1 THEN
      DBMS_OUTPUT.PUT_LINE('Trap');
  END;
EXCEPTION
  WHEN e2 THEN
    DBMS_OUTPUT.PUT_LINE('Outer Trap');
END;

====显式定义异常==============
DECLARE
  v_num number;
  e1 EXCEPTION;
  PRAGMA EXCEPTION_INIT(e1,-1);
BEGIN
  --SELECT c1 INTO v_num FROM test WHERE c1=1100;
  INSERT INTO test VALUES (2);
EXCEPTION
  WHEN TOO_MANY_ROWS THEN
     DBMS_OUTPUT.PUT_LINE(SQLCODE);
     DBMS_OUTPUT.PUT_LINE(SQLERRM);
  WHEN NO_DATA_FOUND THEN
     DBMS_OUTPUT.PUT_LINE(SQLCODE);
     DBMS_OUTPUT.PUT_LINE(SQLERRM);
  WHEN e1 THEN
     DBMS_OUTPUT.PUT_LINE('e1'||SQLCODE);
     DBMS_OUTPUT.PUT_LINE(SQLERRM);
  WHEN OTHERS THEN
     DBMS_OUTPUT.PUT_LINE('others'||SQLCODE);
     DBMS_OUTPUT.PUT_LINE(SQLERRM);
END;
/

DECLARE
  v_num number;
BEGIN
  SELECT count(c1) INTO v_num FROM test WHERE c1=1100;
  IF v_num = 0 THEN
    RAISE_APPLICATION_ERROR(-20001,'=0');
  END IF;
  
错误号：-20000 到 -20999


DECLARE
  CURSOR c1_cur IS
    SELECT c2 FROM  test  WHERE c1 IS NULL FOR UPDATE;
  v_num number ;
  v_sum number :=0;
BEGIN
   SELECT c1 INTO v_sum FROM test WHERE ROWNUM = 1;
   OPEN c1_cur;
   FETCH c1_cur INTO v_num;
   WHILE c1_cur%FOUND LOOP
     DBMS_OUTPUT.PUT_LINE(v_sum);
     v_sum := v_sum + v_num;
     UPDATE test SET c1=v_sum
     WHERE CURRENT OF c1_cur;
     FETCH c1_cur INTO v_num;
   END LOOP;
   COMMIT;
END;


SET VERIFY OFF
UNDEFINE s_id
ACCEPT s_id PROMPT 'Please enter ID key:';
DECLARE
   CURSOR flight_cursor(p_id number) IS
      SELECT * FROM flight
      WHERE ID = p_id FOR UPDATE OF cnt;
   v_flight flight%ROWTYPE;
   v_id  flight.id%TYPE :=1;
   v_cnt flight.cnt%TYPE;
BEGIN
   v_id := &s_id;
   OPEN flight_cursor(v_id);
   FETCH flight_cursor INTO v_flight;
   IF flight_cursor%FOUND THEN
      IF v_flight.cnt = 0 THEN
         DBMS_OUTPUT.PUT_LINE('There isn''t ticket.');
      ELSE
         DBMS_OUTPUT.PUT_LINE(v_flight.cnt);
         DBMS_OUTPUT.PUT_LINE('There is '||v_flight.cnt|| 'tickets');
         
         UPDATE flight SET CNT = v_flight.cnt - 1
         WHERE CURRENT OF flight_cursor;
         v_cnt := v_flight.cnt - 1;
         DBMS_OUTPUT.PUT_LINE('There is '||v_cnt|| 'tickets');
         COMMIT;
      END IF;
   ELSE
      DBMS_OUTPUT.PUT_LINE('There is not flight id');
   END IF;
   CLOSE flight_cursor;
END;
/

DECLARE
  TYPE emp_record IS RECORD (
    empno  emp.empno%TYPE,
    ename  emp.ename%TYPE,
    job    emp.job%TYPE,
    mgr    emp.mgr%TYPE,
    hiredate  emp.hiredate%TYPE,
    sal    emp.sal%TYPE,
    comm   emp.comm%TYPE,
    deptno emp.deptno%TYPE);
  TYPE emp_table is TABLE OF emp_record INDEX BY BINARY_INTEGER;
  v_emp_table  emp_table;
  v_emp_record emp_record;
  CURSOR emp_cursor IS
     SELECT * FROM emp;
  v_int  INTEGER :=0 ;
  e_emp_nodata EXCEPTION;
  v_index INTEGER;
BEGIN
  DBMS_OUTPUT.PUT_LINE(v_int);
  OPEN emp_cursor;
  FETCH emp_cursor INTO v_emp_record;
  IF emp_cursor%ROWCOUNT = 0 THEN
     raise e_emp_nodata;
  END IF;
  IF emp_cursor%NOTFOUND THEN
     raise e_emp_nodata;
  END IF; 

  WHILE emp_cursor%FOUND LOOP
     FETCH emp_cursor INTO v_emp_record;
     v_emp_table(v_emp_record.empno) := v_emp_record;
  END LOOP;

  v_index := v_emp_table.FIRST;
  WHILE v_index <= v_emp_table.LAST LOOP
     DBMS_OUTPUT.PUT_LINE(v_emp_table(v_index).ename);
     v_index := v_emp_table.NEXT(v_index);
  END LOOP;
  CLOSE emp_cursor;  

  EXCEPTION 
     WHEN e_emp_nodata THEN
       DBMS_OUTPUT.PUT_LINE('NO DATA FOUND');
END;

DECLARE
  e_nodata  EXCEPTION;
  TYPE test_table_type IS TABLE OF test%ROWTYPE INDEX BY BINARY_INTEGER;
  v_test_table test_table_type;
  CURSOR test_cursor IS
     SELECT * FROM TEST;
  v_index  BINARY_INTEGER :=0;
BEGIN
  FOR v_cursor IN test_cursor 

     v_index := v_index + 1; 
     v_test_table(v_index).c1 := v_cursor.c1;
  END LOOP;
  IF v_index = 0 THEN
     raise e_nodata;
  END IF;
  FOR i in 1..v_index LOOP
     DBMS_OUTPUT.PUT_LINE(v_test_table(v_index).c1);
  END LOOP;
EXCEPTION
  WHEN e_nodata THEN
   DBMS_OUTPUT.PUT_LINE('NO Data');
END;
/

DECLARE
   CURSOR test_cursor IS
      SELECT * FROM TEST;
BEGIN
   FOR i_cursor IN test_cursor LOOP
       UPDATE test1 set c2 = i_cursor.c2
       WHERE c1 = i_cursor.c1;
       IF SQL%NOTFOUND THEN
          INSERT INTO test1 VALUES (i_cursor.c1,i_cursor.c2);
       END IF;
   END LOOP;
   COMMIT;
END;
=======过程========================================
create or replace procedure modetest 
(p_inparameter in number,
 p_outparameter out number,
 p_inoutparameter in out number) 
is
  v_localvariable number :=0;
begin
  dbms_output.put_line('Inside modetest:');
  if (p_inparameter is null) then
     dbms_output.put_line('p_inparameter is null');
  else
     dbms_output.put_line('p_inparameter = ' ||p_inparameter);
  end if;
  
  if (p_outparameter is null) then
     dbms_output.put_line('p_outparameter is null');
  else
     dbms_output.put_line('p_outparameter = ' ||p_outparameter);
  end if;
  
  if (p_inoutparameter is null) then
     dbms_output.put_line('p_inoutparameter is null');
  else
     dbms_output.put_line('p_inoutparameter = ' ||p_inoutparameter);
  end if;
  
  v_localvariable := p_inparameter;
  p_inparameter :=7;
  p_outparameter :=7;

  --8.0.4 or higher this is legal
  v_localvariable := p_outparameter;
  v_localvariable := p_inoutparameter;
  
  p_inoutparameter := 8;
  
  dbms_output.put_line('at end of modetest:');
  if (p_inparameter is null) then
     dbms_output.put_line('p_inparameter is null');
  else
     dbms_output.put_line('p_inparameter = ' ||p_inparameter);
  end if;
  if (p_outparameter is null) then
     dbms_output.put_line('p_outparameter is null');
  else
     dbms_output.put_line('p_outparameter = ' ||p_outparameter);
  end if;
  if (p_inoutparameter is null) then
     dbms_output.put_line('p_inoutparameter is null');
  else
     dbms_output.put_line('p_inoutparameter = ' ||p_inoutparameter);
  end if;
end;

declare
  v_in number(3) :=1;
  v_out number(3) :=2;
  v_inout number(3) :=3;
begin
  dbms_output.put_line('before calling modetest:');
  dbms_output.put_line('v_in ='||v_in);
  dbms_output.put_line('v_out ='||v_out);
  dbms_output.put_line('v_inout='||v_inout);
  dbms_output.put_line('------------------------');
  
  modetest(v_in,v_out,v_inout);
  
  dbms_output.put_line('------------------------');
  dbms_output.put_line('after calling modetest:');
  dbms_output.put_line('v_in ='||v_in);
  dbms_output.put_line('v_out ='||v_out);
  dbms_output.put_line('v_inout='||v_inout);
end;

CREATE OR REPLACE PACKAGE pkg1
AS
  CURSOR test_cur(p_cnt NUMBER) IS
    SELECT * FROM test 
    WHERE c1 = p_cnt;
  v_cnt BINARY_INTEGER :=1;
  TYPE test_rec IS RECORD(
  c1 test.c1%TYPE,
  c2 test.c2%TYPE);
  E_nodata EXCEPTION;
  FUNCTION  data_found(p_cnt NUMBER) RETURN BOOLEAN;
  FUNCTION  rtn_data (p_cnt NUMBER) RETURN test_rec;
  PROCEDURE print_it(p_bln BOOLEAN);
  PROCEDURE print_it(p_rec test_rec);
END;
/

CREATE OR REPLACE PACKAGE BODY pkg1
AS
  FUNCTION data_found(p_cnt NUMBER) RETURN BOOLEAN
  IS
    v_test_rec test_rec;
  BEGIN
    OPEN test_cur(p_cnt);
    FETCH test_cur INTO v_test_rec;
    IF test_cur%FOUND THEN
       CLOSE test_cur;
       RETURN TRUE;
    ELSE
       CLOSE test_cur;
       RETURN FALSE;
    END IF;
  END;

  FUNCTION rtn_data(p_cnt NUMBER) RETURN test_rec
  IS
    v_test_rec test_rec;
  BEGIN
    OPEN test_cur(p_cnt);
    FETCH test_cur INTO v_test_rec;
    IF test_cur%FOUND THEN
       CLOSE test_cur;
       RETURN v_test_rec;
    ELSE
       CLOSE test_cur;
       raise e_nodata;
    END IF;
  EXCEPTION
    WHEN e_nodata THEN
    DBMS_OUTPUT.PUT_LINE('No Data');
    RETURN v_test_rec;
  END;

  PROCEDURE print_it(p_bln BOOLEAN)
  IS
  BEGIN
    IF p_bln THEN
       DBMS_OUTPUT.PUT_LINE('TRUE');
    ELSE
       DBMS_OUTPUT.PUT_LINE('FALSE');
    END IF;
  END;

  PROCEDURE print_it(p_rec test_rec)
  IS
  BEGIN
     DBMS_OUTPUT.PUT_LINE(p_rec.c1);
     DBMS_OUTPUT.PUT_LINE(p_rec.c2);
  END;
BEGIN
    v_cnt := v_cnt + 1;
END;

/

给表xhl_emp增加一个字段stars，并用commission_pct/10四舍五入得到*的个数，插入stars字段。
alter table xhl_emp add (stars varchar2(100));

create or replace procedure xhl_add_stars
is
   v_stars   xhl_emp.stars%type;
   cursor xhl_emp_cursor
   is
   select id,round(commission_pct) stars from xhl_emp
   where commission_pct is not null for update;
begin
   for i_record in xhl_emp_cursor loop
         v_stars := lpad('*',i_record.stars,'*');
         dbms_output.put_line(v_stars);
         update xhl_emp set stars=v_stars where current of xhl_emp_cursor;
   end loop;
   commit;
end xhl_add_stars;

当DML语句被执行时就会点火触发器。下面给出了执行DML语句的算法：
1)执行before语句级触发器（如果有的话）
2）对于受语句影响的每一行
   a。执行before行级触发器（如果有的话）
   b。执行DML语句
   c。执行after行级触发器（如果有的话）
3）执行after语句级触发器（如果有的话）


create sequence student_sequence
start with 10000
increment by 1

-----------产生主键-----------------
create or replace trigger generatestudentid
before insert or update on students
for each row
begin
  select student_sequence.nextval into :new.id from dual;
end;
/

SQL> insert into students(id,first_name,last_name)
  2  values (1,'Lolita','Lazarus');


-------------自治事务-----------------
create or replace trigger trg_emp_delete
  after delete on emp  
  for each row
declare
  pragma autonomous_transaction;
begin
  insert into emp_hist values(sysdate,user,
  :old.empno,:old.ename,:old.job,:old.mgr,:old.hiredate,
  :old.sal,:old.comm,:old.deptno);
  commit;
end trg_emp_delete;

----------------约束表和编译表---------------
create table students (
id  number(5) primary key,
first_name varchar2(20),
last_name  varchar2(20),
major      varchar2(30),
current_credits   number(3)
)
insert into students values (1,'scott','tiger','History',4);
insert into students values (2,'scott','smith','History',4);
commit;

create or replace trigger limitmajors
before insert or update of major on students
for each row
declare
v_maxstudents  constant number :=1;
v_currentstudents number;
begin
  select count(*) into v_currentstudents from students
  where major=:new.major;
  if v_currentstudents + 1 > v_maxstudents then
     raise_application_error(-20000,'too many students in major'||:new.major);
  end if;
end;

SQL> update students set major='History'
  2  where id=1;
update students set major='History'
       *
ERROR at line 1:
ORA-04091: table SCOTT.STUDENTS is mutating, trigger/function may not see it
ORA-06512: at "SCOTT.LIMITMAJORS", line 5
ORA-04088: error during execution of trigger 'SCOTT.LIMITMAJORS'

如果insert语句只影响一行，那么该行的行前与行后触发器不会把触发表当作变异表对待。这是行级别的触发器可以读取或修改触发表的唯一情况。诸如insert into table select等语句总是把触发表当作变异表对待，即使子查询仅仅返回一行时也是如此。

create or replace package studentData as 
type t_majors is table of students.major%type
     index by binary_integer;
type t_ids is table of students.id%type
     index by binary_integer;

v_studentmajors t_majors;
v_studentids    t_ids;
v_numentries    binary_integer :=0;
end;

create or replace trigger rlimitmajors
before insert or update of major on students
for each row
begin
  studentdata.v_numentries := studentdata.v_numentries + 1;
  studentdata.v_studentmajors(studentdata.v_numentries) :=:new.major;
  studentdata.v_studentids(studentdata.v_numentries) :=:new.id;
end;
/

create or replace trigger slimitmajors
after insert or update of major on students
declare
  v_maxstudents  constant number :=1;
  v_currentstudents number;
  v_studentid    students.id%type;
  v_major        students.major%type;
begin
  for v_loopindex in 1..studentdata.v_numentries loop
      v_studentid := studentdata.v_studentids(v_loopindex);
      v_major     := studentdata.v_studentmajors(v_loopindex);
      select count(*) into v_currentstudents
      from students
      where major=v_major;
  if v_currentstudents > v_maxstudents then
     raise_application_error(-20000,'too many students for major' ||v_major ||'because of student'||v_studentid);
  end if;
end loop;
  studentdata.v_numentries :=0;
end;