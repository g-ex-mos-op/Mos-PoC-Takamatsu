select
   bm61.camp_id
,  bm61.menu_cd
,  pc10.menu_name_kj
,  bm62.conv_value
,  bm62.sum_menu_cd
,  pc10sum.menu_name_kj AS SUM_MENU_NAME_KJ

from pc10smnu pc10
,    bm61cpmn bm61
/*IF kakoFlg*/
left join bm69symp bm62 on (bm61.menu_cd = bm62.menu_cd and bm62.camp_id = /*campId*/'200808')
--ELSE
left join bm62symm bm62 on (bm61.menu_cd = bm62.menu_cd)
/*END*/
left join pc10smnu pc10sum on (bm62.sum_menu_cd = pc10sum.menu_cd)
  
where 
    bm61.camp_id = /*campId*/'200808'
and bm61.menu_cd = pc10.menu_cd

order by
  sum_menu_cd,
  menu_cd