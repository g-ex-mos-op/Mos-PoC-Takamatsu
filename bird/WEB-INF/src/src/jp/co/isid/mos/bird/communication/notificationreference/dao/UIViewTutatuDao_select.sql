SELECT MAIN.REG_DATE,
	   MAIN.SEQ,
	   MAIN.CATE_ID,
	   MAIN.CATE_NAME,
	   MAIN.TITLE,
	   MAIN.SUB_TITLE,
	   MAIN.PUB_DATE,
	   MAIN.PUB_USER,
	   MAIN.USER_NAME_KJ,
	   MAIN.COMPANY_CD,
	   MAIN.PUB_ORG,
	   MAIN.PUB_ORG_NAME,
	   MAIN.KANRI_NO,
	   MAIN.FILE_NAME,
	   MAIN.ATTACH_NAME1,
	   MAIN.ATTACH_NAME2,
	   MAIN.ATTACH_NAME3,
	   MAIN.ATTACH_FL1,
	   MAIN.ATTACH_FL2,
	   MAIN.ATTACH_FL3,
	   MAIN.SAKUJO_FLG
,	   MIN(MAIN.KOBETSU_FLG) AS KOBETSU_FLG
,	   MIN(MAIN.MISE_FLG) AS MISE_FLG
,	   MAIN.CNT
FROM (
	SELECT distinct
	    rtrim(BT02.REG_DATE) as REG_DATE,
	    rtrim(BT02.SEQ) as SEQ,
	    rtrim(BT02.CATE_ID) as CATE_ID,
	    rtrim(BM02.CATE_NAME) as CATE_NAME,
	    rtrim(BT02.TITLE) as TITLE,
	    rtrim(BT02.SUB_TITLE) as SUB_TITLE,
	    rtrim(BT02.PUB_DATE) as PUB_DATE,
	    rtrim(BT02.PUB_USER) as PUB_USER,
	    rtrim(BR01.USER_NAME_KJ) as USER_NAME_KJ,
	    rtrim(BT02.R_COMPANY_CD) as COMPANY_CD,
	    rtrim(BT02.PUB_ORG) as PUB_ORG,
	    rtrim(BT02.PUB_ORG_NAME) as PUB_ORG_NAME,
	    rtrim(BT02.KANRI_NO) as KANRI_NO,
	    rtrim(BT02.FILE_NAME) as FILE_NAME,
	    rtrim(BT02.ATTACH_NAME1) as ATTACH_NAME1,
	    rtrim(BT02.ATTACH_NAME2) as ATTACH_NAME2,
	    rtrim(BT02.ATTACH_NAME3) as ATTACH_NAME3,
	    rtrim(BT02.ATTACH_FL1) as ATTACH_FL1,
	    rtrim(BT02.ATTACH_FL2) as ATTACH_FL2,
	    rtrim(BT02.ATTACH_FL3) as ATTACH_FL3,
	    rtrim(BT02.SAKUJO_FLG) as SAKUJO_FLG,
	    rtrim(BT14.GYOTAI_KBN) as GYOTAI_KBN,
	    rtrim(BT14.KOBETSU_FLG) as KOBETSU_FLG,
	    rtrim(BT14.MISE_FLG) as MISE_FLG
	   ,(case when  BT11.CNT is null then 0 else BT11.CNT end )  as CNT

	from
	    BT02NTCM as BT02
	    inner join BM02IFCT BM02 on BM02.INFO_SHU = /*infoShu*/'00' and BM02.CATE_ID = BT02.CATE_ID
	    inner join BT12IACP BT12 on BT12.REG_DATE = BT02.REG_DATE and BT12.SEQ = BT02.SEQ
	    inner join BT13IASZ BT13 on BT13.INFO_SHU = BT12.INFO_SHU and BT13.REG_DATE = BT02.REG_DATE and BT13.SEQ = BT02.SEQ
	    inner join BT14IAGT BT14 on BT14.INFO_SHU = BT12.INFO_SHU and BT14.REG_DATE = BT02.REG_DATE and BT14.SEQ = BT02.SEQ
	    inner join BM03USCP as BM03 on BM03.R_COMPANY_CD = BT12.R_COMPANY_CD
	    inner join BM13SHKM as BM13 on BM13.USER_ID = BM03.USER_ID and BM13.SHOZOKU_KBN = BT13.SHOZOKU_KBN
	    inner join BM05USGT as BM05 on BM05.USER_ID = BM03.USER_ID and BM05.GYOTAI_KBN = BT14.GYOTAI_KBN
	    left outer join BR01USER BR01 on BT02.PUB_USER = BR01.USER_ID
	    left outer join (select reg_date
	                          , seq
	                          , count(*) as CNT
	                     from bt11rlfl
	                     where info_shu='02'
	                     group by reg_date
	                            , seq
	                    ) as BT11 on BT02.REG_DATE=BT11.REG_DATE and BT02.SEQ=BT11.SEQ

	where
	    BT02.SAKUJO_FLG <> '1' and
	    BT02.PUB_DATE between /*pubDate*/'20060101' and /*pubDateTo*/'20060699' and
	    /*IF title != null*/BT02.TITLE like /*title*/'%' and/*END*/
	    /*IF gyotaiKbn != null*/BT14.GYOTAI_KBN = /*gyotaiKbn*/'011' and/*END*/
	    BT12.INFO_SHU = /*infoShu*/'00' and
	    BM03.USER_ID = /*userId*/'99990003'
	AND BT02.PUB_DATE <= /*sysDate*/'20070813'
	/*IF fullTextSearchFileList != null*/
	AND concat(bt02.reg_date, bt02.seq) in /*fullTextSearchFileList*/('')
	/*END*/
) MAIN
/*IF kobetsuFlg == true */
LEFT JOIN  (
SELECT BT15.INFO_SHU
,      BT15.REG_DATE
,      BT15.SEQ
,      BT15.GYOTAI_KBN
from BT15IAID BT15
,    BM04GTCP BM04
,    BM05USGT BM05
,    BM01TENM BM01
/*IF userTypeCd == '01' */
,    BM06UONR USER
/*END*/
/*IF userTypeCd == '02' */
,    BM06UONR USER
/*END*/
/*IF userTypeCd == '03' */
,    BM07UTEN USER
/*END*/
WHERE BT15.INFO_SHU    = '02'
AND   BT15.KOBETSU_SHU = '01'
AND   BM05.USER_ID     = /*userId*/'99990001'
AND   BM01.CLOSE_DT > /*sysDate*/''
AND   USER.USER_ID     = BM05.USER_ID
AND   BM04.COMPANY_CD = USER.COMPANY_CD
AND   BM01.COMPANY_CD = BM04.COMPANY_CD
AND   BM05.GYOTAI_KBN = BM04.GYOTAI_KBN
AND   BT15.GYOTAI_KBN = BM05.GYOTAI_KBN
/*IF userTypeCd == '01' */
	AND BM01.ONER_CD = USER.ONER_CD
/*END*/
/*IF userTypeCd == '02' */
	AND BM01.ONER_CD = USER.ONER_CD
/*END*/
/*IF userTypeCd == '03' */
	AND BM01.MISE_CD = USER.MISE_CD
/*END*/
AND BT15.KOBETSU_CD = BM01.AREA_DAI
group by
    BT15.INFO_SHU,
    BT15.REG_DATE,
    BT15.SEQ,
    BT15.GYOTAI_KBN
   ) KOBETSU ON (KOBETSU.REG_DATE = MAIN.REG_DATE)
