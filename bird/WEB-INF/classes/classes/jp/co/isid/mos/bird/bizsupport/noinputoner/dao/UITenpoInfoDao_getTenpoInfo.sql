select
    MISE.PL_TYPE,
    MISE.SIBU_CD,
    MISE.SIBU_NAME,
    MISE.ONER_CD,
    MISE.ONER_NAME_KJ,
    MISE.MISE_CD,
    MISE.MISE_NAME_KJ,
    MISE.CLOSE_DT,
    '' as PL_DT1,
    '' as PL_DT2,
    '' as PL_DT3,
    '' as PL_DT4,
    '' as PL_DT5,
    '' as PL_DT6,
    '' as PL_DT7,
    '' as PL_DT8,
    '' as PL_DT9,
    '' as PL_DT10,
    '' as PL_DT11,
    '' as PL_DT12,
    '' as PL_DT13,
    '' as PL_DT14,
    '' as PL_DT15,
    '' as PL_DT16,
    '' as PL_DT17,
    '' as PL_DT18,
    '' as PL_DT19,
    '' as PL_DT20,
    '' as PL_DT21,
    '' as PL_DT22,
    '' as PL_DT23,
    '' as PL_DT24,
    '' as PL_DT25,
    '' as PL_DT26,
    '' as PL_DT27 
from
(
    select
        BM11.COMPANY_CD as COMPANY_CD,
        BM11.SIBU_CD as SIBU_CD,
        rtrim(BM10.SIBU_NAME) as SIBU_NAME,
        BM11.ONER_CD as ONER_CD,
        rtrim(BM11.ONER_NAME_KJ) as ONER_NAME_KJ,
        '' as MISE_CD,
        '' as MISE_NAME_KJ,
        '' as CLOSE_DT,
        '0' as PL_TYPE
    from
        BM11ONER BM11,
        BM10GSIB BM10
    where
        BM11.ONER_CD in 
        (select
            distinct rtrim(BM01.ONER_CD) as ONER_CD
        from 
            BM01TENM BM01
        where 
            BM01.MISE_KBN like '_1_' 
        and BM01.COMPANY_CD = /*companyCd*/'00'
        /*IF sibuCd != null && sibuCd != '' */and BM01.SIBU_CD = /*sibuCd*/'051'/*END*/
        /*IF !closeMiseFlg */and BM01.CLOSE_DT > /*nengetuTo*/'200605'/*END*/
        order by
            ONER_CD
        )
    and BM11.COMPANY_CD = /*companyCd*/'00'
    /*IF !closeMiseFlg */and BM11.KEIYAKU_END > /*nengetuTo*/'200605'/*END*/
    and BM11.COMPANY_CD = BM10.COMPANY_CD
    and BM11.SIBU_CD = BM10.SIBU_CD

    union all
    
    select
        BM01.COMPANY_CD as COMPANY_CD,
        BM01.SIBU_CD as SIBU_CD,
        rtrim(BM10.SIBU_NAME) as SIBU_NAME,
        BM01.ONER_CD as ONER_CD,
        rtrim(BM11.ONER_NAME_KJ) as ONER_NAME_KJ,
        BM01.MISE_CD as MISE_CD,
        rtrim(BM01.MISE_NAME_KJ) as MISE_NAME_KJ,
        BM01.CLOSE_DT as CLOSE_DT,
        '1' as PL_TYPE
    from 
        BM01TENM BM01,
        BM10GSIB BM10,
        BM11ONER BM11
    where
        BM01.MISE_KBN like '_1_'
    and BM01.COMPANY_CD = /*companyCd*/'00'
    /*IF sibuCd != null && sibuCd != '' */and BM01.SIBU_CD = /*sibuCd*/'051'/*END*/
    /*IF !closeMiseFlg */and BM01.CLOSE_DT > /*nengetuTo*/'200605'/*END*/
    and BM01.COMPANY_CD = BM10.COMPANY_CD
    and BM01.SIBU_CD = BM10.SIBU_CD
    and BM01.ONER_CD in (
        select rtrim(ONER_CD)
          from BM11ONER
         where COMPANY_CD = /*companyCd*/'00'
    /*IF !closeMiseFlg */and KEIYAKU_END > /*nengetuTo*/'200605'/*END*/
    )
    and BM01.COMPANY_CD = BM11.COMPANY_CD
    and BM01.ONER_CD = BM11.ONER_CD
    order by
        SIBU_CD,
        ONER_CD,
        MISE_CD
) MISE
/*IF sibuCd != null && sibuCd != '' */
where 
	MISE.SIBU_CD = /*sibuCd*/'051'
/*END*/
order by
    SIBU_CD,
    ONER_CD,
    PL_TYPE,
    MISE_CD
