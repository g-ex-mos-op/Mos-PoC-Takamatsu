SELECT distinct 
/*IF taishoJoken == "JIGYOU"*/
   TBL.JIGYOU_NAME as HYOJITAISHO_NAME
/*END*/
/*IF taishoJoken == "SLAREA"*/
   TBL.SLAREA_NAME as HYOJITAISHO_NAME
/*END*/
/*IF taishoJoken == "SIBU"*/
   TBL.SIBU_NAME as HYOJITAISHO_NAME
/*END*/
/*IF taishoJoken == "ONER"*/
   TBL.ONER_NAME_KJ as HYOJITAISHO_NAME
/*END*/
/*IF taishoJoken == "MISE"*/
   TBL.MISE_NAME_KJ as HYOJITAISHO_NAME
/*END*/
FROM 
/*IF taishoJoken == "MISE"*/
    BM01TENM TBL
/*END*/
/*IF taishoJoken == "ONER"*/
    BM11ONER TBL
/*END*/
/*IF taishoJoken != "MISE" && taishoJoken != "ONER"*/
    BM10GSIB TBL 
/*END*/    
WHERE 
    TBL.COMPANY_CD = /*companyCd*/'00'
/*IF taishoJoken == "JIGYOU"*/
AND TBL.JIGYOU_CD = /*hyojiTaisho*/'11000'
/*END*/
/*IF taishoJoken == "SLAREA"*/
AND TBL.SLAREA_CD = /*hyojiTaisho*/'10001'
/*END*/
/*IF taishoJoken == "SIBU"*/
AND TBL.SIBU_CD = /*hyojiTaisho*/'011'
/*END*/
/*IF taishoJoken == "ONER"*/
AND TBL.ONER_CD = /*hyojiTaisho*/'011'
/*END*/
/*IF taishoJoken == "MISE"*/
AND TBL.MISE_CD = /*hyojiTaisho*/'011'
/*END*/
