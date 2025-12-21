WITH OPENAREA AS (
          SELECT  BM01.COMPANY_CD
               	, BM01.AREA_DAI AS KOBETSU_CD
                , BM01.GYOTAI_KBN
          FROM BM01TENM AS BM01
          LEFT JOIN (SELECT COMPANY_CD,ONER_CD FROM BM06UONR
          WHERE USER_ID = /*userId*/'99990001'
          ) BM06
          ON (BM06.COMPANY_CD = BM01.COMPANY_CD)
          LEFT JOIN (SELECT COMPANY_CD,MISE_CD FROM BM07UTEN
          WHERE USER_ID = /*userId*/'99990001'
          ) BM07
          ON (BM07.COMPANY_CD = BM01.COMPANY_CD)
          WHERE (BM06.ONER_CD    = BM01.ONER_CD  OR BM07.MISE_CD    = BM01.MISE_CD)
          AND   BM01.CLOSE_DT >  /*sysDate*/''
          GROUP BY
                BM01.COMPANY_CD ,
                BM01.AREA_DAI   ,
                BM01.GYOTAI_KBN
          UNION ALL
                   SELECT  BM01.COMPANY_CD
               	,      BM01.MISE_CD AS KOBETSU_CD
               	,      BM01.GYOTAI_KBN
               	FROM BM01TENM AS BM01
               	LEFT JOIN (SELECT COMPANY_CD,ONER_CD FROM BM06UONR
               	           WHERE USER_ID =/*userId*/'99990001'
               	           ) BM06
               	   ON (BM06.COMPANY_CD = BM01.COMPANY_CD)
               	LEFT JOIN (SELECT COMPANY_CD,MISE_CD FROM BM07UTEN
               	           WHERE USER_ID = /*userId*/'99990001'
               	           ) BM07
               	   ON (BM07.COMPANY_CD = BM01.COMPANY_CD)
               	WHERE (BM06.ONER_CD    = BM01.ONER_CD  OR BM07.MISE_CD    = BM01.MISE_CD)

                   AND   BM01.CLOSE_DT >  /*sysDate*/''
                GROUP BY
                  BM01.COMPANY_CD ,
                  BM01.MISE_CD    ,
                 BM01.GYOTAI_KBN)
SELECT DISTINCT
       BT11.INFO_SHU AS MOTO_INFO_SHU
,      BT11.REG_DATE AS MOTO_REG_DATE
,      BT11.SEQ AS MOTO_SEQ
,      BT11.REL_INFO_SHU AS INFO_SHU
,      BT11.REL_REG_DATE AS REG_DATE
,      BT11.REL_SEQ AS SEQ
,      BT020304.TITLE
,      BT020304.FILE_NAME
,      (CASE WHEN SORT_TBL.SORT_KEY_BM02 IS NOT NULL THEN SORT_TBL.SORT_KEY_BM02 ELSE '000' END) AS SORT_KEY_CATE
,      (CASE WHEN SORT_TBL.SORT_KEY_BM17 IS NOT NULL THEN SORT_TBL.SORT_KEY_BM17 ELSE '000' END) AS SORT_KEY_SUB_CATE

