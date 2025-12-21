select (CASE WHEN BT4YOSAN.YOSAN_DT IS NULL THEN BT64.EIGYO_DT ELSE BT4YOSAN.YOSAN_DT END) AS EIGYO_DT 
,      (CASE WHEN BT64.URIAGE IS NULL THEN 0 ELSE BT64.URIAGE END) AS URIAGE
,      (CASE WHEN BT4YOSAN.YOSAN IS NULL THEN 0 ELSE BT4YOSAN.YOSAN END) AS YOSAN
,      (CASE WHEN BT64.EIGYO_DAYS IS NULL THEN 0 ELSE BT64.EIGYO_DAYS END) AS EIGYO_DAYS
,      (CASE WHEN BT64.URIAGE_ZEN IS NULL THEN 0 ELSE BT64.URIAGE_ZEN END) AS URIAGE_ZEN
,      (CASE WHEN BT64.EIGYO_DAYS_ZEN IS NULL THEN 0 ELSE BT64.EIGYO_DAYS_ZEN END) AS EIGYO_DAYS_ZEN
,      (CASE WHEN BT64.KYAKUSU IS NULL THEN 0 ELSE BT64.KYAKUSU END) AS KYAKUSU
,      (CASE WHEN BT64.KYAKUSU_ZEN IS NULL THEN 0 ELSE BT64.KYAKUSU_ZEN END) AS KYAKUSU_ZEN

/*IF "SIBU".equals(taishoJoken) && ("090".equals(hyojiTaisho) || "091".equals(hyojiTaisho)) || "MISE".equals(taishoJoken) */
,      (CASE WHEN BT65.NEBIKI IS NULL THEN 0 ELSE BT65.NEBIKI END) AS NEBIKI
,      nvl(URIAGE,0) - nvl(NEBIKI,0) AS URIAGE_AFTER_NEBIKI
,      (CASE WHEN BT59.URIAGE_ZEN_AFTER_NEBIKI IS NULL THEN 0 ELSE BT59.URIAGE_ZEN_AFTER_NEBIKI END) AS URIAGE_ZEN_AFTER_NEBIKI
/*IF zennenDatashubetu == "HOSEI" */
,      (CASE WHEN nvl(BT64.URIAGE_NET, 0) > 0 AND nvl(BT64.URIAGE_ZEN_NET, 0) > 0 THEN BT65.NEBIKI_HOSEI ELSE 0 END) AS NET_NEBIKI
,      (CASE WHEN BT64.URIAGE_NET IS NULL THEN 0 ELSE BT64.URIAGE_NET END)
     - (CASE WHEN nvl(BT64.URIAGE_NET, 0) > 0 AND nvl(BT64.URIAGE_ZEN_NET, 0) > 0 THEN BT65.NEBIKI_HOSEI ELSE 0 END) AS NET_URIAGE_AFTER_NEBIKI
/*END*/
/*END*/
/*IF "MISE".equals(taishoJoken) */
,      (
         SELECT SIBU_CD
          FROM BM01TENM BM01
         WHERE BM01.COMPANY_CD = /*companyCd*/'00'
           AND BM01.MISE_CD = /*hyojiTaisho*/'01776'
        ) AS SYOZOKU_SIBU
