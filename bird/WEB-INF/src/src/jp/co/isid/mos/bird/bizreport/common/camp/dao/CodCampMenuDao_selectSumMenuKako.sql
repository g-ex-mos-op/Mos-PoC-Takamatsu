select menumst.menu_cd
,      RTRIM(PC10.MENU_NAME_KJ) AS MENU_NAME_KJ
from
(
SELECT distinct 
       case when bm69.sum_menu_cd is null then bm61.menu_cd
            else bm69.sum_menu_cd
       end as menu_cd
FROM BM61CPMN BM61
     left outer join BM69SYMP BM69 on (bm61.camp_id = bm69.camp_id and bm61.menu_cd = bm69.menu_cd)
WHERE BM61.CAMP_ID = /*campId*/'200808'
) menumst
,    PC10SMNU PC10
where menumst.menu_cd = pc10.menu_cd
ORDER BY MENU_CD