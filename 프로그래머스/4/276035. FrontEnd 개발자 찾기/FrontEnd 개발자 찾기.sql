-- 코드를 작성해주세요
select distinct d.id, d.email, d.first_name, d.last_name
from skillcodes as s
    join developers as d
    on (d.skill_code & s.code) = s.code and s.category = 'Front End'
order by d.id asc