/*END*/
/*IF zennenDatashubetu == "HOSEI" */
,      (CASE WHEN BT64.URIAGE_NET IS NULL THEN 0 ELSE BT64.URIAGE_NET END) AS NET_URIAGE
,      (CASE WHEN BT64.EIGYO_DAYS_NET IS NULL THEN 0 ELSE BT64.EIGYO_DAYS_NET END) AS NET_EIGYO_DAYS
,      (CASE WHEN BT64.URIAGE_ZEN_NET IS NULL THEN 0 ELSE BT64.URIAGE_ZEN_NET END) AS NET_URIAGE_ZEN
,      (CASE WHEN BT64.EIGYO_DAYS_NET IS NULL THEN 0 ELSE BT64.EIGYO_DAYS_NET END) AS NET_EIGYO_DAYS_ZEN
,      (CASE WHEN BT64.KYAKUSU_NET IS NULL THEN 0 ELSE BT64.KYAKUSU_NET END) AS NET_KYAKUSU
,      (CASE WHEN BT64.KYAKUSU_ZEN_NET IS NULL THEN 0 ELSE BT64.KYAKUSU_ZEN_NET END) AS NET_KYAKUSU_ZEN
/*END*/
from (SELECT YOSANTBL.YOSAN_DT
      ,      SUM(YOSANTBL.YOSAN) AS YOSAN
      FROM   (SELECT BM01.SIBU_CD
/*IF ("SIBU".equals(taishoJoken) && blockCd != null) || "MISE".equals(taishoJoken) */
            ,      BM01.MISE_CD 
/*END*/
            FROM BM01TENM BM01
            WHERE BM01.COMPANY_CD = /*companyCd*/'00'
/*IF "SIBU".equals(taishoJoken) */  
            AND   BM01.SIBU_CD = /*hyojiTaisho*/'031'
 /*IF blockCd != null */
            AND   BM01.BLOCK_CD = /*blockCd*/'004'
 /*END*/
/*END*/
/*IF taishoJoken.equals("MISE") */  
            AND   BM01.MISE_CD = /*hyojiTaisho*/'01776'
/*END*/
/*IF "JIGYOU".equals(taishoJoken) || "SLAREA".equals(taishoJoken) || "SIBU".equals(taishoJoken) */  
            AND   EXISTS (
                 SELECT * FROM BM10GSIB BM10
                 WHERE  BM10.COMPANY_CD = /*companyCd*/'00'
                 AND /*$taishoJoken*/'SIBU'_CD = /*hyojiTaisho*/'031'
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
/*IF (("SIBU".equals(taishoJoken) && blockCd != null) || "MISE".equals(taishoJoken))==false */
        GROUP BY BM01.SIBU_CD
/*END*/
     ) BM01
,    BT44MSJY YOSANTBL
      WHERE YOSANTBL.COMPANY_CD = /*companyCd*/'00'
      AND   YOSANTBL.YOSAN_DT BETWEEN /*kikanFrom*/'200704' AND /*kikanTo*/'200704'
/*IF "1".equals(tenpoShubetu) || "3".equals(tenpoShubetu) */
      AND   YOSANTBL.TENPO_SHU = /*tenpoShubetu*/'1'
/*END*/
/*IF "2".equals(tenpoShubetu) */
      AND   YOSANTBL.TENPO_SHU IN ('1', '2')
/*END*/
      AND   YOSANTBL.SIBU_CD = BM01.SIBU_CD
/*IF ("SIBU".equals(taishoJoken) && blockCd != null) || "MISE".equals(taishoJoken) */
      AND   YOSANTBL.MISE_CD = BM01.MISE_CD
/*END*/
      
/*END*/
      group by YOSANTBL.YOSAN_DT
      ) BT4YOSAN
FULL OUTER JOIN    ( 
      SELECT SUB.EIGYO_DT
      ,      SUM(SUB.URIAGE) AS URIAGE
      ,      SUM(SUB.ONER_YOSAN) AS ONER_YOSAN
      ,      SUM(SUB.EIGYO_DAYS) AS EIGYO_DAYS
      ,      SUM(SUB.URIAGE_ZEN) AS URIAGE_ZEN
      ,      SUM(SUB.EIGYO_DAYS_ZEN) AS EIGYO_DAYS_ZEN
      ,      SUM(SUB.KYAKUSU) AS KYAKUSU
      ,      SUM(SUB.KYAKUSU_ZEN) AS KYAKUSU_ZEN
/*IF zennenDatashubetu == "HOSEI" */
      ,      SUM(SUB.URIAGE_NET) AS URIAGE_NET
      ,      SUM(SUB.ONER_YOSAN_NET) AS ONER_YOSAN_NET
      ,      SUM(SUB.EIGYO_DAYS_NET) AS EIGYO_DAYS_NET
      ,      SUM(SUB.URIAGE_ZEN_NET) AS URIAGE_ZEN_NET
      ,      SUM(SUB.EIGYO_DAYS_ZEN_NET) AS EIGYO_DAYS_ZEN_NET
      ,      SUM(SUB.KYAKUSU_NET) AS KYAKUSU_NET
      ,      SUM(SUB.KYAKUSU_ZEN_NET) AS KYAKUSU_ZEN_NET
/*END*/

      FROM (SELECT COMPANY_CD, SIBU_CD, MISE_CD, ONER_CD FROM BM01TENM BM01
            WHERE BM01.COMPANY_CD = /*companyCd*/'00'
/*IF "SIBU".equals(taishoJoken) */  
            AND   BM01.SIBU_CD = /*hyojiTaisho*/'031'
 /*IF blockCd != null */
            AND   BM01.BLOCK_CD = /*blockCd*/'004'
 /*END*/
/*END*/
/*IF taishoJoken.equals("MISE") */  
            AND   BM01.MISE_CD = /*hyojiTaisho*/'01776'
/*END*/
/*IF "JIGYOU".equals(taishoJoken) || "SLAREA".equals(taishoJoken) || "SIBU".equals(taishoJoken) */  
            AND   EXISTS (
                 SELECT * FROM BM10GSIB BM10
                 WHERE  BM10.COMPANY_CD = /*companyCd*/'00'
                 AND /*$taishoJoken*/'SIBU'_CD = /*hyojiTaisho*/'031'
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
            ) BM01
      ,     (
        SELECT BT64.EIGYO_DT
        ,      BT64.MISE_CD
/*IF zennenDatashubetu == "HOSEI" */
        ,      BT64.URIAGE_DOGETU AS URIAGE
        ,      BT64.ONER_YOSAN_DOGETU AS ONER_YOSAN
        ,      BT64.EIGYO_DAYS_DOGETU AS EIGYO_DAYS
        ,      BT64.URIAGE_ZEN_DOGETU AS URIAGE_ZEN
        ,      BT64.EIGYO_DAYS_ZEN_DOGETU AS EIGYO_DAYS_ZEN
        ,      BT64.KYAKUSU_DOGETU AS KYAKUSU
        ,      BT64.KYAKUSU_ZEN_DOGETU AS KYAKUSU_ZEN
        ,      BT64.URIAGE_HOSEI AS URIAGE_NET
        ,      BT64.ONER_YOSAN_HOSEI AS ONER_YOSAN_NET
        ,      BT64.EIGYO_DAYS_HOSEI AS EIGYO_DAYS_NET
        ,      BT64.URIAGE_ZEN_HOSEI AS URIAGE_ZEN_NET
        ,      BT64.EIGYO_DAYS_ZEN_HOSEI AS EIGYO_DAYS_ZEN_NET
        ,      BT64.KYAKUSU_HOSEI AS KYAKUSU_NET
        ,      BT64.KYAKUSU_ZEN_HOSEI AS KYAKUSU_ZEN_NET
--ELSE
        ,      BT64.URIAGE_/*$zennenDatashubetu*/'DOGETU' AS URIAGE
        ,      BT64.ONER_YOSAN_/*$zennenDatashubetu*/'DOGETU' AS ONER_YOSAN
        ,      BT64.EIGYO_DAYS_/*$zennenDatashubetu*/'DOGETU' AS EIGYO_DAYS
        ,      BT64.URIAGE_ZEN_/*$zennenDatashubetu*/'DOGETU' AS URIAGE_ZEN
        ,      BT64.EIGYO_DAYS_ZEN_/*$zennenDatashubetu*/'DOGETU' AS EIGYO_DAYS_ZEN
        ,      BT64.KYAKUSU_/*$zennenDatashubetu*/'DOGETU' AS KYAKUSU
        ,      BT64.KYAKUSU_ZEN_/*$zennenDatashubetu*/'DOGETU' AS KYAKUSU_ZEN
/*END*/
        FROM  BT64ZGEP BT64
        ,     BN01DTEN BN01
        WHERE BT64.EIGYO_DT BETWEEN /*kikanFrom*/'200704' AND /*kikanTo*/'200704'
        AND   BT64.COMPANY_CD = /*companyCd*/'00'
/*IF taishoJoken.equals("MISE") */  
        AND   BT64.MISE_CD = /*hyojiTaisho*/'01776'
/*END*/
        AND   BT64.OLDM_FLG <> '1'
/*IF tenpoShubetu == "1" || tenpoShubetu == "3"*/
        AND   BN01.KBN1 = /*tenpoShubetu*/'1'
/*END*/
/*IF tenpoShubetu == "2" */
        AND   BN01.KBN1 IN ('1', '2')
/*END*/
/*IF "01".equals(userTypeCd) && "ALL".equals(tenpoShubetu) */
        AND   (BT64.OPEN_KBN + BT64.OPEN_KBN_ZEN) > 0
--ELSE
        AND   BT64.OPEN_KBN  = 1
/*END*/
        AND   BT64.EIGYO_DT   = BN01.EIGYO_YM
        AND   BT64.COMPANY_CD = BN01.COMPANY_CD
        AND   BT64.MISE_CD    = BN01.MISE_CD
        GROUP BY BT64.EIGYO_DT
        ,        BT64.MISE_CD
/*IF zennenDatashubetu == "HOSEI" */
        ,        BT64.URIAGE_DOGETU
        ,        BT64.ONER_YOSAN_DOGETU
        ,        BT64.EIGYO_DAYS_DOGETU
        ,        BT64.URIAGE_ZEN_DOGETU
        ,        BT64.EIGYO_DAYS_ZEN_DOGETU
        ,        BT64.KYAKUSU_DOGETU
        ,        BT64.KYAKUSU_ZEN_DOGETU
        ,        BT64.URIAGE_HOSEI 
        ,        BT64.ONER_YOSAN_HOSEI 
        ,        BT64.EIGYO_DAYS_HOSEI
        ,        BT64.URIAGE_ZEN_HOSEI 
        ,        BT64.EIGYO_DAYS_ZEN_HOSEI 
        ,        BT64.KYAKUSU_HOSEI 
        ,        BT64.KYAKUSU_ZEN_HOSEI 
--ELSE
        ,        BT64.URIAGE_/*$zennenDatashubetu*/'DOGETU'
        ,        BT64.ONER_YOSAN_/*$zennenDatashubetu*/'DOGETU'
        ,        BT64.EIGYO_DAYS_/*$zennenDatashubetu*/'DOGETU'
        ,        BT64.URIAGE_ZEN_/*$zennenDatashubetu*/'DOGETU'
        ,        BT64.EIGYO_DAYS_ZEN_/*$zennenDatashubetu*/'DOGETU'
        ,        BT64.KYAKUSU_/*$zennenDatashubetu*/'DOGETU'
        ,        BT64.KYAKUSU_ZEN_/*$zennenDatashubetu*/'DOGETU'
/*END*/
      ) SUB
      WHERE SUB.MISE_CD    = BM01.MISE_CD 
      GROUP BY SUB.EIGYO_DT
) BT64
ON (BT64.EIGYO_DT = BT4YOSAN.YOSAN_DT)
/*IF "SIBU".equals(taishoJoken) && ("090".equals(hyojiTaisho) || "091".equals(hyojiTaisho)) || "MISE".equals(taishoJoken) */
left outer join (
			SELECT
			   SUB.EIGYO_DT
			  ,SUM(COALESCE(SUB.NEBIKI,0)) AS NEBIKI
/*IF zennenDatashubetu == "HOSEI" */
			  ,SUM(COALESCE(SUB.NEBIKI_HOSEI,0)) AS NEBIKI_HOSEI
/*END*/
			FROM
			  (SELECT MISE_CD FROM BM01TENM BM01
            WHERE BM01.COMPANY_CD = /*companyCd*/'00'
            AND   BM01./*$taishoJoken*/'SIBU'_CD = /*hyojiTaisho*/'031'
/*IF "SIBU".equals(taishoJoken) && blockCd != null */
            AND   BM01.BLOCK_CD = /*blockCd*/'004'
/*END*/
            AND   EXISTS (
                 SELECT * FROM BM10GSIB BM10
                 WHERE  BM10.COMPANY_CD = /*companyCd*/'00'
                 AND /*$taishoJoken*/'SIBU'_CD = /*hyojiTaisho*/'031'
                 AND BM10.SIBU_CD = BM01.SIBU_CD)
/*IF "01".equals(userTypeCd) && limitFlg*/
            AND EXISTS (
                      SELECT * FROM BM51SVSB AS BM51
                      WHERE BM51.SV_CD = /*userId*/'00000921'
                      AND BM51.SIBU_CD = BM01.SIBU_CD)
/*END*/
            ) BM01
            ,    (
                 select
					 LEFT(BT65.EIGYO_DT, 6) AS EIGYO_DT
					,COALESCE(BT65.NEBIKI,0) AS NEBIKI
					,BT65.MISE_CD
/*IF zennenDatashubetu == "HOSEI" */
					,(CASE WHEN BT60_HOSEI.MISE_CD IS NOT NULL THEN COALESCE(BT65.NEBIKI,0) ELSE 0 END) AS NEBIKI_HOSEI
/*END*/
			     from
			         BT65SADY BT65
			         INNER JOIN  BN01DTEN BN01 ON
			              BT65.EIGYO_DT   = BN01.EIGYO_DT
                    AND   BT65.COMPANY_CD = BN01.COMPANY_CD
                    AND   BT65.MISE_CD    = BN01.MISE_CD
/*IF zennenDatashubetu == "HOSEI" */
					LEFT OUTER JOIN
					(
						SELECT
							EIGYO_DT,
							MISE_CD
						FROM
						 	BT60ZNIP
						WHERE
							URIAGE > 0  AND
							URIAGE_ZEN_DOGETU > 0 AND
							COMPANY_CD = /*companyCd*/'00' AND
			                LEFT(EIGYO_DT, 6) BETWEEN /*kikanFrom*/'200704' AND /*kikanTo*/'200704'
					) BT60_HOSEI ON (BT60_HOSEI.EIGYO_DT = BT65.EIGYO_DT) AND (BT60_HOSEI.MISE_CD = BT65.MISE_CD)
/*END*/
			    where
			            LEFT(BT65.EIGYO_DT, 6) BETWEEN /*kikanFrom*/'200704' AND /*kikanTo*/'200704'
                    AND   BT65.COMPANY_CD = /*companyCd*/'00'
/*IF tenpoShubetu == "1" || tenpoShubetu == "3"*/
                    AND   BN01.KBN1 = /*tenpoShubetu*/'1'
/*END*/
/*IF tenpoShubetu == "2" */
                    AND   BN01.KBN1 IN ('1', '2')
/*END*/
             )SUB
      where
          SUB.MISE_CD    = BM01.MISE_CD
      group by
          SUB.EIGYO_DT

) AS BT65
ON (BT65.EIGYO_DT = BT4YOSAN.YOSAN_DT) OR (BT65.EIGYO_DT = BT64.EIGYO_DT)

left outer join (
			SELECT
			  SUB.EIGYO_DT,
			  SUM(COALESCE(SUB.URIAGE_ZEN_AFTER_NEBIKI,0)) AS URIAGE_ZEN_AFTER_NEBIKI
			FROM
			  (SELECT MISE_CD FROM BM01TENM BM01
            WHERE BM01.COMPANY_CD = /*companyCd*/'00'
            AND   BM01./*$taishoJoken*/'SIBU'_CD = /*hyojiTaisho*/'031'
/*IF "SIBU".equals(taishoJoken) && blockCd != null */
            AND   BM01.BLOCK_CD = /*blockCd*/'004'
/*END*/
            AND   EXISTS (
                 SELECT * FROM BM10GSIB BM10
                 WHERE  BM10.COMPANY_CD = /*companyCd*/'00'
                 AND /*$taishoJoken*/'SIBU'_CD = /*hyojiTaisho*/'031'
                 AND BM10.SIBU_CD = BM01.SIBU_CD)
/*IF "01".equals(userTypeCd) && limitFlg*/
            AND EXISTS (
                      SELECT * FROM BM51SVSB AS BM51
                      WHERE BM51.SV_CD = /*userId*/'00000921'
                      AND BM51.SIBU_CD = BM01.SIBU_CD)
/*END*/
            ) BM01
            ,    (
                 select
                     LEFT(BT59.EIGYO_DT, 6) AS EIGYO_DT,
                     COALESCE(BT59.URI_ZEN_AFT_NBK_/*$zennenDatashubetu*/'DOGETU',0) AS URIAGE_ZEN_AFTER_NEBIKI,
			         BT59.MISE_CD
			     from
			         BT59ANZJ BT59
			        ,BN01DTEN BN01
			    where
			            LEFT(BT59.EIGYO_DT, 6) BETWEEN /*kikanFrom*/'200704' AND /*kikanTo*/'200704'
			        AND   BT59.EIGYO_DT <= (SELECT CNT_DATE FROM BR33CDAY WHERE DAY_KBN = '01')
                    AND   BT59.COMPANY_CD = /*companyCd*/'00'
/*IF tenpoShubetu == "1" || tenpoShubetu == "3"*/
                    AND   BN01.KBN1 = /*tenpoShubetu*/'1'
/*END*/
/*IF tenpoShubetu == "2" */
                    AND   BN01.KBN1 IN ('1', '2')
/*END*/
                    AND   BT59.EIGYO_DT   = BN01.EIGYO_DT
                    AND   BT59.COMPANY_CD = BN01.COMPANY_CD
                    AND   BT59.MISE_CD    = BN01.MISE_CD
             )SUB
      where
          SUB.MISE_CD    = BM01.MISE_CD
      group by
          SUB.EIGYO_DT

) AS BT59
ON (BT59.EIGYO_DT = BT4YOSAN.YOSAN_DT) OR (BT59.EIGYO_DT = BT64.EIGYO_DT)
/*END*/
order by EIGYO_DT
