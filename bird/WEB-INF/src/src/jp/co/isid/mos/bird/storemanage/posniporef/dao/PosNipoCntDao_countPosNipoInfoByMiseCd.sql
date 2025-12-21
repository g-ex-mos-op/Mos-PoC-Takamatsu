SELECT
    count(*) AS TENPO_CNT
FROM (
    SELECT
        MISE_CD
    FROM
        BT63SNIP BT63 
    WHERE
          BT63.COMPANY_CD = /*companyCd*/'00'
      AND BT63.MISE_CD    = /*miseCd*/'00744'
      AND BT63.EIGYO_DT >=  /*startYmd*/'20061201'
      AND BT63.EIGYO_DT <=  /*endYmd*/'20061231'

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
