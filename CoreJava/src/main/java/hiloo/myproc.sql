create or replace procedure 
	proc_print_bool(v_bool boolean)
is

begin
	if v_bool then
		dbms_output.put_line('true');
	else
		dbms_output.put_line('false');
	end if;
end; 
