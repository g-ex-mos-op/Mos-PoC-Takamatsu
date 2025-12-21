select distinct
  bm62.sum_menu_cd,
  rtrim(pc10.menu_name_kj) as sum_menu_name
from
  bm62symm bm62,
  pc10smnu pc10
where
  bm62.sum_menu_cd = pc10.menu_cd
/*IF menuCd != null*/
and bm62.sum_menu_cd = /*menuCd*/'220001'
/*END*/
order by
  bm62.sum_menu_cd