select distinct
  bt73.company_cd, 
  bt73.mise_cd, 
  RTRIM(bm01.mise_name_kj) AS mise_name_kj,
  bt73.takuhai_kbn,
  RTRIM(bm47.taku_detail_name) AS TAKUHAI_KBN_NAME,
  bm22.event_start_dt,
  bm22.event_end_dt,
  bm22.henko_dt 
from
  bm47tdms bm47,
  bt73taku bt73,
  bm01tenm bm01,
  bm22evnt bm22,
  (select 
     company_cd,
     mise_cd,
     eigyo_dt
   from 
     bt73taku
   where 
     takuhai_kbn <> '00' and
     company_cd = /*companyCd*/'00' and 
     eigyo_dt = /*targetYM*/'200709' and
     uriage_DOGETU_taku = 0
   ) as bt73k
where
  bt73.company_cd = bm01.company_cd and
  bt73.mise_cd = bm01.mise_cd and
  bm01.company_cd = bm22.company_cd and
  bm01.mise_cd = bm22.mise_cd and
  bt73.company_cd = bt73k.company_cd and
  bt73.mise_cd = bt73k.mise_cd and
  bt73.eigyo_dt = bt73k.eigyo_dt and
/*IF sibuCd != null */
  bm01.sibu_cd = /*sibuCd*/'031' and
/*END*/
  bm22.event_cd = '04' and
/*IF closeDt != null */
  bm01.close_dt >= /*closeDt*/'20070901' and
/*END*/
  bt73.takuhai_kbn <> '00'
  and bt73.takuhai_kbn = bm47.taku_detail_cd
order by 
  bt73.mise_cd