-- 코드를 입력하세요
select m.member_name, r.review_text, date_format(r.review_date, "%Y-%m-%d") as review_date
from member_profile as m
    join rest_review as r
    on m.member_id = r.member_id
where m.member_id = (
    select m.member_id
    from member_profile as m
        join rest_review as r
        on m.member_id = r.member_id
    group by r.member_id    
    order by count(r.member_id) desc limit 1)
order by r.review_date asc, r.review_text asc

