select distinct
  bm01.oner_cd,
  bm11.oner_name_kj,
  bm01./*$shukeiKbn*/'AREA_DAI' as sibu_cd,
  bm10.sibu_name 
from 
  bm01tenm bm01,
  bm11oner bm11,
  bm10gsib bm10 
where 
  bm01.company_cd = /*companyCd*/'00' and
  bm01.oner_cd = /*onerCd*/'36001' and 
  bm01.company_cd = bm11.company_cd and 
  bm01.oner_cd = bm11.oner_cd and 
  bm01.company_cd = bm10.company_cd and
  bm01./*$shukeiKbn*/'AREA_DAI' = bm10.sibu_cd
/*IF userTypeCd.equals("01") && limitFlg*/
  AND BM01.SIBU_CD IN (
      SELECT   BM51.SIBU_CD
      FROM     BM51SVSB BM51
      WHERE  BM51.COMPANY_CD = /*companyCd*/'00'
      AND    BM51.SV_CD      = /*userId*/'99990005'
      GROUP BY BM51.SIBU_CD
  )
/*END*/
order by 
  sibu_cd