FROM
      (SELECT
              BT12.INFO_SHU
             ,BT12.REG_DATE
             ,BT12.SEQ
       FROM (
             SELECT
                 BT111213.R_COMPANY_CD,
                 BT111213.INFO_SHU    ,
                 BT111213.REG_DATE    ,
                 BT111213.SEQ         ,
                 BT111213.SHOZOKU_KBN
             FROM
             (SELECT
                 BT12.R_COMPANY_CD,
                 BT12.INFO_SHU    ,
                 BT12.REG_DATE    ,
                 BT12.SEQ         ,
                 BT13.SHOZOKU_KBN
                 FROM
                     (SELECT
                         REL_INFO_SHU ,
                         REL_REG_DATE ,
                         REL_SEQ
	                  FROM BT11RLFL
	                  WHERE INFO_SHU = /*infoShu*/'02'
	                   /*IF regDate != null */
		              AND   REG_DATE = /*regDate*/'20060126'
		               /*END*/
		               /*IF seq != null */
		              AND   SEQ      = /*seq*/'0002'
		              /*END*/
	                  ) BT11,
       (SELECT
              BT12.R_COMPANY_CD,
              BT12.INFO_SHU,
              BT12.REG_DATE,
              BT12.SEQ
        FROM BT12IACP BT12
        WHERE EXISTS
              (SELECT *
               FROM BM03USCP BM03
               WHERE
                   BM03.R_COMPANY_CD = BT12.R_COMPANY_CD
                   AND BM03.USER_ID = /*userId*/'99990001')) AS BT12,
       (SELECT
             BT13.INFO_SHU,
             BT13.REG_DATE,
             BT13.SEQ,
             BT13.SHOZOKU_KBN
        FROM BT13IASZ BT13
        WHERE EXISTS
              (SELECT *
              FROM BM13SHKM BM13
              WHERE
                  BM13.SHOZOKU_KBN = BT13.SHOZOKU_KBN
                  AND BM13.USER_ID = /*userId*/'99990001')) AS BT13
WHERE BT12.INFO_SHU = BT11.REL_INFO_SHU
                    AND   BT12.REG_DATE = BT11.REL_REG_DATE
                    AND   BT12.SEQ      = BT11.REL_SEQ
                    AND   BT13.INFO_SHU = BT12.INFO_SHU
                    AND   BT13.REG_DATE = BT12.REG_DATE
                    AND   BT13.SEQ      = BT12.SEQ) BT111213
LEFT JOIN OPENAREA ON (OPENAREA.COMPANY_CD = BT111213.R_COMPANY_CD)
LEFT JOIN (
       SELECT
            BT14.INFO_SHU   ,
            BT14.REG_DATE   ,
            BT14.SEQ        ,
            BT14.GYOTAI_KBN ,
            BT14.KOBETSU_FLG,
            BT14.MISE_FLG   ,
            BT15.KOBETSU_CD ,
            BT16.MISE_CD
       FROM
         (SELECT
                BT14.INFO_SHU,
                BT14.REG_DATE,
                BT14.SEQ,BT14.GYOTAI_KBN,
                BT14.KOBETSU_FLG,
                BT14.MISE_FLG
          FROM BT14IAGT BT14
          WHERE EXISTS
                (SELECT *
                 FROM BM05USGT BM05
                 WHERE BM05.GYOTAI_KBN = BT14.GYOTAI_KBN
                 AND BM05.USER_ID = /*userId*/'99990001')) AS BT14
 LEFT JOIN BT15IAID AS BT15
          ON (BT14.INFO_SHU = BT15.INFO_SHU
          AND BT14.REG_DATE = BT15.REG_DATE
          AND BT14.SEQ = BT15.SEQ
          AND BT14.GYOTAI_KBN = BT15.GYOTAI_KBN
            )
LEFT JOIN (
          SELECT BT16.INFO_SHU
           ,      BT16.REG_DATE
           ,      BT16.SEQ
           ,      BT16.GYOTAI_KBN
           ,      BT16.MISE_CD
           FROM BM01TENM BM01
           ,    BM04GTCP BM04
           ,    BT16IAST BT16
           WHERE BM04.COMPANY_CD = BM01.COMPANY_CD
           AND   BM04.GYOTAI_KBN = BM01.MISE_GRP1
           AND   BT16.GYOTAI_KBN = BM04.GYOTAI_KBN
           AND   BT16.MISE_CD    = BM01.MISE_CD ) AS BT16
           ON (BT14.INFO_SHU = BT16.INFO_SHU
           AND BT14.REG_DATE = BT16.REG_DATE
           AND BT14.SEQ = BT16.SEQ
           AND BT14.GYOTAI_KBN = BT16.GYOTAI_KBN
            )
) AS BT141516 ON
    BT141516.INFO_SHU = BT111213.INFO_SHU    AND
    BT141516.REG_DATE = BT111213.REG_DATE    AND
    BT141516.SEQ  = BT111213.SEQ

WHERE (OPENAREA.COMPANY_CD IS NULL
           OR (BT141516.KOBETSU_FLG = '0' AND BT141516.MISE_FLG = '0')
           OR (OPENAREA.GYOTAI_KBN = BT141516.GYOTAI_KBN
                AND ((BT141516.KOBETSU_FLG = '1' AND OPENAREA.KOBETSU_CD = BT141516.KOBETSU_CD)
                      OR (BT141516.MISE_FLG = '1' AND OPENAREA.KOBETSU_CD = BT141516.MISE_CD)
                    )
              )
         )
) BT12
GROUP BY
       BT12.INFO_SHU
,      BT12.REG_DATE
,      BT12.SEQ
) REL_ACSS,
 (SELECT DISTINCT BT12.INFO_SHU,
       BT12.REG_DATE,
       BT12.SEQ
  FROM
  (SELECT DISTINCT
            BT0203041213.R_COMPANY_CD,
            BT0203041213.INFO_SHU    ,
            BT0203041213.REG_DATE    ,
            BT0203041213.SEQ         ,
            BT0203041213.SHOZOKU_KBN
   FROM
       (SELECT
             BT12.R_COMPANY_CD,
             BT12.INFO_SHU    ,
             BT12.REG_DATE    ,
             BT12.SEQ         ,
             BT13.SHOZOKU_KBN
        FROM(
		   SELECT REG_DATE, SEQ
	   	/*IF infoShu == '01'*/
	   ,      '01' AS INFO_SHU
	   FROM BT01INFM
	   WHERE SAKUJO_FLG <> '1'
	   /*IF pubDtFrom != null && pubDtTo != null */
	    AND PUB_DATE between /*pubDtFrom*/'20060101' and /*pubDtTo*/'20060699'
	   /*END*/
	/*END*/
	/*IF infoShu == '02'*/
	   ,      '02' AS INFO_SHU
	   FROM BT02NTCM
	   WHERE SAKUJO_FLG <> '1'
	   /*IF pubDateTutatu != null */
	    AND PUB_DATE <= /*pubDateTutatu*/'20091209'
	   /*END*/
	   /*IF pubDtFrom != null && pubDtTo != null */
	    AND PUB_DATE between /*pubDtFrom*/'20060101' and /*pubDtTo*/'20060699'
	   /*END*/
	/*END*/
	/*IF infoShu == '03'*/
	   ,      '03' AS INFO_SHU
	   FROM BT03DOCM
	   WHERE SAKUJO_FLG <> '1'
	   /*IF pubDate != null */
	   AND PUB_DATE_FROM <= /*pubDate*/'20091209'
	   AND PUB_DATE_TO >= /*pubDate*/'20091209'
	   /*END*/
	   /*IF pubDtFrom != null && pubDtTo != null */
	    AND PUB_DATE_FROM between /*pubDtFrom*/'20060101' and /*pubDtTo*/'20060699'
	    AND PUB_DATE_TO between /*pubDtFrom*/'20060101' and /*pubDtTo*/'20060699'
	   /*END*/
	/*END*/
	/*IF infoShu == '04'*/
	   ,      '04' AS INFO_SHU
	   FROM BT04FRMM
	   WHERE SAKUJO_FLG <> '1'
	   /*IF pubDate != null */
	   AND PUB_DATE_FROM <= /*pubDate*/'20091209'
	   AND PUB_DATE_TO >= /*pubDate*/'20091209'
	   /*END*/
	   /*IF pubDtFrom != null && pubDtTo != null */
	    AND PUB_DATE_FROM between /*pubDtFrom*/'20060101' and /*pubDtTo*/'20060699'
	    AND PUB_DATE_TO between /*pubDtFrom*/'20060101' and /*pubDtTo*/'20060699'
	   /*END*/
	/*END*/
	/*IF regDate != null */
	AND   REG_DATE = /*regDate*/'20060126'
	/*END*/
	/*IF seq != null */
	AND   SEQ      = /*seq*/'0002'
	/*END*/
    /*IF title != null*/AND TITLE like /*title*/'%' /*END*/
	/*IF searchEnginFileList != null*/
	AND concat(REG_DATE, SEQ) in /*searchEnginFileList*/('')
	/*END*/) BT020304,
	 (SELECT
	      BT12.R_COMPANY_CD,
	      BT12.INFO_SHU,
	      BT12.REG_DATE,
	      BT12.SEQ
	  FROM BT12IACP BT12
	  WHERE EXISTS
	        (SELECT * FROM
	                BM03USCP BM03
	         WHERE BM03.R_COMPANY_CD = BT12.R_COMPANY_CD
	         AND BM03.USER_ID = /*userId*/'99990001'
	         AND BT12.INFO_SHU =/*infoShu*/'02')) AS BT12
     , (SELECT
            BT13.INFO_SHU,
            BT13.REG_DATE,
            BT13.SEQ,
            BT13.SHOZOKU_KBN
        FROM BT13IASZ BT13
        WHERE EXISTS
              (SELECT * FROM
                      BM13SHKM BM13
               WHERE BM13.SHOZOKU_KBN = BT13.SHOZOKU_KBN
               AND BM13.USER_ID = /*userId*/'99990001'
               AND BT13.INFO_SHU = /*infoShu*/'02')) AS BT13
    WHERE BT12.INFO_SHU = BT020304.INFO_SHU
    AND   BT12.REG_DATE = BT020304.REG_DATE
    AND   BT12.SEQ      = BT020304.SEQ
    AND   BT13.INFO_SHU = BT12.INFO_SHU
    AND   BT13.REG_DATE = BT12.REG_DATE
    AND   BT13.SEQ      = BT12.SEQ)  BT0203041213
LEFT JOIN OPENAREA ON (OPENAREA.COMPANY_CD = BT0203041213.R_COMPANY_CD)
LEFT JOIN (SELECT  DISTINCT
                    BT14.INFO_SHU   ,
                    BT14.REG_DATE   ,
                    BT14.SEQ        ,
                    BT14.GYOTAI_KBN ,
                    BT14.KOBETSU_FLG,
                    BT14.MISE_FLG   ,
                    BT15.KOBETSU_CD ,
                    BT16.MISE_CD
          FROM
             (SELECT
                   BT14.INFO_SHU,
                   BT14.REG_DATE,
                   BT14.SEQ,
                   BT14.GYOTAI_KBN,
                   BT14.KOBETSU_FLG,
                   BT14.MISE_FLG
               FROM
                   BT14IAGT BT14
               WHERE EXISTS
                   (SELECT * FROM
                         BM05USGT BM05
                    WHERE
                         BM05.GYOTAI_KBN = BT14.GYOTAI_KBN AND BM05.USER_ID = /*userId*/'99990001'
                         AND BT14.INFO_SHU = /*infoShu*/'02')) AS BT14
LEFT JOIN BT15IAID AS BT15
             ON (BT14.INFO_SHU = BT15.INFO_SHU
             AND BT14.REG_DATE = BT15.REG_DATE
             AND BT14.SEQ = BT15.SEQ
             AND BT14.GYOTAI_KBN = BT15.GYOTAI_KBN
             AND BT15.INFO_SHU = /*infoShu*/'02'
            )
LEFT JOIN (SELECT
               BT16.INFO_SHU
        ,      BT16.REG_DATE
        ,      BT16.SEQ
        ,      BT16.GYOTAI_KBN
        ,      BT16.MISE_CD
           FROM
               BM01TENM BM01
           ,   BM04GTCP BM04
           ,  (SELECT
                    INFO_SHU,
                    REG_DATE,SEQ,GYOTAI_KBN,
                    MISE_CD
                FROM
                     BT16IAST
                WHERE
                     INFO_SHU = /*infoShu*/'02'
                /*IF regDate != null */
	             AND   REG_DATE = /*regDate*/'20100302'
	            /*END*/
				/*IF seq != null */
				 AND   SEQ      = /*seq*/'0002'
				 /*END*/) BT16
           WHERE BM04.COMPANY_CD = BM01.COMPANY_CD
           AND   BM04.GYOTAI_KBN = BM01.MISE_GRP1
           AND   BT16.GYOTAI_KBN = BM04.GYOTAI_KBN
           AND   BT16.MISE_CD    = BM01.MISE_CD
       ) AS BT16
         ON (BT14.INFO_SHU = BT16.INFO_SHU
          AND BT14.REG_DATE = BT16.REG_DATE
          AND BT14.SEQ = BT16.SEQ
          AND BT14.GYOTAI_KBN = BT16.GYOTAI_KBN
            )
) AS BT141516 ON
    BT141516.INFO_SHU = BT0203041213.INFO_SHU    AND
    BT141516.REG_DATE = BT0203041213.REG_DATE    AND
    BT141516.SEQ  = BT0203041213.SEQ

WHERE (OPENAREA.COMPANY_CD IS NULL
           OR (BT141516.KOBETSU_FLG = '0' AND BT141516.MISE_FLG = '0')
           OR (OPENAREA.GYOTAI_KBN = BT141516.GYOTAI_KBN
                AND ((BT141516.KOBETSU_FLG = '1' AND OPENAREA.KOBETSU_CD = BT141516.KOBETSU_CD)
                      OR (BT141516.MISE_FLG = '1' AND OPENAREA.KOBETSU_CD = BT141516.MISE_CD)
                    )
              )
         )
) BT12
) ACSS
,  (
   SELECT CATE_ID, SUB_CATE_ID, '02' AS INFO_SHU, REG_DATE, SEQ, TITLE, FILE_NAME
   FROM BT02NTCM
   WHERE SAKUJO_FLG <> '1'
      /*IF PUBDATETUTATU != NULL */
   AND   PUB_DATE <= /*PUBDATETUTATU*/'20091209'
   /*END*/
 UNION
   SELECT CATE_ID, SUB_CATE_ID, '03' AS INFO_SHU, REG_DATE, SEQ, TITLE, FILE_NAME
   FROM BT03DOCM
   WHERE SAKUJO_FLG <> '1'
   /*IF pubDate != null */
   AND   PUB_DATE_FROM <= /*pubDate*/'20091209'
   AND   PUB_DATE_TO >= /*pubDate*/'20091209'
   /*END*/
 UNION
   SELECT CATE_ID, SUB_CATE_ID, '04' AS INFO_SHU, REG_DATE, SEQ, TITLE, FILE_NAME
   FROM BT04FRMM
   WHERE SAKUJO_FLG <> '1'
   /*IF pubDate != null */
   AND    PUB_DATE_FROM <= /*pubDate*/'20091209'
   AND    PUB_DATE_TO >= /*pubDate*/'20091209'
   /*END*/
) BT020304
LEFT JOIN (
      SELECT BM17.CATE_ID , BM17.SUB_CATE_ID, BM17.INFO_SHU
      ,      BM02.SORT_KEY AS SORT_KEY_BM02
      ,      BM17.SORT_KEY AS SORT_KEY_BM17
      FROM BM02IFCT BM02
       ,     BM17SBCT BM17
      WHERE  BM17.INFO_SHU = BM02.INFO_SHU AND BM17.CATE_ID = BM02.CATE_ID) SORT_TBL
