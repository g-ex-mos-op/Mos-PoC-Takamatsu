select distinct 
  bc06.company_cd,
  bc05.company_name
from
  bc06comm bc06,
  bc05kcom bc05
where 
  bc06.r_company_cd in (
    select 
      bm03.r_company_cd 
    from
      bm03uscp bm03 
    where 
      user_id = /*userId*/'99990005' and 
      zokusei_kbn in ('0', '1')
  ) and 
  bc06.company_cd = bc05.company_cd
order by
  bc06.company_cd