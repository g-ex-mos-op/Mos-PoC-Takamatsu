select menumst.menu_cd
,      RTRIM(PC10.MENU_NAME_KJ) AS MENU_NAME_KJ
from
(
SELECT distinct 
       case when bm62.sum_menu_cd is null then bm61.menu_cd
            else bm62.sum_menu_cd
       end as menu_cd
FROM BM61CPMN BM61
     left outer join BM62SYMM BM62 on (bm61.menu_cd = bm62.menu_cd)
WHERE BM61.CAMP_ID= /*campId*/'200808'
) menumst
,    PC10SMNU PC10
where menumst.menu_cd = pc10.menu_cd
ORDER BY MENU_CD