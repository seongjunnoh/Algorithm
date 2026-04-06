-- 코드를 작성해주세요
select year(differentiation_date) as year, 
(   # 각 연도별 size max 값
    select max(size_of_colony)
    from ecoli_data
    where year(differentiation_date) = year
) - size_of_colony as year_dev, id
from ecoli_data
order by year asc, year_dev asc;

# select 절 내에 서브쿼리 작성
# 서브쿼리를 통해 연도별 max 값 찾기