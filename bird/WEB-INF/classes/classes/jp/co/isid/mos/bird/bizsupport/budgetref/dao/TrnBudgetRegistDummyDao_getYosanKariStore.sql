SELECT DISTINCT
       YOSAN_KARIMISE4
  ,    YOSAN_KARIMISE5
  ,    YOSAN_KARIMISE6
  ,    YOSAN_KARIMISE7
  ,    YOSAN_KARIMISE8
  ,    YOSAN_KARIMISE9
  ,    YOSAN_KARIMISE10
  ,    YOSAN_KARIMISE11
  ,    YOSAN_KARIMISE12
  ,    YOSAN_KARIMISE1
  ,    YOSAN_KARIMISE2
  ,    YOSAN_KARIMISE3
  ,    BT42.SIBU_CD
  ,    rtrim(BT42.SIBU_NAME) as SIBU_NAME
FROM   (SELECT DISTINCT BT42MSYD.SIBU_CD
           ,   SIBU_NAME
        FROM   BT42MSYD BT42MSYD
         LEFT JOIN    (SELECT COMPANY_CD
                          ,   SIBU_CD
                          ,   SIBU_NAME
                       FROM BM57NSIB
                      WHERE COMPANY_CD = /*companyCd*/'00') BM57
               ON     (BT42MSYD.COMPANY_CD = BM57.COMPANY_CD AND BT42MSYD.SIBU_CD = BM57.SIBU_CD)
        WHERE BT42MSYD.COMPANY_CD =/*companyCd*/'00'
        AND   SAKUJO_KBN <> '1'
        AND   YOSAN_DT BETWEEN /*dto.yosanYm4*/'200704' AND /*dto.yosanYm3*/'200803') BT42

LEFT JOIN (SELECT SIBU_CD
              ,   SUM(YOSAN) AS YOSAN_KARIMISE4
             FROM BT42MSYD
            WHERE MISE_CD LIKE 'X%'
            AND   YOSAN_DT = /*dto.yosanYm4*/'200704'
            AND   COMPANY_CD = /*companyCd*/'00'
            AND   SAKUJO_KBN <> '1'
            GROUP BY SIBU_CD) SIBU4
       ON (BT42.SIBU_CD = SIBU4.SIBU_CD)

LEFT JOIN (SELECT SIBU_CD
              ,   SUM(YOSAN) AS YOSAN_KARIMISE5
             FROM BT42MSYD BT42
            WHERE MISE_CD LIKE 'X%'
            AND   YOSAN_DT = /*dto.yosanYm5*/'200705'
            AND   COMPANY_CD = /*companyCd*/'00'
            AND   SAKUJO_KBN <> '1'
            GROUP BY SIBU_CD) SIBU5
       ON (BT42.SIBU_CD = SIBU5.SIBU_CD)

LEFT JOIN (SELECT SIBU_CD
              ,   SUM(YOSAN) AS YOSAN_KARIMISE6
             FROM BT42MSYD
            WHERE MISE_CD LIKE 'X%'
            AND   YOSAN_DT = /*dto.yosanYm6*/'200706'
            AND   COMPANY_CD = /*companyCd*/'00'
            AND   SAKUJO_KBN <> '1'
            GROUP BY SIBU_CD) SIBU6
       ON (BT42.SIBU_CD = SIBU6.SIBU_CD)

LEFT JOIN (SELECT SIBU_CD
              ,   SUM(YOSAN) AS YOSAN_KARIMISE7
             FROM BT42MSYD
            WHERE MISE_CD LIKE 'X%'
            AND   YOSAN_DT = /*dto.yosanYm7*/'200707'
            AND   COMPANY_CD = /*companyCd*/'00'
            AND   SAKUJO_KBN <> '1'
            GROUP BY SIBU_CD) SIBU7
       ON (BT42.SIBU_CD = SIBU7.SIBU_CD)

