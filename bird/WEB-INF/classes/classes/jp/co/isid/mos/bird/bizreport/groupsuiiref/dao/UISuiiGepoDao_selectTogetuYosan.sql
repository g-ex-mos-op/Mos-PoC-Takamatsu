SELECT SUM(CASE WHEN BT45.YOSAN IS NOT NULL THEN BT45.YOSAN ELSE 0 END) AS YOSAN
FROM (SELECT BM01.SIBU_CD
/*IF ("SIBU".equals(taishoJoken) && blockCd != null) || "MISE".equals(taishoJoken) */  
             , BM01.MISE_CD 
/*END*/
             FROM BM01TENM BM01
             WHERE BM01.COMPANY_CD = /*companyCd*/'00'
/*IF "MISE".equals(taishoJoken) */  
            AND   BM01.MISE_CD = /*hyojiTaisho*/'01776'
/*END*/
/*IF "JIGYOU".equals(taishoJoken) || "SLAREA".equals(taishoJoken) || "SIBU".equals(taishoJoken) */  
             AND   EXISTS (
                 SELECT * FROM BM10GSIB BM10
                 WHERE  BM10.COMPANY_CD = /*companyCd*/'00'
                 AND /*$taishoJoken*/'SIBU'_CD = /*hyojiTaisho*/'031'
/*IF "SIBU".equals(taishoJoken) && blockCd != null */
                 AND   BM01.BLOCK_CD = /*blockCd*/'004'
/*END*/
                 AND BM10.SIBU_CD = BM01.SIBU_CD) 
/*END*/         
/*IF "SEGMENT".equals(taishoJoken) */  
              AND   EXISTS (SELECT * FROM BM09GTSG BM09
                      WHERE SEGMENT_TYPE = /*hyojiTaisho*/'M' 
                      AND BM09.GYOTAI_KBN = BM01.GYOTAI_KBN)
/*END*/
/*IF "01".equals(userTypeCd) && limitFlg*/
              AND EXISTS (
                      SELECT * FROM BM51SVSB AS BM51
                      WHERE BM51.SV_CD = /*userId*/'00000921'
                      AND BM51.SIBU_CD = BM01.SIBU_CD)
/*END*/
		    GROUP BY BM01.SIBU_CD
/*IF ("SIBU".equals(taishoJoken) && blockCd != null) || "MISE".equals(taishoJoken) */  
            ,        BM01.MISE_CD 
/*END*/
     ) BM01
     ,      BT45DSJY BT45
      WHERE BT45.YOSAN_DT < /*sysDate*/'20090302'
      AND   SUBSTR(BT45.YOSAN_DT, 1,6)  = /*togetuYm*/'200704'
      AND   BT45.COMPANY_CD = /*companyCd*/'00'
/*IF "SIBU".equals(taishoJoken) */  
      AND   BT45.SIBU_CD = /*hyojiTaisho*/'031'
/*END*/
/*IF "MISE".equals(taishoJoken) */  
      AND   BT45.MISE_CD = /*hyojiTaisho*/'01776'
/*END*/
/*IF "1".equals(tenpoShubetu) || "3".equals(tenpoShubetu) */
      AND   BT45.TENPO_SHU = /*tenpoShubetu*/'1'
/*END*/
/*IF "2".equals(tenpoShubetu) */
      AND   BT45.TENPO_SHU IN ('1', '2')
/*END*/
      AND   BT45.SIBU_CD = BM01.SIBU_CD
/*IF ("SIBU".equals(taishoJoken) && blockCd != null) || "MISE".equals(taishoJoken) */
      AND   BT45.MISE_CD = BM01.MISE_CD
	/*END*/
      
/*END*/

