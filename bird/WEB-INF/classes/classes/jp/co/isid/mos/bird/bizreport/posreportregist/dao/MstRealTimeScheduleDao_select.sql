SELECT BR82.COMPANY_CD
,      BR82.MISE_CD
,      BR82.SHU_DT_STA
,      BR82.SHU_DT_END
,      BR82.FIRST_USER
,      BR82.FIRST_PGM
,      BR82.FIRST_TMSP
,      BR82.LAST_USER
,      BR82.LAST_PGM
,      BR82.LAST_TMSP
FROM
    BR82RTSM BR82
WHERE BR82.COMPANY_CD = /*companyCd*/'00'
/*IF miseCd != null */
AND   BR82.MISE_CD           = /*miseCd*/'02001
/*END*/
/*IF shuDtStart != null */
AND   BR82.SHU_DT_STA  = /*shuDtStart*/'20101119'
/*END*/
/*IF sysDate != null */
AND   BR82.SHU_DT_END >= /*sysDate*/'20101119'
/*END*/