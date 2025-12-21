SELECT SUM(CASE WHEN BT45.YOSAN IS NOT NULL THEN BT45.YOSAN ELSE 0 END) AS YOSAN
FROM (
	SELECT BM01.COMPANY_CD, BM01.SIBU_CD
/*IF ("SIBU".equals(paramsDto.taishoJoken) && paramsDto.existsBlockCd) || "MISE".equals(paramsDto.taishoJoken) */  
    ,      BM01.MISE_CD 
/*END*/
    FROM BM01TENM BM01
    WHERE BM01.COMPANY_CD = /*paramsDto.companyCd*/'00'
/*IF "MISE".equals(paramsDto.taishoJoken) */  
    AND   BM01.MISE_CD = /*paramsDto.hyojiTaisho*/'01776'
/*END*/
    AND   EXISTS (
          SELECT * FROM BM10GSIB BM10
          WHERE  BM10.COMPANY_CD = BM01.COMPANY_CD
/*IF "JIGYOU".equals(paramsDto.taishoJoken) || "SLAREA".equals(paramsDto.taishoJoken) || "SIBU".equals(paramsDto.taishoJoken) */  
          AND /*$paramsDto.taishoJoken*/'SIBU'_CD = /*paramsDto.hyojiTaisho*/'031'
 /*IF "SIBU".equals(paramsDto.taishoJoken) && paramsDto.existsBlockCd */
          AND   BM01.BLOCK_CD = /*paramsDto.blockCd*/'004'
 /*END*/
/*END*/         
          AND BM10.SIBU_CD = BM01.SIBU_CD) 
/*IF "SEGMENT".equals(paramsDto.taishoJoken) */  
    AND   EXISTS (
              SELECT * FROM BM09GTSG BM09
              WHERE BM09.SEGMENT_TYPE = /*paramsDto.hyojiTaisho*/'M' 
              AND   BM09.GYOTAI_KBN = BM01.GYOTAI_KBN)
/*END*/
/*IF "01".equals(birdUserInfo.mstUser.userTypeCd) && birdUserInfo.limit*/
    AND EXISTS (
              SELECT * FROM BM51SVSB AS BM51
              WHERE BM51.SV_CD      = /*birdUserInfo.userID*/'00000921'
              AND   BM51.COMPANY_CD = BM01.COMPANY_CD
              AND   BM51.SIBU_CD    = BM01.SIBU_CD
              )
/*END*/
	GROUP BY BM01.COMPANY_CD, BM01.SIBU_CD
/*IF ("SIBU".equals(paramsDto.taishoJoken) && paramsDto.existsBlockCd) || "MISE".equals(paramsDto.taishoJoken) */  
    ,        BM01.MISE_CD 
/*END*/
     ) BM01
     ,      BT45DSJY BT45
WHERE BT45.YOSAN_DT < /*birdDateInfo.sysDate*/'20130502'
AND   BT45.YOSAN_DT BETWEEN /*togetuYmdFrom*/'20130501' AND /*togetuYmdTo*/'20130531'
/*IF "1".equals(paramsDto.tenpoShubetu) || "3".equals(paramsDto.tenpoShubetu) */
AND   BT45.TENPO_SHU = /*paramsDto.tenpoShubetu*/'1'
/*END*/
/*IF "2".equals(paramsDto.tenpoShubetu) */
AND   BT45.TENPO_SHU IN ('1', '2')
/*END*/
AND   BT45.COMPANY_CD = BM01.COMPANY_CD
AND   BT45.SIBU_CD = BM01.SIBU_CD
/*IF ("SIBU".equals(paramsDto.taishoJoken) && paramsDto.existsBlockCd) || "MISE".equals(paramsDto.taishoJoken) */
AND   BT45.MISE_CD = BM01.MISE_CD
/*END*/
