-- 코드를 입력하세요
SELECT 
    a.car_id,
    a.car_type,
    floor(a.daily_fee * 30 * (100 - c.discount_rate) / 100) as FEE     
from car_rental_company_car as a 
join car_rental_company_discount_plan as c
    on a.car_type = c.car_type
    and c.duration_type = '30일 이상'
where
    a.car_type in ('세단', 'SUV')
    and a.car_id not in (
        select b.car_id
        from car_rental_company_rental_history as b
        where b.start_date <= '2022-11-30' and b.end_date >= '2022-11-01' 
    )
    and floor(a.daily_fee * 30 * (100 - c.discount_rate) / 100) >= 500000
    and floor(a.daily_fee * 30 * (100 - c.discount_rate) / 100) < 2000000
order by
    FEE desc,
    a.car_type asc,
    a.car_id desc;