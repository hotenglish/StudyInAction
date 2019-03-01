select deptno,
count(decode(trunc(sal/1000),1,sal)) "1000-2000",
count(decode(trunc(sal/1000),2,sal)) "2000-3000",
count(decode(trunc(sal/1000),3,sal)) ">3000"
from emp
group by deptno
/
