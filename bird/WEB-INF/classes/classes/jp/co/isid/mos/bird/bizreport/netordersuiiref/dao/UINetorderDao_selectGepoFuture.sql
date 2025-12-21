select BT4YOSAN.YOSAN_DT AS EIGYO_DT
,      BT4YOSAN.YOSAN AS YOSAN
,      (CASE WHEN LAST_BD53.EIGYO_DT IS NULL THEN 0 ELSE LAST_BD53.EIGYO_DAYS END) AS EIGYO_DAYS_ZEN
,      (CASE WHEN LAST_BD53.EIGYO_DT IS NULL THEN 0 ELSE LAST_BD53.URIAGE END) AS URIAGE_ZEN
,      (CASE WHEN LAST_BD53.EIGYO_DT IS NULL THEN 0 ELSE LAST_BD53.KYAKUSU END) AS KYAKUSU_ZEN
,      (CASE WHEN LAST_BD53.EIGYO_DT IS NULL THEN 0 ELSE LAST_BD53.URIAGE_NSUM END) AS URIAGE_NSUM_ZEN
,      (CASE WHEN LAST_BD53.EIGYO_DT IS NULL THEN 0 ELSE LAST_BD53.KYAKUSU_NSUM END) AS KYAKUSU_NSUM_ZEN
,      (CASE WHEN LAST_BD53.EIGYO_DT IS NULL THEN 0 ELSE LAST_BD53.URIAGE_NTAKE END) AS URIAGE_NTAKE_ZEN
,      (CASE WHEN LAST_BD53.EIGYO_DT IS NULL THEN 0 ELSE LAST_BD53.KYAKUSU_NTAKE END) AS KYAKUSU_NTAKE_ZEN
,      (CASE WHEN LAST_BD53.EIGYO_DT IS NULL THEN 0 ELSE LAST_BD53.URIAGE_NTAKU END) AS URIAGE_NTAKU_ZEN
,      (CASE WHEN LAST_BD53.EIGYO_DT IS NULL THEN 0 ELSE LAST_BD53.KYAKUSU_NTAKU END) AS KYAKUSU_NTAKU_ZEN
/*IF paramsDto.selectHosei */
,      (CASE WHEN LAST_BD53.URIAGE IS NULL THEN 0 ELSE LAST_BD53.URIAGE END) AS NET_URIAGE_ZEN
,      (CASE WHEN LAST_BD53.KYAKUSU IS NULL THEN 0 ELSE LAST_BD53.KYAKUSU END) AS NET_KYAKUSU_ZEN
,      (CASE WHEN LAST_BD53.EIGYO_DT IS NULL THEN 0 ELSE LAST_BD53.URIAGE_NSUM END) AS NET_URIAGE_NSUM_ZEN
,      (CASE WHEN LAST_BD53.EIGYO_DT IS NULL THEN 0 ELSE LAST_BD53.KYAKUSU_NSUM END) AS NET_KYAKUSU_NSUM_ZEN
,      (CASE WHEN LAST_BD53.EIGYO_DT IS NULL THEN 0 ELSE LAST_BD53.URIAGE_NTAKE END) AS NET_URIAGE_NTAKE_ZEN
,      (CASE WHEN LAST_BD53.EIGYO_DT IS NULL THEN 0 ELSE LAST_BD53.KYAKUSU_NTAKE END) AS NET_KYAKUSU_NTAKE_ZEN
,      (CASE WHEN LAST_BD53.EIGYO_DT IS NULL THEN 0 ELSE LAST_BD53.URIAGE_NTAKU END) AS NET_URIAGE_NTAKU_ZEN
,      (CASE WHEN LAST_BD53.EIGYO_DT IS NULL THEN 0 ELSE LAST_BD53.KYAKUSU_NTAKU END) AS NET_KYAKUSU_NTAKU_ZEN
/*END*/
from (
      SELECT BT44.YOSAN_DT
      ,      SUM(BT44.YOSAN) AS YOSAN
      FROM (SELECT BM01.SIBU_CD
/*IF ("SIBU".equals(paramsDto.taishoJoken) && paramsDto.existsBlockCd) || "MISE".equals(paramsDto.taishoJoken) */
            ,      BM01.MISE_CD 
/*END*/
            FROM BM01TENM BM01
            WHERE BM01.COMPANY_CD = /*paramsDto.companyCd*/'00'
/*IF paramsDto.taishoJoken.equals("MISE") */  
            AND   BM01.MISE_CD = /*paramsDto.hyojiTaisho*/'01776'
/*END*/
/*IF "01".equals(birdUserInfo.mstUser.userTypeCd) */
    /*IF "JIGYOU".equals(paramsDto.taishoJoken) || "SLAREA".equals(paramsDto.taishoJoken) || "SIBU".equals(paramsDto.taishoJoken) */  
            AND   EXISTS (SELECT * FROM BM10GSIB BM10
                          WHERE BM10.COMPANY_CD = /*paramsDto.companyCd*/'00'
                          AND   BM10./*$paramsDto.taishoJoken*/'SIBU'_CD = /*paramsDto.hyojiTaisho*/'031'
        /*IF "SIBU".equals(paramsDto.taishoJoken) && paramsDto.existsBlockCd */  
                          AND   BM01.BLOCK_CD   = /*paramsDto.blockCd*/'004'
        /*END*/         
                          AND   BM10.SIBU_CD    = BM01.SIBU_CD) 
    /*END*/
    /*IF "SEGMENT".equals(paramsDto.taishoJoken) */  
            AND   EXISTS (SELECT * FROM BM09GTSG BM09
                          WHERE BM09.SEGMENT_TYPE = /*paramsDto.hyojiTaisho*/'M' 
                          AND   BM09.GYOTAI_KBN = BM01.GYOTAI_KBN)
    /*END*/
    /*IF birdUserInfo.limit*/
            AND   EXISTS (SELECT * FROM BM51SVSB AS BM51
                          WHERE BM51.COMPANY_CD = /*paramsDto.companyCd*/'00'
                          AND   BM51.SV_CD = /*birdUserInfo.userID*/'00000921'
                          AND   BM51.SIBU_CD = BM01.SIBU_CD)
    /*END*/
/*END*/
/*IF (("SIBU".equals(paramsDto.taishoJoken) && paramsDto.existsBlockCd) || "MISE".equals(paramsDto.taishoJoken))==false */
        GROUP BY BM01.SIBU_CD
/*END*/
     ) BM01
     ,     BT44MSJY BT44
      WHERE BT44.YOSAN_DT BETWEEN /*futureFrom*/'' AND /*futureTo*/
      AND   BT44.COMPANY_CD = /*paramsDto.companyCd*/'00'
/*IF "SIBU".equals(paramsDto.taishoJoken) */  
      AND   BT44.SIBU_CD = /*paramsDto.hyojiTaisho*/'031'
/*END*/
/*IF "MISE".equals(paramsDto.taishoJoken) */  
      AND   BT44.MISE_CD = /*paramsDto.hyojiTaisho*/'01776'
/*END*/
/*IF "1".equals(paramsDto.tenpoShubetu) || "3".equals(paramsDto.tenpoShubetu) */
      AND   BT44.TENPO_SHU = /*paramsDto.tenpoShubetu*/'1'
/*END*/
/*IF "2".equals(paramsDto.tenpoShubetu) */
      AND   BT44.TENPO_SHU IN ('1', '2')
/*END*/
      AND   BT44.SIBU_CD = BM01.SIBU_CD
/*IF ("SIBU".equals(paramsDto.taishoJoken) && paramsDto.existsBlockCd) || "MISE".equals(paramsDto.taishoJoken) */
      AND   BT44.MISE_CD = BM01.MISE_CD
/*END*/
      group by BT44.YOSAN_DT
      ) BT4YOSAN