ON (BT020304.INFO_SHU = SORT_TBL.INFO_SHU
AND   BT020304.CATE_ID = SORT_TBL.CATE_ID
AND   BT020304.SUB_CATE_ID = SORT_TBL.SUB_CATE_ID)
,     ( SELECT
            INFO_SHU,
            REG_DATE,
            SEQ,REL_INFO_SHU,
            REL_REG_DATE,
            REL_SEQ
        FROM
            BT11RLFL BT11
        WHERE BT11.INFO_SHU = /*infoShu*/'02'
        /*IF regDate != null */
		AND   BT11.REG_DATE = /*regDate*/'20060126'
	    /*END*/
		/*IF seq != null */
		AND   BT11.SEQ      = /*seq*/'0002'
		/*END*/) BT11
WHERE BT11.INFO_SHU = ACSS.INFO_SHU
AND   BT11.REG_DATE = ACSS.REG_DATE
AND   BT11.SEQ      = ACSS.SEQ
AND   BT020304.INFO_SHU = REL_ACSS.INFO_SHU
AND   BT020304.REG_DATE = REL_ACSS.REG_DATE
AND   BT020304.SEQ      = REL_ACSS.SEQ
AND   BT020304.INFO_SHU = BT11.REL_INFO_SHU
AND   BT020304.REG_DATE = BT11.REL_REG_DATE
AND   BT020304.SEQ      = BT11.REL_SEQ

ORDER BY BT11.REG_DATE
,     BT11.SEQ
