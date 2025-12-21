UPDATE BT42MSYD
   SET SAKUJO_KBN = '0',
       YOSAN = /*budgetInfo.yosan*/'11111',
       SIBU_CD = /*budgetInfo.sibuCd*/'031',
       AREA_DAI = /*budgetInfo.areaDai*/'031',
       TENPO_SHU = /*budgetInfo.tenpoShu*/'1',
       FC_RC = /*budgetInfo.fcRc*/'1',
       TENPO_COUNT = /*budgetInfo.tenpoCount*/'1',
       LAST_DATE = /*budgetInfo.lastDate*/'20061101',
       LAST_USER = /*budgetInfo.lastUser*/'',
       LAST_PGM  = /*budgetInfo.lastPgm*/'BBS022',
       LAST_TMSP = /*budgetInfo.lastTmsp*/
 WHERE YOSAN_DT = /*budgetInfo.yosanDt*/'200611'
   AND COMPANY_CD = /*budgetInfo.companyCd*/'00'
   AND MISE_CD = /*budgetInfo.miseCd*/'0000'
   AND SAKUJO_KBN = /*budgetInfo.sakujoKbn*/'0'
