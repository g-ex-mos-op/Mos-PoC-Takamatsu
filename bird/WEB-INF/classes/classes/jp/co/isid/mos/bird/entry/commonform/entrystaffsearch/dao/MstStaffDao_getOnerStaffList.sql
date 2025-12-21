select 
  bm12.staff_id,
  bm12.company_cd,
  bm12.oner_cd,
  bm12.old_oner_cd,
  bm12.mise_cd_1,
  bm01.mise_name_kj as mise_cd_1_name,
  bm12.mise_cd_2,
  bm12.mise_cd_3,
  bm12.mise_cd_4,
  bm12.mise_cd_5,
  bm12.staff_l_name_kj,
  bm12.staff_f_name_kj,
  bm12.staff_l_name_kna,
  bm12.staff_f_name_kna,
  bm12.sex,
  bm12.birthday,
  bm12.job,
  bm12.situation_kbn,
  case when bm12.situation_kbn = '0' then 'Šˆ“®’†' 
       when bm12.situation_kbn = '1' then '‹xE’†'
  end as SITUATION_KBN_DISP,
  bm12.move_dt,
  bm12.retire_dt,
  bm12.leave_dt,
  bm12.return_dt,
  bm12.note,
  bm12.spare_flg1,
  bm12.spare_flg2,
  bm12.spare_flg3,
  bm12.first_user,
  bm12.first_pgm,
  bm12.first_tmsp,
  bm12.last_user,
  bm12.last_pgm,
  bm12.last_tmsp
from
  bm12staf bm12,
  bm01tenm bm01
where
  bm12.company_cd = /*companyCd*/'00' and
  bm01.oner_cd = /*onerCd*/'36478' and
  bm12.company_cd = bm01.company_cd and
  bm12.mise_cd_1 = bm01.mise_cd and 
  bm12.situation_kbn in ('0', '1') 
order by 
  bm12.mise_cd_1, bm12.situation_kbn, bm12.staff_id