select distinct BM02.SORT_KEY
,      BT02.CATE_ID
,      rtrim(BM02.CATE_NAME) as CATE_NAME
,      count(*) as CNT

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
                     where info_shu = /*infoShu*/'00'
                     group by reg_date
                            , seq
                    ) as BT11 on BT02.REG_DATE=BT11.REG_DATE and BT02.SEQ=BT11.SEQ

where
    BT02.SAKUJO_FLG <> '1' and
    /*IF cateId != null*/BT02.CATE_ID = /*cateId*/'04' and/*END*/
    BT02.PUB_DATE between /*pubDate*/'20060101' and /*pubDateTo*/'20060199' and
    /*IF title != null*/BT02.TITLE like /*title*/'%' and/*END*/
    /*IF gyotaiKbn != null*/BT14.GYOTAI_KBN = /*gyotaiKbn*/'011' and/*END*/
    BT12.INFO_SHU = /*infoShu*/'00' and
    BM03.USER_ID = /*userId*/'99990003'
/*IF fullTextSearchFileList != null*/
AND concat(bt02.reg_date, bt02.seq) in /*fullTextSearchFileList*/('')
/*END*/

group by BM02.SORT_KEY
,        BT02.CATE_ID
,        rtrim(BM02.CATE_NAME)
order by BM02.SORT_KEY
,        BT02.CATE_ID

