select 
  bm62.menu_cd,
  rtrim(pc10.menu_name_kj) as menu_name,
  bm62.sum_menu_cd,
  rtrim(pc10sum.menu_name_kj) as sum_menu_name,
  bm62.conv_value
from
  bm62symm bm62,
  pc10smnu pc10,
  pc10smnu pc10sum
where
    bm62.menu_cd = pc10.menu_cd
and bm62.sum_menu_cd = pc10sum.menu_cd
order by
  bm62.sum_menu_cd,
  bm62.menu_cd