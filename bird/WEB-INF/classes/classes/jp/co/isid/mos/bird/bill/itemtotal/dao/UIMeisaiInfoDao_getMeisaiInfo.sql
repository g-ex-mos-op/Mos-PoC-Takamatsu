SELECT
	BT37.NOHIN_DT_J,
	BT37.DENPYO_NO,
	BT37.SHO_AMOUNT,
	TC03.NISU_NAME,
	BT37.NOHIN_TANKA,
	BT37.URI_KIN,
	BT37.TEKIYOU,
	BT37.UCHI_TAX,
	BT37.SOTO_TAX,
    SUB3.SHO_NAME_KJ,
    SUB4.SHO_IRIME
FROM
    BT37URTR AS BT37
    	LEFT JOIN
    	      (SELECT
                	MM20.SHO_CD_JITU,
                 	MM20.SHO_NAME_KJ,
                 	MM20.SHO_M_DATE
            	FROM
                	(SELECT
                    	SHO_CD_JITU,
                    	MAX(SHO_M_DATE) AS SHO_M_DATE
                	FROM
                   		MM20SHOM
                	WHERE
                   		SHO_M_DATE  <= /*URIENDYM*/'20140501'
                	GROUP BY
                   		SHO_CD_JITU
                ) MM
                INNER JOIN MM20SHOM MM20
            	ON  MM.SHO_CD_JITU = MM20.SHO_CD_JITU
            	AND MM.SHO_M_DATE = MM20.SHO_M_DATE) AS SUB3
              ON   (BT37.SHO_CD_JITU = SUB3.SHO_CD_JITU)
    	LEFT JOIN
              (SELECT
                  TM66.SHO_CD_JITU
                  ,TM66.SHO_M_DATE
                  ,TM66.SHO_NISUGATA
                  ,TM66.UNIT_KBN
                  ,TM66.SHO_IRIME
              FROM
                  (
                   SELECT
                        SHO_CD_JITU
                        ,MAX( SHO_M_DATE ) AS SHO_M_DATE
                        ,UNIT_KBN
                   FROM
                        TM66SHOK
                   WHERE
                        SHO_M_DATE <= /*URIENDYM*/'20140510'
                   GROUP BY
                        SHO_CD_JITU
                       ,UNIT_KBN
                   ) TM
                  INNER JOIN
                       TM66SHOK TM66
                  ON  TM.SHO_CD_JITU = TM66.SHO_CD_JITU
                  AND TM.SHO_M_DATE = TM66.SHO_M_DATE
                  AND TM.UNIT_KBN = TM66.UNIT_KBN)  AS SUB4 ON (SUB3.SHO_CD_JITU = SUB4.SHO_CD_JITU AND BT37.NOHIN_UNIT = SUB4.UNIT_KBN)
    	LEFT JOIN TC03NISU AS TC03 ON (SUB4.SHO_NISUGATA = TC03.SHO_NISUGATA)
  , BM01TENM AS BM01
WHERE
    BT37.COMPANY_CD = '00' AND
    BM01.COMPANY_CD = '00' AND
    BT37.MISE_CD = /*MISECD*/'01962' AND
    BT37.SHO_CD_JITU = /*SHOJITUCD*/'05751' AND
    BT37.URIKAKE_YM = /*CONDYM*/'200607' AND
    BT37.MISE_CD = BM01.MISE_CD AND
    BM01.ONER_CD = (SELECT ONER_CD
  				 	FROM BM01TENM
 					WHERE MISE_CD = /*MISECD*/'01962'
    					AND  COMPANY_CD = '00')
ORDER BY
	BT37.NOHIN_DT_J
