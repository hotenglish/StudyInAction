set head off;
set feed off;
set echo off;  
spool sqlcombak2.sql;
select 'select * from '||table_name||';' from user_tables;
spool  off;   
exit;
