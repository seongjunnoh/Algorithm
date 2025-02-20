-- 코드를 입력하세요
select fp.product_id, fp.product_name, sum(fo.amount) * fp.price as total_sales
from food_product as fp
    join food_order as fo
    on fp.product_id = fo.product_id
where fo.produce_date >= str_to_date('2022-05-01', '%Y-%m-%d') and fo.produce_date < str_to_date('2022-06-01', '%Y-%m-%d')
group by fp.product_id
order by total_sales desc, fp.product_id asc
