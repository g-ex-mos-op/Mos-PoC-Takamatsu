select
/*IF typeCd == "1"*/
  data.bnr_m,
  bc30.bnr_m_name,
/*END*/
  data.row_type,
  data.claim_count
/*IF typeCd == "1"*/  
  ,data.hyojitaisho_name
/*END*/
from
(
SELECT 
/*IF typeCd == "1"*/
    BD05.BNR_M, 
/*END*/
    BD05.TYPE_CD as ROW_TYPE,
    COUNT(BD05.BNR_M) AS CLAIM_COUNT 
/*IF typeCd == "1"*/    
/*IF taishoJoken == "ALL"*/
   ,'‘SŽÐ' as HYOJITAISHO_NAME
/*END*/
/*IF taishoJoken == "JIGYOU"*/
   ,BM10.JIGYOU_NAME as HYOJITAISHO_NAME
/*END*/
/*IF taishoJoken == "SLAREA"*/
   ,BM10.SLAREA_NAME as HYOJITAISHO_NAME
/*END*/
/*IF taishoJoken == "SIBU"*/
   ,BM10.SIBU_NAME as HYOJITAISHO_NAME
/*END*/
/*IF taishoJoken == "ONER"*/
   ,BM11.ONER_NAME_KJ as HYOJITAISHO_NAME
/*END*/
/*IF taishoJoken == "MISE"*/
   ,BM01.MISE_NAME_KJ as HYOJITAISHO_NAME
/*END*/
/*END*/
FROM 
    BD05VICE BD05 
    left outer join BM01TENM BM01 on (BD05.COMPANY_CD = BM01.COMPANY_CD AND BD05.MISE_CD = BM01.MISE_CD) 
    left outer join BM10GSIB BM10 on (BM01.COMPANY_CD = BM10.COMPANY_CD AND BM01.SIBU_CD = BM10.SIBU_CD)
/*IF typeCd == "1"*/    
/*IF taishoJoken == "ONER"*/
   ,BM11ONER BM11
/*END*/
/*END*/
WHERE 
    BD05.COMPANY_CD = /*companyCd*/'00'
AND substr(BD05.JUSIN_DT, 1, 6) BETWEEN /*kikanFrom*/'20070701' AND /*kikanTo*/'20080631'  
AND BD05.TYPE_CD = /*typeCd*/'1'  
/*IF typeCd == "1"*/
/*IF taishoJoken == "JIGYOU"*/
AND BM10.JIGYOU_CD = /*hyojiTaisho*/'11000'
/*END*/
/*IF taishoJoken == "SLAREA"*/
AND BM10.SLAREA_CD = /*hyojiTaisho*/'10001'
/*END*/
/*IF taishoJoken == "SIBU"*/
AND BM10.SIBU_CD = /*hyojiTaisho*/'011'
/*END*/
/*IF taishoJoken == "ONER"*/
AND BM01.COMPANY_CD = BM11.COMPANY_CD
AND BM01.ONER_CD = BM11.ONER_CD
AND BM01.ONER_CD = /*hyojiTaisho*/'011'
/*END*/
/*IF taishoJoken == "MISE"*/
AND BM01.MISE_CD = /*hyojiTaisho*/'011'
/*END*/
/*IF userTypeCd == "01" && limitKbn == true */
and  bm01.sibu_cd in (
            select   bm51.sibu_cd
            from     bm51svsb bm51
            where  bm51.company_cd = /*companycd*/'00'
            and    bm51.sv_cd      = /*userid*/'99990005'
            group by bm51.sibu_cd
       )
/*END*/
/*IF userTypeCd == "02" */
and  bm01.mise_cd in (
          select mise_cd 
          from bm01tenm 
          where company_cd = /*companyCd*/'00' 
          and   oner_cd in (select oner_cd from bm06uonr 
                            where  USER_ID = /*userId*/'99990002'   
                             AND   COMPANY_CD = bm01tenm.company_cd)
     )
/*END*/
/*IF userTypeCd == "03" */
and  bm01.mise_cd in (
          select mise_cd 
          from bm07uten 
          where USER_ID = /*userId*/'99990003' and COMPANY_CD = /*companyCd*/'00'
     )
/*END*/
--ELSE
and  BD05.UKETUKE_NO not like 'H%' 
/*END*/
GROUP BY 
     BD05.TYPE_CD
/*IF typeCd == "1"*/
    ,BD05.BNR_M 
/*IF taishoJoken == "JIGYOU"*/
    ,BM10.JIGYOU_NAME
/*END*/
/*IF taishoJoken == "SLAREA"*/
    ,BM10.SLAREA_NAME
/*END*/
/*IF taishoJoken == "SIBU"*/
    ,BM10.SIBU_NAME
/*END*/
/*IF taishoJoken == "ONER"*/
   ,BM11.ONER_NAME_KJ 
/*END*/
/*IF taishoJoken == "MISE"*/
   ,BM01.MISE_NAME_KJ 
/*END*/
/*END*/
) as data
/*IF typeCd == "1"*/
,BC30CBRM bc30
where
  data.bnr_m = bc30.bnr_m 
ORDER BY 
     data.BNR_M
/*END*/
