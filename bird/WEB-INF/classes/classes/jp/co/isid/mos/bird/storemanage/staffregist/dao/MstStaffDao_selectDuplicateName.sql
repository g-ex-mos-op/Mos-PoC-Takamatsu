SELECT
    rtrim(BM12.STAFF_ID) as STAFF_ID,
    rtrim(BM12.COMPANY_CD) as COMPANY_CD,
    rtrim(BM12.ONER_CD) as ONER_CD,
    rtrim(BM12.MISE_CD_1) as MISE_CD_1,
    rtrim(BM12.STAFF_L_NAME_KJ) as STAFF_L_NAME_KJ,
    rtrim(BM12.STAFF_F_NAME_KJ) as STAFF_F_NAME_KJ,
    rtrim(BM12.STAFF_L_NAME_KNA) as STAFF_L_NAME_KNA,
    rtrim(BM12.STAFF_F_NAME_KNA) as STAFF_F_NAME_KNA

FROM  BM12STAF BM12
WHERE BM12.COMPANY_CD = /*entity.companyCd*/'00'
AND   BM12.ONER_CD    = /*entity.onerCd*/'36001'
/*IF entity.staffId != null */
AND   BM12.STAFF_ID <> /*entity.staffId*/'95000029'
/*END*/
AND   BM12.STAFF_L_NAME_KJ = /*entity.staffLNameKj*/'鳥'
AND   BM12.STAFF_F_NAME_KJ = /*entity.staffFNameKj*/'モス'
AND   BM12.STAFF_L_NAME_KNA = /*entity.staffLNameKna*/'バード'
AND   BM12.STAFF_F_NAME_KNA = /*entity.staffFNameKna*/'モス'
