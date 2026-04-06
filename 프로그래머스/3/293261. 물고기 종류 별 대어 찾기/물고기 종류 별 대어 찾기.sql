-- 코드를 작성해주세요
select fi.id, fni.fish_name, fi.length
from fish_info as fi
    join fish_name_info as fni
    on fi.fish_type = fni.fish_type
where (fi.fish_type, fi.length) in 
    (   # 각 그룹의 max length 구하기
        select fish_type, max(length)
        from fish_info
        group by fish_type
    )
order by fi.id ASC;

# 서브 쿼리로 각 물고기 종류의 max(length) 값 구하기 -> group by 활용
# 원본 데이터 셋에서, 서브 쿼리의 정답과 일치하는 데이터 구하기 -> in 키워드 활용