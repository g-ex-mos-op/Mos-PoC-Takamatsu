SELECT DISTINCT
/*IF i == 0 */
      YOSAN_KARIMISE4
/*END*/
/*IF i == 1 */
      YOSAN_KARIMISE5
/*END*/
/*IF i == 2 */
      YOSAN_KARIMISE6
/*END*/
/*IF i == 3 */
      YOSAN_KARIMISE7
/*END*/
/*IF i == 4 */
      YOSAN_KARIMISE8
/*END*/
/*IF i == 5 */
      YOSAN_KARIMISE9
/*END*/
/*IF i == 6 */
      YOSAN_KARIMISE10
/*END*/
/*IF i == 7 */
      YOSAN_KARIMISE11
/*END*/
/*IF i == 8 */
      YOSAN_KARIMISE12
/*END*/
/*IF i == 9 */
      YOSAN_KARIMISE1
/*END*/
/*IF i == 10 */
      YOSAN_KARIMISE2
/*END*/
/*IF i == 11 */
      YOSAN_KARIMISE3
/*END*/
FROM   (SELECT DISTINCT BT42MSYD.YOSAN_DT
        FROM   BT42MSYD BT42MSYD
        WHERE COMPANY_CD =/*companyCd*/'00'
        AND   YOSAN_DT BETWEEN /*dto.yosanYm4*/'200704' AND /*dto.yosanYm3*/'200803') BT42/*IF i == 0 */

LEFT JOIN (SELECT  YOSAN_DT
	      ,   SUM(YOSAN) AS YOSAN_KARIMISE4
	    FROM  BT42MSYD
	    WHERE MISE_CD LIKE 'X%'
	    AND   YOSAN_DT = /*dto.yosanYm4*/'200604'
	    AND   COMPANY_CD = /*companyCd*/'00'
	    AND   SAKUJO_KBN <> '1'
	    GROUP BY YOSAN_DT) YOSAN4
       ON (BT42.YOSAN_DT = YOSAN4.YOSAN_DT)
/*END*/
/*IF i == 1 */
LEFT JOIN (SELECT YOSAN_DT
          ,    SUM(YOSAN) AS YOSAN_KARIMISE5
     	FROM   BT42MSYD
        WHERE  MISE_CD LIKE 'X%'
        AND    YOSAN_DT = /*dto.yosanYm5*/'200605'
        AND    COMPANY_CD = /*companyCd*/'00'
        AND    SAKUJO_KBN <> '1'
        GROUP BY YOSAN_DT) YOSAN5
       ON (BT42.YOSAN_DT = YOSAN5.YOSAN_DT)
/*END*/
/*IF i == 2 */
LEFT JOIN (SELECT YOSAN_DT
              ,   SUM(YOSAN) AS YOSAN_KARIMISE6
             FROM BT42MSYD
            WHERE MISE_CD LIKE 'X%'
            AND   YOSAN_DT = /*dto.yosanYm6*/'200606'
            AND   COMPANY_CD = /*companyCd*/'00'
            AND   SAKUJO_KBN <> '1'
            GROUP BY YOSAN_DT) YOSAN6
       ON (BT42.YOSAN_DT = YOSAN6.YOSAN_DT)
/*END*/
/*IF i == 3 */
LEFT JOIN (SELECT YOSAN_DT
              ,   SUM(YOSAN) AS YOSAN_KARIMISE7
             FROM BT42MSYD
            WHERE MISE_CD LIKE 'X%'
            AND   YOSAN_DT = /*dto.yosanYm7*/'200607'
            AND   COMPANY_CD = /*companyCd*/'00'
            AND   SAKUJO_KBN <> '1'
            GROUP BY YOSAN_DT) YOSAN7
       ON (BT42.YOSAN_DT = YOSAN7.YOSAN_DT)
/*END*/
/*IF i == 4 */
LEFT JOIN (SELECT YOSAN_DT
              ,   SUM(YOSAN) AS YOSAN_KARIMISE8
             FROM BT42MSYD
            WHERE MISE_CD LIKE 'X%'
            AND   YOSAN_DT = /*dto.yosanYm8*/'200608'
            AND   COMPANY_CD = /*companyCd*/'00'
            AND   SAKUJO_KBN <> '1'
            GROUP BY YOSAN_DT) YOSAN8
       ON (BT42.YOSAN_DT = YOSAN8.YOSAN_DT)
