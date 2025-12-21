SELECT DISTINCT 
	BR18ENTD.ENTRY_CD,
	BR18ENTD.ENTRY_YEAR,
	BR18ENTD.ENTRY_KAI,
/*IF userTypeCd == '02' */
    DISPLAY.FLG as DISPLAY_FLG, 
/*END*/
    EDIT.FLG as EDIT_FLG
FROM
    BR18ENTD
    INNER JOIN BR17ENTL ON BR17ENTL.ENTRY_CD = BR18ENTD.ENTRY_CD
                        AND BR17ENTL.ENTRY_YEAR = BR18ENTD.ENTRY_YEAR
                        AND BR17ENTL.ENTRY_KAI = BR18ENTD.ENTRY_KAI
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
              ) EDIT ON  EDIT.ENTRY_CD = BR18ENTD.ENTRY_CD
                        AND EDIT.ENTRY_YEAR = BR18ENTD.ENTRY_YEAR
                        AND EDIT.ENTRY_KAI = BR18ENTD.ENTRY_KAI
/*IF userTypeCd == '02' */
    LEFT JOIN (SELECT ENTRY_CD, ENTRY_YEAR, ENTRY_KAI, '1' AS FLG  
               FROM   BR18ENTD
               WHERE  ENTRY_CD = /*entryCd*/'05'
                 AND  USERTYPE_CD = '02' 
                 AND  DAY_KBN = '04' 
                 AND  TO_DT < /*sysDate*/'20060605'
              ) DISPLAY ON  DISPLAY.ENTRY_CD = BR18ENTD.ENTRY_CD
                        AND DISPLAY.ENTRY_YEAR = BR18ENTD.ENTRY_YEAR
                        AND DISPLAY.ENTRY_KAI = BR18ENTD.ENTRY_KAI
/*END*/
WHERE
     BR18ENTD.ENTRY_CD = /*entryCd*/'05'
/*IF userTypeCd == '02' */
AND BR18ENTD.USERTYPE_CD = '02' 
AND BR18ENTD.DAY_KBN = '03' 
AND (BR18ENTD.FROM_DT <= /*sysDate*/'20060605' AND BR18ENTD.TO_DT >= /*sysDate*/'20060605')
/*END*/
/*IF userTypeCd == '01' */
AND BR18ENTD.USERTYPE_CD = '01' 
AND BR18ENTD.DAY_KBN = '03' 
AND (BR18ENTD.FROM_DT <= /*sysDate*/'20060605' AND BR18ENTD.TO_DT >= /*sysDate*/'20060605')
/*END*/
AND BR17ENTL.SAKUJO_FLG <>'1' 
ORDER BY 
    BR18ENTD.ENTRY_CD, 
    BR18ENTD.ENTRY_YEAR, 
    BR18ENTD.ENTRY_KAI
