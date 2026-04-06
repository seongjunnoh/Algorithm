-- 코드를 작성해주세요
select sum(price) as TOTAL_PRICE
from item_info
where rarity = 'LEGEND';

# = : 숫자, 문자열 등 일반적인 값 비교 시 사용, is : NULL 값 비교 시 사용