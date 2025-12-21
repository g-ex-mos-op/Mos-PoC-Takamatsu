select
   '' AS CAMP_ID
,  pc10.menu_cd
,  pc10.menu_name_kj
,  bm62.conv_value
,  bm62.sum_menu_cd
,  pc10sum.menu_name_kj AS SUM_MENU_NAME_KJ

from pc10smnu pc10
left join bm62symm bm62 on (pc10.menu_cd = bm62.menu_cd)
left join pc10smnu pc10sum on (bm62.sum_menu_cd = pc10sum.menu_cd)
  
where 
    pc10.menu_cd in /*menuCds*/('011276', '140732', '140733', '011277')


order by
  sum_menu_cd,
  menu_cd