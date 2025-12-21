select 
  bm62.menu_cd,
  rtrim(pc10ko.menu_name_kj) as menu_name,
  bm62.sum_menu_cd,
  rtrim(pc10oya.menu_name_kj) as sum_menu_name,
  conv_value
from
  bm62symm bm62,
  pc10smnu pc10ko,
  pc10smnu pc10oya
where
    bm62.menu_cd = /*menuCd*/'220007'
and bm62.menu_cd = pc10ko.menu_cd
and bm62.sum_menu_cd = pc10oya.menu_cd