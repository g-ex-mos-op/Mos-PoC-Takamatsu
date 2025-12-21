SELECT BM45.EIGYO_DT 
,      sum(URIAGE)   as URIAGE_ZEN
,      sum(OTHER_KIN_1) as URIAGE_TAKU_ZEN
,      sum(KYAKUSU) as KYAKUSU_ZEN
,      sum(OTHER_KEN_1) as KYAKUSU_TAKU_ZEN 
FROM 
BT63SNIP BT63,
BM45ZDAY BM45,
(
    SELECT BM45.COMPANY_CD
    ,      BM45.MISE_CD
    FROM BM01TENM BM01
/*IF taishoJoken.equals("SEGMENT") */
,    (SELECT GYOTAI_KBN FROM BM09GTSG  WHERE SEGMENT_TYPE = /*hyojiTaisho*/'M') BM09 
/*END*/
,    (SELECT COMPANY_CD , SIBU_CD FROM BM10GSIB 
      WHERE COMPANY_CD = /*companyCd*/'00'
/*IF taishoJoken.equals("JIGYOU")*/
      AND JIGYOU_CD = /*hyojiTaisho*/'00'
/*END*/
/*IF taishoJoken.equals("SLAREA")*/
      AND SLAREA_CD = /*hyojiTaisho*/'00'
/*END*/
/*IF taishoJoken.equals("SIBU")*/
      AND SIBU_CD = /*hyojiTaisho*/'00'
/*END*/
    ) BM10 
    ,    BN01DTEN BN01
    ,    BM45ZDAY BM45
	WHERE BM01.COMPANY_CD = /*companyCd*/'00'
	AND   BN01.EIGYO_DT  = /*tounenKizyunDt*/'20090228'
	AND   BM45.EIGYO_DT  = /*tounenKizyunDt*/'20090228'
	AND   BN01.KBN17 <> '  '
	AND   BN01.KBN17 <> '00' 
	AND   BM45.OLDM_FLG = '0'
	/*IF tenpoShubetu.equals("ALL") */
	AND   (BM45.OPEN_KBN  = 1 OR BM45.OP_KBN_ZEN_DOGETU = 1)
	-- ELSE
	AND   BM45.OPEN_KBN  = 1
	/*END*/
	
	/*IF tenpoShubetu.equals("1") */
	AND   BN01.KBN1  = '1'
	/*END*/
	/*IF tenpoShubetu.equals("2") */
	AND   (BN01.KBN1 = '1' OR BN01.KBN1 = '2')
	/*END*/
	/*IF tenpoShubetu.equals("3") */
	AND   BN01.KBN1  = '3'
	/*END*/
	/*IF taishoJoken.equals("SEGMENT") */
	AND   BM01.GYOTAI_KBN = BM09.GYOTAI_KBN
	/*END*/
	/*IF taishoJoken.equals("SIBU") && blockCd != null */
	AND   BM01.BLOCK_CD = /*blockCd*/'00'
	/*END*/
	/*IF taishoJoken.equals("MISE") */
	AND   BM01.MISE_CD = /*hyojiTaisho*/'01002'
	/*END*/
	
	/*IF limitFlg==true */
	AND   BM01.SIBU_CD IN ( 
	         SELECT   BM51.SIBU_CD
	         FROM     BM51SVSB BM51
	         WHERE  BM51.COMPANY_CD = /*companyCd*/'00'
	         AND    BM51.SV_CD = /*userId*/'99990005'
	         GROUP BY BM51.SIBU_CD
	      ) 
	/*END*/
	AND   BM01.COMPANY_CD = BM10.COMPANY_CD
	AND   BM01.SIBU_CD = BM10.SIBU_CD
	AND   BM01.COMPANY_CD = BM45.COMPANY_CD
	AND   BM01.MISE_CD    = BM45.MISE_CD
	AND   BM45.COMPANY_CD = BN01.COMPANY_CD
	AND   BM45.MISE_CD    = BN01.MISE_CD
	AND   BM45.EIGYO_DT   = BN01.EIGYO_DT
) SUB

WHERE 
    BT63.COMPANY_CD = BM45.COMPANY_CD
AND BT63.EIGYO_DT = BM45.ZEN_DOGETU_DT
AND BT63.MISE_CD = BM45.ZEN_DOGETU_MISE
AND BM45.COMPANY_CD = SUB.COMPANY_CD
AND BM45.MISE_CD = SUB.MISE_CD
AND BM45.EIGYO_DT = /*leapYearDt*/'20090299'

GROUP BY 
  BM45.EIGYO_DT