select distinct
    rtrim(BM01.MISE_CD) as  HYOJITAISHO_CD,
    rtrim(BM01.MISE_NAME_KJ) as  HYOJITAISHO_NAME,
    case when CLOSE_DT >= /*appDate*/'20060501' then '1' else '0' end as MISE_OPEN_FLG
from
    BM01TENM BM01
    /*IF userTypeCd == '02'*/
    inner join BM06UONR BM06 on BM01.COMPANY_CD = BM06.COMPANY_CD and BM01.ONER_CD = BM06.ONER_CD
    /*END*/
    /*IF userTypeCd == '03'*/
    inner join BM07UTEN BM07 on BM01.COMPANY_CD = BM07.COMPANY_CD and BM01.MISE_CD = BM07.MISE_CD
    /*END*/
/*BEGIN*/
where
    /*IF limitFlg == true && userTypeCd == '01' */
    BM01.SIBU_CD IN (
           SELECT   BM51.SIBU_CD
           FROM     BM51SVSB AS BM51
           WHERE    BM51.SV_CD = /*userId*/'00000921'
           GROUP BY BM51.SIBU_CD
    )
    /*END*/
    /*IF userTypeCd == '02'*/
    BM06.COMPANY_CD = /*companyCd*/'00' and
    BM06.USER_ID = /*userId*/'99990002'
    /*END*/
    /*IF userTypeCd == '03'*/
    BM07.COMPANY_CD = /*companyCd*/'00' and
    BM07.USER_ID = /*userId*/'99990003'
    /*END*/
/*END*/
order by
    HYOJITAISHO_CD
