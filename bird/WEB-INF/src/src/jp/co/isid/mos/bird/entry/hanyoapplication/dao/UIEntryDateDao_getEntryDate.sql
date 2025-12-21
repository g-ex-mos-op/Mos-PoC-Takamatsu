SELECT 
	BR18.ENTRY_CD,
	BR18.ENTRY_YEAR,
	BR18.ENTRY_KAI,
	BR18.USERTYPE_CD,
	BR18.DAY_KBN,
	BR18.FROM_DT,
	BR18.TO_DT,
	BR18.FIRST_USER,
	BR18.FIRST_PGM,
	BR18.FIRST_TMSP,
	BR18.LAST_USER,
	BR18.LAST_PGM,
	BR18.LAST_TMSP,
/*IF userTypeCd == '02' */
	DISPLAY.FLG as DISPLAY_FLG, 
/*END*/
	EDIT.FLG as EDIT_FLG
FROM
    BR18ENTD BR18 
    LEFT JOIN (SELECT ENTRY_CD, ENTRY_YEAR, ENTRY_KAI, '1' AS FLG  
               FROM   BR18ENTD
               WHERE  ENTRY_CD = /*entryCd*/'05'
                /*IF userTypeCd == '01' */
                 AND USERTYPE_CD = '01' 
                 AND DAY_KBN = '04' 
                 AND (FROM_DT <= /*sysDate*/'20060605' AND TO_DT >= /*sysDate*/'20060605')
                /*END*/
                /*IF userTypeCd == '02' */
                 AND USERTYPE_CD = '02' 
                 AND DAY_KBN = '04' 
                 AND (FROM_DT <= /*sysDate*/'20060605' AND TO_DT >= /*sysDate*/'20060605')
                /*END*/
              ) EDIT ON  EDIT.ENTRY_CD = BR18.ENTRY_CD
                        AND EDIT.ENTRY_YEAR = BR18.ENTRY_YEAR
                        AND EDIT.ENTRY_KAI = BR18.ENTRY_KAI
/*IF userTypeCd == '02' */
    LEFT JOIN (SELECT ENTRY_CD, ENTRY_YEAR, ENTRY_KAI, '1' AS FLG  
               FROM   BR18ENTD
               WHERE  ENTRY_CD = /*entryCd*/'05'
                 AND  USERTYPE_CD = '02' 
                 AND  DAY_KBN = '04' 
                 AND  TO_DT < /*sysDate*/'20060605'
              ) DISPLAY ON  DISPLAY.ENTRY_CD = BR18.ENTRY_CD
                        AND DISPLAY.ENTRY_YEAR = BR18.ENTRY_YEAR
                        AND DISPLAY.ENTRY_KAI = BR18.ENTRY_KAI
/*END*/
WHERE
    CONCAT(BR18.ENTRY_CD, CONCAT(BR18.ENTRY_YEAR, BR18.ENTRY_KAI)) IN /*joinCharacterList*/('052006001')
AND (BR18.DAY_KBN = '01' 
    OR BR18.DAY_KBN = '03'
    OR BR18.DAY_KBN = '04'
    OR BR18.DAY_KBN = '06')
ORDER BY 
    BR18.ENTRY_CD,
    BR18.ENTRY_YEAR,
    BR18.ENTRY_KAI,
    BR18.USERTYPE_CD,
    BR18.DAY_KBN,
    BR18.FROM_DT,
    BR18.TO_DT
