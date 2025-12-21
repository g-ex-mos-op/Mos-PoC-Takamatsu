SELECT
    count(*) AS TENPO_CNT
FROM (
    SELECT
        BT63.MISE_CD
    FROM
        BT63SNIP BT63 
        INNER JOIN BM01TENM BM01 ON 
        (BT63.COMPANY_CD = BM01.COMPANY_CD and BT63.MISE_CD = BM01.MISE_CD)
    WHERE
          BT63.COMPANY_CD = /*companyCd*/'00'
      AND BT63.EIGYO_DT >=  /*startYmd*/'20061201'
      AND BT63.EIGYO_DT <=  /*endYmd*/'20061231'
      AND BM01.ONER_CD    = /*onerCd*/'38001'

      /*IF limitFlg==true */
      AND BT63.MISE_CD IN ( 
          SELECT 
              BM50.MISE_CD
          FROM
              BM50TANM BM50 
          WHERE
              BM50.COMPANY_CD = /*companyCd*/'00' AND
              BM50.SV_CD = /*userId*/'00000121'
          GROUP BY
              BM50.MISE_CD 
          )
      /*END*/

    GROUP BY
        BT63.MISE_CD
) SUB1
