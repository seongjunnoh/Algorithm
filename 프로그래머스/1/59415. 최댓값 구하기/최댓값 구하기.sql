-- 코드를 입력하세요
SELECT datetime
from animal_ins
where datetime = (select max(datetime) from animal_ins);

# 이것도 특정 컬럼값이 max(컬럼) 과 동일한 데이터를 찾는 방식으로 해결