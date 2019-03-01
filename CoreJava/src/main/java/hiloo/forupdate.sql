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
