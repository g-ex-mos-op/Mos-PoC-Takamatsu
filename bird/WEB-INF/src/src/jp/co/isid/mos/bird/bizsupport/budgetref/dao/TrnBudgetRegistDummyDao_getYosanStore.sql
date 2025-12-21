SELECT DISTINCT
       YOSAN_ALL4
  ,    YOSAN_ALL5
  ,    YOSAN_ALL6
  ,    YOSAN_ALL7
  ,    YOSAN_ALL8
  ,    YOSAN_ALL9
  ,    YOSAN_ALL10
  ,    YOSAN_ALL11
  ,    YOSAN_ALL12
  ,    YOSAN_ALL1
  ,    YOSAN_ALL2
  ,    YOSAN_ALL3
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
        AND SAKUJO_KBN <> '1'
        AND   YOSAN_DT BETWEEN /*dto.yosanYm4*/'200704' AND /*dto.yosanYm3*/'200803') BT42

LEFT JOIN (SELECT SIBU_CD
              ,   SUM(YOSAN) AS YOSAN_ALL4
             FROM BT42MSYD
            WHERE YOSAN_DT = /*dto.yosanYm4*/'200704'
            AND   COMPANY_CD = /*companyCd*/'00'
            AND   SAKUJO_KBN <> '1'
            GROUP BY SIBU_CD) SIBU4
       ON (BT42.SIBU_CD = SIBU4.SIBU_CD)

LEFT JOIN (SELECT SIBU_CD
              ,   SUM(YOSAN) AS YOSAN_ALL5
             FROM BT42MSYD BT42
            WHERE YOSAN_DT = /*dto.yosanYm5*/'200705'
            AND   COMPANY_CD = /*companyCd*/'00'
            AND   SAKUJO_KBN <> '1'
            GROUP BY SIBU_CD) SIBU5
       ON (BT42.SIBU_CD = SIBU5.SIBU_CD)

LEFT JOIN (SELECT SIBU_CD
              ,   SUM(YOSAN) AS YOSAN_ALL6
             FROM BT42MSYD
            WHERE YOSAN_DT = /*dto.yosanYm6*/'200706'
            AND   COMPANY_CD = /*companyCd*/'00'
            AND   SAKUJO_KBN <> '1'
            GROUP BY SIBU_CD) SIBU6
       ON (BT42.SIBU_CD = SIBU6.SIBU_CD)

LEFT JOIN (SELECT SIBU_CD
              ,   SUM(YOSAN) AS YOSAN_ALL7
             FROM BT42MSYD
            WHERE YOSAN_DT = /*dto.yosanYm7*/'200707'
            AND   COMPANY_CD = /*companyCd*/'00'
            AND   SAKUJO_KBN <> '1'
            GROUP BY SIBU_CD) SIBU7
       ON (BT42.SIBU_CD = SIBU7.SIBU_CD)

LEFT JOIN (SELECT SIBU_CD
              ,   SUM(YOSAN) AS YOSAN_ALL8
             FROM BT42MSYD
            WHERE YOSAN_DT = /*dto.yosanYm8*/'200708'
            AND   COMPANY_CD = /*companyCd*/'00'
            AND   SAKUJO_KBN <> '1'
            GROUP BY SIBU_CD) SIBU8
       ON (BT42.SIBU_CD = SIBU8.SIBU_CD)

LEFT JOIN (SELECT SIBU_CD
              ,   SUM(YOSAN) AS YOSAN_ALL9
             FROM BT42MSYD
            WHERE YOSAN_DT = /*dto.yosanYm9*/'200709'
            AND   COMPANY_CD = /*companyCd*/'00'
            AND   SAKUJO_KBN <> '1'
            GROUP BY SIBU_CD) SIBU9
       ON (BT42.SIBU_CD = SIBU9.SIBU_CD)

LEFT JOIN (SELECT SIBU_CD
              ,   SUM(YOSAN) AS YOSAN_ALL10
             FROM BT42MSYD
            WHERE YOSAN_DT = /*dto.yosanYm10*/'200710'
            AND   COMPANY_CD = /*companyCd*/'00'
            AND   SAKUJO_KBN <> '1'
            GROUP BY SIBU_CD) SIBU10
       ON (BT42.SIBU_CD = SIBU10.SIBU_CD)

LEFT JOIN (SELECT SIBU_CD
              ,   SUM(YOSAN) AS YOSAN_ALL11
             FROM BT42MSYD
            WHERE YOSAN_DT = /*dto.yosanYm11*/'200711'
            AND   COMPANY_CD = /*companyCd*/'00'
            AND   SAKUJO_KBN <> '1'
            GROUP BY SIBU_CD) SIBU11
       ON (BT42.SIBU_CD = SIBU11.SIBU_CD)

LEFT JOIN (SELECT SIBU_CD
              ,   SUM(YOSAN) AS YOSAN_ALL12
             FROM BT42MSYD
            WHERE YOSAN_DT = /*dto.yosanYm12*/'200712'
            AND   COMPANY_CD = /*companyCd*/'00'
            AND   SAKUJO_KBN <> '1'
            GROUP BY SIBU_CD) SIBU12
       ON (BT42.SIBU_CD = SIBU12.SIBU_CD)

LEFT JOIN (SELECT SIBU_CD
              ,   SUM(YOSAN) AS YOSAN_ALL1
             FROM BT42MSYD
            WHERE YOSAN_DT = /*dto.yosanYm1*/'200801'
            AND   COMPANY_CD = /*companyCd*/'00'
            AND   SAKUJO_KBN <> '1'
            GROUP BY SIBU_CD) SIBU1
       ON (BT42.SIBU_CD = SIBU1.SIBU_CD)

LEFT JOIN (SELECT SIBU_CD
              ,   SUM(YOSAN) AS YOSAN_ALL2
             FROM BT42MSYD
            WHERE YOSAN_DT = /*dto.yosanYm2*/'200802'
            AND   COMPANY_CD = /*companyCd*/'00'
            AND   SAKUJO_KBN <> '1'
            GROUP BY SIBU_CD) SIBU2
       ON (BT42.SIBU_CD = SIBU2.SIBU_CD)

LEFT JOIN (SELECT SIBU_CD
              ,   SUM(YOSAN) AS YOSAN_ALL3
             FROM BT42MSYD
            WHERE YOSAN_DT = /*dto.yosanYm3*/'200803'
            AND   COMPANY_CD = /*companyCd*/'00'
            AND   SAKUJO_KBN <> '1'
            GROUP BY SIBU_CD) SIBU3
       ON (BT42.SIBU_CD = SIBU3.SIBU_CD)

ORDER BY BT42.SIBU_CD
