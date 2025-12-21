SELECT MENU_CD
,      RTRIM(MENU_NAME_10) AS MENU_NAME_10
,      RTRIM(MENU_NAME_8) AS MENU_NAME_8
,      RTRIM(MENU_NAME_KJ) AS MENU_NAME_KJ
,      MENU_BUNRUI
,      MENU_HANBAI
,      MENU_SHOKUZAI
,      MENU_STA_DT
,      MENU_END_DT
,      MENU_KBN1,      MENU_KBN2,      MENU_KBN3
,      FIRST_USER,      FIRST_PGM,      FIRST_TMSP
,      LAST_USER,      LAST_PGM,      LAST_TMSP
FROM PC10SMNU
WHERE MENU_CD IS NOT null
AND   MENU_END_DT > /*sysDate*/'20060927'
/*IF cdFrom != null */
AND MENU_CD >= /*cdFrom*/'000000'
/*END*/
/*IF cdTo != null */
AND MENU_CD <=/*cdTo*/'999999'  
/*END*/
/*IF name != null */
AND (
         MENU_NAME_KJ like /*name*/'abc'
     OR  MENU_NAME_10 like /*name*/'abc'
     OR  MENU_NAME_8  like /*name*/'abc'
    )
/*END*/
ORDER BY MENU_CD
