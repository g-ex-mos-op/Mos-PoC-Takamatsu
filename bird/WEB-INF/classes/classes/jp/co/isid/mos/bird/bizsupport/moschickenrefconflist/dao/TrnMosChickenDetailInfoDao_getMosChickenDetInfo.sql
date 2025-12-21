 SELECT DISTINCT
       BT71.MENU_CD as MENU_CD
  ,    rtrim(MENU_NAME_KJ) as MENU_NAME_KJ
  ,    RESERVE_AMT
  ,    SEQ_NO
FROM BT71CRSD BT71
INNER JOIN (SELECT MISE_CD 
              ,    MENU_CD
              ,    MENU_NAME_KJ
            FROM   BM38MMNU
            WHERE  COMPANY_CD = /*companyCd*/'00'
            AND    MISE_CD  = /*miseCd*/'01057') BM38
   ON (BT71.MENU_CD = BM38.MENU_CD)
WHERE BT71.COMPANY_CD = /*companyCd*/'00'
AND   BT71.MISE_CD = BM38.MISE_CD
AND   BT71.SEQ_NO = /*seqNo*/1
AND   Bt71.CKANRI_NO = /*ckanriNo*/'200601'
ORDER BY  BT71.MENU_CD