SELECT PC10.MENU_CD
,      RTRIM(PC10.MENU_NAME_10) AS MENU_NAME_10
,      RTRIM(PC10.MENU_NAME_8) AS MENU_NAME_8
,      RTRIM(PC10.MENU_NAME_KJ) AS MENU_NAME_KJ
,      PC10.MENU_BUNRUI
,      PC11.MBUNRUI_NAME_KJ
,      PC11.MBUNRUI_NAME_KNA
,      PC10.MENU_HANBAI
,      PC12.MHANBAI_NAME_KJ
,      PC12.MHANBAI_NAME_KNA
,      PC10.MENU_SHOKUZAI
,      PC13.MSHOKUZAI_NAME_KJ
,      PC13.MSHOKUZAI_NAME_KNA
,      MENU_STA_DT
,      MENU_END_DT
,      MENU_KBN1,      MENU_KBN2,      MENU_KBN3
FROM PC10SMNU PC10
LEFT JOIN PC11MBUN PC11 ON (PC11.MENU_BUNRUI = PC10.MENU_BUNRUI)
LEFT JOIN PC12MHAN PC12 ON (PC12.MENU_HANBAI = PC10.MENU_HANBAI)
LEFT JOIN PC13MSHK PC13 ON (PC13.MENU_SHOKUZAI = PC10.MENU_SHOKUZAI)

WHERE PC10.MENU_CD IS NOT null
/*IF sysDate != null */
AND   PC10.MENU_END_DT >= /*sysDate*/'20080820'
/*END*/
/*IF cdFrom != null */
AND PC10.MENU_CD >= /*cdFrom*/'000000'
/*END*/
/*IF cdTo != null */
AND PC10.MENU_CD <=/*cdTo*/'999999'  
/*END*/
/*IF name1 != null */
AND (
	(
         PC10.MENU_NAME_10 like /*name1*/'abc'
     OR  PC10.MENU_NAME_KJ like /*name1*/'abc'
     OR  PC10.MENU_NAME_8  like /*name1*/'abc'
   )
/*IF name2 != null */
OR 
	(
         PC10.MENU_NAME_10 like /*name2*/'abc'
     OR  PC10.MENU_NAME_8  like /*name2*/'abc'
     OR  PC10.MENU_NAME_KJ like /*name2*/'abc'
    )
/*END*/
/*IF name3 != null */
OR 
	(
         PC10.MENU_NAME_10 like /*name3*/'abc'
     OR  PC10.MENU_NAME_8  like /*name3*/'abc'
     OR  PC10.MENU_NAME_KJ like /*name3*/'abc'
    )
/*END*/
/*IF name4 != null */
OR 
	(
         PC10.MENU_NAME_10 like /*name4*/'abc'
     OR  PC10.MENU_NAME_8  like /*name4*/'abc'
     OR  PC10.MENU_NAME_KJ like /*name4*/'abc'
    )
/*END*/

)
/*END*/
/*IF menuBunrui != null */
AND PC10.MENU_BUNRUI =/*menuBunrui*/'999999'  
/*END*/
/*IF sortType == null || "CODE".equals(sortType) */
ORDER BY MENU_CD
/*END*/
/*IF "NAME".equals(sortType) */
ORDER BY Hex(MENU_NAME_10)
/*END*/