/*END*/
/*IF misebetsuFlg == true */
LEFT JOIN  (
SELECT BT16.INFO_SHU,
       BT16.REG_DATE,
       BT16.SEQ,
       BT16.GYOTAI_KBN
FROM BT16IAST BT16,
     BM04GTCP BM04,
     BM05USGT BM05,
     BM01TENM BM01
/*IF userTypeCd == '01' */
,    BM06UONR USER
/*END*/
/*IF userTypeCd == '02' */
,    BM06UONR USER
/*END*/
/*IF userTypeCd == '03' */
,    BM07UTEN USER
/*END*/
WHERE BT16.INFO_SHU = '02'
AND   BM05.USER_ID = /*userId*/'99990001'
AND   BM01.CLOSE_DT > /*sysDate*/''
AND   USER.USER_ID = BM05.USER_ID
AND   BM04.COMPANY_CD = USER.COMPANY_CD
AND   BM01.COMPANY_CD = BM04.COMPANY_CD
AND   BT16.GYOTAI_KBN = BM05.GYOTAI_KBN
AND   BT16.GYOTAI_KBN = BM04.GYOTAI_KBN
AND   BT16.MISE_CD = BM01.MISE_CD
/*IF userTypeCd == '01' */
	AND BM01.ONER_CD = USER.ONER_CD
/*END*/
/*IF userTypeCd == '02' */
	AND BM01.ONER_CD = USER.ONER_CD
/*END*/
/*IF userTypeCd == '03' */
	AND BM01.MISE_CD = USER.MISE_CD
/*END*/
group by
    BT16.INFO_SHU,
    BT16.REG_DATE,
    BT16.SEQ,
    BT16.GYOTAI_KBN
   ) MISEBETSU ON (MISEBETSU.REG_DATE = MAIN.REG_DATE)
/*END*/
/*IF kobetsuFlg == true ||  misebetsuFlg == true*/
WHERE (MAIN.KOBETSU_FLG <> '1' AND MAIN.MISE_FLG <> '1')
    OR (MAIN.KOBETSU_FLG ='1'
	 AND MAIN.REG_DATE   = KOBETSU.REG_DATE
	 AND MAIN.SEQ        = KOBETSU.SEQ
	 AND MAIN.GYOTAI_KBN = KOBETSU.GYOTAI_KBN
    )
    OR (MAIN.MISE_FLG ='1'
	 AND MAIN.REG_DATE   = MISEBETSU.REG_DATE
	 AND MAIN.SEQ        = MISEBETSU.SEQ
	 AND MAIN.GYOTAI_KBN = MISEBETSU.GYOTAI_KBN
    )
/*END*/
GROUP BY MAIN.REG_DATE,
      MAIN.SEQ,
      MAIN.CATE_ID,
      MAIN.CATE_NAME,
      MAIN.TITLE,
      MAIN.SUB_TITLE,
      MAIN.PUB_DATE,
      MAIN.PUB_USER,
      MAIN.USER_NAME_KJ,
      MAIN.COMPANY_CD,
      MAIN.PUB_ORG,
      MAIN.PUB_ORG_NAME,
      MAIN.KANRI_NO,
      MAIN.FILE_NAME,
      MAIN.ATTACH_NAME1,
      MAIN.ATTACH_NAME2,
      MAIN.ATTACH_NAME3,
      MAIN.ATTACH_FL1,
      MAIN.ATTACH_FL2,
      MAIN.ATTACH_FL3,
	  MAIN.SAKUJO_FLG
,	   MAIN.CNT
order by
    PUB_DATE desc,
    KANRI_NO