/*END*/
/*IF i == 5 */
LEFT JOIN (SELECT YOSAN_DT
              ,   SUM(YOSAN) AS YOSAN_KARIMISE9
             FROM BT42MSYD
            WHERE MISE_CD LIKE 'X%'
            AND   YOSAN_DT = /*dto.yosanYm9*/'200609'
            AND   COMPANY_CD = /*companyCd*/'00'
            AND   SAKUJO_KBN <> '1'
            GROUP BY YOSAN_DT) YOSAN9
       ON (BT42.YOSAN_DT = YOSAN9.YOSAN_DT)
/*END*/
/*IF i == 6 */
LEFT JOIN (SELECT YOSAN_DT
              ,   SUM(YOSAN) AS YOSAN_KARIMISE10
             FROM BT42MSYD
            WHERE MISE_CD LIKE 'X%'
            AND   YOSAN_DT = /*dto.yosanYm10*/'200610'
            AND   COMPANY_CD = /*companyCd*/'00'
            AND   SAKUJO_KBN <> '1'
            GROUP BY YOSAN_DT) YOSAN10
       ON (BT42.YOSAN_DT = YOSAN10.YOSAN_DT)
/*END*/
/*IF i == 7 */
LEFT JOIN (SELECT YOSAN_DT
              ,   SUM(YOSAN) AS YOSAN_KARIMISE11
             FROM BT42MSYD
            WHERE MISE_CD LIKE 'X%'
            AND   YOSAN_DT = /*dto.yosanYm11*/'200611'
            AND   COMPANY_CD = /*companyCd*/'00'
            AND   SAKUJO_KBN <> '1'
            GROUP BY YOSAN_DT) YOSAN11
       ON (BT42.YOSAN_DT = YOSAN11.YOSAN_DT)
/*END*/
/*IF i == 8 */
LEFT JOIN (SELECT YOSAN_DT
              ,   SUM(YOSAN) AS YOSAN_KARIMISE12
             FROM BT42MSYD
            WHERE MISE_CD LIKE 'X%'
            AND   YOSAN_DT = /*dto.yosanYm12*/'200612'
            AND   COMPANY_CD = /*companyCd*/'00'
            AND   SAKUJO_KBN <> '1'
            GROUP BY YOSAN_DT) YOSAN12
       ON (BT42.YOSAN_DT = YOSAN12.YOSAN_DT)
/*END*/
/*IF i == 9 */
LEFT JOIN (SELECT YOSAN_DT
              ,   SUM(YOSAN) AS YOSAN_KARIMISE1
             FROM BT42MSYD
            WHERE MISE_CD LIKE 'X%'
            AND   YOSAN_DT = /*dto.yosanYm1*/'200701'
            AND   COMPANY_CD = /*companyCd*/'00'
            AND   SAKUJO_KBN <> '1'
            GROUP BY YOSAN_DT) YOSAN1
       ON (BT42.YOSAN_DT = YOSAN1.YOSAN_DT)
/*END*/
/*IF i == 10 */
LEFT JOIN (SELECT YOSAN_DT
              ,   SUM(YOSAN) AS YOSAN_KARIMISE2
             FROM BT42MSYD
            WHERE MISE_CD LIKE 'X%'
            AND   YOSAN_DT = /*dto.yosanYm2*/'200702'
            AND   COMPANY_CD = /*companyCd*/'00'
            AND   SAKUJO_KBN <> '1'
            GROUP BY YOSAN_DT) YOSAN2
       ON (BT42.YOSAN_DT = YOSAN2.YOSAN_DT)
/*END*/
/*IF i == 11 */
LEFT JOIN (SELECT YOSAN_DT
              ,   SUM(YOSAN) AS YOSAN_KARIMISE3
             FROM BT42MSYD
            WHERE MISE_CD LIKE 'X%'
            AND   YOSAN_DT = /*dto.yosanYm3*/'200703'
            AND   COMPANY_CD = /*companyCd*/'00'
            AND   SAKUJO_KBN <> '1'
            GROUP BY YOSAN_DT) YOSAN3
       ON (BT42.YOSAN_DT = YOSAN3.YOSAN_DT)
/*END*/