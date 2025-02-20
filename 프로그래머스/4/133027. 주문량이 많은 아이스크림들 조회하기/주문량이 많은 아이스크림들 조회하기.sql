-- 코드를 입력하세요
select fh.flavor
from first_half as fh
    join july as j
    on fh.flavor = j.flavor
group by j.flavor
order by sum(j.total_order) + fh.total_order desc limit 3