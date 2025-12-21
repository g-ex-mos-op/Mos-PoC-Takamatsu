UPDATE BT42MSYD
   SET SAKUJO_KBN = '1',
       LAST_DATE = /*lastDate*/'',
       LAST_USER = /*lastUser*/'',
       LAST_PGM  = /*lastPgm*/'',
       LAST_TMSP = /*lastTmsp*/''
 WHERE YOSAN_DT BETWEEN /*fromDate*/'200609' AND /*toDate*/'200703'
   AND COMPANY_CD = /*companyCd*/'00'