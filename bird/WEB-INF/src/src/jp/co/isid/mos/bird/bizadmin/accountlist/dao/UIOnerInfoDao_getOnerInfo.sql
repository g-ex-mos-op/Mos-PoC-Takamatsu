select 
  oner_info.oner_name_kj,
  oner_info.keiyaku_type,
  miseInfo.mise_cd,
  miseInfo.mise_name_kj,
  miseInfo.close_dt
from 
(
select distinct
  bm11.company_cd,
  bm11.oner_name_kj,
  keiyaku_type.keiyaku_type
from 
  bm11oner bm11,
  (
    select 
      min(br59.keiyaku_type) as keiyaku_type
    
    from 
      br59ktrl br59,
      br04usrl br04 
    
    where 
      br59.role_cd = br04.role_cd and
      br04.user_id = /*userId*/'MSF70040'
  ) as keiyaku_type
where 
  company_cd = /*companyCd*/'00' and 
  oner_cd = /*onerCd*/'37004'
) as oner_info 
left outer join
(
select 
  bm01.company_cd,
  bm01.mise_cd,
  bm01.mise_name_kj,
  bm01.close_dt
from 
  bm01tenm bm01 
where 
  bm01.mise_cd 
    = (select min(mise_cd)
       from bm01tenm 
       where 
         company_cd = /*companyCd*/'00' and 
         oner_cd = /*onerCd*/'37004' and 
         close_dt >= /*kizyunDt*/'20081201')
) as miseInfo
on (oner_info.company_cd = miseInfo.company_cd)