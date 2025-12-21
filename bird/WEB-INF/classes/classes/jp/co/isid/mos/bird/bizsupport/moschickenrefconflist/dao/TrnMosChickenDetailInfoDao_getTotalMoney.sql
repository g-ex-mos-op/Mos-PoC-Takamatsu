SELECT UCHI_TANKA as TOTAL_MONEY
   ,   BT71.MENU_CD
   ,   BT71.RESERVE_AMT
FROM (SELECT MENU_CD
         ,   RESERVE_AMT
         ,   COMPANY_CD
         ,   CKANRI_NO
         ,   SEQ_NO
        FROM BT71CRSD 
        WHERE MISE_CD =/*miseCd*/'04179'
        AND   COMPANY_CD =/*companyCd*/'00'	
        AND   SEQ_NO = /*seqNo*/1
        AND   CKANRI_NO =/*ckanriNo*/'200601'
        AND   RESERVE_AMT > 0) BT71
INNER JOIN
	 (SELECT UCHI_TANKA
          ,  MENU_CD
	    FROM BM38MMNU
        WHERE MISE_CD =/*miseCd*/'04179'
        AND   COMPANY_CD =/*companyCd*/'00') BM38
ON(BM38.MENU_CD  = BT71.MENU_CD) 