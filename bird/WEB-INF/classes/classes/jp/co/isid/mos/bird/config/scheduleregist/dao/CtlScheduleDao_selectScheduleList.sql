select 
  br70.taisho_cd,
  br70.company_cd,
  br70.scdl_date,
  br70.scdl_id,
  rtrim(br70.title) as title,
  rtrim(br70.title) as title_db,
  br70.sakujo_flg,
  br70.first_user,
  br70.first_pgm,
  br70.first_tmsp,
  br70.last_user,
  br70.last_pgm,
  br70.last_tmsp,
  br01.user_name_kj as first_user_name,
  row_number() over(partition by br70.scdl_date order by br70.scdl_id) as seq 
from 
  br70scdl br70
  left outer join br01user br01 on (br70.first_user = br01.user_id)
where 
  br70.taisho_cd = /*taishoCd*/'02' and 
  br70.company_cd = /*companyCd*/'00' and 
  br70.scdl_date between /*kikanFrom*/'20081208' and /*kikanTo*/'20081214' and 
  br70.sakujo_flg <> '1' 
order by 
  br70.scdl_date,
  br70.scdl_id