LEFT JOIN    ( 
      SELECT SUBSTR(CHAR(DECIMAL(DECIMAL(SUBSTR(SUB.EIGYO_DT, 1,6))+100, 6,0)),1,6) AS EIGYO_DT
      ,      SUM(SUB.URIAGE_DOGETU) AS URIAGE
      ,      SUM(SUB.ONER_YOSAN_DOGETU) AS ONER_YOSAN
      ,      SUM(SUB.EIGYO_DAYS_DOGETU) AS EIGYO_DAYS
      ,      SUM(SUB.KYAKUSU_DOGETU) AS KYAKUSU
      ,      SUM(SUB.URIAGE_DOGETU_NSUM) AS URIAGE_NSUM
      ,      SUM(SUB.KYAKUSU_DOGETU_NSUM) AS KYAKUSU_NSUM
      ,      SUM(SUB.URIAGE_DOGETU_NTAKE) AS URIAGE_NTAKE
      ,      SUM(SUB.KYAKUSU_DOGETU_NTAKE) AS KYAKUSU_NTAKE
      ,      SUM(SUB.URIAGE_DOGETU_NTAKU) AS URIAGE_NTAKU
      ,      SUM(SUB.KYAKUSU_DOGETU_NTAKU) AS KYAKUSU_NTAKU
      FROM (
            SELECT SUBBD53.*
            FROM (
               SELECT EIGYO_YM, MISE_CD
               FROM BN01DTEN 
               WHERE  COMPANY_CD = /*paramsDto.companyCd*/'00'
/*IF paramsDto.tenpoShubetu == "1" || paramsDto.tenpoShubetu == "3"*/
               AND   KBN1 = /*paramsDto.tenpoShubetu*/''
/*END*/
/*IF paramsDto.tenpoShubetu == "2" */
               AND   KBN1 IN ('1', '2')
/*END*/
               AND    EIGYO_YM  BETWEEN /*kijunYm*/'' AND /*kijunYm*/''
               GROUP BY EIGYO_YM, MISE_CD
            ) BN01
            , BD53NGEP SUBBD53
            WHERE SUBBD53.EIGYO_DT BETWEEN /*zennenFrom*/'' AND /*zennenTo*/''
            AND   SUBBD53.COMPANY_CD = /*paramsDto.companyCd*/'00'
            AND   SUBBD53.OLDM_FLG = '0'
/*IF paramsDto.tenpoShubetu != "ALL" */
            AND   SUBBD53.OPEN_KBN = 1
/*END*/
            AND   SUBBD53.MISE_CD  = BN01.MISE_CD
            AND   EXISTS (
                  SELECT * FROM BM01TENM BM01
                  WHERE BM01.COMPANY_CD = /*paramsDto.companyCd*/'00'
/*IF paramsDto.taishoJoken.equals("MISE") */  
                  AND   BM01.MISE_CD = /*paramsDto.hyojiTaisho*/'01776'
/*END*/
/*IF "01".equals(birdUserInfo.mstUser.userTypeCd) */
	/*IF "JIGYOU".equals(paramsDto.taishoJoken) || "SLAREA".equals(paramsDto.taishoJoken) || "SIBU".equals(paramsDto.taishoJoken) */  
                  AND   EXISTS (SELECT * FROM BM10GSIB BM10
		                WHERE BM10.COMPANY_CD = /*paramsDto.companyCd*/'00'
		                AND   BM10./*$paramsDto.taishoJoken*/'SIBU'_CD = /*paramsDto.hyojiTaisho*/'031'
		/*IF "SIBU".equals(paramsDto.taishoJoken) && paramsDto.existsBlockCd */  
		                AND   BM01.BLOCK_CD = /*paramsDto.blockCd*/'004'
		/*END*/
		                AND   BM10.SIBU_CD    = BM01.SIBU_CD) 
	/*END*/         
	/*IF "SEGMENT".equals(paramsDto.taishoJoken) */  
                  AND   EXISTS (SELECT * FROM BM09GTSG BM09
		                WHERE SEGMENT_TYPE = /*paramsDto.hyojiTaisho*/'M' 
		                AND   BM09.GYOTAI_KBN = BM01.GYOTAI_KBN)
	/*END*/
	/*IF birdUserInfo.limit */
                  AND   EXISTS (SELECT * FROM BM51SVSB AS BM51
		                WHERE BM51.COMPANY_CD = /*paramsDto.companyCd*/'00'
                        AND   BM51.SV_CD      = /*birdUserInfo.userID*/'00000921'
		                AND   BM51.SIBU_CD    = BM01.SIBU_CD)
	/*END*/
                  AND   SUBBD53.MISE_CD  = BM01.MISE_CD) 
/*END*/
      ) SUB 
      GROUP BY SUBSTR(CHAR(DECIMAL(DECIMAL(SUBSTR(SUB.EIGYO_DT, 1,6))+100, 6,0)),1,6)
) LAST_BD53
ON (LAST_BD53.EIGYO_DT    = BT4YOSAN.YOSAN_DT )
order by BT4YOSAN.YOSAN_DT