LEFT JOIN (SELECT SIBU_CD
              ,   SUM(YOSAN) AS YOSAN_KARIMISE8
             FROM BT42MSYD
            WHERE MISE_CD LIKE 'X%'
            AND   YOSAN_DT = /*dto.yosanYm8*/'200708'
            AND   COMPANY_CD = /*companyCd*/'00'
            AND   SAKUJO_KBN <> '1'
            GROUP BY SIBU_CD) SIBU8
       ON (BT42.SIBU_CD = SIBU8.SIBU_CD)

LEFT JOIN (SELECT SIBU_CD
              ,   SUM(YOSAN) AS YOSAN_KARIMISE9
             FROM BT42MSYD
            WHERE MISE_CD LIKE 'X%'
            AND   YOSAN_DT = /*dto.yosanYm9*/'200709'
            AND   COMPANY_CD = /*companyCd*/'00'
            AND   SAKUJO_KBN <> '1'
            GROUP BY SIBU_CD) SIBU9
       ON (BT42.SIBU_CD = SIBU9.SIBU_CD)

LEFT JOIN (SELECT SIBU_CD
              ,   SUM(YOSAN) AS YOSAN_KARIMISE10
             FROM BT42MSYD
            WHERE MISE_CD LIKE 'X%'
            AND   YOSAN_DT = /*dto.yosanYm10*/'200710'
            AND   COMPANY_CD = /*companyCd*/'00'
            AND   SAKUJO_KBN <> '1'
            GROUP BY SIBU_CD) SIBU10
       ON (BT42.SIBU_CD = SIBU10.SIBU_CD)

LEFT JOIN (SELECT SIBU_CD
              ,   SUM(YOSAN) AS YOSAN_KARIMISE11
             FROM BT42MSYD
            WHERE MISE_CD LIKE 'X%'
            AND   YOSAN_DT = /*dto.yosanYm11*/'200711'
            AND   COMPANY_CD = /*companyCd*/'00'
            AND   SAKUJO_KBN <> '1'
            GROUP BY SIBU_CD) SIBU11
       ON (BT42.SIBU_CD = SIBU11.SIBU_CD)

LEFT JOIN (SELECT SIBU_CD
              ,   SUM(YOSAN) AS YOSAN_KARIMISE12
             FROM BT42MSYD
            WHERE MISE_CD LIKE 'X%'
            AND   YOSAN_DT = /*dto.yosanYm12*/'200712'
            AND   COMPANY_CD = /*companyCd*/'00'
            AND   SAKUJO_KBN <> '1'
            GROUP BY SIBU_CD) SIBU12
       ON (BT42.SIBU_CD = SIBU12.SIBU_CD)

LEFT JOIN (SELECT SIBU_CD
              ,   SUM(YOSAN) AS YOSAN_KARIMISE1
             FROM BT42MSYD
            WHERE MISE_CD LIKE 'X%'
            AND   YOSAN_DT = /*dto.yosanYm1*/'200801'
            AND   COMPANY_CD = /*companyCd*/'00'
            AND   SAKUJO_KBN <> '1'
            GROUP BY SIBU_CD) SIBU1
       ON (BT42.SIBU_CD = SIBU1.SIBU_CD)

LEFT JOIN (SELECT SIBU_CD
              ,   SUM(YOSAN) AS YOSAN_KARIMISE2
             FROM BT42MSYD
            WHERE MISE_CD LIKE 'X%'
            AND   YOSAN_DT = /*dto.yosanYm2*/'200802'
            AND   COMPANY_CD = /*companyCd*/'00'
            AND   SAKUJO_KBN <> '1'
            GROUP BY SIBU_CD) SIBU2
       ON (BT42.SIBU_CD = SIBU2.SIBU_CD)

LEFT JOIN (SELECT SIBU_CD
              ,   SUM(YOSAN) AS YOSAN_KARIMISE3
             FROM BT42MSYD
            WHERE MISE_CD LIKE 'X%'
            AND   YOSAN_DT = /*dto.yosanYm3*/'200803'
            AND   COMPANY_CD = /*companyCd*/'00'
            AND   SAKUJO_KBN <> '1'
            GROUP BY SIBU_CD) SIBU3
       ON (BT42.SIBU_CD = SIBU3.SIBU_CD)

ORDER BY BT42.SIBU_CD