SELECT
    BS03.SEIKYU_BNRUI,
    BS03.SHO_CD_JITU,
    SUB3.SHO_NAME_KJ,
    SUM(BS03.SHO_AMOUNT) SHO_AMOUNT,
    SUB2.NISU_NAME ,BS03.NOHIN_TANKA,
    SUM(BS03.URI_KIN + BS03.UCHI_TAX) KINGAKU,
    SUB1.SOGOKEI,
    SUB3.TAX_URI
FROM
    BS03USSR AS BS03
	  LEFT JOIN (SELECT N.NISU_NAME, S.SHO_NISUGATA AS SHO_NISUGATA,
	                    S.SHO_CD_JITU AS SHO_CD_JITU, S.UNIT_KBN
   	             FROM 
   	                (SELECT
                         TM66.SHO_CD_JITU,
                         TM66.SHO_M_DATE,
                         TM66.SHO_NISUGATA,
                         TM66.UNIT_KBN
                     FROM
                        (SELECT
                            SHO_CD_JITU 
                            ,MAX(SHO_M_DATE) AS SHO_M_DATE
                            ,UNIT_KBN
                        FROM
                            TM66SHOK
                        WHERE
                            SHO_M_DATE <= /*URIENDYM*/'20060501'
                        GROUP BY
                            SHO_CD_JITU,
                            UNIT_KBN
                        ) TM
                        INNER JOIN
                            TM66SHOK TM66
                        ON  TM. SHO_CD_JITU = TM66.SHO_CD_JITU
                        AND TM.SHO_M_DATE = TM66.SHO_M_DATE
                        AND TM.UNIT_KBN = TM66.UNIT_KBN) S
   	   			 LEFT JOIN TC03NISU N ON (S.SHO_NISUGATA = N.SHO_NISUGATA)) SUB2
   	  ON (BS03.SHO_CD_JITU = SUB2.SHO_CD_JITU AND BS03.NOHIN_UNIT = SUB2.UNIT_KBN)
   	  LEFT JOIN
            (SELECT
                 MM20.SHO_CD_JITU,
                 MM20.SHO_M_DATE,
                 MM20.SHO_NAME_KJ,
                 MM20.TAX_URI
            FROM
                (SELECT
                    SHO_CD_JITU,
                    MAX(SHO_M_DATE) AS SHO_M_DATE
                FROM
                   MM20SHOM
                WHERE
                   SHO_M_DATE <= /*URIENDYM*/'20060501'
                GROUP BY
                   SHO_CD_JITU
                ) MM
             INNER JOIN
                MM20SHOM MM20
            ON  MM.SHO_CD_JITU = MM20.SHO_CD_JITU
            AND MM.SHO_M_DATE = MM20.SHO_M_DATE) SUB3 ON  (BS03.SHO_CD_JITU = SUB3.SHO_CD_JITU),
	   (SELECT MISE_CD, URIKAKE_YM, SUM(URI_KIN+UCHI_TAX) AS SOGOKEI
   	   FROM BS03USSR
       WHERE
        COMPANY_CD = '00' AND
        MISE_CD = /*MISECD*/'01776' AND
        URIKAKE_YM = /*URIKAKEYM*/'200605'
	   GROUP BY MISE_CD, URIKAKE_YM) SUB1
WHERE
    BS03.COMPANY_CD = '00' AND
    BS03.MISE_CD = /*MISECD*/'01776' AND
    BS03.URIKAKE_YM = /*URIKAKEYM*/'200605' AND
    BS03.MISE_CD = SUB1.MISE_CD AND BS03.URIKAKE_YM = SUB1.URIKAKE_YM

GROUP BY
	BS03.SEIKYU_BNRUI, BS03.SHO_CD_JITU, SUB3.SHO_NAME_KJ,
	SUB2.NISU_NAME ,BS03.NOHIN_TANKA, SUB1.SOGOKEI,SUB3.TAX_URI
ORDER BY
	BS03.SEIKYU_BNRUI
