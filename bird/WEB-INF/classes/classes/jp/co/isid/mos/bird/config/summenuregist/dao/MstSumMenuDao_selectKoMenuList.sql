select 
  bm62.menu_cd,
  rtrim(pc10.menu_name_kj) as menu_name,
  bm62.sum_menu_cd,
  rtrim(pc10sum.menu_name_kj) as sum_menu_name,
  bm62.conv_value,
  case when bm62.menu_cd = bm62.sum_menu_cd then 1 else 0 end as sum_menu_flg,
  bm62.first_user,
  bm62.first_pgm,
  bm62.first_tmsp,
  bm62.last_user,
  bm62.last_pgm,
  bm62.last_tmsp
from
  bm62symm bm62,
  pc10smnu pc10,
  pc10smnu pc10sum
where
    bm62.sum_menu_cd = /*sumMenuCd*/'220001'
and bm62.menu_cd = pc10.menu_cd
and bm62.sum_menu_cd = pc10sum.menu_cd
order by
  case when bm62.menu_cd = bm62.sum_menu_cd then 1 else 0 end desc,
  bm62.menu_cd