select distinct
    BT01.REG_DATE,
    BT01.SEQ,
    BT01.CATE_ID,
    rtrim(BM02.CATE_NAME) as CATE_NAME,
    rtrim(BT01.TITLE) as TITLE,
    BT01.PUB_DATE,
    BT01.PUB_USER,
    rtrim(BR01.USER_NAME_KJ) as USER_NAME_KJ,
    BT01.R_COMPANY_CD,
    BT01.PUB_ORG,
    rtrim(BT01.PUB_ORG_NAME) as PUB_ORG_NAME,
    BT01.MESSAGE,
    rtrim(BT01.ATTACH_NAME1) as ATTACH_NAME1,
    rtrim(BT01.ATTACH_NAME2) as ATTACH_NAME2,
    rtrim(BT01.ATTACH_NAME3) as ATTACH_NAME3,
    rtrim(BT01.ATTACH_FL1) as ATTACH_FL1,
    rtrim(BT01.ATTACH_FL2) as ATTACH_FL2,
    rtrim(BT01.ATTACH_FL3) as ATTACH_FL3,
    BT01.SAKUJO_FLG,
    BT14.GYOTAI_KBN,
    BT14.KOBETSU_FLG,
    BT14.MISE_FLG
from
    BT01INFM as BT01
    left outer join BR01USER BR01 on BT01.PUB_USER = BR01.USER_ID
    inner join BM02IFCT BM02 on BM02.INFO_SHU = '01' and BM02.CATE_ID = BT01.CATE_ID
    inner join BT12IACP BT12 on BT12.REG_DATE = BT01.REG_DATE and BT12.SEQ = BT01.SEQ
    inner join BT13IASZ BT13 on BT13.INFO_SHU = BT12.INFO_SHU and BT13.REG_DATE = BT01.REG_DATE and BT13.SEQ = BT01.SEQ
    inner join BT14IAGT BT14 on BT14.INFO_SHU = BT12.INFO_SHU and BT14.REG_DATE = BT01.REG_DATE and BT14.SEQ = BT01.SEQ
    inner join BM03USCP as BM03 on BM03.R_COMPANY_CD = BT12.R_COMPANY_CD
    inner join BM13SHKM as BM13 on BM13.USER_ID = BM03.USER_ID and BM13.SHOZOKU_KBN = BT13.SHOZOKU_KBN
    inner join BM05USGT as BM05 on BM05.USER_ID = BM03.USER_ID and BM05.GYOTAI_KBN = BT14.GYOTAI_KBN
where
    BT01.SAKUJO_FLG <> '1' and
    /*IF cateId != null*/BT01.CATE_ID = /*cateId*/'04' and/*END*/
    /*IF pubDate != null*/BT01.PUB_DATE like /*pubDate*/'200601%' and/*END*/
    /*IF title != null*/BT01.TITLE like /*title*/'%' and/*END*/
    /*IF gyotaiKbn != null*/BT14.GYOTAI_KBN = /*gyotaiKbn*/'011' and/*END*/
    BT12.INFO_SHU = '01' and
    BM03.USER_ID = /*userId*/'99990003'
AND BT01.PUB_DATE <= /*sysDate*/'20070813'
order by
    BT01.PUB_DATE desc,
    BT01.REG_DATE desc,
    BT01.SEQ desc
