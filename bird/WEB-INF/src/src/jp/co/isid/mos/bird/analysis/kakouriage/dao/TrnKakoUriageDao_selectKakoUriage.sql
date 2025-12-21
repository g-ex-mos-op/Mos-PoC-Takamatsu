select 
/*IF taishoJoken.equals('MISE')*/
  bd19.mise_cd,
  rtrim(bm01.mise_name_kj) as mise_name_kj,
/*END*/
  bd19.eigyo_dt,
  bd19.nendo,
  sum(bd19.uriage) as uriage,
  sum(bd19.kyakusu) as kyakusu,
  count(distinct bd19.mise_cd) as mise_cnt
from 
  bd19past bd19, 
  bm01tenm bm01 
where 
    bd19.nendo between /*nendoFrom*/'2003' and /*nendoTo*/'2008'
and bd19.company_cd = /*companyCd*/'00'
and bd19.company_cd = bm01.company_cd 
and bd19.mise_cd = bm01.mise_cd
/*IF taishoJoken.equals('MISE')*/
and bd19.mise_cd = /*miseCd*/'02001'
/*END*/
/*IF userTypeCd.equals("01") && limitFlg*/
AND    BM01.SIBU_CD IN (
   SELECT   BM51.SIBU_CD
   FROM     BM51SVSB BM51
   WHERE  BM51.COMPANY_CD = /*companyCd*/'00'
   AND    BM51.SV_CD      = /*userId*/'99990005'
   GROUP BY BM51.SIBU_CD
)
/*END*/
/*IF userTypeCd == "02" */
and     bd19.mise_cd in (select mise_cd 
                 from bm01tenm 
                 where company_cd = /*companyCd*/'00' 
                 and   oner_cd in (select oner_cd from bm06uonr 
                                   where  USER_ID = /*userId*/'99990002'   
                                   AND   COMPANY_CD = /*companyCd*/'00' )
                )
/*END*/
/*IF userTypeCd == "03" */
and     bd19.mise_cd in (select mise_cd 
                 from bm07uten 
                 where USER_ID = /*userId*/'99990003' and COMPANY_CD = /*companyCd*/'00'
                )
/*END*/

group by rollup
(bd19.nendo,(
  bd19.nendo, bd19.eigyo_dt 
/*IF taishoJoken.equals('MISE')*/
  ,bd19.mise_cd ,rtrim(bm01.mise_name_kj)
/*END*/
))
order by 
  bd19.nendo, bd19.eigyo_dt