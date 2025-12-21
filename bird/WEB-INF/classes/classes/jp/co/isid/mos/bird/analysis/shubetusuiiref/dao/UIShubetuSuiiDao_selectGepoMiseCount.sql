select 
  count(distinct bd20.mise_cd) as mise_cnt

from 
  bd20shum bd20
/*IF !taishoJoken.equals("ALL") || (userTypeCd.equals("01") && limitFlg)*/  
  ,bm01tenm bm01
/*END*/  
  /*IF "1".equals(tenpoShu)*/
  ,(select distinct eigyo_ym,
          mise_cd
   from   bn01dten bn01
   where  eigyo_ym between /*targetDtFrom*/'200706' and /*targetDtTo*/'200806'
   and    company_cd = /*companyCd*/'00'
   and    kbn1 = '1'
  ) bn01
  /*END*/

where
    bd20.company_cd = /*companyCd*/'00'
and bd20.eigyo_ym between /*targetDtFrom*/'200706' and /*targetDtTo*/'200806'
/*IF !taishoJoken.equals("ALL") || (userTypeCd.equals("01") && limitFlg)*/  
and bd20.company_cd = bm01.company_cd 
and bd20.mise_cd = bm01.mise_cd
/*END*/
/*IF taishoJoken.equals("MISE")*/
and bd20.mise_cd = /*miseCd*/'01776'
/*END*/
/*IF taishoJoken.equals("SIBU")*/
and bm01.sibu_cd = /*sibuCd*/'031'
/*END*/
/*IF "1".equals(tenpoShu)*/
and bd20.eigyo_ym = bn01.eigyo_ym
and bd20.mise_cd = bn01.mise_cd
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
and     bd20.mise_cd in (select mise_cd 
                 from bm01tenm 
                 where company_cd = /*companyCd*/'00' 
                 and   oner_cd in (select oner_cd from bm06uonr 
                                   where  USER_ID = /*userId*/'99990002'   
                                   AND   COMPANY_CD = bm01tenm.company_cd)
                )
/*END*/
/*IF userTypeCd == "03" */
and     bd20.mise_cd in (select mise_cd 
                 from bm07uten 
                 where USER_ID = /*userId*/'99990003' and COMPANY_CD = /*companyCd*/'00'
                )
/*END*/