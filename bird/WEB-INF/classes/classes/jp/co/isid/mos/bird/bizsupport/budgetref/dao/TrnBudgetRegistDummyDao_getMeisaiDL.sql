SELECT DISTINCT BT42.MISE_CD
  ,     rtrim(BT42.MISE_NAME_KJ) as MISE_NAME_KJ
  ,     BT42.FC_RC
  ,     BT42.SIBU_CD
  ,     rtrim(BT42.SIBU_NAME) as SIBU_NAME
  ,     BT42.AREA_DAI
  ,     rtrim(BT42.AREA_DAI_NAME) as AREA_DAI_NAME
  ,     YOSAN_ALL4
  ,     YOSAN_ALL5
  ,     YOSAN_ALL6
  ,     YOSAN_ALL7
  ,     YOSAN_ALL8
  ,     YOSAN_ALL9
  ,     YOSAN_ALL10
  ,     YOSAN_ALL11
  ,     YOSAN_ALL12
  ,     YOSAN_ALL1
  ,     YOSAN_ALL2
  ,     YOSAN_ALL3
FROM  (SELECT DISTINCT BT42MSYD.SIBU_CD
           ,   BM57.SIBU_NAME
           ,   FC_RC
           ,   BT42MSYD.AREA_DAI
           ,   BM57_AREADAI.SIBU_NAME as AREA_DAI_NAME
           ,   BT42MSYD.MISE_CD
           ,   MISE_NAME_KJ
        FROM   BT42MSYD BT42MSYD
        LEFT JOIN BM01TENM BM01 ON (BT42MSYD.COMPANY_CD = BM01.COMPANY_CD AND BT42MSYD.MISE_CD = BM01.MISE_CD)
        LEFT JOIN BM57NSIB BM57 ON (BT42MSYD.COMPANY_CD = BM57.COMPANY_CD AND BT42MSYD.SIBU_CD = BM57.SIBU_CD)
        LEFT JOIN BM57NSIB BM57_AREADAI ON (BT42MSYD.COMPANY_CD = BM57_AREADAI.COMPANY_CD AND BT42MSYD.AREA_DAI = BM57_AREADAI.SIBU_CD)
        WHERE BT42MSYD.COMPANY_CD =/*companyCd*/'00'
        AND   SAKUJO_KBN <> '1'
        AND   YOSAN_DT BETWEEN /*dto.yosanYm4*/'200604' AND /*dto.yosanYm3*/'200703') BT42


LEFT JOIN (SELECT MISE_CD
              ,   YOSAN AS YOSAN_ALL4
             FROM BT42MSYD
            WHERE YOSAN_DT = /*dto.yosanYm4*/'200604'
            AND   COMPANY_CD = /*companyCd*/'00'
            AND   SAKUJO_KBN <> '1'
            ) MISE4
       ON (BT42.MISE_CD = MISE4.MISE_CD)

LEFT JOIN (SELECT MISE_CD
              ,   YOSAN AS YOSAN_ALL5
             FROM BT42MSYD
            WHERE YOSAN_DT = /*dto.yosanYm5*/'200605'
            AND   COMPANY_CD = /*companyCd*/'00'
            AND   SAKUJO_KBN <> '1') MISE5

       ON (BT42.MISE_CD = MISE5.MISE_CD)

LEFT JOIN (SELECT MISE_CD
              ,   YOSAN AS YOSAN_ALL6
             FROM BT42MSYD
            WHERE YOSAN_DT = /*dto.yosanYm6*/'200606'
            AND   COMPANY_CD = /*companyCd*/'00'
            AND   SAKUJO_KBN <> '1') MISE6
       ON (BT42.MISE_CD = MISE6.MISE_CD)

LEFT JOIN (SELECT MISE_CD
              ,   YOSAN AS YOSAN_ALL7
             FROM BT42MSYD
            WHERE YOSAN_DT = /*dto.yosanYm7*/'200607'
            AND   COMPANY_CD = /*companyCd*/'00'
            AND   SAKUJO_KBN <> '1') MISE7
       ON (BT42.MISE_CD = MISE7.MISE_CD)

LEFT JOIN (SELECT MISE_CD
              ,   YOSAN AS YOSAN_ALL8
             FROM BT42MSYD
            WHERE YOSAN_DT = /*dto.yosanYm8*/'200608'
            AND   COMPANY_CD = /*companyCd*/'00'
            AND   SAKUJO_KBN <> '1') MISE8
       ON (BT42.MISE_CD = MISE8.MISE_CD)

LEFT JOIN (SELECT MISE_CD
              ,   YOSAN AS YOSAN_ALL9
             FROM BT42MSYD
            WHERE YOSAN_DT = /*dto.yosanYm9*/'200609'
            AND   COMPANY_CD = /*companyCd*/'00'
            AND   SAKUJO_KBN <> '1') MISE9
       ON (BT42.MISE_CD = MISE9.MISE_CD)

LEFT JOIN (SELECT MISE_CD
              ,   YOSAN AS YOSAN_ALL10
             FROM BT42MSYD
            WHERE YOSAN_DT = /*dto.yosanYm10*/'200610'
            AND   COMPANY_CD = /*companyCd*/'00'
            AND   SAKUJO_KBN <> '1') MISE10
       ON (BT42.MISE_CD = MISE10.MISE_CD)

LEFT JOIN (SELECT MISE_CD
              ,   YOSAN AS YOSAN_ALL11
             FROM BT42MSYD
            WHERE YOSAN_DT = /*dto.yosanYm11*/'200611'
            AND   COMPANY_CD = /*companyCd*/'00'
            AND   SAKUJO_KBN <> '1') MISE11
       ON (BT42.MISE_CD = MISE11.MISE_CD)

LEFT JOIN (SELECT MISE_CD
              ,   YOSAN AS YOSAN_ALL12
             FROM BT42MSYD
            WHERE YOSAN_DT = /*dto.yosanYm12*/'200612'
            AND   COMPANY_CD = /*companyCd*/'00'
            AND   SAKUJO_KBN <> '1') MISE12
       ON (BT42.MISE_CD = MISE12.MISE_CD)

LEFT JOIN (SELECT MISE_CD
              ,   YOSAN AS YOSAN_ALL1
             FROM BT42MSYD
            WHERE YOSAN_DT = /*dto.yosanYm1*/'200701'
            AND   COMPANY_CD = /*companyCd*/'00'
            AND   SAKUJO_KBN <> '1') MISE1
       ON (BT42.MISE_CD = MISE1.MISE_CD)

LEFT JOIN (SELECT MISE_CD
              ,   YOSAN AS YOSAN_ALL2
             FROM BT42MSYD
            WHERE YOSAN_DT = /*dto.yosanYm2*/'200702'
            AND   COMPANY_CD = /*companyCd*/'00'
            AND   SAKUJO_KBN <> '1') MISE2
       ON (BT42.MISE_CD = MISE2.MISE_CD)

LEFT JOIN (SELECT MISE_CD
              ,   YOSAN AS YOSAN_ALL3
             FROM BT42MSYD
            WHERE YOSAN_DT = /*dto.yosanYm3*/'200703'
            AND   COMPANY_CD = /*companyCd*/'00'
            AND   SAKUJO_KBN <> '1') MISE3
       ON (BT42.MISE_CD = MISE3.MISE_CD)
ORDER BY BT42.SIBU_CD
     ,   BT42.MISE_CD

